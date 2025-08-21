package com.tool.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tool.entity.AttributeDefinition;

public interface AttributeDefinitionRepository extends JpaRepository<AttributeDefinition, Long> {
    Optional<AttributeDefinition> findByCategoryIdAndCode(Long categoryId, String code);
    List<AttributeDefinition> findByCategoryId(Long categoryId);
}