package com.umaraliev.FakePaymentProvider.security;

import com.umaraliev.FakePaymentProvider.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@RequiredArgsConstructor
public class MerchantAuthenticationFilter implements WebFilter {


    private final  MerchantService merchantService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String merchantId = exchange.getRequest().getHeaders().getFirst("merchantId");
        String secretKey = exchange.getRequest().getHeaders().getFirst("secretKey");

        if (merchantId != null && secretKey != null) {
            return merchantService.getMerchant(merchantId)
                    .filter(merchant -> secretKey.equals(merchant.getSecretKey()))
                    .flatMap(merchant -> {
                        Authentication authentication = new UsernamePasswordAuthenticationToken(merchantId, secretKey);
                        return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
                    })
                    .switchIfEmpty(chain.filter(exchange));
        } else {
            return chain.filter(exchange);
        }
    }
}



