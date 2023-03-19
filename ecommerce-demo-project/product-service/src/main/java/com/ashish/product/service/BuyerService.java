package com.ashish.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ashish.product.model.UserVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BuyerService {
	
	@Autowired
	@Qualifier("restTemplate")
	private RestTemplate restTemplate;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	public List<UserVO> getAllBuyers() {

		ResponseEntity<List<UserVO>> responseEntity = null;
		List<UserVO> buyers = null;
		try {
			responseEntity = restTemplate.exchange("http://localhost:8081/userservice/api/v1/user", HttpMethod.GET,
					null, new ParameterizedTypeReference<List<UserVO>>() {
					});
			buyers = responseEntity.getBody();
			if (responseEntity.getBody() != null) {
				buyers = responseEntity.getBody().stream()
						.filter(user -> "BUYER".equalsIgnoreCase(user.getUserRole().getName()))
						.collect(Collectors.toList());
			}
		} catch (RestClientException ce) {
			log.error("Exception occured while invoking get user api : " + ce.getMessage());
		}
		return buyers;
	}
	
	public List<UserVO> getAllBuyers2() {

		ResponseEntity<List<UserVO>> responseEntity = null;
		List<UserVO> buyers = null;
		try {
			responseEntity = restTemplate.exchange("http://userservice/userservice/api/v1/user", HttpMethod.GET,
					null, new ParameterizedTypeReference<List<UserVO>>() {
					});
			buyers = responseEntity.getBody();
			if (responseEntity.getBody() != null) {
				buyers = responseEntity.getBody().stream()
						.filter(user -> "BUYER".equalsIgnoreCase(user.getUserRole().getName()))
						.collect(Collectors.toList());
			}
		} catch (RestClientException ce) {
			log.error("Exception occured while invoking get user api : " + ce.getMessage());
		}
		return buyers;
	}
	
	public List<UserVO> getAllBuyers3() {
		ResponseEntity<List<UserVO>> responseEntity = null;
		List<UserVO> buyers = null;
		ServiceInstance instance = discoveryClient.getInstances("user-service").stream().findAny().orElseThrow(()-> new IllegalStateException("user-service not available"));
		responseEntity = restTemplate.exchange(instance.getUri()+"/userservice/api/v1/user", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<UserVO>>() {
				});
		buyers = responseEntity.getBody();
		if (responseEntity.getBody() != null) {
			buyers = responseEntity.getBody().stream()
					.filter(user -> "BUYER".equalsIgnoreCase(user.getUserRole().getName()))
					.collect(Collectors.toList());
		}
		return buyers;
	}

}
