package com.tool.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tool.dto.CreateAttributeRequest;
import com.tool.dto.CreateCategoryRequest;
import com.tool.entity.AttributeDefinition;
import com.tool.entity.Category;
import com.tool.repo.AttributeDefinitionRepository;
import com.tool.repo.CategoryRepository;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepo;
    private final AttributeDefinitionRepository attrDefRepo;

    public CategoryService(CategoryRepository categoryRepo, AttributeDefinitionRepository attrDefRepo) {
        this.categoryRepo = categoryRepo;
        this.attrDefRepo = attrDefRepo;
    }

    @Transactional
    public Category createCategory(CreateCategoryRequest req) {
        if (categoryRepo.existsBySlug(req.getSlug())) {
            throw new IllegalArgumentException("slug already exists");
        }
        Category c = new Category();
        c.setName(req.getName());
        c.setSlug(req.getSlug());
        c.setDescription(req.getDescription());
        return categoryRepo.save(c);
    }

    @Transactional
    public AttributeDefinition addAttribute(Long categoryId, CreateAttributeRequest req) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new IllegalArgumentException("category not found"));
        AttributeDefinition ad = new AttributeDefinition();
        ad.setCategory(category);
        ad.setName(req.getName());
        ad.setCode(req.getCode());
        ad.setDataType(req.getDataType());
        ad.setIsRequired(req.getIsRequired());
        //ad.setValidation(req.getValidation());
        return attrDefRepo.save(ad);
    }


    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }
}
