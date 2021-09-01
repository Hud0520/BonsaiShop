package com.t9.bsshop.repository;

import com.t9.bsshop.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account,Long> {
	Optional<Account> findByEmail(String email);
}
