package com.jerrydev.mallcenter.service;

import com.jerrydev.mallcenter.dto.AddHabilidadDTO;
import com.jerrydev.mallcenter.dto.PersonaShowDTO;
import com.jerrydev.mallcenter.entity.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonaService {

    //Listar todos las personas
    List<PersonaShowDTO> showAll();

    //Listar persona por id
    Optional<PersonaShowDTO> findById(Integer id);

    //Insertar una persona
    Optional<PersonaShowDTO> createPersona(PersonaShowDTO personaShowDTO);

    //Actualizar una persona
    Optional<PersonaShowDTO> updatePersona(PersonaShowDTO personaShowDTO, Integer id);

    //Eliminar una persona
    void deletePersona(Integer id);

    //Agregar una habilidad a una persona
    PersonaShowDTO addHabilidad(AddHabilidadDTO addHabilidadDTO, int idPersona);
}
