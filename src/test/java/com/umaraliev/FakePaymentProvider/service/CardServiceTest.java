package com.umaraliev.FakePaymentProvider.service;

import com.umaraliev.FakePaymentProvider.model.CardEntity;
import com.umaraliev.FakePaymentProvider.repository.CardRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
public class CardServiceTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardService cardService;

    @Test
    public void getCardTest() {
        Long id = 1L;
        CardEntity cardEntity = new CardEntity();
        Mockito.when(cardRepository.findById(id)).thenReturn(Mono.just(cardEntity));

        StepVerifier.create(cardService.getCard(id))
                .expectNext(cardEntity)
                .verifyComplete();
    }

    @Test
    public void saveCardTest() {
        CardEntity cardEntity = new CardEntity();
        Mockito.when(cardRepository.save(cardEntity)).thenReturn(Mono.just(cardEntity));

        StepVerifier.create(cardService.saveCard(cardEntity))
                .expectNext(cardEntity)
                .verifyComplete();
    }
}
