package com.scaler.productservice.service.write;

import com.scaler.productservice.entity.ProductEntity;

public interface ProductWriteService {

    ProductEntity create(ProductEntity product);

    ProductEntity update(Long id, ProductEntity product);

    void delete(Long id);
}
