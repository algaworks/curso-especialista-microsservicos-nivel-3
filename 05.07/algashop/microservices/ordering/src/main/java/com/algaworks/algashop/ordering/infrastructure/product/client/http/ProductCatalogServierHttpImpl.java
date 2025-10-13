package com.algaworks.algashop.ordering.infrastructure.product.client.http;

import com.algaworks.algashop.ordering.domain.model.commons.Money;
import com.algaworks.algashop.ordering.domain.model.product.Product;
import com.algaworks.algashop.ordering.domain.model.product.ProductCatalogService;
import com.algaworks.algashop.ordering.domain.model.product.ProductId;
import com.algaworks.algashop.ordering.domain.model.product.ProductName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductCatalogServierHttpImpl implements ProductCatalogService {

    private final ProductCatalogAPIClient productCatalogAPIClient;

    @Override
    public Optional<Product> ofId(ProductId productId) {
        ProductResponse productResponse = productCatalogAPIClient.getById(productId.value());
        return Optional.of(
                Product.builder()
                        .id(new ProductId(productResponse.getId()))
                        .name(new ProductName(productResponse.getName()))
                        .inStock(productResponse.getInStock())
                        .price(new Money(productResponse.getSalePrice()))
                        .build()
        );
    }
}
