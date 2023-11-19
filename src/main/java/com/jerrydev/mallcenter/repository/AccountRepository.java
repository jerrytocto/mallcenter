package com.jerrydev.mallcenter.repository;

import com.jerrydev.mallcenter.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {

    List<Account> findByEmailIn(Set<String> emails);
}
