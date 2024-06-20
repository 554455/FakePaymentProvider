package com.umaraliev.FakePaymentProvider.dto;

import lombok.Data;

@Data
public class RequestCardEntityDTO {

    private Long id;
    private String cardNumber;
    private String expDate;
    private String cvv;
    private RequestCustomerEntityDTO requestCustomerEntity;
}
