package com.jerrydev.mallcenter.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_locals")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Local {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    @NotNull
    private String name ;

    @NotNull
    private int floor;
    @NotNull
    private boolean enable;


    @OneToMany(mappedBy = "local",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<Manager> managerList = new ArrayList<>();

    @ManyToMany(mappedBy = "localList", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Customer> customerList = new ArrayList<>();

    @OneToMany(mappedBy = "local", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> productList = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    public void setManagerList(List<Manager> managerList) {
        this.managerList = managerList;
        for(Manager manager:managerList){
            manager.setLocal(this);
        }
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
        for(Customer customer:customerList) customer.getLocalList().add(this);
    }
    public void addCustomer(Customer customer){
        this.customerList.add(customer);
        customer.getLocalList().add(this);
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
        for(Product product:productList) product.setLocal(this);
    }
}
