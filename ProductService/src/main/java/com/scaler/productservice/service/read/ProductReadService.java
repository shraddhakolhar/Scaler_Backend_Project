package com.scaler.productservice.service.read;

import com.scaler.productservice.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductReadService {

    Page<ProductEntity> getAll(Pageable pageable);

    ProductEntity getById(Long id);

    Page<ProductEntity> search(String keyword, Pageable pageable);
}
