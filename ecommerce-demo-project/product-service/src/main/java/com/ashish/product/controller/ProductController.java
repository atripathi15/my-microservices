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

import com.ashish.product.model.Product;
import com.ashish.product.model.Seller;
import com.ashish.product.repository.ProductRepository;
import com.ashish.product.repository.SellerRepository;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private SellerRepository sellerRepository;
	
	
	@PostMapping()
	public String saveProduct(@RequestBody Product product) throws Exception {
		if (product.getSeller().getId() == null || product.getSeller().getId() < 1) {
			Seller newSeller = product.getSeller();
			newSeller = sellerRepository.save(newSeller);
			product.setSeller(newSeller);
		} else if (product.getSeller().getId() != null) {
			Seller existingSeller = sellerRepository.findById(product.getSeller().getId()).orElse(null);
			System.out.println("valid seller id");
			if(existingSeller==null) {
				throw new Exception("Invalid seller id");
			}
		}
			productRepository.save(product);
			return "Added Successfully";
	}
  
    @GetMapping()
    public List<Product> getAllProducts() {        
        return productRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {        
        return productRepository.findById(id).orElse(null);
    }
  
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
    	productRepository.deleteById(id);        
        return "Deleted Successfully";
    }
}
