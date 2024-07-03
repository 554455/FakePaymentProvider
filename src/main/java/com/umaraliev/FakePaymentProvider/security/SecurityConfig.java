package com.umaraliev.FakePaymentProvider.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
@ComponentScan
public class SecurityConfig {

    private MerchantAuthenticationFilter merchantAuthenticationFilter;


    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) throws Exception {
        // Отключение CSRF, т.к. у нас нет форм
        http.csrf().disable()
                // Аутентификация не требуется
                .authorizeExchange()
                .anyExchange().permitAll();

        // Возвращение SecurityWebFilterChain
        return http.build();
    }

}

