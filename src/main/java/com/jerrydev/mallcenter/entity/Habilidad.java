package com.jerrydev.mallcenter.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Table(name = "tb_habilidades")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "persona")
public class Habilidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    @Enumerated(EnumType.STRING)
    private Nivel nivel;

    private String nombre ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    @JsonBackReference
    private Persona persona;

}
