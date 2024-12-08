package dev.rohit.productservice.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Product {

    private String name;
    private String description;
    private double price;
    private Category category;
    

}
