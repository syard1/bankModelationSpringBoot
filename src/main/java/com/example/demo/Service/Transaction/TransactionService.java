package com.example.demo.Service.Transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.TransactionRepoasitory;
import com.example.demo.Model.Account;
import com.example.demo.Model.Transact.Deposit;
import com.example.demo.Model.Transact.Transaction;
import com.example.demo.Service.Account.AccountService;
@Service
public abstract class TransactionService {
    
    
     @Autowired
    protected AccountService accountService;

    @Autowired
    protected TransactionRepoasitory transactionRepoasitory;

     public boolean canPerformTransaction(double availabelAmount,double amount) {
        return (availabelAmount - amount >= 0) ? true : false;
    }

    
    public abstract List<? extends Transaction> getAllTransactions(int id);

    public abstract String execute(Transaction transaction, double availabelBalance);

}