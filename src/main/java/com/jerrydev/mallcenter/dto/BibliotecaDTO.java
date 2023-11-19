package com.jerrydev.mallcenter.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
public class BibliotecaDTO {

    private int id ;

    @NotBlank(message = "Nombre Inválido: Nombre vacío")
    @NotEmpty(message = "Nombre Inválido: Nombre nulo")
    @Size(min = 1, max = 200, message = "Nombre Inválido: Nombre debe tener más de 1 y menos de 200 carácteres")
    private String  name;


    List<LibroDTO> libros = new ArrayList<>();


}
