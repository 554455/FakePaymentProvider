package com.umaraliev.FakePaymentProvider.service;

import com.umaraliev.FakePaymentProvider.model.MerchantEntity;
import com.umaraliev.FakePaymentProvider.repository.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final MerchantRepository merchantRepository;

    public MerchantService(MerchantRepository marchantRepository) {
        this.merchantRepository = marchantRepository;
    }

    private Mono<MerchantEntity> getMerchant(UUID id) {
        return merchantRepository.findById(id);
    }

    private Flux<MerchantEntity> getMerchantList() {
        return merchantRepository.findAll();
    }

    private Mono<MerchantEntity> saveMerchant(MerchantEntity merchantEntity) {
        return merchantRepository.save(merchantEntity);
    }
}
