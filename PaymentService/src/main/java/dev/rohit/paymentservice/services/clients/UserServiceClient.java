package dev.rohit.paymentservice.services.clients;

import dev.rohit.paymentservice.dtos.OrderDto;
import dev.rohit.paymentservice.dtos.UserDto;

public interface UserServiceClient {
    UserDto getUser(Long userId);

}
