package com.example.demo.Service.Bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.text.html.Option;

import com.example.demo.Dao.AccountRepository;
import com.example.demo.Dao.BankRepository;
import com.example.demo.Model.Account;
import com.example.demo.Model.Bank;
import com.example.demo.Model.Transact.Deposit;
import com.example.demo.Model.Transact.Transaction;
import com.example.demo.Model.Transact.Transfer;
import com.example.demo.Service.Account.AccountService;
import com.example.demo.Service.Transaction.DepositService;
import com.example.demo.Service.Transaction.TransactionService;
import com.example.demo.Service.Transaction.TransferService;
import com.example.demo.Service.Transaction.WithdrawService;

@Service
public class BankService implements BankSpecification {

    private final BankRepository bankRepository;
    private final AccountRepository accountRepository;
    private final AccountService accountService;
    private final Map<String, TransactionService> transactionServiceMap;

    @Autowired
    public BankService(
            BankRepository bankRepository,
            AccountRepository accountRepository,
            AccountService accountService,
            Map<String, TransactionService> transactionServiceMap) {
        this.bankRepository = bankRepository;
        this.accountRepository = accountRepository;
        this.accountService = accountService;
        this.transactionServiceMap = transactionServiceMap;
    }

    @Override
    public String createBank(Bank bank) {
        Optional<Bank> existingBank = bankRepository.findById(bank.getBankName());
        if (existingBank.isPresent()) {
            return "Bank already exists";
        } else {
            bankRepository.save(bank);
            return "Bank added successfully";
        }
    }

    @Override
    public String createAccount(String bankName, Account account) {
        Optional<Bank> bank = bankRepository.findById(bankName);
        if (!bank.isPresent()) {
            return "Bank does not exist";
        }
        boolean doesExist = accountRepository.existsByIdAndBank_BankName(account.getId(), bankName);
        if (doesExist) {
            return "This account already exist in this Bank";
        } else {
            account.setBank(bank.get());
            accountRepository.save(account);
            return "Account added succesfully";
        }

    }

    @Override
    public String changeFeeType(String feeType, String bankName) {
        Optional<Bank> bank = bankRepository.findById(bankName);
        if (bank.isPresent() && (feeType.equals("flatFee") || feeType.equals("percentageFee"))) {
            bank.get().setFeeInUse(feeType);
            bankRepository.save(bank.get());
            return "This bank use " + feeType;
        } else {
            return "Bank doesn't exist or feeType wrong";
        }

    }

    @Override
    public String performTransaction(Transaction transaction) {
        if (transaction.getAmount() <= 0) {
            return "Amount to perform transaction should be greater than 0";
        }
        TransactionService transactionService;
        if (transaction instanceof Deposit) {
            transactionService = transactionServiceMap.get("depositService");
        } else if (transaction instanceof Transfer) {
            transactionService = transactionServiceMap.get("transferService");
        } else {
            transactionService = transactionServiceMap.get("withdrawService");
        }
        Optional<Account> account = accountRepository.findById(transaction.getIdOriginTransaction());
        if (account.isPresent()) {

            Bank bank = account.get().getBank();

            String fee = bank.getFeeInUse();
            System.out.println("feee " + fee);

            if (transaction instanceof Transfer) {
                Optional<Account> to = accountRepository.findById(((Transfer) transaction).getIdRealiseTransation());

                if (to.isPresent()) {

                    double availabelBalance = (fee.equals("flatFee")) ? calculateFlateFee(account.get())
                            : calculatePercentageFee(account.get(), transaction.getAmount());
                    boolean canMakeTransaction = transactionService.canPerformTransaction(availabelBalance,
                            transaction.getAmount());

                    if (canMakeTransaction) {
                        bank.setTotalTransferAmount(bank.getTotalTransferAmount() + transaction.getAmount());
                        bankRepository.save(bank);
                        return transactionService.execute(transaction, availabelBalance);

                    } else {
                        return "You don't have money to transfer";
                    }
                } else {
                    return "Account 2 doesn't existr";
                }
            } else {

                double availabelBalance = (fee.equals("flatFee")) ? calculateFlateFee(account.get())
                        : calculatePercentageFee(account.get(),transaction.getAmount());
                boolean canMakeTransaction = transactionService.canPerformTransaction(availabelBalance,
                        transaction.getAmount());
                        System.out.println("Tranasslslslslsl "+ availabelBalance );
                if (canMakeTransaction) {
                    return transactionService.execute(transaction, availabelBalance);

                } else {
                    return "You don't have money to Withdraw";
                }
            }
        } else {
            return "Account does not excist";
        }
    }

    public double calculateFlateFee(Account account) {

        return accountService.getAccountBalance(account.getId())
                - accountService.getTransactionFlatByAccountId(account.getId());

    }

    public double calculatePercentageFee(Account account, double tansactionAmoount) {
        return accountService.getAccountBalance(account.getId()) - (tansactionAmoount -
        tansactionAmoount* accountService.getTransactionPercentageByAccountId(account.getId()));
    }

    @Override
    public String getTrnsferAmount(String name) {
        Optional<Bank> bank = bankRepository.findById(name);

        if (bank.isPresent()) {
            return bank.get().getTotalTransferAmount() + "";
        } else {
            return "This bank doesn't exist";
        }
    }

    @Override
    public List<Account> getAccountsByBankName(String bankName) {
        
        Optional<Bank> bank = bankRepository.findById(bankName);
        if(bank.isPresent()){
            return accountService.getAccountsByBankName(bankName);
        }
        else{
            return Collections.emptyList();
        }
    }

}
