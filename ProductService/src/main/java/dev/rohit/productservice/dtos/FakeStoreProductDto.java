package dev.rohit.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FakeStoreProductDto {

    private Long id;
    private String title;
    private String description;
    private String category;
    private String image;
    private Double price;

    public GenericProductDto toGenericProductDto(){
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(this.id);
        genericProductDto.setTitle(this.title);
        genericProductDto.setDescription(this.description);
        genericProductDto.setPrice(this.price);
        genericProductDto.setCategory(this.category);
        genericProductDto.setImage(this.image);
        return genericProductDto;
    }

}
