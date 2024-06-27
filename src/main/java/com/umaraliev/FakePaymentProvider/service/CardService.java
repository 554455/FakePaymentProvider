package com.umaraliev.FakePaymentProvider.service;

import com.umaraliev.FakePaymentProvider.model.CardEntity;
import com.umaraliev.FakePaymentProvider.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public Mono<CardEntity> getCard(Long id) {
        return cardRepository.findById(id);
    }

    public Mono<CardEntity> saveCard(CardEntity cardEntity) {
        return cardRepository.save(cardEntity);
    }

}
