package com.jerrydev.mallcenter.dto;

import com.jerrydev.mallcenter.entity.Local;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    private int id ;
    @NotBlank(message = "Nombre Inválido: Nombre vacío")
    @NotEmpty(message = "Nombre Inválido: Nombre nulo")
    @Size(min = 1, max = 200, message = "Nombre Inválido: Nombre debe tener más de 1 y menos de 200 carácteres")
    private String name ;

    private String description ;

    @NotNull(message = "Precion inválido: El precio no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = false, message = "Preció inválidod: El precio debe ser mayor que 0")
    private BigDecimal price;

    @NotNull(message = "Stock Inválido: El stock no puede ser nulo")
    @Min(value = 1, message = "Stock Inválido: El stock debe ser mayor que 0")
    private int stock;
}
