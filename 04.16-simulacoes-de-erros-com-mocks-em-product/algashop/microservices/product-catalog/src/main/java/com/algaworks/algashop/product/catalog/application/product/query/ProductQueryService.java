package com.algaworks.algashop.product.catalog.application.product.query;

import java.util.UUID;

public interface ProductQueryService {
    ProductDetailOutput findById(UUID productId);
    PageModel<ProductDetailOutput> filter(Integer size, Integer number);
}
