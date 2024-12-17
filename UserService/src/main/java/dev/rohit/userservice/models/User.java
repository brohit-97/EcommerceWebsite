package dev.rohit.userservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class User extends BaseModel {
    private String name;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
    private boolean isActive;
    private boolean isDeleted;
    private String profilePic;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Address> addresses;

    public User() {
        roles = new HashSet<>();
        addresses = new ArrayList<>();
    }

    public User(String name, String email, String password, Set<Role> roles, boolean isActive, boolean isDeleted, String profilePic, List<Address> addresses) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.profilePic = profilePic;
        this.addresses = addresses;
    }
}
