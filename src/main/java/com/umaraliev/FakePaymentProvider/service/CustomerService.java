package com.umaraliev.FakePaymentProvider.service;

import com.umaraliev.FakePaymentProvider.model.CustomerEntity;
import com.umaraliev.FakePaymentProvider.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private Mono<CustomerEntity> getCustomer(Long id) {
        return customerRepository.findById(id);
    }

    private Flux<CustomerEntity> getCustomerList() {
        return customerRepository.findAll();
    }

    private Mono<CustomerEntity> saveCustomer(CustomerEntity customerEntity) {
        return customerRepository.save(customerEntity);
    }
}
