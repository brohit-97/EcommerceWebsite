package dev.rohit.userservice.auth.dtos;

public class OAuth2UserDto {
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String postLogoutRedirectUri;

    public OAuth2UserDto() {
    }

    public OAuth2UserDto(String clientId, String clientSecret, String redirectUri, String postLogoutRedirectUri) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.postLogoutRedirectUri = postLogoutRedirectUri;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getPostLogoutRedirectUri() {
        return postLogoutRedirectUri;
    }

}
