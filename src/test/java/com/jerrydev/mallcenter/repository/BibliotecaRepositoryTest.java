package com.jerrydev.mallcenter.repository;

import com.jerrydev.mallcenter.entity.Biblioteca;
import com.jerrydev.mallcenter.entity.Libro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BibliotecaRepositoryTest {

    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    @Test
    public void insertBiblioteca(){
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.setName("Biblioteca 1");
        bibliotecaRepository.save(biblioteca);

        System.out.println(biblioteca);
    }

    @Test
    public void insertBibliotecaWithLibros(){
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.setName("Biblioteca 3");

        Libro libro1 = new Libro();
        Libro libro2 = new Libro();
        Libro libro3 = new Libro();

        libro1.setName("Dinero es oro");
        libro2.setName("El rico más rico");
        libro3.setName("Reviviendo muertos");

        biblioteca.setLibros(List.of(libro1,libro2,libro3));

        bibliotecaRepository.save(biblioteca);

        System.out.println(biblioteca);
    }

    @Test
    public void showAllBibliotecas(){
        System.out.println(bibliotecaRepository.findAll());
    }


}