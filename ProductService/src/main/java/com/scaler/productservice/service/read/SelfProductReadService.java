package com.scaler.productservice.service.read;

import com.scaler.productservice.entity.ProductEntity;
import com.scaler.productservice.repository.ProductRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@ConditionalOnProperty(
        name = "product.read.provider",
        havingValue = "self"
)
public class SelfProductReadService implements ProductReadService {

    private final ProductRepository productRepository;

    public SelfProductReadService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Cache paginated product list
     */
    @Override
    @Cacheable(
            cacheNames = "product_get_all",
            key = "'page=' + #pageable.pageNumber + ':size=' + #pageable.pageSize",
            unless = "#result == null"
    )
    public Page<ProductEntity> getAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    /**
     * Cache product by ID
     */
    @Override
    @Cacheable(
            cacheNames = "product_by_id",
            key = "#id",
            unless = "#result == null"
    )
    public ProductEntity getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("Product not found with id " + id));
    }

    /**
     * Cache search results (keyword + pagination aware)
     */
    @Override
    @Cacheable(
            cacheNames = "product_search",
            key = "'kw=' + #keyword + ':page=' + #pageable.pageNumber + ':size=' + #pageable.pageSize",
            unless = "#result == null"
    )
    public Page<ProductEntity> search(String keyword, Pageable pageable) {
        return productRepository
                .findByTitleContainingIgnoreCase(keyword, pageable);
    }
}
