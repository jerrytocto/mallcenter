package com.jerrydev.mallcenter.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTO {

    private int id ;

    @NotBlank(message = "Correo Inválido: Correo vacío")
    @NotEmpty(message = "Correo Inválido: Correo nulo")
    @Email
    private String correo ;

    @NotBlank(message = "Password Inválido: Password vacío")
    @NotEmpty(message = "Password Inválido: Password nulo")
    @Size(min = 5, max = 50, message = "Password Inválido: El tamaño del password debe estar entre 5 y 100")
    private String password ;
}
