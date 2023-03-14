package com.ecomm.user.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ecomm.user.model.UserAddressVO;
import com.ecomm.user.service.AddressService;

@RestController
@RequestMapping("api/v1")
public class UserAddressController {

	@Autowired
	private AddressService addressService;

	@PostMapping("/user/{userId}/address")
	public ResponseEntity<Object> addAddress(@PathVariable int userId,
			@Valid @RequestBody UserAddressVO userAddressVO) {
		int addressId = addressService.addAddress(userId, userAddressVO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addressId).toUri();
		return ResponseEntity.created(uri).build();

	}

	@GetMapping("/user/{userId}/address")
	public ResponseEntity<List<UserAddressVO>> getAllUserAddresses(@PathVariable int userId) {
		List<UserAddressVO> userList = addressService.getAllUserAddresses(userId);
		return new ResponseEntity<List<UserAddressVO>>(userList, HttpStatus.OK);
	}

	@GetMapping("/user/{userId}/address/{addressId}")
	public ResponseEntity<UserAddressVO> getAddress(@PathVariable int userId, @PathVariable int addressId) {
		UserAddressVO userAddressVO = addressService.getAddress(userId, addressId);
		return new ResponseEntity<UserAddressVO>(userAddressVO, HttpStatus.OK);

	}

	@PutMapping("/user/{userId}/address/{addressId}")
	public ResponseEntity<Object> updateAddress(@Valid @RequestBody UserAddressVO userAddressVO,
			@PathVariable int userId, @PathVariable int addressId) {
		int uid = addressService.updateAddress(userId, userAddressVO);
		return new ResponseEntity<>("User Address updated", HttpStatus.OK);
	}

	@DeleteMapping("/user/{userId}/address/{addressId}")
	public ResponseEntity<Void> deleteAddress(@PathVariable int userId, @PathVariable int addressId) {
		addressService.deleteAddress(addressId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
