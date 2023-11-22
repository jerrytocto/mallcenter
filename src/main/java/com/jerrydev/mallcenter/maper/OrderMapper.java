package com.jerrydev.mallcenter.maper;

import com.jerrydev.mallcenter.dto.ItemOrderDTO;
import com.jerrydev.mallcenter.dto.OrderDTO;
import com.jerrydev.mallcenter.entity.ItemOrder;
import com.jerrydev.mallcenter.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderMapper {


    @Autowired
    private ItemOrderMapper itemOrderMapper;

    public Order fromOrderDTO(OrderDTO orderDTO){

        if(orderDTO.getItemOrdersDTO() != null && orderDTO.getItemOrdersDTO().size() > 0){

            List<ItemOrder> itemOrderList = new ArrayList<>();
            for (ItemOrderDTO itemOrderDTO : orderDTO.getItemOrdersDTO()) {
                itemOrderList.add(itemOrderMapper.fromItemOrderDTO(itemOrderDTO));
            }
            return Order.builder()
                    .total(orderDTO.getTotal())
                    .status(orderDTO.getStatus())
                    .itemOrder(itemOrderList)
                    .build();
        }

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
