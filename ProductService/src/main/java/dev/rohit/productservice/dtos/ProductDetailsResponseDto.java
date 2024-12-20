package dev.rohit.productservice.dtos;


import dev.rohit.productservice.models.ProductDetails;

public class ProductDetailsResponseDto {
    private Long id;
    private Long productId;
    private String key;
    private String value;

    public ProductDetailsResponseDto() {
    }

    public ProductDetailsResponseDto(Long id, Long productId, String key, String value) {
        this.id = id;
        this.productId = productId;
        this.key = key;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static ProductDetailsResponseDto toProductDetailsResponseDto(ProductDetails productDetails){
        return new ProductDetailsResponseDto(productDetails.getId(), productDetails.getProduct().getId(),
                productDetails.getKey(), productDetails.getValue());
    }
}
