package com.example.painting.model;
import javax.persistence.*;


@Entity
@Table
public class Painting {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String codePain;
    private String height;
    private String weight;
    private String material;
    private String description;
    private Double price;
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Painting() {
    }

    public Painting(String codePain, String height, String weight, String material, String description, Double price, String image) {
        this.codePain = codePain;
        this.height = height;
        this.weight = weight;
        this.material = material;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public Painting(String codePain, String height, String weight, String material, String description, Double price, String image, Category category) {
        this.codePain = codePain;
        this.height = height;
        this.weight = weight;
        this.material = material;
        this.description = description;
        this.price = price;
        this.image = image;
        this.category = category;
    }

    public Painting(Long id, String codePain, String height, String weight, String material, String description, Double price, String image, Category category) {
        this.id = id;
        this.codePain = codePain;
        this.height = height;
        this.weight = weight;
        this.material = material;
        this.description = description;
        this.price = price;
        this.image = image;
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

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
