package com.tool.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tool.dto.CreateAttributeRequest;
import com.tool.dto.CreateCategoryRequest;
import com.tool.entity.Category;
import com.tool.service.CategoryService;

import jakarta.validation.Valid;

//@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService service;
    public CategoryController(CategoryService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateCategoryRequest req) {
        return ResponseEntity.ok(service.createCategory(req));
    }

    @PostMapping("/{categoryId}/attributes")
    public ResponseEntity<?> addAttribute(@PathVariable Long categoryId, @Valid @RequestBody CreateAttributeRequest req) {
        return ResponseEntity.ok(service.addAttribute(categoryId, req));
    }
    
//    @GetMapping
//    public ResponseEntity<List<Category>> getAllCategories() {
//        List<Category> categories = service.getAllCategories();
//        return ResponseEntity.ok(categories);
//    }
    
    @GetMapping
    public List<Category> getAllCategories() {
        return service.getAllCategories();}
    
}