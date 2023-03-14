package com.ecomm.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecomm.user.entity.CityMaster;
import com.ecomm.user.entity.CountryMaster;
import com.ecomm.user.entity.StateMaster;
import com.ecomm.user.entity.UserAddress;
import com.ecomm.user.entity.UserDetail;
import com.ecomm.user.model.UserAddressVO;
import com.ecomm.user.repository.AddressRepository;
import com.ecomm.user.repository.CityMasterRepository;
import com.ecomm.user.repository.CountryMasterRepository;
import com.ecomm.user.repository.StateMasterRepository;
import com.ecomm.user.repository.UserRepository;
import com.ecomm.user.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private CityMasterRepository cityMasterRepository;
	@Autowired
	private StateMasterRepository stateMasterRepository;
	@Autowired
	private CountryMasterRepository countryMasterRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public int addAddress(int userId, UserAddressVO userAddressVO) {
		UserAddress userAddress = modelMapper.map(userAddressVO, UserAddress.class);
		if (userAddress.getCity() != null) {
			int cityId = userAddress.getCity().getId();
			CityMaster cm = cityMasterRepository.findById(cityId)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
							"City with id : " + cityId + " does not exists"));
			userAddress.setCity(cm);
		}
		if (userAddress.getState() != null) {
			int stateId = userAddress.getState().getId();
			StateMaster sm = stateMasterRepository.findById(userAddress.getState().getId())
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
							"State with id : " + stateId + " does not exists"));
			userAddress.setState(sm);
		}
		if (userAddress.getCountry() != null) {
			int countryId = userAddress.getCountry().getId();
			CountryMaster country = countryMasterRepository.findById(countryId)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
							"Country with id : " + countryId + " does not exists"));
			userAddress.setCountry(country);
		}
		if (userAddress.getUser() != null) {
			UserDetail userDetail = userRepository.findById(userId)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
							"User with id : " + userId + " does not exists"));
			userAddress.setUser(userDetail);
		}

		userAddress = addressRepository.save(userAddress);
		return userAddress != null ? userAddress.getId() : -1;
	}

	@Override
	public List<UserAddressVO> getAllUserAddresses(int userId) {
		List<UserAddressVO> userAddressVOList = new ArrayList<>();
		List<UserAddress> userAddresses = addressRepository.findByUserId(userId);
		userAddresses.forEach(u-> userAddressVOList.add(modelMapper.map(u, UserAddressVO.class)));
		return userAddressVOList;
	}

	@Override
	public UserAddressVO getAddress(int userId, int addressId) {
		UserAddressVO userAddressVO = null;
		UserAddress userAddress = addressRepository.findById(addressId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						"Address with id : " + addressId + " does not exists"));

		if (userAddress != null) {
			userAddressVO = modelMapper.map(userAddress, UserAddressVO.class);
		}
		return userAddressVO;
	}

	@Override
	public int updateAddress(int userId, UserAddressVO userAddressVO) {
		UserDetail existingUserDetail = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User with id : " + userId +" does not exists"));
		UserAddress updatedUserAddress = modelMapper.map(userAddressVO, UserAddress.class);
		updatedUserAddress.setUser(existingUserDetail);
		updatedUserAddress = addressRepository.save(updatedUserAddress);
		return updatedUserAddress != null ? updatedUserAddress.getId() : -1;
	}

	@Override
	public void deleteAddress(int addressId) {
		addressRepository.deleteById(addressId);
	}

}
