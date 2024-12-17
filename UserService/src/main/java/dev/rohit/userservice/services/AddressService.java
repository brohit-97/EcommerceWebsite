package dev.rohit.userservice.services;

import dev.rohit.userservice.models.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAllAddressesByUser(Long userId);
    Address updateAddress(Address address);
    Address addAddress(Address address,Long userId);
}
