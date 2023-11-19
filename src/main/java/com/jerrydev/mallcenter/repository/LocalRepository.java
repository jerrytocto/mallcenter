package com.jerrydev.mallcenter.repository;

import com.jerrydev.mallcenter.entity.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalRepository extends JpaRepository<Local,Integer> {
}
