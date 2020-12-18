package com.example.demo.persistence.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.persistence.domain.Todo;

@Repository
public interface TodoRepo extends JpaRepository<Todo, Long>{

	
	@Query(value = "SELECT * FROM TODO WHERE NAME =?1", nativeQuery = true)
	List<Todo> findByName(String name);
}
