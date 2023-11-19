package com.jerrydev.mallcenter.repository;

import com.jerrydev.mallcenter.entity.Account;
import com.jerrydev.mallcenter.entity.Customer;
import com.jerrydev.mallcenter.entity.Local;
import com.jerrydev.mallcenter.entity.Manager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LocalRepositoryTest {

    @Autowired
    private LocalRepository localRepository;

    @Test
    public void CreateOnlyLocal(){
        Local local = new Local();
        local.setName("Cevichería Don Pepe");
        local.setFloor(3);
        local.setEnable(true);

        System.out.println(localRepository.save(local));
    }

    @Test
    public void createLocalWithManager(){

        Local local = new Local();
        local.setName("Juguetería mi niño");
        local.setFloor(1);
        local.setEnable(true);

        Manager manager = Manager.builder()
                .local(local)
                .firstName("David")
                .lastName("García")
                .enable(true)
                .build();
        Manager manager2 = Manager.builder()
                .local(local)
                .firstName("Rodri")
                .lastName("Eva")
                .enable(false)
                .build();


        local.setManagerList(List.of(manager,manager2));
        localRepository.save(local);
    }

    @Test
    public void ListAllLocals(){
        List<Local> localList = localRepository.findAll();
        System.out.println(/********************* LISTA DE LOCALES *****************************/);
        int i = 1;
        for(Local local:localList) {
            System.out.println("----- LOCAL N°"+ i + "------------");
            System.out.println("Nombre: "+" "+ local.getName());
            System.out.println("Piso: "+" "+ local.getName());
            System.out.println("Managers: "+" "+local.getManagerList());
            i++;
        }

    }

    @Test
    public void createLocalWithCustomersWhitAccounts(){
        Local local = new Local();
        local.setName("Rustica TV 1");
        local.setFloor(3);
        local.setEnable(true);

        Customer customer1 = new Customer();
        Customer customer2 = new Customer();

        customer1.setFirstName("Federico 1");
        customer1.setLastName("Orderique 1");
        customer1.setEmail("fedeor1@gmail.com");
        customer1.setEnable(true);

        customer2.setFirstName("Dieter1");
        customer2.setLastName("Augusto1");
        customer2.setEmail("dietera1@gmail.com");
        customer2.setEnable(true);

        Account account1 = new Account();
        Account account2 = new Account();

        account1.setEmail("federico11@gmail.com");
        account1.setPassword("federico11");
        account2.setEmail("filadelfio22@gmail.com");
        account1.setPassword("filadelfio22");



        local.setCustomerList(List.of(customer1,customer2));

        Local localSaved = localRepository.save(local);

        System.out.println(localSaved);
    }

    @Test
    public  void createLocalWithCustomerWhitAccounts(){
        Local local = new Local();
        local.setName("Rustica TV 1");
        local.setFloor(3);
        local.setEnable(true);

        Manager manager1  = new Manager();
        manager1.setLastName("Huaman");
        manager1.setFirstName("Fiscal");
        manager1.setEnable(true);

        Account account1 = new Account();
        account1.setEmail("huamanf@gmail.com");
        account1.setPassword("huamanf");

        manager1.setAccounts(List.of(account1));

        local.setManagerList(List.of(manager1));

        System.out.println(localRepository.save(local));
    }

}