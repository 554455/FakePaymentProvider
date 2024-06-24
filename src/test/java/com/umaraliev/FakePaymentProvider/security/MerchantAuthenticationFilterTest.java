package com.umaraliev.FakePaymentProvider.security;

import com.umaraliev.FakePaymentProvider.model.MerchantEntity;
import com.umaraliev.FakePaymentProvider.service.MerchantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MerchantAuthenticationWebFilterTest {

    private MerchantAuthenticationFilter filter = new MerchantAuthenticationFilter();
    private MerchantService merchantService = Mockito.mock(MerchantService.class);
    private WebFilterChain chain = Mockito.mock(WebFilterChain.class);
    private MockServerWebExchange exchange = MockServerWebExchange.from(MockServerWebExchange.builder().build());

    @Test
    void filter_withValidCredentials_shouldAuthenticate() {
        // Данные мерчанта
        MerchantEntity merchant = new MerchantEntity("merchant1", "secret1");

        // Задаем заголовки
        HttpHeaders headers = new HttpHeaders();
        headers.add("merchantId", "merchant1");
        headers.add("secretKey", "secret1");
        exchange = MockServerWebExchange.from(MockServerWebExchange.builder()
                .request().headers(headers).build());

        // Настраиваем Mock
        when(merchantService.getMerchant("merchant1")).thenReturn(Mono.just(merchant));

        // Запускаем фильтр
        Mono<Void> result = filter.filter(exchange, chain);

        // Проверяем, что аутентификация установлена в контексте
        StepVerifier.create(ReactiveSecurityContextHolder.getContext()
                        .map(SecurityContext::getAuthentication)
                        .cast(UsernamePasswordAuthenticationToken.class)
                        .map(UsernamePasswordAuthenticationToken::getPrincipal))
                .expectNext("merchant1") // Проверяем, что principal совпадает с merchantId
                .verifyComplete();

        // Проверяем, что фильтр вызвал chain.filter()
        Mockito.verify(chain).filter(exchange);
    }

    @Test
    void filter_withInvalidSecretKey_shouldNotAuthenticate() {
        // Данные мерчанта
        Merchant merchant = new Merchant("merchant1", "secret1");

        // Задаем заголовки
        HttpHeaders headers = new HttpHeaders();
        headers.add("merchantId", "merchant1");
        headers.add("secretKey", "wrongSecret");
        exchange = MockServerWebExchange.from(MockServerWebExchange.builder()
                .request().headers(headers).build());

        // Настраиваем Mock
        when(merchantService.getMerchant("merchant1")).thenReturn(Mono.just(merchant));

        // Запускаем фильтр
        Mono<Void> result = filter.filter(exchange, chain);

        // Проверяем, что аутентификация не установлена в контексте
        StepVerifier.create(ReactiveSecurityContextHolder.getContext()
                        .map(SecurityContext::getAuthentication))
                .expectNextMatches(Authentication::isAuthenticated)
                .verifyComplete();

        // Проверяем, что фильтр вызвал chain.filter()
        Mockito.verify(chain).filter(exchange);
    }

    @Test
    void filter_withMissingCredentials_shouldNotAuthenticate() {
        // Запускаем фильтр
        Mono<Void> result = filter.filter(exchange, chain);

        // Проверяем, что аутентификация не установлена в контексте
        StepVerifier.create(ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication))
                .expectNextMat
