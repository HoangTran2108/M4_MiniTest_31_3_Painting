package com.example.painting.model;

import javax.persistence.*;

@Entity
@Table
public class Painting {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String codePain;
    private Integer height;
    private Integer weight;
    private String material;
    private String description;
    private Double price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Painting() {
    }

    public Painting(String codePain, Integer height, Integer weight, String material, String description, Double price) {
        this.codePain = codePain;
        this.height = height;
        this.weight = weight;
        this.material = material;
        this.description = description;
        this.price = price;
    }

    public Painting(String codePain, Integer height, Integer weight, String material, String description, Double price, Category category) {
        this.codePain = codePain;
        this.height = height;
        this.weight = weight;
        this.material = material;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodePain() {
        return codePain;
    }

    public void setCodePain(String codePain) {
        this.codePain = codePain;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Painting{" +
                "id=" + id +
                ", codePain='" + codePain + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", material='" + material + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }
}
