package dev.rohit.paymentservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Payment extends BaseModel {

    private Long orderId;
    private Double amount;
    private String currency;
    private String paymentLink;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

}
