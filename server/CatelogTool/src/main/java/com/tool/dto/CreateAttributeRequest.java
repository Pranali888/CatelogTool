package com.tool.dto;

import com.tool.entity.DataType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateAttributeRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String code;
    @NotNull
    private DataType dataType;
    private Boolean isRequired = false;
    private String validation; // JSON string with rules if needed
}
