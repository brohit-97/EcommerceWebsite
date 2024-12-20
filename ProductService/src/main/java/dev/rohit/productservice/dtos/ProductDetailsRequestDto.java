package dev.rohit.productservice.dtos;

import dev.rohit.productservice.models.ProductDetails;

public class ProductDetailsRequestDto {

    private Long productId;
    private String key;
    private String value;


    public ProductDetailsRequestDto() {
    }

    public ProductDetailsRequestDto(Long productId, String key, String value) {
        this.productId = productId;
        this.key = key;
        this.value = value;
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

    public static ProductDetails toProductDetails(ProductDetailsRequestDto productDetailsRequestDto){
        ProductDetails productDetails = new ProductDetails();
        productDetails.setKey(productDetailsRequestDto.getKey());
        productDetails.setValue(productDetailsRequestDto.getValue());
        return productDetails;
    }
}
