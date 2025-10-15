package com.algaworks.algashop.ordering.presentation;

public class GatewayTimeoutException extends RuntimeException {
    public GatewayTimeoutException() {
    }

    public GatewayTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}
