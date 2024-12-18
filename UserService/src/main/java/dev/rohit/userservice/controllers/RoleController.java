package dev.rohit.userservice.controllers;

import dev.rohit.userservice.dtos.RoleRequestDto;
import dev.rohit.userservice.dtos.RoleResponseDto;
import dev.rohit.userservice.models.Role;
import dev.rohit.userservice.services.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleResponseDto>> getRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<RoleResponseDto> getRoleById(@PathVariable Long roleId) {
        return ResponseEntity.ok(RoleResponseDto.fromRole(roleService.getRoleById(roleId)));
    }

    @PostMapping
    public ResponseEntity<RoleResponseDto> createRole(@RequestBody String role) {
        return ResponseEntity.ok(roleService.createRole(role));
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<RoleResponseDto> updateRole(@RequestBody Role roleRequestDto, @PathVariable Long roleId) {
        return ResponseEntity.ok(RoleResponseDto.fromRole(roleService.updateRole(roleRequestDto, roleId)));
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long roleId) {
        roleService.deleteRole(roleId);
        return ResponseEntity.ok().build();
    }

}
