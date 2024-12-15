package dev.rohit.paymentservice.notification;

import dev.rohit.paymentservice.dtos.EmailNotificationDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService {

    private KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${support.email}")
    private String supportEmail;

    public EmailNotificationService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEmail(String email, String subject, String message, String topic) {
        EmailNotificationDto emailNotificationDto = EmailNotificationDto.builder()
                                                        .senderEmail(supportEmail)
                                                        .recipientEmail(email)
                                                        .subject(subject)
                                                        .message(message).build();
        kafkaTemplate.send(topic, emailNotificationDto);
    }
}
