package com.umaraliev.FakePaymentProvider.service.validation.impl;

import com.umaraliev.FakePaymentProvider.service.AccountService;
import com.umaraliev.FakePaymentProvider.service.validation.ValidCheckingAvailabilityAccount;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@RequiredArgsConstructor
public class CheckingAvailabilityAccountImpl implements ConstraintValidator<ValidCheckingAvailabilityAccount, Long> {

    private final AccountService accountService;

    @Override
    public boolean isValid(Long accountId, ConstraintValidatorContext context) {
        if (accountId == null) {
            return true;
        }

        Mono<Boolean> accountExists = accountService.getAccount(accountId)
                .map(account -> true)
                .defaultIfEmpty(false);

        return accountExists.block();
    }
}
