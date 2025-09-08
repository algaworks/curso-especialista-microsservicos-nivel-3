package com.algaworks.algashop.product.catalog.presentation;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDetailOutput create(@RequestBody @Valid ProductInput input) {
        return ProductDetailOutput.builder()
                .id(UUID.randomUUID())
                .addedAt(OffsetDateTime.now())
                .inStock(false)
                .name(input.getName())
                .brand(input.getBrand())
                .name(input.getName())
                .brand(input.getBrand())
                .description(input.getDescription())
                .regularPrice(input.getRegularPrice())
                .salePrice(input.getSalePrice())
                .enabled(input.getEnabled())
                .category(
                        CategoryMinimalOutput.builder()
                                .id(input.getCategoryId())
                                .name("Notebook")
                                .build()
                )
                .build();
    }

    @GetMapping("/{productId}")
    public ProductDetailOutput findById(@PathVariable UUID productId) {
        return ProductDetailOutput.builder()
                .id(productId)
                .addedAt(OffsetDateTime.now())
                .name("Notebook X11")
                .brand("Deep Diver")
                .description("A Gamer Notebook")
                .regularPrice(new BigDecimal("1500.00"))
                .salePrice(new BigDecimal("1000.00"))
                .inStock(true)
                .enabled(true)
                .category(CategoryMinimalOutput.builder()
                        .id(UUID.randomUUID())
                        .name("Notebook")
                        .build())
                .build();
    }

    @GetMapping
    public PageModel<ProductDetailOutput> filter(
            @RequestParam(name = "size", required = false) Integer size,
            @RequestParam(name = "number", required = false) Integer number
    ) {
        return PageModel.<ProductDetailOutput>builder()
                .number(0)
                .size(size)
                .totalPages(1)
                .totalElements(2)
                .content(
                        List.of(
                                ProductDetailOutput.builder()
                                        .id(UUID.randomUUID())
                                        .addedAt(OffsetDateTime.now())
                                        .name("Notebook X11")
                                        .brand("Deep Diver")
                                        .description("A Gamer Notebook")
                                        .regularPrice(new BigDecimal("1500.00"))
                                        .salePrice(new BigDecimal("1000.00"))
                                        .inStock(true)
                                        .enabled(true)
                                        .category(CategoryMinimalOutput.builder()
                                                .id(UUID.randomUUID())
                                                .name("Notebook")
                                                .build())
                                        .build(),
                                ProductDetailOutput.builder()
                                        .id(UUID.randomUUID())
                                        .addedAt(OffsetDateTime.now())
                                        .name("Desktop I9000")
                                        .brand("Deep Diver")
                                        .description("A Gamer Desktop")
                                        .regularPrice(new BigDecimal("3500.00"))
                                        .salePrice(new BigDecimal("3000.00"))
                                        .inStock(false)
                                        .enabled(true)
                                        .category(CategoryMinimalOutput.builder()
                                                .id(UUID.randomUUID())
                                                .name("Desktop")
                                                .build())
                                        .build()
                        )
                )
                .build();
    }

}
