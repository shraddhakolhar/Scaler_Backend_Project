package com.scaler.productservice.service.write;

import com.scaler.productservice.entity.ProductEntity;
import com.scaler.productservice.repository.ProductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class SelfProductWriteService implements ProductWriteService {

    private final ProductRepository productRepository;

    public SelfProductWriteService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Create product → clear list & search caches
     */
    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = "product_get_all", allEntries = true),
            @CacheEvict(cacheNames = "product_search", allEntries = true)
    })
    public ProductEntity create(ProductEntity product) {
        return productRepository.save(product);
    }

    /**
     * Update product → clear by-id + list + search caches
     */
    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = "product_by_id", key = "#id"),
            @CacheEvict(cacheNames = "product_get_all", allEntries = true),
            @CacheEvict(cacheNames = "product_search", allEntries = true)
    })
    public ProductEntity update(Long id, ProductEntity product) {

        ProductEntity existing = productRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("Product not found with id " + id));

        existing.setTitle(product.getTitle());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setCategory(product.getCategory());
        existing.setImageUrl(product.getImageUrl());

        return productRepository.save(existing);
    }

    /**
     * Delete product → clear everything related
     */
    @Override
    @Caching(evict = {
            @CacheEvict(cacheNames = "product_by_id", key = "#id"),
            @CacheEvict(cacheNames = "product_get_all", allEntries = true),
            @CacheEvict(cacheNames = "product_search", allEntries = true)
    })
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new NoSuchElementException("Product not found with id " + id);
        }
        productRepository.deleteById(id);
    }
}
