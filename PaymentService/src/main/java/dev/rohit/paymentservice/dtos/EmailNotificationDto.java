package dev.rohit.paymentservice.dtos;

import lombok.Builder;

@Builder
public class EmailNotificationDto {
    private String senderEmail;
    private String recipientEmail;
    private String subject;
    private String message;

    public EmailNotificationDto(String senderEmail, String recipientEmail, String subject, String message) {
        this.senderEmail = senderEmail;
        this.recipientEmail = recipientEmail;
        this.subject = subject;
        this.message = message;
    }

}
