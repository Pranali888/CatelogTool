package com.tool.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

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

    @Column(name="value_integer")
    private Long valueInteger;

    @Column(name="value_decimal", precision=18, scale=6)
    private BigDecimal valueDecimal;

    @Column(name="value_boolean")
    private Boolean valueBoolean;

    @Column(name="value_date")
    private LocalDate valueDate;

    @Column(name="value_json", columnDefinition = "jsonb")
    private String valueJson;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="value_option_id")
    private AttributeOption valueOption;

    @Column(name="created_at")
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Column(name="updated_at")
    private OffsetDateTime updatedAt = OffsetDateTime.now();
}
