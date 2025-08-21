package com.tool.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_attribute_value",
       uniqueConstraints = @UniqueConstraint(columnNames = {"product_id", "attribute_definition_id"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductAttributeValue {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="product_id", nullable=false)
    @JsonIgnore
    private Product product;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="attribute_definition_id", nullable=false)
    private AttributeDefinition attributeDefinition;

    @Column(name="value_string", length=1000)
    private String valueString;

    @Column(name="created_at")
    private OffsetDateTime createdAt = OffsetDateTime.now();

}
