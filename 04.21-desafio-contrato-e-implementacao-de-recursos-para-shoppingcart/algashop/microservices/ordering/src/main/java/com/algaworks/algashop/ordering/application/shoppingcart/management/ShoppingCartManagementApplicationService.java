package com.algaworks.algashop.ordering.application.shoppingcart.management;

import com.algaworks.algashop.ordering.domain.model.commons.Quantity;
import com.algaworks.algashop.ordering.domain.model.customer.CustomerId;
import com.algaworks.algashop.ordering.domain.model.product.Product;
import com.algaworks.algashop.ordering.domain.model.product.ProductCatalogService;
import com.algaworks.algashop.ordering.domain.model.product.ProductId;
import com.algaworks.algashop.ordering.domain.model.product.ProductNotFoundException;
import com.algaworks.algashop.ordering.domain.model.shoppingcart.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShoppingCartManagementApplicationService {

	private final ShoppingCarts shoppingCarts;
	private final ProductCatalogService productCatalogService;
	private final ShoppingService shoppingService;

	@Transactional
	public void addItem(ShoppingCartItemInput input) {
		Objects.requireNonNull(input);
		ShoppingCartId shoppingCartId = new ShoppingCartId(input.getShoppingCartId());
		ProductId productId = new ProductId(input.getProductId());

		ShoppingCart shoppingCart = shoppingCarts.ofId(shoppingCartId)
				.orElseThrow(()-> new ShoppingCartNotFoundException());

		Product product = productCatalogService.ofId(productId)
				.orElseThrow(()-> new ProductNotFoundException());

		shoppingCart.addItem(product, new Quantity(input.getQuantity()));

		shoppingCarts.add(shoppingCart);
	}

	@Transactional
	public UUID createNew(UUID rawCustomerId) {
		Objects.requireNonNull(rawCustomerId);
		ShoppingCart shoppingCart = shoppingService.startShopping(new CustomerId(rawCustomerId));
		shoppingCarts.add(shoppingCart);
		return shoppingCart.id().value();
	}

	@Transactional
	public void removeItem(UUID rawShoppingCartId, UUID rawShoppingCartItemId) {
		Objects.requireNonNull(rawShoppingCartId);
		Objects.requireNonNull(rawShoppingCartItemId);
		ShoppingCartId shoppingCartId = new ShoppingCartId(rawShoppingCartId);
		ShoppingCart shoppingCart = shoppingCarts.ofId(shoppingCartId)
				.orElseThrow(()-> new ShoppingCartNotFoundException());
		shoppingCart.removeItem(new ShoppingCartItemId(rawShoppingCartItemId));
		shoppingCarts.add(shoppingCart);
	}

	@Transactional
	public void empty(UUID rawShoppingCartId) {
		Objects.requireNonNull(rawShoppingCartId);
		ShoppingCartId shoppingCartId = new ShoppingCartId(rawShoppingCartId);
		ShoppingCart shoppingCart = shoppingCarts.ofId(shoppingCartId)
				.orElseThrow(()-> new ShoppingCartNotFoundException());
		shoppingCart.empty();
		shoppingCarts.add(shoppingCart);
	}

	@Transactional
	public void delete(UUID rawShoppingCartId) {
		Objects.requireNonNull(rawShoppingCartId);
		ShoppingCartId shoppingCartId = new ShoppingCartId(rawShoppingCartId);
		ShoppingCart shoppingCart = shoppingCarts.ofId(shoppingCartId)
				.orElseThrow(()-> new ShoppingCartNotFoundException());
		shoppingCarts.remove(shoppingCart);
	}

}