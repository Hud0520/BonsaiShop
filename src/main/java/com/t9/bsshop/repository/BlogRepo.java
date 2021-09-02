package com.t9.bsshop.repository;

import com.t9.bsshop.model.Blog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepo extends  PagingAndSortingRepository<Blog,Long>,JpaRepository<Blog,Long> {
	List<Blog> findAllByOrderByIdAsc(Pageable p);
}
