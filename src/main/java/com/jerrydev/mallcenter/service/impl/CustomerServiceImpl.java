package com.jerrydev.mallcenter.service.impl;

import com.jerrydev.mallcenter.dto.CustomerDTO;
import com.jerrydev.mallcenter.dto.OrderDTO;
import com.jerrydev.mallcenter.entity.Customer;
import com.jerrydev.mallcenter.entity.Order;
import com.jerrydev.mallcenter.exception.DatabaseOperationException;
import com.jerrydev.mallcenter.exception.ResourceNotFoundException;
import com.jerrydev.mallcenter.maper.CustomerMapper;
import com.jerrydev.mallcenter.maper.OrderMapper;
import com.jerrydev.mallcenter.repository.CustomerRepository;
import com.jerrydev.mallcenter.repository.OrderRepository;
import com.jerrydev.mallcenter.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public CustomerDTO create(CustomerDTO customerDTO) {
        Customer customer = customerMapper.fromCustomerDTO(customerDTO);
        return customerMapper.fromCustomer(customerRepository.save(customer));
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
            customer.setOrders(List.of(orderMapper.fromOrderDTO(orderDTO)));
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

        if(orderFound.getCustomer().getId() == customerFound.getId()){
            orderFound.setTotal(orderDTO.getTotal());
            orderFound.setStatus(orderDTO.getStatus());
            return orderMapper.fromOrder(orderRepository.save(orderFound));
        }
        else throw new ResourceNotFoundException("Order y Customer no compatibles","id",idOrder);
    }
}
