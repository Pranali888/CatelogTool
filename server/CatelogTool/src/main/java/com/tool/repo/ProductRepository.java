package com.tool.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tool.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsBySku(String sku);

	List<Product> findByCategoryId(Long categoryId);
}