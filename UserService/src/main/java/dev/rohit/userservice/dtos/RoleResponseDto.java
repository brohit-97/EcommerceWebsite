package dev.rohit.userservice.dtos;

import dev.rohit.userservice.models.Role;

public class RoleResponseDto {

    private Long id;
    private String role;

    public RoleResponseDto() {
    }

    public RoleResponseDto(Long id, String role) {
        this.id = id;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static RoleResponseDto fromRole(Role role) {
        return new RoleResponseDto(role.getId(), role.getName());
    }
}
