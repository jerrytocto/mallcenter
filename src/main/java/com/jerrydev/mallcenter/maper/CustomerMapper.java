package com.jerrydev.mallcenter.maper;

import com.jerrydev.mallcenter.dto.CustomerDTO;
import com.jerrydev.mallcenter.dto.LocalDTO;
import com.jerrydev.mallcenter.dto.OrderDTO;
import com.jerrydev.mallcenter.entity.Customer;
import com.jerrydev.mallcenter.entity.Local;
import com.jerrydev.mallcenter.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerMapper {

    @Autowired
    private LocalMapper localMapper ;

    @Autowired
    private OrderMapper orderMapper ;

    public Customer fromCustomerDTO(CustomerDTO customerDTO){

        List<LocalDTO> localDTOS = customerDTO.getLocalDTOS() ;
        List<Local> locals = new ArrayList<>();

        List<OrderDTO> orderDTOS = customerDTO.getOrders() ;
        List<Order> orders = new ArrayList<>();

        for(LocalDTO localDTO: localDTOS) locals.add(localMapper.fromLocalDTO(localDTO));
        for (OrderDTO orderDTO: orderDTOS) orders.add(orderMapper.fromOrderDTO(orderDTO));

        Customer customer = new Customer();

        customer.setLastName(customerDTO.getLastName());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setEmail(customerDTO.getEmail());
        customer.setEnable(customer.isEnable());
        customer.setLocalList(locals);
        customer.setOrders(orders);

        return  customer ;
    }

    public CustomerDTO fromCustomer(Customer customer){

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setLastName(customer.getLastName());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setEnable(customer.isEnable());

        return  customerDTO ;
    }
}
