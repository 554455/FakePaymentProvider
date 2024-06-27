package com.umaraliev.FakePaymentProvider.security;

import com.umaraliev.FakePaymentProvider.model.MerchantEntity;
import com.umaraliev.FakePaymentProvider.service.MerchantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MerchantAuthenticationWebFilterTest {

    @Mock
    private MerchantService merchantService;

    @Mock
    private WebFilterChain webFilterChain;

    @InjectMocks
    private MerchantAuthenticationFilter merchantAuthenticationFilter;

    @Test
    public void filterTest() {
        String merchantId = "merchant1";
        String secretKey = "secretKey1";
        MerchantEntity merchantEntity = new MerchantEntity();
        merchantEntity.setMerchantId(merchantId);
        merchantEntity.setSecretKey(secretKey);

        when(merchantService.getMerchant(merchantId)).thenReturn(Mono.just(merchantEntity));
        when(webFilterChain.filter(any())).thenReturn(Mono.empty());

        MockServerHttpRequest request = MockServerHttpRequest.get("/")
                .header("merchantId", merchantId)
                .header("secretKey", secretKey)
                .build();
        MockServerWebExchange exchange = MockServerWebExchange.from(request);

        merchantAuthenticationFilter.filter(exchange, webFilterChain)
                .contextWrite(ReactiveSecurityContextHolder.withAuthentication(new UsernamePasswordAuthenticationToken(merchantId, secretKey)))
                .block();

    }
}
