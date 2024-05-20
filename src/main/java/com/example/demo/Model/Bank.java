package com.example.demo.Model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Bank {
    
    @Id
    private String bankName;
    private double transactionFlat;
    private double transactionPercentage;
    private double totalTransactionFee;
    private double totalTransferAmount;
    private String feeInUse;


    public Bank(){

    }

    public Bank(String bankName, double transactionFlat,double transactionPercentage,String feeInUse){
        this.bankName = bankName;
        this.transactionFlat = transactionFlat;
        this.transactionPercentage = transactionPercentage;
        this.feeInUse = feeInUse;
    }


    @OneToMany(mappedBy = "bank")
    private List<Account> accounts;
    
}
