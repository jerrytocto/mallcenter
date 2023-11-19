package com.jerrydev.mallcenter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class ManagerDTO {

    private int id ;

    @NotBlank(message = "First Name Inválido: First name vacío")
    @NotEmpty(message = "First Name Inválido: First name nulo ")
    @Size(min = 3, max = 150, message = "First Name Inválido: La longitud del firs name debe estar entre 0 y 150")
    private String firstName ;

    @NotBlank(message = "Last Name Inválido: Last name vacío")
    @NotEmpty(message = "Last Name Inválido: Last name nulo ")
    @Size(min = 3, max = 150, message = "Last Name Inválido: La longitud del Last name debe estar entre 0 y 150")
    private String lastName ;

    @NotNull
    private boolean enable ;

    private List<AccountDTO> accounts = new ArrayList<>();

}
