package com.t9.bsshop.repository;


import com.t9.bsshop.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Plant,Long> {

}
