package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Account;
import com.example.demo.Model.Transact.Deposit;
import com.example.demo.Model.Transact.Transaction;
import com.example.demo.Model.Transact.Transfer;
import com.example.demo.Model.Transact.Withdraw;
import com.example.demo.Service.Account.AccountService;
import com.example.demo.Service.Account.AccountSpecification;
import com.example.demo.Service.Bank.BankSpecification;
import com.example.demo.Service.Transaction.WithdrawService;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/account")

public class AccountController {

    @Autowired
    private BankSpecification bankSpecification;

    @Autowired
    private AccountSpecification accountSpecification;

    @PostMapping("/create/{bankName}")
    public String createAccount(@PathVariable String bankName, @RequestBody Account account) {
        return bankSpecification.createAccount(bankName, account);
    }

    @PostMapping("/deposit")
    public String deposit(@RequestBody Deposit transaction) {
        return bankSpecification.performTransaction(transaction);
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestBody Withdraw transaction) {
        return bankSpecification.performTransaction(transaction);
    }

    @PostMapping("/transfer")
    public String transfer(@RequestBody Transfer transaction) {
        return bankSpecification.performTransaction(transaction);
    }

   

}
