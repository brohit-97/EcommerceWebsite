package dev.rohit.userservice.services;

import dev.rohit.userservice.dtos.RoleResponseDto;
import dev.rohit.userservice.exceptions.NotFoundException;
import dev.rohit.userservice.models.Role;
import dev.rohit.userservice.models.User;
import dev.rohit.userservice.repositories.RoleRepository;
import dev.rohit.userservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public RoleServiceImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }


    @Override
    public RoleResponseDto createRole(String role) {
        Role newRole = new Role();
        newRole.setName(role);
        roleRepository.save(newRole);
        return RoleResponseDto.fromRole(newRole);
    }

    @Override
    public Role updateRole(Role role, Long roleId) {
        Role oldRole = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        oldRole.setName(role.getName());
        oldRole.setDescription(role.getDescription());
        return roleRepository.save(oldRole);
    }

    @Override
    public Role getRoleById(Long roleId) {
        return roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
    }

    @Override
    public List<RoleResponseDto> getAllRoles() {
        List<Role> roles =  roleRepository.findAll();
        if(roles.isEmpty()) {
            throw new NotFoundException("No roles found");
        }
        return roles.stream().map(RoleResponseDto::fromRole).toList();
    }

    @Override
    public void deleteRole(Long roleId) {
        roleRepository.deleteById(roleId);
    }

    @Override
    public void addRoleToUser(Long userId, Long roleId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        user.getRoles().add(role);
        userRepository.save(user);

    }

    @Override
    public void removeRoleFromUser(Long userId, Long roleId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        user.getRoles().remove(role);
        userRepository.save(user);
    }
}
