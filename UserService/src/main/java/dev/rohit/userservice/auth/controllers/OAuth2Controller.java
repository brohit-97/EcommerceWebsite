package dev.rohit.userservice.auth.controllers;

import dev.rohit.userservice.auth.services.OAuth2Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.rohit.userservice.auth.dtos.OAuth2UserDto;

@RestController
@RequestMapping("/oauth2")
public class OAuth2Controller {

    private final OAuth2Service oAuth2Service;

    public OAuth2Controller(OAuth2Service oAuth2Service) {
        this.oAuth2Service = oAuth2Service;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOAuth2User(@RequestBody OAuth2UserDto request) {
        try {
            oAuth2Service.createNewClient(request.getClientId(), request.getClientSecret(),
                    request.getRedirectUri(), request.getPostLogoutRedirectUri());
            return ResponseEntity.ok("Success");
        }catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }
}
