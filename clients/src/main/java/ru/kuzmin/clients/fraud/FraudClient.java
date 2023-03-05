package ru.kuzmin.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("FRAUD")
public interface FraudClient {
    @GetMapping("/api/v1/fraud-check/{email}")
    FraudCheckResponse isFraudster(
            @PathVariable("email") String email);

}
