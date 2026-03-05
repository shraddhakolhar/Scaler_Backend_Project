package com.scaler.productservice.controller;

import com.scaler.productservice.dto.*;
import com.scaler.productservice.entity.ProductEntity;
import com.scaler.productservice.mapper.ProductMapper;
import com.scaler.productservice.service.read.ProductReadService;
import com.scaler.productservice.service.write.ProductWriteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductReadService readService;
    private final ProductWriteService writeService;

    public ProductController(ProductReadService readService,
                             ProductWriteService writeService) {
        this.readService = readService;
        this.writeService = writeService;
    }

    /* -------- READ -------- */

    @GetMapping
    public PageResponseDto<ProductResponseDto> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        Pageable pageable = PageRequest.of(
                page, size,
                direction.equalsIgnoreCase("asc")
                        ? Sort.by(sortBy).ascending()
                        : Sort.by(sortBy).descending()
        );

        Page<ProductEntity> result = readService.getAll(pageable);

        return new PageResponseDto<>(
                result.getContent()
                        .stream()
                        .map(ProductMapper::toDto)
                        .collect(Collectors.toList()),
                page,
                size,
                result.getTotalElements()
        );
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return ProductMapper.toDto(readService.getById(id));
    }

    @GetMapping("/search")
    public PageResponseDto<ProductResponseDto> search(
            @RequestParam String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductEntity> result = readService.search(q, pageable);

        return new PageResponseDto<>(
                result.getContent()
                        .stream()
                        .map(ProductMapper::toDto)
                        .collect(Collectors.toList()),
                page,
                size,
                result.getTotalElements()
        );
    }

    /* -------- WRITE (SELF ONLY) -------- */

    @PostMapping
    public ProductResponseDto create(
            @Valid @RequestBody ProductRequestDto dto) {
        return ProductMapper.toDto(
                writeService.create(ProductMapper.toEntity(dto))
        );
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDto dto) {
        return ProductMapper.toDto(
                writeService.update(id, ProductMapper.toEntity(dto))
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        writeService.delete(id);
    }
}
