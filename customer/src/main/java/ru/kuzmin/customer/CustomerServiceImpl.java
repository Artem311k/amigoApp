package ru.kuzmin.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kuzmin.amqp.RabbitMQMessageProducer;
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
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    @Override
    public void registerCustomer(CustomerRegistrationRequest request) {

        log.info("new customer registration {}", request);
        Customer newCustomer = new Customer();
        FraudCheckResponse fraudCheckResponse = new FraudCheckResponse(false);
        boolean isAlreadyRegistered = false;
        if (customerRepository.getCustomersByEmail(request.email()).isPresent()) {
            isAlreadyRegistered = true;
            log.info("Email " + request.email() + " already registered");

        } else {
            // todo check if email valid
            // todo check if email not taken
            fraudCheckResponse = fraudClient.isFraudster(request.email());

            if (!fraudCheckResponse.isFraudster()) {
                newCustomer.setFirstName(request.firstname());
                newCustomer.setLastName(request.lastname());
                newCustomer.setEmail(request.email());
                customerRepository.saveAndFlush(newCustomer);
                log.info("new customer " + request.firstname() + " registered, " + " id is " + newCustomer.getId());
            } else {
                log.info("USER " + request.firstname() + " WAS NOT REGISTERED BECAUSE OF A FRAUDSTER");
            }

        }

        NotificationRequest notificationRequest = new NotificationRequest(request.email(),
                request.firstname(), fraudCheckResponse.isFraudster(), isAlreadyRegistered);

//        notificationClient.sendNotification(notificationRequest);

        rabbitMQMessageProducer.publish(notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key");
    }

}

