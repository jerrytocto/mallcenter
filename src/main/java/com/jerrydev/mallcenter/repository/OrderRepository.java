package com.jerrydev.mallcenter.repository;

import com.jerrydev.mallcenter.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Optional<List<Order>> findByCustomerId(int idCustomer);
}
