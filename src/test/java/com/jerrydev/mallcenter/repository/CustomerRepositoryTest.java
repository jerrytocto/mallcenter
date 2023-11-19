package com.jerrydev.mallcenter.repository;

import com.jerrydev.mallcenter.entity.Customer;
import com.jerrydev.mallcenter.entity.Local;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LocalRepository localRepository ;


    @Test
    @Transactional
    public void createCustomer(){
        Customer customer = new Customer();
        Customer customer2 = new Customer();
        Customer customer3 = new Customer();

        Local local = new Local();
        local.setName("Dulcería tu miel");
        local.setFloor(1);
        local.setEnable(true);
        localRepository.save(local);
        //local.setCustomerList(List.of(customer, customer2, customer3));

        customer.setFirstName("Juan");
        customer.setLastName("Julio");
        customer.setEnable(true);
        customer.setEmail("juana@gmail.com");
        customer.setLocalList(List.of(local));

        customer2.setFirstName("Fidel");
        customer2.setLastName("ajila");
        customer2.setEnable(true);
        customer2.setEmail("fidelaa@gmail.com");
        customer2.setLocalList(List.of(local));

        customer3.setFirstName("Nury");
        customer3.setLastName("Bico");
        customer3.setEnable(true);
        customer3.setEmail("nury@gmail.com");
        customer3.setLocalList(List.of(local));


        customerRepository.save(customer);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
    }

    @Test
    public void customerWithLocal(){

        Local local = localRepository.findById(1).get();
        Customer customer3 = new Customer();

        customer3.setFirstName("Nury yanela");
        customer3.setLastName("Bico");
        customer3.setEnable(true);
        customer3.setEmail("nuryy@gmail.com");
        customer3.setLocalList(List.of(local));

        customerRepository.save(customer3);
    }
}