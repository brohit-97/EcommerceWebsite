package dev.rohit.productservice.clients.thirdparty.fakestore;

import dev.rohit.productservice.dtos.GenericProductDto;
import dev.rohit.productservice.models.Product;
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

    public static FakeStoreProductDto fromGenericProductDto(GenericProductDto genericProductDto) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(genericProductDto.getId());
        fakeStoreProductDto.setTitle(genericProductDto.getTitle());
        fakeStoreProductDto.setDescription(genericProductDto.getDescription());
        fakeStoreProductDto.setCategory(genericProductDto.getCategory());
        fakeStoreProductDto.setImage(genericProductDto.getImage());
        fakeStoreProductDto.setPrice(genericProductDto.getPrice());
        return fakeStoreProductDto;
    }

    public static Product toProduct(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImage(fakeStoreProductDto.getImage());
        return product;
    }

}
