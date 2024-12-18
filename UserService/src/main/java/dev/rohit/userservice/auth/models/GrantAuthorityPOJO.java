package dev.rohit.userservice.auth.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class GrantAuthorityPOJO implements GrantedAuthority {
    private String authority;
}
