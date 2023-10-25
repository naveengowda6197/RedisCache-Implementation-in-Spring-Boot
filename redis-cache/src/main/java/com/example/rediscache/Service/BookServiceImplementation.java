package com.example.rediscache.Service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.rediscache.Domain.BookDto;
import com.example.rediscache.Entity.Book;
import com.example.rediscache.Repository.BookRepository;

@Service
@EnableCaching
public class BookServiceImplementation implements BookService {
	@Autowired
	private BookRepository bookRepository;
	
	
	@Override
	@CacheEvict(allEntries = true,cacheNames = "bookdto")
	public Book save(Book book) {
		//redisTemplate.opsForHash().put("books", book.getId(), book);
		return bookRepository.save(book);	
	}

	
	
	@Override
	@Cacheable(cacheNames = "bookdto",key = "#id")
	//@Cacheable(cacheNames = "book",key = "#id")
	//@Cacheable
	public BookDto getById(Integer id) {
		System.out.println(" DB hit ");
		Book b= bookRepository.getById(id);
		BookDto bookDto=new BookDto(b.getId(),b.getBookname());
		return bookDto;	
	}

	@Override
	@CacheEvict(cacheNames = "bookdto", key = "#book.id")
	public void update(Book book)  {
		System.out.println("fetch from db");
		Integer bookid=book.getId();
		Book book1=bookRepository.findById(bookid).orElse(null);
		book1.setBookname(book.getBookname());
		bookRepository.save(book1);	
	}
}
