package com.jerrydev.mallcenter.maper;

import com.jerrydev.mallcenter.dto.HabilidadShowDTO;
import com.jerrydev.mallcenter.dto.PersonaShowDTO;
import com.jerrydev.mallcenter.entity.Habilidad;
import com.jerrydev.mallcenter.entity.Persona;
import org.antlr.v4.runtime.tree.xpath.XPathLexerErrorListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaMapper {

    //Método para convertir clase Persona a DTO
    public PersonaShowDTO fromPersona(Persona persona){

        List<HabilidadShowDTO> habilidadShowDTOList = new ArrayList<>();

        for(Habilidad habilidad:persona.getHabilidads()){
            habilidadShowDTOList.add(fromHabilidad(habilidad));
        }

        PersonaShowDTO personaShowDTO = PersonaShowDTO.builder()
                .id(persona.getId())
                .edad(persona.getEdad())
                .name(persona.getName())
                .habilidad(habilidadShowDTOList)
                .build();
        return personaShowDTO;
    }

    //Método para convertir DTO a clase persona
    public  Persona fromPersonaDTO(PersonaShowDTO personaShowDTO){

        List<Habilidad> habilidadList = new ArrayList<>();
        for(HabilidadShowDTO habilidadShowDTO:personaShowDTO.getHabilidad()){
            habilidadList.add(fromHabilidadDTO(habilidadShowDTO));
        }

        Persona persona = Persona.builder()
                .name(personaShowDTO.getName())
                .edad(personaShowDTO.getEdad())
                .build();
        persona.setHabilidads(habilidadList);
        return  persona ;
    }

    //Mapear Habilidades A DTO
    public HabilidadShowDTO fromHabilidad(Habilidad habilidad){

        return HabilidadShowDTO.builder()
                .id(habilidad.getId())
                .nombre(habilidad.getNombre())
                .nivel(habilidad.getNivel())
                .build();
    }

    //Mapear DTO a habilidades
    public Habilidad fromHabilidadDTO(HabilidadShowDTO habilidadShowDTO){

        return Habilidad.builder()
                .nombre(habilidadShowDTO.getNombre())
                .nivel(habilidadShowDTO.getNivel())
                .build();
    }
}
