package com.jerrydev.mallcenter.repository;

import com.jerrydev.mallcenter.entity.Fiesta;
import com.jerrydev.mallcenter.entity.Habilidad;
import com.jerrydev.mallcenter.entity.Nivel;
import com.jerrydev.mallcenter.entity.Persona;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FiestaRepositoryTest {

    @Autowired
    private FiestaRepository fiestaRepository;

    @Test
    public void insertFiesta(){
        Fiesta fiesta = new Fiesta();
        fiesta.setName("Fiesta de cumpleaños");
        fiesta.setAddress("Las Orquideas #1254");

        System.out.println(fiestaRepository.save(fiesta));

    }

    @Test
    public void insertFiestaConPersona(){
        Fiesta fiesta = new Fiesta();
        fiesta.setName("Fiesta de cumpleaños");
        fiesta.setAddress("Las Orquideas #1254");

        Habilidad habilidad1 = Habilidad.builder()
                .nombre("Bailar")
                .nivel(Nivel.BUENO)
                .build();
        Habilidad habilidad3 = Habilidad.builder()
                .nombre("Saltar")
                .nivel(Nivel.ASOMBROSO)
                .build();
        Habilidad habilidad2 = Habilidad.builder()
                .nombre("Danza")
                .nivel(Nivel.COMO_DIOS)
                .build();

        Persona persona1 = Persona.builder()
                .name("Juan Segundo")
                .habilidads(List.of(habilidad1,habilidad2))
                .edad(23)
                .build();

        Persona persona2 = Persona.builder()
                .name("Fiorella Rodriguez")
                .habilidads(List.of(habilidad3,habilidad2))
                .edad(28)
                .build();

        fiesta.setPersonas(List.of(persona1,persona2));


        System.out.println(fiestaRepository.save(fiesta));

    }

}