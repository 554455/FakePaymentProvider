package com.umaraliev.FakePaymentProvider.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "users")
public class UserEntity {

    @Id
    private String id;
}