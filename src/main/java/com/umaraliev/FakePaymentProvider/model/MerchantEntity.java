package com.umaraliev.FakePaymentProvider.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Data
@Table(name = "merchants")
@NoArgsConstructor
@AllArgsConstructor
public class MerchantEntity {

    @Id
    private String merchantId;

    private String secretKey;

    private String enabled;

    private Long accountId;
}
