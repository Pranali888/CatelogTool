package com.tool.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "attribute_option")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttributeOption {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="attribute_definition_id", nullable=false)
    private AttributeDefinition attributeDefinition;

    private String value;
    private String label;
    private Integer sortOrder = 0;
}
