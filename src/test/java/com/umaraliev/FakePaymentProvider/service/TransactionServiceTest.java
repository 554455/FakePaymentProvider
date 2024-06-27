package com.umaraliev.FakePaymentProvider.service;

import com.umaraliev.FakePaymentProvider.model.TransactionEntity;
import com.umaraliev.FakePaymentProvider.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    public void getTransactionTest() {
        Long id = 1L;
        TransactionEntity transactionEntity = new TransactionEntity();
        Mockito.when(transactionRepository.findById(id)).thenReturn(Mono.just(transactionEntity));

        StepVerifier.create(transactionService.getTransaction(id))
                .expectNext(transactionEntity)
                .verifyComplete();
    }

    @Test
    public void getTransactionListTest() {
        TransactionEntity transactionEntity = new TransactionEntity();
        Mockito.when(transactionRepository.findAll()).thenReturn(Flux.just(transactionEntity));

        StepVerifier.create(transactionService.getTransactionList())
                .expectNext(transactionEntity)
                .verifyComplete();
    }

    @Test
    public void saveTransactionTest() {
        TransactionEntity transactionEntity = new TransactionEntity();
        Mockito.when(transactionRepository.save(transactionEntity)).thenReturn(Mono.just(transactionEntity));

        StepVerifier.create(transactionService.saveTransaction(transactionEntity))
                .expectNext(transactionEntity)
                .verifyComplete();
    }

    @Test
    public void updateStatusTransactionTest() {
        TransactionEntity transactionEntity = new TransactionEntity();
        Mockito.when(transactionRepository.saveAll(Flux.just(transactionEntity))).thenReturn(Flux.just(transactionEntity));

        StepVerifier.create(transactionService.updateStatusTransaction(Flux.just(transactionEntity)))
                .expectNext(transactionEntity)
                .verifyComplete();
    }
}
