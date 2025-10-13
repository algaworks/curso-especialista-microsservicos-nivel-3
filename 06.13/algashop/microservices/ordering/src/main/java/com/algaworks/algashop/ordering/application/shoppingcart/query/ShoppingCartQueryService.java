package com.algaworks.algashop.ordering.application.shoppingcart.query;

import java.util.UUID;

public interface ShoppingCartQueryService {
    ShoppingCartOutput findById(UUID shoppingCartId);
    ShoppingCartOutput findByCustomerId(UUID customerId);
}
