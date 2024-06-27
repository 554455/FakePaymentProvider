package com.umaraliev.FakePaymentProvider.service.validation.impl;

import com.umaraliev.FakePaymentProvider.dto.TransactionEntityDTO;
import com.umaraliev.FakePaymentProvider.model.AccountEntity;
import com.umaraliev.FakePaymentProvider.model.CardEntity;
import com.umaraliev.FakePaymentProvider.service.AccountService;
import com.umaraliev.FakePaymentProvider.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Component
@RequiredArgsConstructor
public class ValidAmountValidatorImpl implements Validator {

    private final AccountService accountService;
    private final CardService cardService;


    @Override
    public boolean supports(Class<?> clazz) {
        return TransactionEntityDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target == null || !supports(target.getClass())) {
            return;
        }

        TransactionEntityDTO dto = (TransactionEntityDTO) target;

        if (dto.getAccountId() != null && dto.getAmount() != null && dto.getCardNumber() != null) {

            //get account merchant
            Mono<AccountEntity> accountMerchantMono = accountService.getAccount(dto.getAccountId());

            //get card and account customer
            Mono<CardEntity> cardEntityMono = cardService.getCard(dto.getCardNumber());
            Mono<AccountEntity> accountCustomerMono = cardEntityMono
                    .flatMap(cardEntity -> accountService.getAccount(cardEntity.getAccountId()));

            Mono<Tuple2<AccountEntity, AccountEntity>> accountsMono = Mono.zip(accountMerchantMono, accountCustomerMono);

            accountsMono.subscribe(
                    accounts -> {
                        AccountEntity accountMerchant = accounts.getT1();
                        AccountEntity accountCustomer = accounts.getT2();

                        if (accountCustomer.getBalance() < dto.getAmount() || dto.getAmount() > accountMerchant.getBalance()) {
                            errors.reject("amount", "Недостаточно средств для выполнения транзакции");
                        }
                    }
            );
        }
    }
}
