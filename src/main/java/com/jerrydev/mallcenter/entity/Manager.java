package com.jerrydev.mallcenter.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_managers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column(name = "firs_name",nullable = false)
    private String firstName;

    @Column(nullable = false)
    private boolean enable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "local_id", referencedColumnName = "id")
    private Local local;

    @OneToMany(mappedBy = "manager", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
        for(Account account: accounts) account.setManager(this);
    }
}
