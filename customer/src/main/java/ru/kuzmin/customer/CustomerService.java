package ru.kuzmin.customer;

import org.springframework.stereotype.Service;


public interface CustomerService {

    void registerCustomer(CustomerRegistrationRequest request);
}
