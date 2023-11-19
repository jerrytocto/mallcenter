package com.jerrydev.mallcenter.repository;

import com.jerrydev.mallcenter.entity.Biblioteca;
import com.jerrydev.mallcenter.entity.Libro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LibroRepositoryTest {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private BibliotecaRepository bibliotecaRepository;
    @Test
    public void showAllLibro(){
        System.out.println(libroRepository.findAll());
    }

    @Test
    public void insertLibro(){

        Biblioteca biblioteca = bibliotecaRepository.findById(1).get();

        Libro libro = new Libro();
        libro.setName("Basicamente null");
        libro.setBiblioteca(biblioteca);

        System.out.println(libroRepository.save(libro));
    }
}