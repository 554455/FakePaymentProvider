package com.umaraliev.FakePaymentProvider.service;

import com.umaraliev.FakePaymentProvider.dto.TransactionEntityDTO;
import com.umaraliev.FakePaymentProvider.model.TransactionEntity;
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


    public Mono<TransactionEntity> getTransaction(Long id) {
        return transactionRepository.findById(id);
    }
    public Flux<TransactionEntity> getTransactionList() {
        return transactionRepository.findAll();
    }

    public Mono<TransactionEntity> saveTransaction(TransactionEntity transactionEntity) {
        return transactionRepository.save(transactionEntity);
    }
    public Flux<TransactionEntity> updateStatusTransaction(Flux<TransactionEntity> transactions) {
        return transactionRepository.saveAll(transactions);
    }
    public Mono<TransactionEntity> builder(TransactionEntityDTO transactionEntityDTO) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setTransactionType(transactionEntityDTO.getTransactionType());
        transactionEntity.setAmount(transactionEntityDTO.getAmount());
        transactionEntity.setCurrency(transactionEntityDTO.getCurrency());
        transactionEntity.setLanguage(transactionEntityDTO.getLanguage());
        transactionEntity.setCreatedAt(LocalDateTime.now());
        transactionEntity.setCardNumber(transactionEntityDTO.getCardNumber());
        transactionEntity.setAccountId(transactionEntityDTO.getAccountId());
        transactionEntity.setStatus("IN_PROGRESS");
        return transactionRepository.save(transactionEntity);
    }
}
