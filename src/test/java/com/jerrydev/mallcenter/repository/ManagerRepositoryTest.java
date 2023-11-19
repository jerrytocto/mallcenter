package com.jerrydev.mallcenter.repository;

import com.jerrydev.mallcenter.entity.Account;
import com.jerrydev.mallcenter.entity.Manager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ManagerRepositoryTest {

    @Autowired
    private ManagerRepository managerRepository;

    @Test
    public void createManager(){
        Manager manager = Manager.builder()
                .firstName("David")
                .lastName("Banner")
                .enable(false)
                .build();

        Manager managerSave = managerRepository.save(manager);
        System.out.println(managerSave);
    }
    @Test
    public void createManagerWhitAccounts(){
        Manager manager = Manager.builder()
                .firstName("Franklin")
                .lastName("Figueroa")
                .enable(false)
                .build();

        Account account1 = new Account("account1@gmail.com","account1");
        Account account2 = new Account("account2@gmail.com","account2");
        manager.setAccounts(List.of(account1,account2));
        Manager managerSave = managerRepository.save(manager);
        System.out.println(managerSave);
    }
    @Test
    public void ListAllManager(){
        List<Manager> managerList = managerRepository.findAll();

        for (Manager manager:managerList) {
            System.out.println("Nombre : "+manager.getFirstName() + " " + manager.getLastName());
        }
    }
}