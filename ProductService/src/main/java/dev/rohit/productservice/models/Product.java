package dev.rohit.productservice.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Product extends BaseModel{

    private String title;
    private String description;
    private double price;
    private Category category;
    private String image;
    

}
