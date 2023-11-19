package com.jerrydev.mallcenter.maper;

import com.jerrydev.mallcenter.dto.AccountDTO;
import com.jerrydev.mallcenter.dto.ManagerDTO;
import com.jerrydev.mallcenter.entity.Account;
import com.jerrydev.mallcenter.entity.Manager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerMapper {

    public Manager fromManagerDTO(ManagerDTO managerDTO){

        List<AccountDTO> accountDTOS = managerDTO.getAccounts();
        List<Account> accounts = new ArrayList<>();

        for (AccountDTO accountDTO:accountDTOS){
            accounts.add(fromAccountDTO(accountDTO));
        }

        Manager manager= Manager.builder()
                .firstName(managerDTO.getFirstName())
                .lastName(managerDTO.getLastName())
                .build();
        manager.setAccounts(accounts);
        return manager;
    }

    public ManagerDTO fromManagerDTO(Manager manager){

        List<AccountDTO> accountDTOS = new ArrayList<>();
        for (Account account: manager.getAccounts()) accountDTOS.add(fromAccount(account));

        return  ManagerDTO.builder()
                .id(manager.getId())
                .firstName(manager.getFirstName())
                .lastName(manager.getLastName())
                .accounts(accountDTOS)
                .build();
    }

    public Account fromAccountDTO(AccountDTO  accountDTO){
        Account account = new Account();
        account.setEmail(accountDTO.getCorreo());
        account.setPassword(accountDTO.getCorreo());

        return account;
    }

    public AccountDTO fromAccount(Account account){

        return  AccountDTO.builder()
                .id(account.getId())
                .correo(account.getEmail())
                .build();
    }
}
