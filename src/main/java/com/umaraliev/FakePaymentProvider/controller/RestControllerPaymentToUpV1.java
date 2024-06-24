package com.umaraliev.FakePaymentProvider.controller;

import com.umaraliev.FakePaymentProvider.service.ProcessingFakeService;
import com.umaraliev.FakePaymentProvider.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequiredArgsConstructor
@Slf4j
public class RestControllerPaymentToUpV1 {

    private final TransactionService transactionService;
    private final ProcessingFakeService processingFakeService;

//    @GetMapping("/transaction/list")
//    public Flux<TransactionEntity> transactionEntityFlux() {
//        return transactionService.getTransactionList();
//    }
//
//    @PostMapping("/create")
//    public Mono<TransactionEntity> createTransaction(@RequestBody RequestTransactionEntityDTO requestTransactionEntityDTO) {
//        return transactionService.builder(
//                requestTransactionEntityDTO.getTransactionType(),
//                requestTransactionEntityDTO.getAmount(),
//                requestTransactionEntityDTO.getCurrency(),
//                requestTransactionEntityDTO.getLanguage(),
//                requestTransactionEntityDTO.getCardNumber(),
//                requestTransactionEntityDTO.getAccountId());
//    }


}
