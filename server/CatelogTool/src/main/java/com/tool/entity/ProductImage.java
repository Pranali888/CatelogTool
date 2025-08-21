package com.tool.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product_images")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;   // Image storage URL (S3, local, etc.)
    private String altText; // SEO/Accessibility text

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    // --- Constructors ---
    public ProductImage() {}

    public ProductImage(String url, String altText, Product product) {
        this.url = url;
        this.altText = altText;
        this.product = product;
    }

    // --- Getters & Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getAltText() { return altText; }
    public void setAltText(String altText) { this.altText = altText; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
}