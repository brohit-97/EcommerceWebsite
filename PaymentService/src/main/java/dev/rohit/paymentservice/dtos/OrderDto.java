package dev.rohit.paymentservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    private Long id;
    private Long customerId;
    private Double amount;
    private Long paymentId;
}
