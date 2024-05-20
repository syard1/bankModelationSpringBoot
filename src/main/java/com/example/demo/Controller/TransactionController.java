package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Transact.Deposit;
import com.example.demo.Model.Transact.Transaction;
import com.example.demo.Service.Transaction.DepositService;
import com.example.demo.Service.Transaction.TransactionService;
import com.example.demo.Service.Transaction.TransferService;
import com.example.demo.Service.Transaction.WithdrawService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    

   private final List<TransactionService> transactions;

    @Autowired
    public TransactionController(List<TransactionService> transactions){
        this.transactions = transactions;
    }


    @GetMapping("/{id}")
    public List<? extends Transaction> getAllTransaction(@PathVariable int id) {
        List<Transaction> transactionList = new ArrayList<>();
        transactions.forEach(transactionService -> {
            List<? extends Transaction> specificTransactionType = transactionService.getAllTransactions(id);
            transactionList.addAll(specificTransactionType);
        });
        return transactionList;
    }
}
