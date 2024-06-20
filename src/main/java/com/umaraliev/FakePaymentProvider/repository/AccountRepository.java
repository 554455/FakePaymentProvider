package com.umaraliev.FakePaymentProvider.repository;


import com.umaraliev.FakePaymentProvider.model.AccountEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends ReactiveCrudRepository<AccountEntity, Long> {
}
