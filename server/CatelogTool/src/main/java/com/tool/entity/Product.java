package com.tool.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String sku;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="category_id", nullable=false)
    @JsonIgnore
    private Category category;

   @Column(nullable=false)
    private String name;

//    @Column(nullable=false, unique=true)
//    private String slug;


    private BigDecimal price = BigDecimal.ZERO;
    private String currency = "INR";
    private Boolean isActive = true;

    @Column(name="created_at")
    private OffsetDateTime createdAt = OffsetDateTime.now();

//    @Column(name="updated_at")
//    private OffsetDateTime updatedAt = OffsetDateTime.now();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductAttributeValue> attributes = new ArrayList<>();
}
