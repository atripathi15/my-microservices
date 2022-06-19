package com.ashish.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ashish.product.model.Book;

public interface BookRepository extends MongoRepository<Book, Integer> {


}
