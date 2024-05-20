package com.example.demo.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Account;
import com.example.demo.Model.Bank;

public interface BankRepository extends JpaRepository<Bank,String>{
    
}
