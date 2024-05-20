package com.example.demo.Service.Transaction;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.DepositRepository;
import com.example.demo.Dao.WithdrawRepository;
import com.example.demo.Model.Account;
import com.example.demo.Model.Transact.Transaction;

@Service("withdrawService")
public class WithdrawService extends TransactionService {
    

     @Autowired
    private WithdrawRepository withdrawRepository;

   

    @Override
    public String execute(Transaction transaction, double availabelBalance) {
        accountService.updateAccountBalance(transaction.getIdOriginTransaction(), availabelBalance -transaction.getAmount());
        transactionRepoasitory.save(transaction);
        return "Withdraw successfully";


    }



    @Override
    public List<? extends Transaction> getAllTransactions(int id) {
       return withdrawRepository.findAllByidOriginTransaction(id);
    }
}
