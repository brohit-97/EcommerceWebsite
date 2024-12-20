package dev.rohit.productservice.clients.internal.dto;

import java.util.Set;

public class UserDto {

    private String name;
    private String email;
    private Set<RoleDto> roles;

    public UserDto() {
    }

    public UserDto(String name, String email, Set<RoleDto> roles) {
        this.name = name;
        this.email = email;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }

}
