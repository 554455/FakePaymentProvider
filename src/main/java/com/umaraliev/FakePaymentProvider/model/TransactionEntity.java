package com.umaraliev.FakePaymentProvider.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.umaraliev.FakePaymentProvider.model.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
public class TransactionEntity {

    @Id()
    @JsonIgnore
    private Long id;

    private TransactionType transactionType;
    private Long amount;
    private String currency;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String status;

    private String language;

    private Long cardNumber;

    private Long accountId;

}
