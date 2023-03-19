package com.ashish.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ashish.product.model.UserVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BuyerServiceV2 {
	
	@Autowired
	@Qualifier("loadBalancedRestTemplate")
	private RestTemplate restTemplate;
	
	public List<UserVO> getAllBuyers() {
		ResponseEntity<List<UserVO>> responseEntity = null;
		List<UserVO> buyers = null;
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl("http://user-service/userservice/api/v1/user");
		responseEntity = restTemplate.exchange(uriComponentsBuilder.toUriString(), HttpMethod.GET,
				null, new ParameterizedTypeReference<List<UserVO>>() {});
		buyers = responseEntity.getBody();
		if (responseEntity.getBody() != null) {
			buyers = responseEntity.getBody().stream()
					.filter(user -> "BUYER".equalsIgnoreCase(user.getUserRole().getName()))
					.collect(Collectors.toList());
		}
		return buyers;
	}

}
