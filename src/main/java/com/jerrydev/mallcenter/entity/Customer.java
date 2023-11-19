package com.jerrydev.mallcenter.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "localList")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column(name = "firs_name",nullable = false)
    private String firstName;

    @Column(name = "email",nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private boolean enable;

    @ManyToMany( fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "customer_local",
            joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "local_id", referencedColumnName = "id")
    )
    @NotEmpty
    private List<Local> localList = new ArrayList<>();


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    public void setLocalList(List<Local> localList) {
        this.localList = localList;
        for(Local local:localList) local.getCustomerList().add(this);
    }
}