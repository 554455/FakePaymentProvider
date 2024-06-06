package com.umaraliev.FakePaymentProvider.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class MerchantEntity {
    @Id
    private UUID merchantId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    private String secretKey;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    private String createdBy;
    private String updatedBy;
    private String status;
}
