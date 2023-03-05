package ru.kuzmin.fraud;

public interface FraudCheckHistoryService {

    boolean isFraudulentCustomer(String email);

}
