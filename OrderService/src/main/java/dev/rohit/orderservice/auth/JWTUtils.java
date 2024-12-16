package dev.rohit.orderservice.auth;

import dev.rohit.orderservice.exceptions.AuthenticationTokenNotFoundException;
import dev.rohit.orderservice.exceptions.AuthenticationClaimNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Map;

public class JWTUtils {

    private JWTUtils() {
    }

    public static String extractToken(Authentication authentication) {
        if(authentication instanceof JwtAuthenticationToken) {
            return ((JwtAuthenticationToken) authentication).getToken().getTokenValue();
        }else{
            throw new AuthenticationTokenNotFoundException("Token not found in the authentication object");
        }
    }

    private static Map<String,Object> extractClaims(Authentication authentication) {
        if(authentication instanceof JwtAuthenticationToken) {
            return ((JwtAuthenticationToken) authentication).getTokenAttributes();
        }else{
            throw new AuthenticationTokenNotFoundException("Token attributes not found in the authentication object");
        }
    }

    public static Long extractUserId(Authentication authentication) {
        Map<String, Object> claims = JWTUtils.extractClaims(authentication);

        if(claims == null || claims.get("userId") == null) {
            throw new AuthenticationClaimNotFoundException("User id not found in the token");
        }

        if(claims.get("userId") instanceof Integer) {
            return Long.valueOf((Integer) claims.get("userId"));
        }   else{
            throw new AuthenticationClaimNotFoundException("User id type mismatch in the token");
        }


    }
}
