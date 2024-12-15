package dev.rohit.notificationservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class MailPropertiesConfig {

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private String port;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String starttls;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String auth;

    @Bean
    public Properties mailProperties() {
        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.host", host);
        mailProperties.put("mail.smtp.port", port);
        mailProperties.put("mail.smtp.auth", auth);
        mailProperties.put("mail.smtp.starttls.enable", starttls);
        return mailProperties;
    }


}
