package dev.rohit.productservice.dtos;

import lombok.Data;

@Data
public class GenericProductDto {

        private Long id;
        private String title;
        private String description;
        private double price;
        private String category;
        private String image;

}
