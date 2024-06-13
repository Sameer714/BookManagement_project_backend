package com.example.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.login.model.Books;

@Repository
public interface BookRepo extends JpaRepository<Books, Long>{
	Books findById(long id);
	List<Books> findAll();
}