package dev.rohit.userservice.services;

import dev.rohit.userservice.exceptions.NotFoundException;
import dev.rohit.userservice.models.Address;
import dev.rohit.userservice.models.User;
import dev.rohit.userservice.repositories.AddressRepository;
import dev.rohit.userservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public AddressServiceImpl(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> getAllAddressesByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        return addressRepository.findAllByUser(user);
    }

    @Override
    public Address updateAddress(Address address) {
        Address savedAddress = addressRepository.findById(address.getId()).orElseThrow(() -> new NotFoundException("Address not found"));
        savedAddress.setCity(address.getCity());
        savedAddress.setCountry(address.getCountry());
        savedAddress.setStreet(address.getStreet());
        savedAddress.setZipCode(address.getZipCode());
        savedAddress.setPhone(address.getPhone());
        savedAddress.setCountry(address.getCountry());
        return addressRepository.save(savedAddress);
    }


    @Override
    public Address addAddress(Address address, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        address.setUser(user);
        return addressRepository.save(address);
    }
}
