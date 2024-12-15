package dev.rohit.notificationservice.dtos;

public class EmailEventDto {
    private String recipient;
    private String subject;
    private String body;
    private String sender;

    public EmailEventDto() {
    }

    public EmailEventDto(String recipient, String subject, String body, String sender) {
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSender() {
        return sender;
    }

}
