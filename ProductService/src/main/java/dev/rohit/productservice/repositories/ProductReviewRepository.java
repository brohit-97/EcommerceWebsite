package dev.rohit.productservice.repositories;

import dev.rohit.productservice.models.Product;
import dev.rohit.productservice.models.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
    List<ProductReview> findByProduct(Product product);
}
