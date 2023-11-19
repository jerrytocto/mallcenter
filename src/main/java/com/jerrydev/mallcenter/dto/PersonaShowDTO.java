package com.jerrydev.mallcenter.dto;

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
public class PersonaShowDTO {

    private int id ;

    private String name ;

    private int edad ;

    private List<HabilidadShowDTO> habilidad = new ArrayList<>();



}
