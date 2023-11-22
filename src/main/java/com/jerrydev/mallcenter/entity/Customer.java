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
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "customer_local",
            joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "local_id", referencedColumnName = "id")
    )
    private List<Local> localList = new ArrayList<>();

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();


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
    public  void  setOrders(List<Order> orders){
        this.orders = orders;
        for(Order order:orders) order.setCustomer(this);
    }
}
