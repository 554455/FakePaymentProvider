package com.umaraliev.FakePaymentProvider.service;

import com.umaraliev.FakePaymentProvider.model.CardEntity;
import com.umaraliev.FakePaymentProvider.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    private Mono<CardEntity> getCard(String id) {
        return cardRepository.findById(id);
    }

    private Flux<CardEntity> getCardList() {
        return cardRepository.findAll();
    }

    private Mono<CardEntity> saveCard(CardEntity cardEntity) {
        return cardRepository.save(cardEntity);
    }
}
