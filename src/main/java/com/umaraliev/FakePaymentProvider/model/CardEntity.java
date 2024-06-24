package com.umaraliev.FakePaymentProvider.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Table(name = "cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardEntity {

    @Id
    private Long cardNumber;
    private String expDate;
    private String cvv;

    private Long balance;

    private Long accountId;

}

