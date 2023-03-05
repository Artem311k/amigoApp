package ru.kuzmin.fraud;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class FraudCheckHistoryServiceImpl
        implements FraudCheckHistoryService {

    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    @Override
    public boolean isFraudulentCustomer(String email) {
        log.info("checking is a fraud email {}", email);
        Optional<FraudCheckHistory> fraudCheckHistory =
                fraudCheckHistoryRepository.getFraudCheckHistoryByCustomerEmail(email);
        if(fraudCheckHistory.isPresent()) {
            log.info("Customer with id " + email + " already in database and is a fraudster");
            return true;
        }
        boolean isFraudster = checkIfFraudster();
        fraudCheckHistoryRepository.saveAndFlush(
                FraudCheckHistory.builder()
                        .createdAt(LocalDateTime.now())
                        .isFraudster(isFraudster)
                        .customerEmail(email)
                        .build());
        log.info("saving " + email + " to fraud_check database");
        if (isFraudster) {
            log.info("Customer with id " + email + " is a fraudster");
        } else {
            log.info("Customer with id " + email + " not a fraudster");
        }
        return isFraudster;

    }

    private boolean checkIfFraudster() {
        boolean isFraudster;
        Random random = new Random(System.currentTimeMillis());
        int dice = random.nextInt(10);
        isFraudster = dice % 2 == 0;
        return isFraudster;
    }
}
