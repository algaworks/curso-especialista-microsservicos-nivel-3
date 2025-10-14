package com.algaworks.algashop.ordering.domain.model.product;

import com.algaworks.algashop.ordering.domain.model.DomainException;
import com.algaworks.algashop.ordering.domain.model.ErrorMessages;

public class ProductNotFoundException extends DomainException {
    public ProductNotFoundException() {

    }

    public ProductNotFoundException(ProductId productId) {
        super(String.format(ErrorMessages.ERROR_PRODUCT_NOT_FOUND, productId));
    }

}
