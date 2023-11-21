package com.jerrydev.mallcenter.maper;

import com.jerrydev.mallcenter.dto.OrderDTO;
import com.jerrydev.mallcenter.entity.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {



    public Order fromOrderDTO(OrderDTO orderDTO){
        return Order.builder()
                .total(orderDTO.getTotal())
                .status(orderDTO.getStatus())
                .build();

    }
    public OrderDTO fromOrder(Order order){
        return OrderDTO.builder()
                .id(order.getId())
                .total(order.getTotal())
                .status(order.getStatus())
                .build();
    }
}
