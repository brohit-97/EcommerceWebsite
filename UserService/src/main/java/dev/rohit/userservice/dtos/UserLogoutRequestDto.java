package dev.rohit.userservice.dtos;

public class UserLogoutRequestDto {
    private Long userId;
    private String token;

    public UserLogoutRequestDto() {
    }

    public UserLogoutRequestDto(Long userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
