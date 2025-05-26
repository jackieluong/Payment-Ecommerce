package com.GeekUp.Shop.service.user;

import com.GeekUp.Shop.entity.Account;

public interface IAccountService {
    Account getAccountById(String accountId);

    Account getAccountByEmail(String email);
}
