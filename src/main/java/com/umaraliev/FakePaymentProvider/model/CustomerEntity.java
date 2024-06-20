package com.umaraliev.FakePaymentProvider.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Data
@Table(name = "customers")
public class CustomerEntity {
    @Id
    private Long id;

    private String firstName;
    private String lastName;
    private String country;

    private Long cardId;
}
