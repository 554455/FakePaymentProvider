package com.umaraliev.FakePaymentProvider.service;

import com.umaraliev.FakePaymentProvider.model.CustomerEntity;
import com.umaraliev.FakePaymentProvider.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Mono<CustomerEntity> getCustomer(Long id) {
        return customerRepository.findById(id);
    }

    public Mono<CustomerEntity> saveCustomer(CustomerEntity customerEntity) {
        return customerRepository.save(customerEntity);
    }
}
