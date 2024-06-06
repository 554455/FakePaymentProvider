package com.umaraliev.FakePaymentProvider.service;

import com.umaraliev.FakePaymentProvider.model.TransactionEntity;
import com.umaraliev.FakePaymentProvider.model.enumUtil.StatusTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Repository
@RequiredArgsConstructor
public class ProcessingFakeService {

    private final TransactionService transactionService;
    private final StatusTransaction statusTransaction;

    private Flux<TransactionEntity> processingFakeTopUpTransaction() {
        Flux<TransactionEntity> entityFlux = transactionService.getTransactionList().filter(transactionEntity -> transactionEntity.getStatus().equals("APPROVED"));

        Flux<TransactionEntity> updatedEntityFlux = entityFlux.map(entity -> {
            entity.setStatus(statusTransaction.getRandomStatus());
            return entity;
        });
        return transactionService.updateStatusTransaction(updatedEntityFlux);
    }
}
