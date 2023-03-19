package com.ashish.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashish.product.model.UserVO;
import com.ashish.product.service.BuyerService;
import com.ashish.product.service.BuyerServiceProxy;
import com.ashish.product.service.BuyerServiceV2;

@RestController
@RequestMapping("/api/v1")
public class BuyerController {	
	
	@Autowired
	private BuyerService buyerService;
	
	/*
	 * @Autowired private BuyerServiceProxy buyerServiceProxy;
	 */
	
	@Autowired
	private BuyerServiceV2 buyerService2;

	@GetMapping("/buyer")
    public List<UserVO> getAllBuyers() {        
        return buyerService.getAllBuyers3();
    }
	
	@GetMapping("/buyerFeign")
    public List<UserVO> getAllBuyersFeign() {      
        //return buyerServiceProxy.getAllBuyers();
		return null;
    }
	
	@GetMapping("/buyer2")
    public List<UserVO> getAllBuyers2() {        
        return buyerService2.getAllBuyers();
    }
}
