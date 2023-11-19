package com.jerrydev.mallcenter.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_personas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "fiestas")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    private String name ;

    private int edad ;

    @ManyToMany
    @JsonBackReference
    @JoinTable(name = "persona_fiesta",
            joinColumns = @JoinColumn(name = "persona_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "fiesta_id", referencedColumnName = "id")
    )
    private List<Fiesta> fiestas = new ArrayList<>();


    @OneToMany(mappedBy = "persona",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Habilidad> habilidads = new ArrayList<>();

    public void setHabilidads(List<Habilidad> habilidads) {
        this.habilidads = habilidads;
        for(Habilidad habilidad:habilidads){
            habilidad.setPersona(this);
        }
    }
}
