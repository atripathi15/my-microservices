package com.ashish.product.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "seller") 
public class Seller {	
	@Transient
    public static final String SEQUENCE_NAME = "seller_sequence";
	@Id private Long id;
    private String name;
    private String website;
    private String address;
    private String emailAddress;
}
