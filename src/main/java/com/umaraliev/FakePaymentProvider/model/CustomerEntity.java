package com.umaraliev.FakePaymentProvider.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
        import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class CustomerEntity {
    @Id
    private UUID customerId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @OneToOne
    @JoinColumn(name = "card_number")
    private CardEntity cardEntity;

    private String firstName;
    private String lastName;
    private String country;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    private String createdBy;
    private String updatedBy;
    private String status;
}
