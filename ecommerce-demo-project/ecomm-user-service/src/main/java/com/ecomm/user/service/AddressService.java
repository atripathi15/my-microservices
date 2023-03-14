package com.ecomm.user.service;

import java.util.List;

import com.ecomm.user.model.UserAddressVO;

public interface AddressService {

	int addAddress(int userId, UserAddressVO userAddressVO);

	List<UserAddressVO> getAllUserAddresses(int userId);

	UserAddressVO getAddress(int userId, int addressId);

	int updateAddress(int userId, UserAddressVO userAddressVO);

	void deleteAddress(int addressId);



}
