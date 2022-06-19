package com.ashish.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashish.product.model.Book;
import com.ashish.product.repository.BookRepository;

@RestController
@RequestMapping("/api/v1/product")
public class BookController {
	@Autowired
    private BookRepository repo;
  
    @PostMapping("/addBook")
    public String saveBook(@RequestBody Book book){
        repo.save(book);
        
        return "Added Successfully";
    }
  
    @GetMapping("/findAllBooks")
    public List<Book> getBooks() {
        
        return repo.findAll();
    }
  
    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id){
        repo.deleteById(id);
        
        return "Deleted Successfully";
    }
  

}
