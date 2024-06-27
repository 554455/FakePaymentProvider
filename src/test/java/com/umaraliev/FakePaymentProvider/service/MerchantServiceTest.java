package com.umaraliev.FakePaymentProvider.service;

import com.umaraliev.FakePaymentProvider.model.MerchantEntity;
import com.umaraliev.FakePaymentProvider.repository.MerchantRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
public class MerchantServiceTest {

    @Mock
    private MerchantRepository merchantRepository;

    @InjectMocks
    private MerchantService merchantService;

    @Test
    public void getMerchantTest() {
        String id = "1";
        MerchantEntity merchantEntity = new MerchantEntity();
        Mockito.when(merchantRepository.findById(id)).thenReturn(Mono.just(merchantEntity));

        StepVerifier.create(merchantService.getMerchant(id))
                .expectNext(merchantEntity)
                .verifyComplete();
    }

    @Test
    public void saveMerchantTest() {
        MerchantEntity merchantEntity = new MerchantEntity();
        Mockito.when(merchantRepository.save(merchantEntity)).thenReturn(Mono.just(merchantEntity));

        StepVerifier.create(merchantService.saveMerchant(merchantEntity))
                .expectNext(merchantEntity)
                .verifyComplete();
    }
}
