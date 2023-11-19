package com.jerrydev.mallcenter.repository;

import com.jerrydev.mallcenter.entity.Fiesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiestaRepository extends JpaRepository<Fiesta,Integer> {
}
