package com.algaworks.algashop.ordering.domain.model.customer;

import com.algaworks.algashop.ordering.domain.model.DomainEntityNotFoundException;
import com.algaworks.algashop.ordering.domain.model.ErrorMessages;

public class CustomerNotFoundException extends DomainEntityNotFoundException {
    public CustomerNotFoundException() {
    }

    public CustomerNotFoundException(CustomerId customerId) {
        super(String.format(ErrorMessages.ERROR_CUSTOMER_NOT_FOUND, customerId));
    }
}
