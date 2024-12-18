package dev.rohit.userservice.auth.services;

import dev.rohit.userservice.auth.repositories.JpaRegisteredClientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OAuth2Service {

    private JpaRegisteredClientRepository jpaRegisteredClientRepository;

    private PasswordEncoder passwordEncoder;

    public OAuth2Service(JpaRegisteredClientRepository jpaRegisteredClientRepository, PasswordEncoder passwordEncoder) {
        this.jpaRegisteredClientRepository = jpaRegisteredClientRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public void createNewClient(String clientId, String clientSecret, String redirectUri, String postLogoutRedirectUri) {
        RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString()).clientId(clientId)
                .clientSecret(passwordEncoder.encode(clientSecret)).clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE).authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .redirectUri(redirectUri).postLogoutRedirectUri(postLogoutRedirectUri).scope(OidcScopes.OPENID).scope(OidcScopes.PROFILE)
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build()).build();

        jpaRegisteredClientRepository.save(oidcClient);
    }
}
