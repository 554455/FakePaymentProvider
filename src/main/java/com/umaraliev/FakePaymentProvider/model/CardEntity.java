package com.umaraliev.FakePaymentProvider.model;

import lombok.Data;

import javax.persistence.Entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class CardEntity {
    @Id
    private String cardNumber;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    private String expDate;
    private String cvv;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    private String createdBy;
    private String updatedBy;
    private String status;
}

