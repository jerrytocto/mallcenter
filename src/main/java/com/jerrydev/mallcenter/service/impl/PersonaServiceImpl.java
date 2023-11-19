package com.jerrydev.mallcenter.service.impl;

import com.jerrydev.mallcenter.dto.AddHabilidadDTO;
import com.jerrydev.mallcenter.dto.PersonaShowDTO;
import com.jerrydev.mallcenter.entity.Habilidad;
import com.jerrydev.mallcenter.entity.Persona;
import com.jerrydev.mallcenter.maper.PersonaMapper;
import com.jerrydev.mallcenter.repository.PersonaRepository;
import com.jerrydev.mallcenter.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private PersonaMapper personaMapper;

    @Override
    public List<PersonaShowDTO> showAll() {
        List<Persona> personaList = new ArrayList<>();
        List<PersonaShowDTO> personaShowDTOList = new ArrayList<>() ;
        personaList = personaRepository.findAll();

        for (Persona persona:personaList){
            personaShowDTOList.add(personaMapper.fromPersona(persona));
        }
        return personaShowDTOList;
    }

    @Override
    public Optional<PersonaShowDTO> findById(Integer id) {

        Persona persona = personaRepository.findById(id).orElse(null);
        return Optional.of(personaMapper.fromPersona(persona));
    }

    @Override
    public Optional<PersonaShowDTO> createPersona(PersonaShowDTO personaShowDTO) {

        Persona persona = personaMapper.fromPersonaDTO(personaShowDTO);
        Persona personaSave = personaRepository.save(persona);
        return Optional.of(personaMapper.fromPersona(personaSave));
    }

    @Override
    public Optional<PersonaShowDTO> updatePersona(PersonaShowDTO personaShowDTO, Integer id) {

        return Optional.empty();
    }

    @Override
    public void deletePersona(Integer id) {
        Persona persona = personaRepository.findById(id).orElse(null);

        if(persona != null){
            personaRepository.delete(persona);
        }

    }

    @Override
    public PersonaShowDTO addHabilidad(AddHabilidadDTO addHabilidadDTO, int idPersona) {

        Persona persona = personaRepository.findById(idPersona).orElse(null);

        if(persona != null){
            persona.getHabilidads().add(Habilidad
                    .builder()
                            .nombre(addHabilidadDTO.getNombre())
                            .nivel(addHabilidadDTO.getNivel())
                            .persona(persona)
                    .build());

            return personaMapper.fromPersona(personaRepository.save(persona));
        }
        return null;
    }
}
