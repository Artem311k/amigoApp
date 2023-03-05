package ru.kuzmin.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kuzmin.clients.fraud.FraudCheckResponse;
import ru.kuzmin.clients.fraud.FraudClient;
import ru.kuzmin.clients.notification.NotificationClient;
import ru.kuzmin.clients.notification.NotificationRequest;
import ru.kuzmin.clients.notification.NotificationResponse;


@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    @Override
    public void registerCustomer(CustomerRegistrationRequest request) {

        log.info("new customer registration {}", request);
        Customer customer = Customer.builder()
                .firstName(request.firstname())
                .lastName(request.lastname())
                .email(request.email())
                .build();
        // todo check if email valid
        // todo check if email not taken

        FraudCheckResponse fraudCheckResponse =
                fraudClient.isFraudster(request.email());


        notificationClient.sendNotification(new NotificationRequest(request.email(),
                request.firstname(), fraudCheckResponse.isFraudster()));

        if (fraudCheckResponse.isFraudster()) {
            log.info("USER " + customer.getFirstName() + " WAS NOT REGISTERED BECAUSE OF A FRAUDSTER");
            return;
        }
        customerRepository.saveAndFlush(customer);
        log.info("new customer" + customer.getFirstName() + "registered, " + " id is " + customer.getId());

    }

}

