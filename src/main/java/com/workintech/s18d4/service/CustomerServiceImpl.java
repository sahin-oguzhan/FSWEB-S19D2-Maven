package com.workintech.s18d4.service;

import com.workintech.s18d4.repository.CustomerRepository;
import com.workintech.s18d4.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer find(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer, Long id) {
        Customer willUpdate = find(id);
        willUpdate.setId(customer.getId());
        willUpdate.setFirstName(customer.getFirstName());
        willUpdate.setLastName(customer.getLastName());
        willUpdate.setEmail(customer.getEmail());
        willUpdate.setAddress(customer.getAddress());
        willUpdate.setSalary(customer.getSalary());
        willUpdate.setAccounts(customer.getAccounts());

        return customerRepository.save(willUpdate);
    }

    @Override
    public Customer delete(Long id) {
        Customer willRemove = find(id);
        customerRepository.delete(willRemove);
        return willRemove;
    }
}
