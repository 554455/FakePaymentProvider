package com.umaraliev.FakePaymentProvider.service;

import com.umaraliev.FakePaymentProvider.model.AccountEntity;
import com.umaraliev.FakePaymentProvider.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    public void getAccountTest() {
        Long id = 1L;
        AccountEntity accountEntity = new AccountEntity();
        Mockito.when(accountRepository.findById(id)).thenReturn(Mono.just(accountEntity));

        StepVerifier.create(accountService.getAccount(id))
                .expectNext(accountEntity)
                .verifyComplete();
    }

    @Test
    public void saveAccountTest() {
        AccountEntity accountEntity = new AccountEntity();
        Mockito.when(accountRepository.save(accountEntity)).thenReturn(Mono.just(accountEntity));

        StepVerifier.create(accountService.saveAccount(accountEntity))
                .expectNext(accountEntity)
                .verifyComplete();
    }
}
