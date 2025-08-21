package com.tool.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
//import com.fasterxml.jackson.databind.JsonNode;

@Entity
@Table(name = "attribute_definition",
       uniqueConstraints = @UniqueConstraint(columnNames = {"category_id","code"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttributeDefinition {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="category_id", nullable=false)
    private Category category;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String code; // machine key, e.g., ram_gb

    @Column(name="data_type", nullable=false)
    @Enumerated(EnumType.STRING)
    private DataType dataType;

  //@Column(name="validation", columnDefinition = "jsonb")
  //private String validation; // store as raw JSON string

    @Column(name="created_at")
    private OffsetDateTime createdAt = OffsetDateTime.now();

}