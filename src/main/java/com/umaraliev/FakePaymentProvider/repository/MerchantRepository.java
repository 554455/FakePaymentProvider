package com.umaraliev.FakePaymentProvider.repository;

import com.umaraliev.FakePaymentProvider.model.MerchantEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface MerchantRepository extends R2dbcRepository<MerchantEntity, String> {
//    @Query("SELECT * FROM merchants WHERE merchant_id = $1")
    Mono<MerchantEntity> findByMerchantId(String merchantId);
}
