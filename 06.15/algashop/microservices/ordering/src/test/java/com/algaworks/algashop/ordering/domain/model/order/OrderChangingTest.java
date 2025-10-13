package com.algaworks.algashop.ordering.domain.model.order;

import com.algaworks.algashop.ordering.domain.model.product.ProductTestDataBuilder;
import com.algaworks.algashop.ordering.domain.model.product.Product;
import com.algaworks.algashop.ordering.domain.model.commons.Quantity;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class OrderChangingTest {

    @Test
    void givenDraftOrder_whenChangeIsPerformed_shouldNotThrowException() {
        Order draftOrder = OrderTestDataBuilder.anOrder().build();

        Product product = ProductTestDataBuilder.aProductAltMousePad().build();
        Quantity quantity = new Quantity(2);
        Billing billing = OrderTestDataBuilder.aBilling();
        Shipping shipping = OrderTestDataBuilder.aShipping();
        PaymentMethod method = PaymentMethod.CREDIT_CARD;

        OrderItem orderItem = draftOrder.items().iterator().next();

        assertThatCode(() -> draftOrder.addItem(product, quantity)).doesNotThrowAnyException();
        assertThatCode(() -> draftOrder.changeBilling(billing)).doesNotThrowAnyException();
        assertThatCode(() -> draftOrder.changeShipping(shipping)).doesNotThrowAnyException();
        assertThatCode(() -> draftOrder.changeItemQuantity(orderItem.id(), quantity)).doesNotThrowAnyException();
        assertThatCode(() -> draftOrder.changePaymentMethod(method)).doesNotThrowAnyException();
    }

    @Test
    void givenPlacedOrder_whenChangeBillingIsCalled_shouldThrowOrderCannotBeEditedException() {
        Order placedOrder = OrderTestDataBuilder.anOrder().status(OrderStatus.PLACED).build();
        Billing billing = OrderTestDataBuilder.aBilling();

        assertThatThrownBy(() -> placedOrder.changeBilling(billing))
                .isInstanceOf(OrderCannotBeEditedException.class);
    }

    @Test
    void givenPlacedOrder_whenChangeShippingIsCalled_shouldThrowOrderCannotBeEditedException() {
        Order placedOrder = OrderTestDataBuilder.anOrder().status(OrderStatus.PLACED).build();
        Shipping shipping = OrderTestDataBuilder.aShipping();

        assertThatThrownBy(() -> placedOrder.changeShipping(shipping))
                .isInstanceOf(OrderCannotBeEditedException.class);
    }

    @Test
    void givenPlacedOrder_whenChangeItemQuantityIsCalled_shouldThrowOrderCannotBeEditedException() {
        Order placedOrder = OrderTestDataBuilder.anOrder().status(OrderStatus.PLACED).build();
        Quantity quantity = new Quantity(5);

        OrderItem orderItem = placedOrder.items().iterator().next();

        assertThatThrownBy(() -> placedOrder.changeItemQuantity(orderItem.id(), quantity))
                .isInstanceOf(OrderCannotBeEditedException.class);
    }

    @Test
    void givenPlacedOrder_whenChangePaymentMethodIsCalled_shouldThrowOrderCannotBeEditedException() {
        Order placedOrder = OrderTestDataBuilder.anOrder().status(OrderStatus.PLACED).build();
        PaymentMethod method = PaymentMethod.GATEWAY_BALANCE;

        assertThatThrownBy(() -> placedOrder.changePaymentMethod(method))
                .isInstanceOf(OrderCannotBeEditedException.class);
    }

}
