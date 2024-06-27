package com.umaraliev.FakePaymentProvider.service.validation.impl;

import com.umaraliev.FakePaymentProvider.service.CardService;
import com.umaraliev.FakePaymentProvider.service.validation.ValidCheckingAvailabilityCardNumber;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@RequiredArgsConstructor
public class ValidCheckingAvailabilityCardNumberImpl implements ConstraintValidator<ValidCheckingAvailabilityCardNumber, Long> {

    private final CardService cardService;

    @Override
    public boolean isValid(Long cardNumber, ConstraintValidatorContext context) {
        if (cardNumber == null) {
            return true; // @NotNull will handle null case
        }

        Mono<Boolean> cardExists = cardService.getCard(cardNumber)
                .map(card -> true)
                .defaultIfEmpty(false);

        return cardExists.block();
    }
}
