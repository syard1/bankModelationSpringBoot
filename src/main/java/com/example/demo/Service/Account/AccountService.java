package com.example.demo.Service.Account;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.AccountRepository;
import com.example.demo.Model.Account;
import com.example.demo.Model.Bank;
import com.example.demo.Model.Transact.Transaction;
import com.example.demo.Service.Transaction.TransactionService;

@Service
public class AccountService implements AccountSpecification{

    @Autowired
    AccountRepository accountRepository;

    

    public void updateAccountBalance(Integer accountId, double newBalance) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setAccountBalance(newBalance);
        accountRepository.save(account);
    }

    public double getAccountBalance(Integer accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return account.getAccountBalance();
    }

    public double getTransactionFlatByAccountId(Integer accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        Bank bank = account.getBank();
        if (bank != null) {
            return bank.getTransactionFlat();
        } else {
            throw new RuntimeException("Bank not found for the given account");
        }
    }

    public double getTransactionPercentageByAccountId(Integer accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        Bank bank = account.getBank();
        if (bank != null) {
            return bank.getTransactionPercentage();
        } else {
            throw new RuntimeException("Bank not found for the given account");
        }
    }

    @Override
    public List<Account> getAccountsByBankName(String bankName) {
        return accountRepository.findByBankBankName(bankName);
    }
   
    

}
