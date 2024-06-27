package com.umaraliev.FakePaymentProvider.service;

import com.umaraliev.FakePaymentProvider.model.MerchantEntity;
import com.umaraliev.FakePaymentProvider.repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final MerchantRepository merchantRepository;

    public Mono<MerchantEntity> getMerchant(String id) {
        return merchantRepository.findById(id);
    }

    public Mono<MerchantEntity> saveMerchant(MerchantEntity merchantEntity) {
        return merchantRepository.save(merchantEntity);
    }

}
