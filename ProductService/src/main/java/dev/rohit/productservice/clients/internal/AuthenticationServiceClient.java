package dev.rohit.productservice.clients.internal;

import dev.rohit.productservice.clients.internal.dto.AuthTokenRequestDto;
import dev.rohit.productservice.clients.internal.dto.AuthTokenResponseDto;
import dev.rohit.productservice.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationServiceClient {

    private RestTemplate restTemplate;

    @Value("${auth.service.url}")
    private String AUTH_SERVICE_URL;

    public AuthenticationServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AuthTokenResponseDto authenticate(String token, Long userId) {
        ResponseEntity<AuthTokenResponseDto> responseEntity = restTemplate.postForEntity(AUTH_SERVICE_URL + "/auth/validate", new AuthTokenRequestDto(token, userId), AuthTokenResponseDto.class);
        if(responseEntity.getStatusCode() != org.springframework.http.HttpStatus.OK) {
            throw new ServiceException("Authentication failed not valid token");
        }
        return responseEntity.getBody();
    }
}
