package com.jerrydev.mallcenter.service.impl;

import com.jerrydev.mallcenter.dto.OrderDTO;
import com.jerrydev.mallcenter.entity.Customer;
import com.jerrydev.mallcenter.entity.Order;
import com.jerrydev.mallcenter.exception.ResourceNotFoundException;
import com.jerrydev.mallcenter.maper.OrderMapper;
import com.jerrydev.mallcenter.repository.CustomerRepository;
import com.jerrydev.mallcenter.repository.OrderRepository;
import com.jerrydev.mallcenter.service.OrderService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public List<OrderDTO> findAll() {

        List<Order> orderList = orderRepository.findAll();
        List<OrderDTO> orderDTOList = new ArrayList<>();

        for(Order order:orderList) orderDTOList.add(orderMapper.fromOrder(order));

        return orderDTOList;
    }

    @Override
    public OrderDTO findById(int id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Order","id",id));
        return orderMapper.fromOrder(order);
    }

    @Override
    public OrderDTO create(OrderDTO orderDTO, int userId) {

        Customer customerFound = customerRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","id",userId));

        Order order = orderMapper.fromOrderDTO(orderDTO);

        customerFound.setOrders(List.of(order));

        return orderMapper.fromOrder(orderRepository.save(order));
    }

    @Override
    public OrderDTO update(OrderDTO orderDTO, int idOrder) {

        Order orderFound = orderRepository.findById(idOrder)
                .orElseThrow(()->new ResourceNotFoundException("Order","id",idOrder));

        orderFound.setTotal(orderDTO.getTotal());
        orderFound.setStatus(orderDTO.getStatus());
        return orderMapper.fromOrder(orderRepository.save(orderFound));
    }

    @Override
    public void delete(int id, int userId) {

    }

    @Override
    public List<OrderDTO> findByUserId(int userId) {
        return null;
    }

    @Override
    public OrderDTO findByUserIdAndOrderId(int userId, int orderId) {
        return null;
    }
}
