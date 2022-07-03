package com.example.rediscache.Service;

import org.springframework.stereotype.Service;

import com.example.rediscache.Domain.BookDto;
import com.example.rediscache.Entity.Book;
@Service
public interface BookService {

	Book save(Book book);

	 BookDto getById(Integer id);

	void update(Book book) ;
}
