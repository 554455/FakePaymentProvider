package com.umaraliev.FakePaymentProvider.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestAccountEntityDTO {

    private Long id;
    private String currency;
    private BigDecimal balance;
    private String enabled;
    private RequestMerchantEntityDTO requestMerchantEntity;
}
