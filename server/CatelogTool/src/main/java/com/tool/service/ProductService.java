package com.tool.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tool.dto.CreateProductRequest;
import com.tool.entity.Product;
import com.tool.entity.ProductAttributeValue;
import com.tool.repo.AttributeDefinitionRepository;
import com.tool.repo.CategoryRepository;
import com.tool.repo.ProductAttributeValueRepository;
import com.tool.repo.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;
    private final AttributeDefinitionRepository attrDefRepo;
    private final ProductAttributeValueRepository pavRepo;

    public ProductService(ProductRepository productRepo,
                          CategoryRepository categoryRepo,
                          AttributeDefinitionRepository attrDefRepo,
                          ProductAttributeValueRepository pavRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.attrDefRepo = attrDefRepo;
        this.pavRepo = pavRepo;
    }
    

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepo.findByCategoryId(categoryId);
    }

    @Transactional
    public Product createProduct(CreateProductRequest req) {
        if (productRepo.existsBySku(req.getSku())) throw new IllegalArgumentException("SKU exists");

        var category = categoryRepo.findById(req.getCategoryId()).orElseThrow(() -> new IllegalArgumentException("Category not found"));

        Product product = new Product();
        product.setSku(req.getSku());
        product.setName(req.getName());
        product.setSlug(req.getSlug());
        product.setBrand(req.getBrand());
        product.setCategory(category);
        product.setPrice(req.getPrice() == null ? BigDecimal.ZERO : req.getPrice());
        product.setStockQuantity(req.getStockQuantity() == null ? 0 : req.getStockQuantity());

        product = productRepo.save(product);

        if (req.getAttributes() != null) {
            for (var entry : req.getAttributes().entrySet()) {
                String code = entry.getKey();
                String rawValue = entry.getValue();

                var opt = attrDefRepo.findByCategoryIdAndCode(category.getId(), code)
                        .orElseThrow(() -> new IllegalArgumentException("Attribute " + code + " not defined for category"));

                ProductAttributeValue pav = new ProductAttributeValue();
                pav.setProduct(product);
                pav.setAttributeDefinition(opt);

                switch (opt.getDataType()) {
                    case STRING -> pav.setValueString(rawValue);
                    case INTEGER -> pav.setValueInteger(Long.valueOf(rawValue));
                    case DECIMAL -> pav.setValueDecimal(new BigDecimal(rawValue));
                    case BOOLEAN -> pav.setValueBoolean(Boolean.valueOf(rawValue));
                    case DATE -> pav.setValueDate(LocalDate.parse(rawValue)); // expect YYYY-MM-DD
                    case JSON -> pav.setValueJson(rawValue);
                    case ENUM -> {
                        // for enums ideally validate against AttributeOption; here we save as string
                        pav.setValueString(rawValue);
                    }
                }
                pavRepo.save(pav);
                product.getAttributes().add(pav);
            }
        }
        return product;
    }
}
