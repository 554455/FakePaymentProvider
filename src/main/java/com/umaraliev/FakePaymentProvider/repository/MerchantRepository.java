package com.umaraliev.FakePaymentProvider.repository;

import com.umaraliev.FakePaymentProvider.model.MerchantEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MerchantRepository extends ReactiveCrudRepository<MerchantEntity, UUID> {
}
