package com.umaraliev.FakePaymentProvider.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;



@Data
@Table(name = "accounts")
public class AccountEntity {

    @Id
    private Long id;

    private String currency;

    private Long balance;

    private String enabled;

    private String merchantId;
}
