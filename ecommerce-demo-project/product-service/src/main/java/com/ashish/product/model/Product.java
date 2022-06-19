package com.ashish.product.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "product") 
public class Product {
	@Transient
    public static final String SEQUENCE_NAME = "product_sequence";
	@Id private Long id;
    private String name;
    private String brand;
    private String description;
    private double price;
    private String imageURL;
    @DBRef
    private Seller seller;
    private Set<ProductDetails> productDetails = new HashSet<>();

}
