package com.algaworks.algashop.billing.application.invoice.management;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenerateInvoiceInput{
    private String orderId;
    private UUID customerId;
    private PaymentSettingsInput paymentSettings;
    private PayerData payer;
    private Set<LineItemInput> items;
}