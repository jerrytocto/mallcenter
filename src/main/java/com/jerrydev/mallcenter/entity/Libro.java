package com.jerrydev.mallcenter.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "tb_libros")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "biblioteca")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String name ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "biblioteca_id",referencedColumnName = "id")
    @NotNull
    @JsonBackReference
    private Biblioteca biblioteca ;


}
