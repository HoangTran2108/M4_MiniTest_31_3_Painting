package com.example.painting.model;
import org.springframework.web.multipart.MultipartFile;

public class PaintingForm {
    private Long id;
    private String codePain;
    private String height;
    private String weight;
    private String material;
    private String description;
    private Double price;
    private MultipartFile image;
    private Category category;

    public PaintingForm() {
    }

    public PaintingForm(Long id, String codePain, String height, String weight, String material, String description,
                        Double price, MultipartFile image, Category category) {
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

    public PaintingForm(String codePain, String height, String weight, String material, String description,
                        Double price, MultipartFile image, Category category) {
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
