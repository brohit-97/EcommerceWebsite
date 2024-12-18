package dev.rohit.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidateUserTokenRequestDto {
    private String token;
    private Long userId;

    public ValidateUserTokenRequestDto() {
    }

    public ValidateUserTokenRequestDto(String token, Long userId) {
        this.token = token;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
