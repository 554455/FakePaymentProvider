package com.umaraliev.FakePaymentProvider.service;

import com.umaraliev.FakePaymentProvider.model.CustomerEntity;
import com.umaraliev.FakePaymentProvider.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    public void getCustomerTest() {
        Long id = 1L;
        CustomerEntity customerEntity = new CustomerEntity();
        Mockito.when(customerRepository.findById(id)).thenReturn(Mono.just(customerEntity));

        StepVerifier.create(customerService.getCustomer(id))
                .expectNext(customerEntity)
                .verifyComplete();
    }

    @Test
    public void saveCustomerTest() {
        CustomerEntity customerEntity = new CustomerEntity();
        Mockito.when(customerRepository.save(customerEntity)).thenReturn(Mono.just(customerEntity));

        StepVerifier.create(customerService.saveCustomer(customerEntity))
                .expectNext(customerEntity)
                .verifyComplete();
    }
}
