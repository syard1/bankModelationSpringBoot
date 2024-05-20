package com.example.demo.Service.Transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.DepositRepository;
import com.example.demo.Model.Account;
import com.example.demo.Model.Transact.Deposit;
import com.example.demo.Model.Transact.Transaction;
@Service("depositService")
public class DepositService extends TransactionService{

    @Autowired
    private DepositRepository depositRepository;

    
    public DepositService(){

    }

    @Override
    public String execute(Transaction transaction, double availabelBalance) {
   accountService.updateAccountBalance(transaction.getIdOriginTransaction(), availabelBalance + transaction.getAmount());
   transactionRepoasitory.save(transaction);
     return "Deposit successfully";
    }
 

    public boolean canPerformTransaction(double availabelAmount, double amount) {
        return (availabelAmount+ amount >= 0) ? true : false;
    }

    @Override
    public List<Deposit> getAllTransactions(int id) {
     return depositRepository.findAllByidOriginTransaction(id);
    }
    

   


}
