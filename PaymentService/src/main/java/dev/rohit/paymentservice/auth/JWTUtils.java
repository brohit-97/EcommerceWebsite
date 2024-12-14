package dev.rohit.paymentservice.auth;

import dev.rohit.paymentservice.exceptions.AuthenticatonClaimNotFoundException;
import dev.rohit.paymentservice.exceptions.AuthenticatonTokenNotFoundException;
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
           throw new AuthenticatonTokenNotFoundException("Token not found in the authentication object");
       }
    }

    private static Map<String,Object> extractClaims(Authentication authentication) {
        if(authentication instanceof JwtAuthenticationToken) {
            return ((JwtAuthenticationToken) authentication).getTokenAttributes();
        }else{
            throw new AuthenticatonTokenNotFoundException("Token attributes not found in the authentication object");
        }
    }

    public static Long extractUserId(Authentication authentication) {
        Map<String, Object> claims = JWTUtils.extractClaims(authentication);

        if(claims == null || claims.get("userId") == null) {
            throw new AuthenticatonClaimNotFoundException("User id not found in the token");
        }

        if(claims.get("userId") instanceof Integer) {
            return Long.valueOf((Integer) claims.get("userId"));
        }   else{
            throw new AuthenticatonClaimNotFoundException("User id type mismatch in the token");
        }


    }
}
