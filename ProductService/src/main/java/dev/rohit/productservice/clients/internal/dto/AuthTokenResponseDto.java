package dev.rohit.productservice.clients.internal.dto;

public class AuthTokenResponseDto {
    UserDto userResponseDto;
    SessionStatus sessionStatus;

    public AuthTokenResponseDto() {
    }

    public AuthTokenResponseDto(UserDto userResponseDto, SessionStatus sessionStatus) {
        this.userResponseDto = userResponseDto;
        this.sessionStatus = sessionStatus;
    }

    public UserDto getUserResponseDto() {
        return userResponseDto;
    }

    public void setUserResponseDto(UserDto userResponseDto) {
        this.userResponseDto = userResponseDto;
    }

    public SessionStatus getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(SessionStatus sessionStatus) {
        this.sessionStatus = sessionStatus;
    }


}
