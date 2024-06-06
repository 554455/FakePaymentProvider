package com.umaraliev.FakePaymentProvider.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
        import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {
    @Id
    private UUID transactionId;

    private String paymentMethod;
    private Long amount;
    private String currency;
    private String language;
    private String notificationUrl;

    @ManyToOne
    @JoinColumn(name = "card_number")
    private CardEntity cardEntity;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity accountEntity;

    private String transactionType;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    private String createdBy;
    private String updatedBy;
    private String status;


}
