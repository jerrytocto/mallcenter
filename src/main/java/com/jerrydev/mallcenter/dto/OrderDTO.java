package com.jerrydev.mallcenter.dto;

import com.jerrydev.mallcenter.entity.StatusOrder;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {

    private int id;

    @NotNull(message = "Total inválido: El tital debe ser mayor a 0")
    @DecimalMin(value = "0.0", inclusive = false, message = "Total inválidod: El total debe ser mayor que 0")
    private BigDecimal total;

    private StatusOrder status;


}
