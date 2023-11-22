package com.jerrydev.mallcenter.service.impl;

import com.jerrydev.mallcenter.dto.CustomerDTO;
import com.jerrydev.mallcenter.dto.OrderDTO;
import com.jerrydev.mallcenter.entity.Customer;
import com.jerrydev.mallcenter.entity.Local;
import com.jerrydev.mallcenter.entity.Order;
import com.jerrydev.mallcenter.exception.DatabaseOperationException;
import com.jerrydev.mallcenter.exception.ResourceNotFoundException;
import com.jerrydev.mallcenter.maper.CustomerMapper;
import com.jerrydev.mallcenter.maper.OrderMapper;
import com.jerrydev.mallcenter.repository.CustomerRepository;
import com.jerrydev.mallcenter.repository.LocalRepository;
import com.jerrydev.mallcenter.repository.OrderRepository;
import com.jerrydev.mallcenter.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private LocalRepository localRepository;


    @Override
    public List<CustomerDTO> findAll() {

        List<Customer> customerList = customerRepository.findAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for(Customer customer: customerList) customerDTOList.add(customerMapper.fromCustomer(customer));
        return customerDTOList;
    }

    @Override
    public CustomerDTO findById(int id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Customer","id",id));
        return customerMapper.fromCustomer(customer);
    }

    @Override
    public CustomerDTO create(CustomerDTO customerDTO, int idLocal) {

        Local local = localRepository.findById(idLocal)
                .orElseThrow(()->new ResourceNotFoundException("Local","id",idLocal));

        Customer customer = customerMapper.fromCustomerDTO(customerDTO);

        customer.setLocalList(List.of(local));

        customerRepository.save(customer);
        localRepository.save(local);
        return customerMapper.fromCustomer(customer);
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO, int id) {
        try{
            Customer customer = customerRepository.findById(id)
                    .orElseThrow(()->new ResourceNotFoundException("Customer","id",id));

            customer.setFirstName(customerDTO.getFirstName());
            customer.setLastName(customerDTO.getLastName());
            customer.setEmail(customerDTO.getEmail());
            customer.setEnable(customerDTO.isEnable());
            return customerMapper.fromCustomer(customerRepository.save(customer));
        }catch(DatabaseOperationException ex){
            throw new DatabaseOperationException("Actualizar","Error al intentar actualizar el cliente",ex);
        }

    }

    @Override
    public void delete(int id) {
        try{
            Customer customer = customerRepository.findById(id)
                    .orElseThrow(()->new ResourceNotFoundException("Customer","id",id));
            customerRepository.delete(customer);
        }catch(DatabaseOperationException ex){
            throw new DatabaseOperationException("Eliminar","Error al intentar eliminar el cliente",ex);
        }
    }

    @Override
    public void insertOrder(OrderDTO orderDTO, int idCustomer) {
        try{
            Customer customer = customerRepository.findById(idCustomer)
                    .orElseThrow(()->new ResourceNotFoundException("Customer","id",idCustomer));

            Order order = orderMapper.fromOrderDTO(orderDTO);
            order.setCustomer(customer);

            orderRepository.save(order);
            customerRepository.save(customer);
        }catch (DatabaseOperationException ex) {
            throw new DatabaseOperationException("Insertar Orden", "Error al intentar insertar la orden", ex);
        }
    }

    @Override
    public List<OrderDTO> findOrdersByCustomerId(int idCustomer) {

        Customer customerFound = customerRepository.findById(idCustomer)
                .orElseThrow(()->new ResourceNotFoundException("Customer","id",idCustomer));

        Optional<List<Order>> orderFound = orderRepository.findByCustomerId(idCustomer);

        List<OrderDTO> orderDTOList = new ArrayList<>();

        if(orderFound.isPresent()) {
            for (Order order : orderFound.get()) orderDTOList.add(orderMapper.fromOrder(order));
            return orderDTOList;
        }else{
            throw new ResourceNotFoundException("Order","id",idCustomer);
        }
    }

    @Override
    public OrderDTO findOrderById(int idCustomer, int idOrder) {

        Customer customerFound = customerRepository.findById(idCustomer)
                .orElseThrow(()->new ResourceNotFoundException("Customer","id",idCustomer));

        Order orderFound = orderRepository.findById(idOrder)
                .orElseThrow(()->new ResourceNotFoundException("Order","id",idOrder));

        if(orderFound.getCustomer().getId() == customerFound.getId()) return orderMapper.fromOrder(orderFound);
        else throw new ResourceNotFoundException("Order","id",idOrder);
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO, int idCustomer, int idOrder) {

        Customer customerFound = customerRepository.findById(idCustomer)
                .orElseThrow(()->new ResourceNotFoundException("Customer","id",idCustomer));

        Order orderFound = orderRepository.findById(idOrder)
                .orElseThrow(()->new ResourceNotFoundException("Order","id",idOrder));

            orderFound.setTotal(orderDTO.getTotal());
            orderFound.setStatus(orderDTO.getStatus());
            orderFound.setCustomer(customerFound);
            orderFound.setUpdatedAt(new Date());
            return orderMapper.fromOrder(orderRepository.save(orderFound));

    }
}
