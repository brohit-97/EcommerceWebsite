package dev.rohit.productservice.models;

import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Category extends BaseModel {

    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products  = new ArrayList<>();
}
