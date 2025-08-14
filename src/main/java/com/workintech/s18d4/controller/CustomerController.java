package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.CustomerResponse;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerResponse> findAll(){
        return customerService.findAll().stream().map(account -> new CustomerResponse(account.getId(),  account.getEmail(),  account.getSalary())).toList();
    }

    @GetMapping("/{id}")
    public CustomerResponse find(@PathVariable Long id){
        Customer customer = customerService.find(id);
        return new CustomerResponse(customer.getId(),customer.getEmail(), customer.getSalary());
    }

    @PostMapping
    public CustomerResponse save(@RequestBody Customer customer){
        Customer willSave = customerService.save(customer);
        return new CustomerResponse(willSave.getId(), willSave.getEmail(), willSave.getSalary());
    }

    @PutMapping("/{id}")
    public CustomerResponse update(@RequestBody Customer customer,@PathVariable Long id){
        Customer willUpdate = customerService.update(customer, id);
        return new CustomerResponse(willUpdate.getId(),willUpdate.getEmail(), willUpdate.getSalary());
    }

    @DeleteMapping("/{id}")
    public CustomerResponse delete(@PathVariable Long id){
        Customer willDelete = customerService.find(id);
        customerService.delete(willDelete.getId());
        return new CustomerResponse(willDelete.getId(), willDelete.getEmail(), willDelete.getSalary());
    }
}
