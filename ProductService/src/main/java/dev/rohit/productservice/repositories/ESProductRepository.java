package dev.rohit.productservice.repositories;

import dev.rohit.productservice.models.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ESProductRepository extends ElasticsearchRepository<Product,Long> {
    List<Product> findAllByTitleContaining(String title);
}
