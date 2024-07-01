package com.umaraliev.FakePaymentProvider.security;

import com.umaraliev.FakePaymentProvider.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Configuration
@RequiredArgsConstructor
public class MerchantAuthenticationFilter implements WebFilter {

    private final MerchantService merchantService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String authorizationHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Basic ")) {
            String base64Credentials = authorizationHeader.substring(6);
            String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);
            String[] values = credentials.split(":", 2);

            if (values.length == 2) {
                String merchantId = values[0];
                String secretKey = values[1];

                return merchantService.getMerchant(merchantId)
                        .flatMap(merchant -> {
                            String storedSecretKey = new String(Base64.getDecoder().decode(merchant.getSecretKey()), StandardCharsets.UTF_8);
                            if (secretKey.equals(storedSecretKey)) {
                                Authentication authentication = new UsernamePasswordAuthenticationToken(merchantId, secretKey);
                                return chain.filter(exchange)
                                        .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
                            } else {
                                return chain.filter(exchange);
                            }
                        })
                        .switchIfEmpty(chain.filter(exchange));
            }
        }
        return chain.filter(exchange);
    }
}






