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

import com.ashish.product.model.Seller;
import com.ashish.product.repository.SellerRepository;

@RestController
@RequestMapping("/api/v1/Seller")
public class SellerController {
	@Autowired
	private SellerRepository sellerRepository;
		
	@PostMapping()
    public String saveSeller(@RequestBody Seller seller){
		sellerRepository.save(seller);        
        return "Added Successfully";
    }
   
    @GetMapping()
    public List<Seller> getAllSellers() {        
        return sellerRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Seller getSeller(@PathVariable Long id) {        
        return sellerRepository.findById(id).orElse(null);
    }
  
    @DeleteMapping("/{id}")
    public String deleteSeller(@PathVariable Long id){
    	sellerRepository.deleteById(id);        
        return "Deleted Successfully";
    }


}
