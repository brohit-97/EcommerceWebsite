package dev.rohit.userservice.dtos;

import java.util.Set;

public class RoleRequestDto {
    private Set<Long> roleIds;

    public RoleRequestDto() {
    }

    public RoleRequestDto(Set<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public Set<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Set<Long> roleIds) {
        this.roleIds = roleIds;
    }

}
