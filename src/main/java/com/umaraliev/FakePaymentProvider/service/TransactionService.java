package com.umaraliev.FakePaymentProvider.service;

import com.umaraliev.FakePaymentProvider.model.TransactionEntity;
import com.umaraliev.FakePaymentProvider.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private Mono<TransactionEntity> getTransaction(UUID id) {
        return transactionRepository.findById(id);
    }

    protected Flux<TransactionEntity> getTransactionList() {
        return transactionRepository.findAll();
    }

    private Mono<TransactionEntity> savaTransaction(TransactionEntity transactionEntity) {
        return transactionRepository.save(transactionEntity);
    }

    protected Flux<TransactionEntity> updateStatusTransaction(Flux<TransactionEntity> transactionEntity) {
        return transactionRepository.saveAll(transactionEntity);
    }
}
