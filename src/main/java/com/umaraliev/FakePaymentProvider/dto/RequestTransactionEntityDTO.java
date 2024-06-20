package com.umaraliev.FakePaymentProvider.dto;

import com.umaraliev.FakePaymentProvider.model.enums.TransactionType;
import lombok.Data;

@Data
public class RequestTransactionEntityDTO {

    private Long id;
    private TransactionType transactionType;
    private Long amount;
    private String currency;
    private String language;
    private Long cardNumber;
    private Long accountId;
}
