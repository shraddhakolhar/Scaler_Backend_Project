package com.scaler.productservice.mapper;

import com.scaler.productservice.dto.*;
import com.scaler.productservice.entity.*;

public class ProductMapper {

    public static ProductResponseDto toDto(ProductEntity entity) {
        return ProductResponseDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .category(entity.getCategory())
                .imageUrl(entity.getImageUrl())
                .build();
    }

    public static ProductEntity toEntity(ProductRequestDto dto) {
        return ProductEntity.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .category(dto.getCategory())
                .imageUrl(dto.getImageUrl())
                .build();
    }
}
