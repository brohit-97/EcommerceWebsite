package dev.rohit.productservice.dtos;

import dev.rohit.productservice.clients.thirdparty.fakestore.FakeStoreProductDto;
import dev.rohit.productservice.models.Product;
import lombok.Data;

@Data
public class GenericProductDto {

        private Long id;
        private String title;
        private String description;
        private Double price;
        private String category;
        private String image;

        public static GenericProductDto fromFakeStoreProductDto(FakeStoreProductDto fakeStoreProductDto) {
            GenericProductDto genericProductDto = new GenericProductDto();
            genericProductDto.setId(fakeStoreProductDto.getId());
            genericProductDto.setTitle(fakeStoreProductDto.getTitle());
            genericProductDto.setDescription(fakeStoreProductDto.getDescription());
            genericProductDto.setPrice(fakeStoreProductDto.getPrice());
            genericProductDto.setCategory(fakeStoreProductDto.getCategory());
            genericProductDto.setImage(fakeStoreProductDto.getImage());
            return genericProductDto;
        }

        public static GenericProductDto fromProduct(Product product){
            return new GenericProductDto();
        }

        public static Product toProduct(GenericProductDto productDto){
           Product product = new Product();
              product.setTitle(productDto.getTitle());
                product.setDescription(productDto.getDescription());
                product.setPrice(productDto.getPrice());
                product.setImage(productDto.getImage());
                return product;
        }

}
