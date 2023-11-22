package com.jerrydev.mallcenter.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {

    private int id ;

    @NotBlank(message = "Nombre Inválido: Nombre vacío")
    @NotEmpty(message = "Nombre Inválido: Nombre nulo")
    @Size(min = 1, max = 200, message = "Nombre Inválido: Nombre debe tener más de 1 y menos de 200 carácteres")
    private String lastName;

    @NotBlank(message = "Apellido Inválido: Nombre vacío")
    @NotEmpty(message = "Apellido Inválido: Nombre nulo")
    @Size(min = 1, max = 200, message = "Apellido Inválido: Nombre debe tener más de 1 y menos de 200 carácteres")
    private String firstName;

    @NotBlank(message = "Correo Inválido: Nombre vacío")
    @NotEmpty(message = "Correo Inválido: Nombre nulo")
    @Size(min = 1, max = 200, message = "Correo Inválido: Nombre debe tener más de 1 y menos de 200 carácteres")
    @Email
    private String email;

    @NotNull
    private boolean enable;

    private List<LocalDTO> locals = new ArrayList<>();

    private List<OrderDTO> orders = new ArrayList<>();

}
