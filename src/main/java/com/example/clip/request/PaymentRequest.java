package com.example.clip.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentRequest {

    Long userId;
    BigDecimal amount;
}
