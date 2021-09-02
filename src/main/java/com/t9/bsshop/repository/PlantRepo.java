package com.t9.bsshop.repository;

import com.t9.bsshop.model.Category;
import com.t9.bsshop.model.Plant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantRepo extends PagingAndSortingRepository<Plant,Long> {
	Page<Plant> findAllByCategoryEquals(Category cat, Pageable p);
	@Query(value = "select * from plant where lower(name) like lower (:q) or lower (description) like lower (:q)",nativeQuery = true)
	List<Plant> search(@Param("q") String q);
}
