package dev.rohit.paymentservice.services.clients;

import dev.rohit.paymentservice.dtos.UserDto;
import dev.rohit.paymentservice.exceptions.ServiceCallFailed;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceClientImpl implements UserServiceClient {

    private RestTemplate restTemplate;

    @Value("${service.user.url}")
    private String userServiceUrl;

    public UserServiceClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UserDto getUser(Long userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf("application/json"));
        ResponseEntity<UserDto> response = restTemplate.exchange(userServiceUrl + "/users/" + userId, HttpMethod.GET,new HttpEntity<>(headers), UserDto.class);
        if(response.getStatusCode() != HttpStatus.OK) {
            throw new ServiceCallFailed("Failed to get user from user service");
        }
        return response.getBody();
    }
}
