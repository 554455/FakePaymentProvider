package com.umaraliev.FakePaymentProvider.service;

import com.umaraliev.FakePaymentProvider.model.AccountEntity;
import com.umaraliev.FakePaymentProvider.model.CardEntity;
import com.umaraliev.FakePaymentProvider.model.TransactionEntity;
import com.umaraliev.FakePaymentProvider.model.enums.TransactionType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Repository
@RequiredArgsConstructor
@Slf4j
public class ProcessingFakeService {

    private final TransactionService transactionService;
    private final StatusTransaction statusTransaction;
    private final AccountService accountService;
    private final CardService cardService;


    @Scheduled(fixedRate = 5000)
    public Flux<TransactionEntity> processingFakeTopUpTransaction() {
        Flux<TransactionEntity> transactionEntityMono = transactionService.getTransactionList().filter(transactionEntity -> !transactionEntity.getStatus().equals("APPROVED") & !transactionEntity.getStatus().equals("FAILED") & transactionEntity.getTransactionType().equals(TransactionType.TO_UP))
                .map(transactionEntity -> {
                    transactionEntity.setStatus(statusTransaction.getRandomStatus());
                    if (!transactionEntity.getStatus().equals("IN_PROGRESS")) {
                        transactionEntity.setUpdatedAt(LocalDateTime.now());
                    }
                    if (transactionEntity.getStatus().equals("APPROVED")) {
                        // Обновление баланса аккаунта получателя
                        Mono<AccountEntity> accountEntityMono = accountService.getAccount(transactionEntity.getAccountId())
                                .flatMap(accountEntity -> {
                                    accountEntity.setBalance(accountEntity.getBalance() + transactionEntity.getAmount());
                                    return accountService.saveAccount(accountEntity);
                                });

                        // Обновление баланса аккаунта отправителя
                        Mono<CardEntity> cardEntityMono = cardService.getCard(transactionEntity.getCardNumber())
                                .flatMap(cardEntity -> {
                                    return accountService.getAccount(cardEntity.getAccountId())
                                            .flatMap(accountEntity -> {
                                                accountEntity.setBalance(accountEntity.getBalance() - transactionEntity.getAmount());
                                                return accountService.saveAccount(accountEntity)
                                                        .thenReturn(cardEntity);
                                            });
                                })
                                .flatMap(cardService::saveCard);

                        // Подписка на моно-операции
                        Flux.merge(accountEntityMono, cardEntityMono)
                                .then(Mono.just(transactionEntity)) // Возвращает transactionEntity после завершения
                                .subscribe(
                                        savedTransaction -> System.out.println("Транзакция обработана: " + savedTransaction),
                                        error -> System.err.println("Ошибка при обработке транзакции: " + error.getMessage()),
                                        () -> System.out.println("Обработка транзакции завершена")
                                );
                        return transactionEntity;
                    }
                    return transactionEntity;
                });

        return transactionService.updateStatusTransaction(transactionEntityMono);
    }


    @Scheduled(fixedRate = 5000)
    public Flux<TransactionEntity> processingFakePayOutTransaction() {
        Flux<TransactionEntity> transactionEntityMono = transactionService.getTransactionList().filter(transactionEntity -> !transactionEntity.getStatus().equals("APPROVED") & !transactionEntity.getStatus().equals("FAILED") & transactionEntity.getTransactionType().equals(TransactionType.PAY_UOT))
                .map(transactionEntity -> {
                    transactionEntity.setStatus(statusTransaction.getRandomStatus());
                    if (!transactionEntity.getStatus().equals("IN_PROGRESS")) {
                        transactionEntity.setUpdatedAt(LocalDateTime.now());
                    }
                    if (transactionEntity.getStatus().equals("APPROVED")) {
                        // Обновление баланса аккаунта получателя
                        Mono<AccountEntity> accountEntityMono = accountService.getAccount(transactionEntity.getAccountId())
                                .flatMap(accountEntity -> {
                                    accountEntity.setBalance(accountEntity.getBalance() - transactionEntity.getAmount());
                                    return accountService.saveAccount(accountEntity);
                                });

                        // Обновление баланса аккаунта отправителя
                        Mono<CardEntity> cardEntityMono = cardService.getCard(transactionEntity.getCardNumber())
                                .flatMap(cardEntity -> {
                                    return accountService.getAccount(cardEntity.getAccountId())
                                            .flatMap(accountEntity -> {
                                                accountEntity.setBalance(accountEntity.getBalance() + transactionEntity.getAmount());
                                                return accountService.saveAccount(accountEntity)
                                                        .thenReturn(cardEntity);
                                            });
                                })
                                .flatMap(cardService::saveCard);

                        // Подписка на моно-операции
                        Flux.merge(accountEntityMono, cardEntityMono)
                                .then(Mono.just(transactionEntity)) // Возвращает transactionEntity после завершения
                                .subscribe(
                                        savedTransaction -> System.out.println("Транзакция обработана: " + savedTransaction),
                                        error -> System.err.println("Ошибка при обработке транзакции: " + error.getMessage()),
                                        () -> System.out.println("Обработка транзакции завершена")
                                );
                        return transactionEntity;
                    }
                    return transactionEntity;
                });

        return transactionService.updateStatusTransaction(transactionEntityMono);
    }
}
