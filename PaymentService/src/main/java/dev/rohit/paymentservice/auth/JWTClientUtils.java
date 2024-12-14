package dev.rohit.paymentservice.auth;

import com.nimbusds.jose.Header;
import dev.rohit.paymentservice.configs.RestTemplateConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class JWTClientUtils {

    @Value("${client.secret}")
    private String clientSecret;

    @Value("${client.id}")
    private String clientId;

    @Value("${token.url}")
    private String tokenUrl;

    @Autowired
    RestTemplate restTemplate;


    public String getAccessToken() {
        // logic to get access token
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(clientId, clientSecret);
        MultiValueMap<String,String> body = new LinkedMultiValueMap<>();
        body.add("grant_type","client_credentials");
        HttpEntity<MultiValueMap<String,String>> entity =  new HttpEntity<>(body,headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, entity, Map.class);

        if(response.getStatusCode() == HttpStatus.OK) {
            if(response.getBody() == null) {
                return null;
            }
            return (String) response.getBody().get("access_token");
        }else{
            throw new RuntimeException("Error while getting access token");
        }
    }
}
