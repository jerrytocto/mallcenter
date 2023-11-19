package com.jerrydev.mallcenter.controller;

import com.jerrydev.mallcenter.dto.AddHabilidadDTO;
import com.jerrydev.mallcenter.dto.PersonaShowDTO;
import com.jerrydev.mallcenter.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PersonaController {

    @Autowired
    private PersonaService personaService ;

    @GetMapping("/personas")
    public List<PersonaShowDTO> showAll(){
        return personaService.showAll();
    }

    @GetMapping("persona/{id}")
    public PersonaShowDTO findById(@PathVariable int id){
        return personaService.findById(id).orElse(null);
    }

    @PostMapping("/persona")
    public PersonaShowDTO savePersona(@RequestBody PersonaShowDTO personaShowDTO){
        return personaService.createPersona(personaShowDTO).orElse(null);
    }

    //Agregar una habilidad a una persona
    @PostMapping("/persona/{id}/Add-habilidad")
    public PersonaShowDTO addHabilidadPersona(@RequestBody AddHabilidadDTO addHabilidadDTO,
                                              @PathVariable int id){

        return personaService.addHabilidad(addHabilidadDTO,id);
    }

}
