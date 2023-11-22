package com.jerrydev.mallcenter.maper;

import com.jerrydev.mallcenter.dto.ItemOrderDTO;
import com.jerrydev.mallcenter.dto.ProductDTO;
import com.jerrydev.mallcenter.entity.ItemOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ItemOrderMapper {

    @Autowired
    private ProductMapper productMapper;
    public ItemOrder fromItemOrderDTO(ItemOrderDTO itemOrderDTO) {
        return ItemOrder.builder()
                .quantity(itemOrderDTO.getQuantity())
                .price(itemOrderDTO.getPrice())
                .product(productMapper.fromProductDTO(itemOrderDTO.getProduct()))
                .build();
    }

    public ItemOrderDTO fromItemOrderDTO(ItemOrder itemOrder, ProductDTO productDTO, BigDecimal subTotal) {
        return ItemOrderDTO.builder()
                .id(itemOrder.getId())
                .product(productDTO)
                .quantity(itemOrder.getQuantity())
                .price(itemOrder.getPrice())
                .subTotal(subTotal)
                .build();
    }
}
