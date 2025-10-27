package com.algaworks.algashop.ordering.application.customer.query;

import org.springframework.data.domain.Page;

import java.util.UUID;

public interface CustomerQueryService {
    CustomerOutput findById(UUID customerId);
    Page<CustomerSummaryOutput> filter(CustomerFilter filter);
}
