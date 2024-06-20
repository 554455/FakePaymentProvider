package com.umaraliev.FakePaymentProvider.controller;

import com.umaraliev.FakePaymentProvider.dto.RequestTransactionEntityDTO;
import com.umaraliev.FakePaymentProvider.model.TransactionEntity;
import com.umaraliev.FakePaymentProvider.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RestControllerPaymentV1 {

    private final TransactionService transactionService;

    @GetMapping("/transaction/list")
    public Flux<TransactionEntity> transactionEntityFlux() {
        return transactionService.getTransactionList();
    }

    @PostMapping("/create")
    public Mono<TransactionEntity> createTransaction(@RequestBody RequestTransactionEntityDTO requestTransactionEntityDTO) {
        return transactionService.builder(requestTransactionEntityDTO.getId(),
                requestTransactionEntityDTO.getTransactionType(),
                requestTransactionEntityDTO.getAmount(),
                requestTransactionEntityDTO.getCurrency(),
                requestTransactionEntityDTO.getLanguage(),
                requestTransactionEntityDTO.getCardNumber(),
                requestTransactionEntityDTO.getAccountId());
    }

}
