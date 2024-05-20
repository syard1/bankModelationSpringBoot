package com.example.demo.Service.Bank;

import java.util.List;

import com.example.demo.Model.Account;
import com.example.demo.Model.Bank;
import com.example.demo.Model.Transact.Transaction;

public interface BankSpecification {
    

    public String createBank(Bank bank);
    public String createAccount(String name,Account account);   
    public String performTransaction(Transaction transaction);
    public String changeFeeType(String feeTyp,String bankName);
    public String getTrnsferAmount(String name);
    public List<Account> getAccountsByBankName( String bankName);

}
