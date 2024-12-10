package dev.rohit.productservice.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Product extends BaseModel{

    private String title;
    private String description;
    private double price;
    private Category category;
    private String image;
    

}
