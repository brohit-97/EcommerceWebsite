package dev.rohit.notificationservice.components;

import dev.rohit.notificationservice.config.Constants;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailComponent {

    public static void sendEmail(Session session, String sender, String recipient, String subject, String body) {
        MimeMessage message = new MimeMessage(session);
        try {
            message.addHeader("Content-type", "text/HTML; charset=UTF-8");
            message.addHeader("format", "flowed");
            message.addHeader("Content-Transfer-Encoding", "8bit");

            message.setFrom(new InternetAddress(sender, Constants.EMAIL_TEAM));
            message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(recipient));
            message.setReplyTo(InternetAddress.parse(sender, false));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
            System.out.println("Email processed successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
