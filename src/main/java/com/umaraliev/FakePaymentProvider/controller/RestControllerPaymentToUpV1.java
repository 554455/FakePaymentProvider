package com.umaraliev.FakePaymentProvider.controller;

import com.umaraliev.FakePaymentProvider.dto.TransactionEntityDTO;
import com.umaraliev.FakePaymentProvider.model.TransactionEntity;
import com.umaraliev.FakePaymentProvider.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController()
@RequestMapping("/api/v1/payments/toup/transaction")
@RequiredArgsConstructor
@Validated
public class RestControllerPaymentToUpV1 {

    private final TransactionService transactionService;

    @GetMapping("/list")
    public Flux<TransactionEntity> transactionEntityFlux() {
        return transactionService.getTransactionList();
    }

    @PostMapping("/create")
    public Mono<TransactionEntity> createTransaction(@Valid @RequestBody TransactionEntityDTO transactionEntityDTO) {
        return ReactiveSecurityContextHolder.getContext()
                .flatMap(context -> {
                    Authentication authentication = context.getAuthentication();
                    if (authentication != null) {
                        return transactionService.builder(transactionEntityDTO);
                    } else {
                        return Mono.error(new RuntimeException("Unauthorized"));
                    }
                });
    }


}
