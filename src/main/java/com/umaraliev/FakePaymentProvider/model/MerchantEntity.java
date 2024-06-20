package com.umaraliev.FakePaymentProvider.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Data
@Table(name = "merchants")
public class MerchantEntity {

    @Id
    private String merchantId;

    private String secretKey;

    private String enabled;

    private Long accountId;
}
