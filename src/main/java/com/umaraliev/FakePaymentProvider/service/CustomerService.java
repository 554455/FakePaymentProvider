package com.umaraliev.FakePaymentProvider.service;

import com.umaraliev.FakePaymentProvider.model.CustomerEntity;
import com.umaraliev.FakePaymentProvider.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private Mono<CustomerEntity> getCustomer(UUID id) {
        return customerRepository.findById(id);
    }

    private Flux<CustomerEntity> getCustomerList() {
        return customerRepository.findAll();
    }

    private Mono<CustomerEntity> saveCustomer(CustomerEntity customerEntity) {
        return customerRepository.save(customerEntity);
    }
}
