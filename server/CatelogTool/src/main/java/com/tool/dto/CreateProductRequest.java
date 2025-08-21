package com.tool.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class CreateProductRequest {
    
    @NotBlank
    private String name;
    @NotBlank
    private String slug;
    @NotNull
    private Long categoryId;
    private BigDecimal price;

    // attributes: map of attribute code -> stringified value
    private Map<String, String> attributes;
    
}
