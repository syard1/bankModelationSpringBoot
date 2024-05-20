package com.example.demo.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.Model.Account;

public interface AccountRepository extends JpaRepository<Account,Integer>{
    
    boolean existsByIdAndBank_BankName(Integer id, String bankName);
    List<Account> findByBankBankName(String bankName);

}
