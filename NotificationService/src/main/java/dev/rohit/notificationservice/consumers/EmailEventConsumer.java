package dev.rohit.notificationservice.consumers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.rohit.notificationservice.components.EmailComponent;
import dev.rohit.notificationservice.dtos.EmailEventDto;
import jakarta.mail.Authenticator;
import jakarta.mail.Session;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


import java.util.Properties;

@Service
public class EmailEventConsumer {

    private final Properties mailProperties;
    private final Authenticator authenticator;
    private final ObjectMapper objectMapper;

    public EmailEventConsumer(Properties mailProperties, Authenticator authenticator, ObjectMapper objectMapper) {
        this.mailProperties = mailProperties;
        this.authenticator = authenticator;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "${topic.email}")
    public void handleSendEmailEvent(String message) throws JsonProcessingException {
        EmailEventDto event = objectMapper.readValue(message, EmailEventDto.class);
        Session mailSession = Session.getInstance(mailProperties, authenticator);

        EmailComponent.sendEmail(mailSession, event.getSender(), event.getRecipient(), event.getSubject(), event.getBody());
    }
}