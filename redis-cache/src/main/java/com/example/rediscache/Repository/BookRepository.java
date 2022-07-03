package com.example.rediscache.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rediscache.Entity.Book;
@Repository

public interface BookRepository extends JpaRepository<Book, Integer> {
	
}
