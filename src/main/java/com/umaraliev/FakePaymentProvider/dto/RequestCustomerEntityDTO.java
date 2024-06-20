package com.umaraliev.FakePaymentProvider.dto;

import lombok.Data;

@Data
public class RequestCustomerEntityDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String country;
    private RequestCardEntityDTO cardEntity;
}
