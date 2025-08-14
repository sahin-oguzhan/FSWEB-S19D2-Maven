package com.workintech.s18d4.service;

import com.workintech.s18d4.repository.AddressRepository;
import com.workintech.s18d4.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address find(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address update(Long id, Address address) {
        Address willUpdate = find(id);
        willUpdate.setId(address.getId());
        willUpdate.setNo(address.getNo());
        willUpdate.setCity(address.getCity());
        willUpdate.setStreet(address.getStreet());
        willUpdate.setCountry(address.getCountry());
        willUpdate.setCustomer(address.getCustomer());
        willUpdate.setDescription(address.getDescription());
        return addressRepository.save(willUpdate);
    }

    @Override
    public Address delete(Long id) {
        Address willRemove = find(id);
        addressRepository.delete(willRemove);
        return willRemove;
    }
}
