package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Account;
import com.example.demo.Model.Bank;
import com.example.demo.Service.Bank.BankSpecification;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/bank")
public class BankController {
    
    @Autowired
    BankSpecification bankSpecification;

    @PostMapping("/createbank")
    public ResponseEntity<String> createBank(@RequestBody Bank bank){
        String response = bankSpecification.createBank(bank);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/changeFeeInUse/{fee}/{bankName}")
    public String changeFeeInUse(@PathVariable String fee, @PathVariable String bankName){
        System.out.println("Fee "+ fee);
       return bankSpecification.changeFeeType(fee, bankName);
        
    }


    @GetMapping("/bankTransferAmount/{bankName}")
    public String transferAmount(@PathVariable String bankName) {
       return bankSpecification.getTrnsferAmount(bankName);
    }
    
     @GetMapping("/allAccounts/{bankName}")
    public List<Account> getMethodName(@PathVariable String bankName) {
        return bankSpecification.getAccountsByBankName(bankName);
    }




}
