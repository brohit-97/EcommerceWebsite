package dev.rohit.userservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Address extends BaseModel {
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String phone;
    @ManyToOne
    private User user;
}
