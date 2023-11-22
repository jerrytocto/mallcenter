package com.jerrydev.mallcenter.service;

import com.jerrydev.mallcenter.dto.CustomerDTO;
import com.jerrydev.mallcenter.dto.OrderDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> findAll();

    CustomerDTO findById(int id);

    CustomerDTO create(CustomerDTO customerDTO, int idLocal);

    CustomerDTO update(CustomerDTO customerDTO, int id);

    void delete(int id);

    void insertOrder(OrderDTO orderDTO, int idCustomer);

    List<OrderDTO> findOrdersByCustomerId(int idCustomer);

    OrderDTO findOrderById(int idCustomer, int idOrder);

    OrderDTO updateOrder(OrderDTO orderDTO, int idCustomer, int idOrder);
}
