package com.umaraliev.FakePaymentProvider.controller;

import com.umaraliev.FakePaymentProvider.dto.TransactionEntityDTO;
import com.umaraliev.FakePaymentProvider.model.TransactionEntity;
import com.umaraliev.FakePaymentProvider.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController()
@RequestMapping("/api/v1/payments/payout/transaction")
@RequiredArgsConstructor
@Validated
public class RestControllerPaymentPayOutV1 {

    private final TransactionService transactionService;

    @GetMapping("/list")
    public Flux<TransactionEntity> transactionEntityFlux() {
        return transactionService.getTransactionList();
    }

    @PostMapping("/create")
    public Mono<TransactionEntity> createTransaction(@Valid
                                                     @RequestHeader("merchantId") String merchantId,
                                                     @RequestHeader("secretKey") String secretKey,
                                                     @RequestBody TransactionEntityDTO transactionEntityDTO) {
        return transactionService.builder(transactionEntityDTO);
    }
}
