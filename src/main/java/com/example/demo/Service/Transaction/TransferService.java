package com.example.demo.Service.Transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.AccountRepository;
import com.example.demo.Dao.DepositRepository;
import com.example.demo.Dao.TransactionRepoasitory;
import com.example.demo.Dao.TransferRepsository;
import com.example.demo.Model.Account;
import com.example.demo.Model.Transact.Transaction;
import com.example.demo.Model.Transact.Transfer;
import com.example.demo.Service.Account.AccountService;

@Service
public class TransferService extends TransactionService {


     @Autowired
    private TransferRepsository transferRepsository;

   

    @Override
    public String execute(Transaction transaction, double availabelBalance) {
        accountService.updateAccountBalance(transaction.getIdOriginTransaction(), availabelBalance - transaction.getAmount());
        accountService.updateAccountBalance(((Transfer) transaction ).getIdRealiseTransation(), accountService.getAccountBalance(((Transfer) transaction ).getIdRealiseTransation()) + transaction.getAmount());
        transactionRepoasitory.save(transaction);
        return "Transfer Successfully";
    }



    @Override
    public List<Transfer> getAllTransactions(int id) {
        // TODO Auto-generated method stub
     return  transferRepsository.findAllByidOriginTransaction(id);
    }


  

}
