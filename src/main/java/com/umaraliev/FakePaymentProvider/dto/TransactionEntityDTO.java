package com.umaraliev.FakePaymentProvider.dto;

import com.umaraliev.FakePaymentProvider.model.enums.TransactionType;
import com.umaraliev.FakePaymentProvider.service.validation.ValidCheckingAvailabilityAccount;
import com.umaraliev.FakePaymentProvider.service.validation.ValidCheckingAvailabilityCardNumber;
import com.umaraliev.FakePaymentProvider.service.validation.ValidTransactionType;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TransactionEntityDTO {

    private Long id;

    @NotNull(message = "Transaction type must not be null")
    @ValidTransactionType
    private TransactionType transactionType;

    @NotNull(message = "Amount must not be null")
    private Long amount;

    private String currency;

    private String language;

    @ValidCheckingAvailabilityCardNumber
    private Long cardNumber;

    @ValidCheckingAvailabilityAccount
    private Long accountId;
}
