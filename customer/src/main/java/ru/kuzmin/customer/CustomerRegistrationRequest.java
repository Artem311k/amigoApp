package ru.kuzmin.customer;

public record CustomerRegistrationRequest(
        String firstname,
        String lastname,
        String email) {

}
