package com.algaworks.algashop.ordering.domain.model.order;

import com.algaworks.algashop.ordering.domain.model.customer.CustomerId;

import java.time.OffsetDateTime;

public record OrderPlacedEvent(OrderId orderId, CustomerId customerId, OffsetDateTime placedAt) {
}