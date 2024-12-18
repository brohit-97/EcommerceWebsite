package dev.rohit.userservice.dtos;


import dev.rohit.userservice.models.Role;
import dev.rohit.userservice.models.User;
import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String profilePic;
    private Set<Role> roles;

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setProfilePic(user.getProfilePic());
        userDto.setRoles(user.getRoles());
        return userDto;
    }
}
