package com.jerrydev.mallcenter.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemOrderDTO {

    private int id ;
    private int quantity ;
    private BigDecimal price ;
    private BigDecimal subTotal;
    private ProductDTO product;
    private OrderDTO order;

}
