package com.scaler.productservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
public class ProductResponseDto {

    private Long id;
    private String title;
    private String description;
    private Double price;
    private String category;
    private String imageUrl;
}
