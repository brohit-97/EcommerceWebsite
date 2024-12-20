package dev.rohit.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class ProductDetails extends BaseModel {

    @ManyToOne
    private Product product;
    private String key;
    private String value;

    public ProductDetails() {
    }

    public ProductDetails(String key, String value) {
        this.key = key;
        this.value = value;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
