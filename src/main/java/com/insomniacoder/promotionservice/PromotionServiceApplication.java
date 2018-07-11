package com.insomniacoder.promotionservice;

import com.insomniacoder.promotionservice.promotion.entities.Promotion;
import com.insomniacoder.promotionservice.promotion.repositories.PromotionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class PromotionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PromotionServiceApplication.class, args);
    }

    private final String code100 = "FREE100";
    private final String code150 = "FREE150";
    private final String code200 = "FREE200";

    @Bean
    @LoadBalanced
    //LoadBalance find service from Eureka
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner initializeUserData(PromotionRepository promotionRepository) {

        return (args) -> {
            promotionRepository.save(Promotion.builder().code(code100).discount(100D).build());
            promotionRepository.save(Promotion.builder().code(code150).discount(150D).build());
            promotionRepository.save(Promotion.builder().code(code200).discount(200D).build());
        };
    }

}
