package com.ashish.product.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.ashish.product.config.RibbonConfig;
import com.ashish.product.model.UserVO;

//@FeignClient(name="user-service",url="localhost:8081")
//@FeignClient(name="user-service")
//@RibbonClient(name = "user-service", configuration = RibbonConfig.class)
public interface BuyerServiceProxy {
	
	@GetMapping("/userservice/api/v1/user")
	public List<UserVO> getAllBuyers();

}
