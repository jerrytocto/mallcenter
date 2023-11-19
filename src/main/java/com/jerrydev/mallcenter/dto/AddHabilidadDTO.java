package com.jerrydev.mallcenter.dto;

import com.jerrydev.mallcenter.entity.Nivel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddHabilidadDTO {

    private Nivel nivel ;

    private String nombre;
    
}
