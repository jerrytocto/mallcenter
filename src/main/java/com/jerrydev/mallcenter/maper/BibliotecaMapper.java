package com.jerrydev.mallcenter.maper;

import com.jerrydev.mallcenter.dto.BibliotecaDTO;
import com.jerrydev.mallcenter.dto.LibroDTO;
import com.jerrydev.mallcenter.entity.Biblioteca;
import com.jerrydev.mallcenter.entity.Libro;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BibliotecaMapper {

        public BibliotecaDTO fromBiblioteca(Biblioteca biblioteca){

            List<LibroDTO> librosDTO = new ArrayList<>();
            for(Libro libro : biblioteca.getLibros()) librosDTO.add(fromLibro(libro));

            BibliotecaDTO bibliotecaDTO =  BibliotecaDTO.builder()
                    .id(biblioteca.getId())
                    .name(biblioteca.getName())
                    .libros(librosDTO)
                    .build();
            return bibliotecaDTO;
        }

        public Biblioteca fromBibliotecaDTO(BibliotecaDTO bibliotecaDTO){

            List<Libro> libros = new ArrayList<>();
            for(LibroDTO libroDTO : bibliotecaDTO.getLibros()) libros.add(fromLibroDTO(libroDTO));

            Biblioteca biblioteca =  Biblioteca.builder()
                    .name(bibliotecaDTO.getName())
                    .build();
            biblioteca.setLibros(libros);

            return biblioteca;
        }

        public LibroDTO fromLibro(Libro libro){
            return LibroDTO.builder()
                    .id(libro.getId())
                    .name(libro.getName())
                    .build();
        }

        public Libro fromLibroDTO(LibroDTO libroDTO){
            return Libro.builder()
                    .name(libroDTO.getName())
                    .build();
        }
}
