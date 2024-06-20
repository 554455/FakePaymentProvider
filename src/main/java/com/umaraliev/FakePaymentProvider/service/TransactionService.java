package com.umaraliev.FakePaymentProvider.service;

import com.umaraliev.FakePaymentProvider.model.TransactionEntity;
import com.umaraliev.FakePaymentProvider.model.enums.TransactionType;
import com.umaraliev.FakePaymentProvider.repository.AccountRepository;
import com.umaraliev.FakePaymentProvider.repository.CardRepository;
import com.umaraliev.FakePaymentProvider.repository.TransactionRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Builder
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final CardRepository cardRepository;


    private final AccountRepository accountRepository;

    private Mono<TransactionEntity> getTransaction(Long id) {
        return transactionRepository.findById(id);
    }

    public Flux<TransactionEntity> getTransactionList() {
        return transactionRepository.findAll();
    }

    public Mono<TransactionEntity> saveTransaction(TransactionEntity transactionEntity) {
        return transactionRepository.save(transactionEntity);
    }

//    protected Flux<TransactionEntity> updateStatusTransaction(Flux<TransactionEntity> transactionEntity) {
//        return transactionRepository.saveAll(transactionEntity);
//    }

    public Mono<TransactionEntity> updateStatusTransaction(TransactionEntity transactions) {
        // ... код для обновления записей в базе данных
        return transactionRepository.save(transactions); // Возвращаем поток обновленных транзакций
    }


    public Mono<TransactionEntity> builder(Long id, TransactionType transactionType, Long amount, String currency, String language, Long cardNumber, Long accountId) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionType(transactionType);
        transactionEntity.setAmount(amount);
        transactionEntity.setCurrency(currency);
        transactionEntity.setLanguage(language);
        transactionEntity.setCreatedAt(LocalDateTime.now());
        transactionEntity.setCardNumber(cardNumber);
        transactionEntity.setAccountId(accountId);
        return transactionRepository.save(transactionEntity);
    }
}
