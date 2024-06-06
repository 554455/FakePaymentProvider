package com.umaraliev.FakePaymentProvider.repository;

import com.umaraliev.FakePaymentProvider.model.TransactionEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends ReactiveCrudRepository<TransactionEntity, UUID> {
}
