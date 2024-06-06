package com.umaraliev.FakePaymentProvider.repository;

import com.umaraliev.FakePaymentProvider.model.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.UUID;

@Repository
public interface UserRepository extends ReactiveCrudRepository<UserEntity, UUID> {

}
