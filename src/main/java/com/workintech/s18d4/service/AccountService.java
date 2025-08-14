package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Account;

import java.util.List;

public interface AccountService {
    List<Account> findAll();
    Account find(Long id);
    Account save(Account account);
    Account update(Account account);
    Account delete(Long id);
}
