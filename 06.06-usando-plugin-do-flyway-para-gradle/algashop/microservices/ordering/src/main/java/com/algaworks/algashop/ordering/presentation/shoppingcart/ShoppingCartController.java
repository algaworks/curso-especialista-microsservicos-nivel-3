package com.algaworks.algashop.ordering.presentation.shoppingcart;

import com.algaworks.algashop.ordering.application.shoppingcart.management.ShoppingCartItemInput;
import com.algaworks.algashop.ordering.application.shoppingcart.management.ShoppingCartManagementApplicationService;
import com.algaworks.algashop.ordering.application.shoppingcart.query.ShoppingCartOutput;
import com.algaworks.algashop.ordering.application.shoppingcart.query.ShoppingCartQueryService;
import com.algaworks.algashop.ordering.domain.model.DomainEntityNotFoundException;
import com.algaworks.algashop.ordering.domain.model.customer.CustomerNotFoundException;
import com.algaworks.algashop.ordering.domain.model.product.ProductNotFoundException;
import com.algaworks.algashop.ordering.presentation.UnprocessableEntityException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/shopping-carts")
@RequiredArgsConstructor
public class ShoppingCartController {

	private final ShoppingCartManagementApplicationService managementService;
	private final ShoppingCartQueryService queryService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ShoppingCartOutput create(@RequestBody @Valid ShoppingCartInput input) {
		UUID shoppingCartId;
        try {
			shoppingCartId = managementService.createNew(input.getCustomerId());
		} catch (CustomerNotFoundException e) {
			throw new UnprocessableEntityException(e.getMessage(), e);
		}
        return queryService.findById(shoppingCartId);
	}

	@GetMapping("/{shoppingCartId}")
	public ShoppingCartOutput getById(@PathVariable UUID shoppingCartId) {
		return queryService.findById(shoppingCartId);
	}

	@GetMapping("/{shoppingCartId}/items")
	public ShoppingCartItemListModel getItems(@PathVariable UUID shoppingCartId) {
		var items = queryService.findById(shoppingCartId).getItems();
		return new ShoppingCartItemListModel(items);
	}

	@DeleteMapping("/{shoppingCartId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable UUID shoppingCartId) {
		managementService.delete(shoppingCartId);
	}

	@DeleteMapping("/{shoppingCartId}/items")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void empty(@PathVariable UUID shoppingCartId) {
		managementService.empty(shoppingCartId);
	}

	@PostMapping("/{shoppingCartId}/items")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void addItem(@PathVariable UUID shoppingCartId,
		   			    @RequestBody @Valid ShoppingCartItemInput input) {
		input.setShoppingCartId(shoppingCartId);
		try {
			managementService.addItem(input);
		} catch (ProductNotFoundException e) {
			throw new UnprocessableEntityException(e.getMessage(), e);
		}
	}

	@DeleteMapping("/{shoppingCartId}/items/{itemId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeItem(@PathVariable UUID shoppingCartId,
						   @PathVariable UUID itemId) {
		managementService.removeItem(shoppingCartId, itemId);
	}
}