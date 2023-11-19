package com.jerrydev.mallcenter.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocalDTO {

    private int id;

    @NotBlank(message = "First Name Inválido: First name vacío")
    @NotEmpty(message = "First Name Inválido: First name nulo ")
    @Size(min = 3, max = 150, message = "First Name Inválido: La longitud del firs name debe estar entre 0 y 150")
    private String name ;

    @NotNull
    @Min(value = 1, message = "Piso inválido: Debe ser mayor a 1")
    private int floor;

    @NotNull
    private boolean enable;

    private List<ManagerDTO> managers = new ArrayList<>();

    private List<ProductDTO> productDTOS = new ArrayList<>();
}
