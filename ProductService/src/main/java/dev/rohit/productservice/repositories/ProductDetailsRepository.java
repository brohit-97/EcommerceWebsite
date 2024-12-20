package dev.rohit.productservice.repositories;

import dev.rohit.productservice.models.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails,Long> {
    List<ProductDetails> findByProductId(Long productId);
    List<ProductDetails> findByKeyAndValue(String key, String value);
}
