package dev.rohit.userservice.services;

import dev.rohit.userservice.dtos.RoleRequestDto;
import dev.rohit.userservice.dtos.RoleResponseDto;
import dev.rohit.userservice.models.Role;

import java.util.List;

public interface RoleService {
    RoleResponseDto createRole(String role);

    Role updateRole(Role role, Long roleId);

    Role getRoleById(Long roleId);

    List<RoleResponseDto> getAllRoles();

    void deleteRole(Long roleId);

    void addRoleToUser(Long userId, Long roleId);

    void removeRoleFromUser(Long userId, Long roleId);
}
