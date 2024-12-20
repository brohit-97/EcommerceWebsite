package dev.rohit.productservice.services;

import dev.rohit.productservice.dtos.GenericProductDto;
import dev.rohit.productservice.exceptions.NotFoundException;
import dev.rohit.productservice.models.Product;
import dev.rohit.productservice.models.SortParam;
import dev.rohit.productservice.models.SortType;
import dev.rohit.productservice.repositories.ESProductRepository;
import dev.rohit.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Primary
@Service("selfProductService")
public class SelfProductServiceImpl implements ProductService{

    private ProductRepository productRepository;
    private ESProductRepository esProductRepository;
    private CategoryService categoryService;

    public SelfProductServiceImpl(ProductRepository productRepository, ESProductRepository esProductRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.esProductRepository = esProductRepository;
        this.categoryService = categoryService;
    }


    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(GenericProductDto product) {
        Product newProduct = GenericProductDto.toProduct(product);
        categoryService.getCategoryByName(product.getCategory());
        newProduct.setCategory(categoryService.getCategoryByName(product.getCategory()));
        newProduct = productRepository.save(newProduct);
        esProductRepository.save(newProduct);
        return newProduct;
    }

    @Override
    public Product updateProduct(Long id, GenericProductDto product) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if(existingProduct.isEmpty()){
            throw new NotFoundException("Product not found");
        }
        Product updatedProduct = existingProduct.get();
        if(StringUtils.hasText(product.getTitle())){
            updatedProduct.setTitle(product.getTitle());
        }
        if(StringUtils.hasText(product.getDescription())){
            updatedProduct.setDescription(product.getDescription());
        }
        if(product.getPrice() != null){
            updatedProduct.setPrice(product.getPrice());
        }
        if(StringUtils.hasText(product.getImage())){
            updatedProduct.setImage(product.getImage());
        }
        if(StringUtils.hasText(product.getCategory())){
            updatedProduct.setCategory(categoryService.getCategoryByName(product.getCategory()));
        }
        updatedProduct = productRepository.save(updatedProduct);
        esProductRepository.save(updatedProduct);
        return updatedProduct;
    }

    @Override
    public Product deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            throw new NotFoundException("Product not found");
        }
        esProductRepository.deleteById(id);
        productRepository.deleteById(id);
        return product.get();
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return esProductRepository.findAllByTitleContaining(keyword);

    }

    @Override
    public Page<Product> getPaginatedProducts(int numOfResults, int offset, List<SortParam> sortParams) {
        Sort sort = sortParams.getFirst().getSortType().equals(SortType.ASC) ?  Sort.by(Sort.Order.asc(sortParams.getFirst().getParamName()))
                : Sort.by(Sort.Order.desc(sortParams.getFirst().getParamName()));
        for(int i = 1; i < sortParams.size(); i++){
            SortParam sortParam = sortParams.get(i);
            sort = sort.and(sortParam.getSortType().equals(SortType.ASC) ? Sort.by(Sort.Order.asc(sortParam.getParamName()))
                    : Sort.by(Sort.Order.desc(sortParam.getParamName())));
        }
        return productRepository.findAll(PageRequest.of(offset/numOfResults, numOfResults, sort));
    }


}
