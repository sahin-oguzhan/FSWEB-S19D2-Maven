package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.AccountResponse;
import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.AccountService;
import com.workintech.s18d4.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    private AccountService accountService;
    private CustomerService customerService;

    @Autowired
    public AccountController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }

    @GetMapping
    public List<AccountResponse> findAll() {
        return accountService.findAll().stream().map(account -> new AccountResponse
                (account.getId(), account.getAccountName(), account.getMoneyAmount(), account.getCustomer())).toList();
    }

    @GetMapping("/{id}")
    public AccountResponse find(@PathVariable Long id) {
        Account account = accountService.find(id);
        return new AccountResponse(account.getId(), account.getAccountName(), account.getMoneyAmount(), account.getCustomer());
    }

    @PostMapping("/{customerId}")
    public AccountResponse addCustomer(@RequestBody Account account, @PathVariable Long customerId) {
        Customer customer = customerService.find(customerId);
        account.setCustomer(customer);
        customer.getAccounts().add(account);
        accountService.save(account);
        return new AccountResponse(account.getId(), account.getAccountName(), account.getMoneyAmount(), account.getCustomer());
    }

    @PutMapping("/{customerId}")
    public AccountResponse update(@RequestBody Account account, @PathVariable Long customerId) {
        Customer customer = customerService.find(customerId);
        account.setCustomer(customer);
        customer.getAccounts().add(account);
        customerService.update(customer, customerId);
        return new AccountResponse(account.getId(), account.getAccountName(), account.getMoneyAmount(), account.getCustomer());
    }

    @DeleteMapping("/{id}")
    public AccountResponse delete(@PathVariable Long id) {
        Account account = accountService.find(id);
        accountService.delete(account.getId());
        return new AccountResponse(account.getId(), account.getAccountName(), account.getMoneyAmount(), account.getCustomer());
    }

}
