package com.jerrydev.mallcenter.repository;

import com.jerrydev.mallcenter.entity.Habilidad;
import com.jerrydev.mallcenter.entity.Nivel;
import com.jerrydev.mallcenter.entity.Persona;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PersonaRepositoryTest {

    @Autowired
    private PersonaRepository personaRepository;

    @Test
    public void insertPersona(){
        Persona persona = new Persona();
        persona.setName("Juan Medina");
        persona.setEdad(25);

        System.out.println(personaRepository.save(persona));
    }

    @Test
    public void insertPersonaConHabilidades(){
        Persona persona = new Persona();
        persona.setName("Dante Durand");
        persona.setEdad(15);

        Habilidad habilidad1 = Habilidad.builder()
                .nombre("Bailar")
                .nivel(Nivel.ASOMBROSO)
                .build();
        /*Habilidad habilidad3 = Habilidad.builder()
                .nombre("Saltar")
                .nivel(Nivel.ASOMBROSO)
                .build();
        Habilidad habilidad2 = Habilidad.builder()
                .nombre("Danza")
                .nivel(Nivel.COMO_DIOS)
                .build();
         */

        persona.setHabilidads(List.of(habilidad1));

        System.out.println(personaRepository.save(persona));
    }


}