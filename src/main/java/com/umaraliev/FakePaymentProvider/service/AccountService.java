package com.umaraliev.FakePaymentProvider.service;

import com.umaraliev.FakePaymentProvider.model.AccountEntity;
import com.umaraliev.FakePaymentProvider.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;


    public Mono<AccountEntity> getAccount(Long id) {
        return accountRepository.findById(id);
    }

    public Mono<AccountEntity> saveAccount(AccountEntity accountEntity) {
        return accountRepository.save(accountEntity);
    }
}
