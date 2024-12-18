package dev.rohit.userservice.dtos;

import dev.rohit.userservice.models.SessionStatus;
import lombok.Data;

@Data
public class ValidateUserTokenResponseDto {
    UserDto userDto;
    SessionStatus sessionStatus;
}
