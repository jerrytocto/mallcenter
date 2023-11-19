package com.jerrydev.mallcenter.repository;

import com.jerrydev.mallcenter.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager,Integer> {
}
