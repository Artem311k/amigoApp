package ru.kuzmin.notification;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Notification {

    public Notification(boolean isFraudster,
                        boolean isAlreadyRegistered,
                        String customerName,
                        String email) {
        this.customerName = customerName;
        this.isFraudster = isFraudster;
        this.isFraudster = isFraudster;
        this.sentAt = LocalDateTime.now();
        this.sentToEmail = email;
        createMessage(isFraudster, isAlreadyRegistered);

    }


    @Id
    @GenericGenerator(
            name = "notification_id_sequence",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "notification_id_sequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notification_id_sequence")
    private Integer id;

    private String message;

    private boolean isAlreadyRegistered;
    private LocalDateTime sentAt;

    private String sentToEmail;

    private String customerName;

    private boolean isFraudster;


    private void createMessage(boolean isFraudster, boolean isAlreadyRegistered) {
        if (isAlreadyRegistered) {
            this.message = ("Hello, " + this.customerName + "! You are already registered!");
        } else {
            if (isFraudster) {
                this.message = ("Hello, " + this.customerName + "!. " +
                        "You are a fraudster so go away! Sent to email:" + sentToEmail);
            } else {
                this.message = ("Hello, " + this.customerName + "!. " +
                        "Welcome to service! Sent to email:" + sentToEmail);
            }
        }


    }
}
