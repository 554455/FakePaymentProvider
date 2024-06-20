package com.umaraliev.FakePaymentProvider.service;

import com.umaraliev.FakePaymentProvider.model.AccountEntity;
import com.umaraliev.FakePaymentProvider.model.CardEntity;
import com.umaraliev.FakePaymentProvider.model.StatusTransaction;
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

    private final MerchantService merchantService;
    private final AccountService accountService;
    private final CardService cardService;

    @Scheduled(fixedRate = 5000)
    public Flux<TransactionEntity> processingFakeTopUpTransaction() {

    Flux<TransactionEntity> transactionsToUpdate = transactionService.getTransactionList()
            .filter(transaction -> !transaction.getStatus().equals("APPROVED") & !transaction.getStatus().equals("FAILED") & transaction.getTransactionType().equals(TransactionType.TO_UP));

    Flux<TransactionEntity> updatedTransactions = transactionsToUpdate
            .map(transaction -> {
                transaction.setStatus(statusTransaction.getRandomStatus());
                if (!transaction.getStatus().equals("IN_PROGRESS")){
                    transaction.setUpdatedAt(LocalDateTime.now());
                }
                if (transaction.getStatus().equals("APPROVED")){
                    Mono<CardEntity> cardEntityMono = cardService.getCard(transaction.getCardNumber())
                            .flatMap(cardEntity -> {
                                cardEntity.setBalance(cardEntity.getBalance() - transaction.getAmount());
                                return cardService.saveCard(cardEntity);
                            });
                    log.info("" + cardEntityMono.subscribe(System.out::println));
                    Mono<AccountEntity> accountEntityMono = accountService.getAccount(transaction.getAccountId())
                            .flatMap(accountEntity -> {
                                accountEntity.setBalance(accountEntity.getBalance() + transaction.getAmount());
                                return  accountService.saveAccount(accountEntity);
                            });
                    log.info("" + accountEntityMono.subscribe(System.out::println));
                }
                return transaction;
            })
            .flatMap(transaction -> transactionService.updateStatusTransaction(transaction)
                    .thenReturn(transaction));

        log.info("list + " + updatedTransactions.subscribe(System.out::println));
        return updatedTransactions;
    }

    @Scheduled(fixedRate = 5000)
    public Flux<TransactionEntity> processingFakePayOutTransaction() {

        Flux<TransactionEntity> transactionsToUpdate = transactionService.getTransactionList()
                .filter(transaction -> !transaction.getStatus().equals("APPROVED") & !transaction.getStatus().equals("FAILED") & transaction.getTransactionType().equals(TransactionType.PAY_UOT));

        Flux<TransactionEntity> updatedTransactions = transactionsToUpdate
                .map(transaction -> {
                    transaction.setStatus(statusTransaction.getRandomStatus());
                    if (!transaction.getStatus().equals("IN_PROGRESS")){
                        transaction.setUpdatedAt(LocalDateTime.now());
                    }
                    if (transaction.getStatus().equals("APPROVED")){
                        Mono<CardEntity> cardEntityMono = cardService.getCard(transaction.getCardNumber())
                                .flatMap(cardEntity -> {
                                    cardEntity.setBalance(cardEntity.getBalance() + transaction.getAmount());
                                    return cardService.saveCard(cardEntity);
                                });
                        log.info("" + cardEntityMono.subscribe(System.out::println));
                        Mono<AccountEntity> accountEntityMono = accountService.getAccount(transaction.getAccountId())
                                .flatMap(accountEntity -> {
                                    accountEntity.setBalance(accountEntity.getBalance() - transaction.getAmount());
                                    return  accountService.saveAccount(accountEntity);
                                });
                        log.info("" + accountEntityMono.subscribe(System.out::println));
                    }
                    return transaction;
                })
                .flatMap(transaction -> transactionService.updateStatusTransaction(transaction)
                        .thenReturn(transaction));

        log.info("list + " + updatedTransactions.subscribe(System.out::println));
        return updatedTransactions;
    }
}
