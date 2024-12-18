package dev.rohit.userservice.dtos;

import dev.rohit.userservice.models.Address;
import dev.rohit.userservice.models.Role;
import dev.rohit.userservice.models.User;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class UserLoginResponseDto {
    private Long userId;
    private String email;
    private String name;
    private Set<Role> roles;
    private List<Address> addresses;

    public static UserLoginResponseDto fromUser(User user) {
        UserLoginResponseDto userLoginResponseDto = new UserLoginResponseDto();
        userLoginResponseDto.userId = user.getId();
        userLoginResponseDto.email = user.getEmail();
        userLoginResponseDto.name = user.getName();
        userLoginResponseDto.roles = user.getRoles();
        userLoginResponseDto.addresses = user.getAddresses();
        return userLoginResponseDto;
    }
}
