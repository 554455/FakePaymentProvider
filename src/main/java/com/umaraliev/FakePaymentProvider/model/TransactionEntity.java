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



    @JsonIgnore
    private LocalDateTime createdAt;



    @JsonIgnore
    private LocalDateTime updatedAt;


    //TOdo потом убрать
    private String status = "IN_PROGRESS";

    private String language;

    private Long cardNumber;

    private Long accountId;

}
