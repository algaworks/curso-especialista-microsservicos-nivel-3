package com.algaworks.algashop.ordering.domain.model.shoppingcart;

import com.algaworks.algashop.ordering.domain.model.customer.CustomerId;
import com.algaworks.algashop.ordering.domain.model.product.ProductId;

import java.time.OffsetDateTime;

public record ShoppingCartItemAddedEvent(
    ShoppingCartId shoppingCartId,
    CustomerId customerId,
    ProductId productId,
    OffsetDateTime addedAt
) {}
