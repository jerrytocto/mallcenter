package com.jerrydev.mallcenter.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_fiestas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Fiesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    private String name ;

    private String address ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "persona_fiesta",
            joinColumns = @JoinColumn(name = "fiesta_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "persona_id", referencedColumnName = "id")
    )
    private List<Persona> personas = new ArrayList<>();

}
