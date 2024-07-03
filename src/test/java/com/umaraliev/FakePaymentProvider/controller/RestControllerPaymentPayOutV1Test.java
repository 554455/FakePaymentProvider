package com.umaraliev.FakePaymentProvider.controller;

import com.umaraliev.FakePaymentProvider.dto.TransactionEntityDTO;
import com.umaraliev.FakePaymentProvider.model.TransactionEntity;
import com.umaraliev.FakePaymentProvider.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@WebFluxTest(RestControllerPaymentPayOutV1.class)
public class RestControllerPaymentPayOutV1Test {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private TransactionService transactionService;

    @BeforeEach
    public void setup() {
        SecurityContext securityContext = new SecurityContextImpl();
        ReactiveSecurityContextHolder.withSecurityContext(Mono.just(securityContext));
    }

    @Test
    public void testTransactionEntityFlux() {
        TransactionEntity transactionEntity = new TransactionEntity();
        when(transactionService.getTransactionList()).thenReturn(Flux.just(transactionEntity));

        webTestClient.get()
                .uri("/api/v1/payments/payout/transaction/list")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(TransactionEntity.class)
                .contains(transactionEntity);
    }

    @Test
    public void testCreateTransaction() {
        TransactionEntityDTO transactionEntityDTO = new TransactionEntityDTO();
        TransactionEntity transactionEntity = new TransactionEntity();
        when(transactionService.builder(transactionEntityDTO)).thenReturn(Mono.just(transactionEntity));

        webTestClient.post()
                .uri("/api/v1/payments/payout/transaction/create")
                .bodyValue(transactionEntityDTO)
                .exchange()
                .expectStatus().isOk()
                .expectBody(TransactionEntity.class)
                .isEqualTo(transactionEntity);
    }
}



