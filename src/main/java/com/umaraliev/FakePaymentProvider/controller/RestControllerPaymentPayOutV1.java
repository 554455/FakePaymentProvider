package com.umaraliev.FakePaymentProvider.controller;

import com.umaraliev.FakePaymentProvider.dto.RequestTransactionEntityDTO;
import com.umaraliev.FakePaymentProvider.model.TransactionEntity;
import com.umaraliev.FakePaymentProvider.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController("api/v1/payments/payout")
@RequiredArgsConstructor
public class RestControllerPaymentPayOutV1 {

    private final TransactionService transactionService;

    @GetMapping("/list")
    public Flux<TransactionEntity> transactionEntityFlux() {
        return transactionService.getTransactionList();
    }

    @PostMapping("/create")
    public Mono<TransactionEntity> createTransaction(@RequestHeader("merchantId") String merchantId,
                                                     @RequestHeader("secretKey") String secretKey,
                                                     @RequestBody RequestTransactionEntityDTO requestTransactionEntityDTO) {
        return transactionService.builder(requestTransactionEntityDTO);
    }
}
