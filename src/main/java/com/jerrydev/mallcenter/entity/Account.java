package com.jerrydev.mallcenter.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_accounts")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    @Column(name = "email", unique = true, nullable = false)
    private String email ;

    @Column(name = "password", nullable = false)
    private String password ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id",referencedColumnName = "id", nullable = false)
    private Manager manager ;

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
