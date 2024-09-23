package com.example.wwwduongtuankietlab01.services;

import com.example.wwwduongtuankietlab01.entities.Account;
import com.example.wwwduongtuankietlab01.repositories.AccountRepository;

import java.util.List;
import java.util.Optional;

public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public String verifyAccount(String userName, String pass) {
        String ans = "";
        Optional<Account> withAdmin = accountRepository.loginForm(userName, pass);
        Optional<Account> withUser = accountRepository.getAccountWithUser(userName, pass);
        if(withAdmin.isPresent()){
            return ans = "admin";
        }else {
            if(withUser.isPresent()){
                ans = "user";
            }
        }
        return ans;
    }

    public List<Account> getAccounts() {
        return accountRepository.getAllAccount();
    }

    public Optional<Account> getAccountById(String accountId){
        return  accountRepository.getAccountById(accountId);
    }

    public boolean addAccount(Account account){
        return accountRepository.insertAccount(account);
    }
    public boolean deleteAccount(String accountId){
        return  accountRepository.deleteAccount(accountId);
    }
    public boolean updateAccount(Account account){
        return  accountRepository.updateAccount(account);
    }
    public List<Account> getAccountsByRole(String roleId){
        return accountRepository.getAccountsByRole(roleId);
    }
}
