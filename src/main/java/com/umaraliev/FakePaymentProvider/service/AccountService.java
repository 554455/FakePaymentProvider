package com.umaraliev.FakePaymentProvider.service;

import com.umaraliev.FakePaymentProvider.model.AccountEntity;
import com.umaraliev.FakePaymentProvider.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.persistence.Id;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    private Mono<AccountEntity> getAccount(Id id) {
        return accountRepository.findById(id);
    }

    private Flux<AccountEntity> getAccountList() {
        return accountRepository.findAll();
    }

    private Mono<AccountEntity> saveAccount(AccountEntity accountEntity) {
        return accountRepository.save(accountEntity);
    }
}
