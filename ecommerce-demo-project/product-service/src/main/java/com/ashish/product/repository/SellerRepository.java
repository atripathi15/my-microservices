package com.ashish.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ashish.product.model.Seller;

@Repository
public interface SellerRepository extends MongoRepository<Seller, Long> {

}
