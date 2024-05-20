package com.example.demo.Service.Account;

import java.util.List;

import com.example.demo.Model.Account;

public interface AccountSpecification {


    List<Account> getAccountsByBankName(String name);
}
