package com.scaler.productservice.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PageResponseDto<T> {

    private List<T> content;
    private int page;
    private int size;
    private long totalElements;
}
