package com.Payment.Shop.service.user;

import com.Payment.Shop.entity.Account;
import com.Payment.Shop.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BaseAccountService implements IAccountService {

    private final AccountRepository accountRepository;


    public BaseAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account getAccountById(String accountId) {
        return accountRepository.findById(Long.valueOf(accountId))
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
    }

    @Override
    public Account getAccountByEmail(String email) {
        return accountRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
    }
}
