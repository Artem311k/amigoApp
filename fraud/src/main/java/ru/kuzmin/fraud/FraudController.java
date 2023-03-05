package ru.kuzmin.fraud;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.kuzmin.clients.fraud.FraudCheckResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/fraud-check")
public class FraudController {

    private final FraudCheckHistoryService fraudCheckHistoryService;

    @GetMapping("/{email}")
    public FraudCheckResponse isFraudster(
            @PathVariable("email") String email) {
        boolean response = fraudCheckHistoryService.isFraudulentCustomer(email);
        return new FraudCheckResponse(response);
    }

}
