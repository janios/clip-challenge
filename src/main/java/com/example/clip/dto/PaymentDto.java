package com.example.clip.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDto {

	private long id;
	private BigDecimal amount;
	private String userId;
	
}
