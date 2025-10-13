package com.algaworks.algashop.billing.application.invoice.management;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PayerData {
	private String fullName;
	private String document;
	private String email;
	private String phone;
	private AddressData address;
}