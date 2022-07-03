package com.example.rediscache.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.rediscache.Domain.BookDto;
import com.example.rediscache.Entity.Book;
import com.example.rediscache.Service.BookService;
@RestController
public class RedisCacheController {
	@Autowired
	private BookService bookService; 
	
	@PostMapping("/save")
	public Book createBook(@RequestBody Book book) {
		return bookService.save(book);
	}
	
	@GetMapping("/get/{id}")
	public BookDto getBook(@PathVariable Integer id ) {
		
		return bookService.getById(id);
	}
	
	@PostMapping("/update")
	public ResponseEntity<String> updateBook(@RequestBody Book book){
		bookService.update(book);
		return ResponseEntity.ok("success");
	}
	
	@GetMapping("/evict")
	@CacheEvict(allEntries = true,cacheNames = "bookdto")
	public void evict() {
		System.out.println("evicted");
	}
}
