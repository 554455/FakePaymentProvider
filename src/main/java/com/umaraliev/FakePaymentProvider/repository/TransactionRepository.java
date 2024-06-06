package com.umaraliev.FakePaymentProvider.repository;

import com.umaraliev.FakePaymentProvider.model.TransactionEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface TransactionRepository extends ReactiveCrudRepository<TransactionEntity, UUID> {
    Flux<TransactionEntity> saveAll(TransactionEntity transactionEntity);
}
