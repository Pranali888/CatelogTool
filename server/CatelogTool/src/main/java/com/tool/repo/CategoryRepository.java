package com.tool.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tool.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsBySlug(String slug);
}
