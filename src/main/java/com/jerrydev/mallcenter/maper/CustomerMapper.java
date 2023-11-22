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

        Customer customer = new Customer();

        if(customerDTO.getLocals()!=null){
            List<OrderDTO> orderDTOS = customerDTO.getOrders() ;
            List<Order> orders = new ArrayList<>();

            for (OrderDTO orderDTO: orderDTOS) orders.add(orderMapper.fromOrderDTO(orderDTO));

            customer.setLastName(customerDTO.getLastName());
            customer.setFirstName(customerDTO.getFirstName());
            customer.setEmail(customerDTO.getEmail());
            customer.setEnable(customer.isEnable());
            customer.setOrders(orders);

            return  customer ;

        }
        customer.setLastName(customerDTO.getLastName());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setEmail(customerDTO.getEmail());
        customer.setEnable(customer.isEnable());

        return  customer ;
    }

    public CustomerDTO fromCustomer(Customer customer){

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setEnable(customer.isEnable());

        return  customerDTO ;
    }
}
