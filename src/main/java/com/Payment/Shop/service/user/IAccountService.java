package com.Payment.Shop.service.user;

import com.Payment.Shop.entity.Account;

public interface IAccountService {
    Account getAccountById(String accountId);

    Account getAccountByEmail(String email);
}
