package com.example.demo.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Transact.Deposit;
import com.example.demo.Model.Transact.Transaction;
import com.example.demo.Model.Transact.Transfer;
import com.example.demo.Model.Transact.Withdraw;





public interface TransactionRepoasitory extends JpaRepository<Transaction,Integer>{
    
    List<Transfer> findAllByidOriginTransaction(Integer id);

}
