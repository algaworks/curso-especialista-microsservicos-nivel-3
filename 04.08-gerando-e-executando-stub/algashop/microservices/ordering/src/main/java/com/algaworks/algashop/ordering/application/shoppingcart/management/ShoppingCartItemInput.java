package com.algaworks.algashop.ordering.application.shoppingcart.management;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartItemInput {
	private Integer quantity;
	private UUID productId;
	private UUID shoppingCartId;
}