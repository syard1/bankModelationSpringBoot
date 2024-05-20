package com.example.demo.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Transact.Deposit;
import com.example.demo.Model.Transact.Transaction;
import com.example.demo.Model.Transact.Withdraw;





public interface DepositRepository extends JpaRepository<Deposit,Integer>{
    
    List< Deposit > findAllByidOriginTransaction(Integer id);

}
