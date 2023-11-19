package com.jerrydev.mallcenter.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_bibliotecas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Biblioteca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name ;

    @OneToMany(mappedBy = "biblioteca",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();


    public void setLibros(List<Libro> libros) {
        this.libros = libros;
        for(Libro libro:libros){
            libro.setBiblioteca(this);
        }
    }
}
