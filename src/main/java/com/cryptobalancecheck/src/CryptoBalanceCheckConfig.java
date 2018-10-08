package com.cryptobalancecheck.src;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan("com.cryptobalancecheck")
public class CryptoBalanceCheckConfig {

    public static void main(String[] args) {
        SpringApplication.run(CryptoBalanceCheckConfig.class, args);
    }
}
