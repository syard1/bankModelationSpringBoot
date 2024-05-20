package com.example.demo.Model;
import java.util.List;

import com.example.demo.Model.Transact.Deposit;
import com.example.demo.Model.Transact.Transaction;
import com.example.demo.Model.Transact.Transfer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Account {
    
    @Id
    private Integer id;
    private String userName;
    private double accountBalance;
    


    @ManyToOne
    @JsonIgnore 
    private Bank bank;


}
