package com.umaraliev.FakePaymentProvider.service;

import com.umaraliev.FakePaymentProvider.model.UserEntity;
import com.umaraliev.FakePaymentProvider.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private Mono<UserEntity> getUser(UUID id){
        return userRepository.findById(id);
    }

    private Flux<UserEntity> getUserList() {
        return userRepository.findAll();
    }

    private Mono<UserEntity> saveUser(UserEntity userEntity){
        return userRepository.save(userEntity);
    }
}
