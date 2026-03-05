package com.scaler.productservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
public class ProductRequestDto {

    @NotBlank
    private String title;

    private String description;

    @NotNull
    private Double price;

    @NotBlank
    private String category;

    private String imageUrl;
}
