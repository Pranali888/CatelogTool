package com.tool.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tool.entity.ProductAttributeValue;

public interface ProductAttributeValueRepository extends JpaRepository<ProductAttributeValue, Long> {

}
