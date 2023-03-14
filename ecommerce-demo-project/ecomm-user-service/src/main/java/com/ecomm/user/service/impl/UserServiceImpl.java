package com.ecomm.user.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecomm.user.entity.CityMaster;
import com.ecomm.user.entity.CountryMaster;
import com.ecomm.user.entity.RoleMaster;
import com.ecomm.user.entity.StateMaster;
import com.ecomm.user.entity.UserAddress;
import com.ecomm.user.entity.UserDetail;
import com.ecomm.user.model.UserVO;
import com.ecomm.user.repository.CityMasterRepository;
import com.ecomm.user.repository.CountryMasterRepository;
import com.ecomm.user.repository.RoleMasterRepository;
import com.ecomm.user.repository.StateMasterRepository;
import com.ecomm.user.repository.UserRepository;
import com.ecomm.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleMasterRepository roleMasterRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CityMasterRepository cityMasterRepository;
	
	@Autowired
	private CountryMasterRepository countryMasterRepository;
	
	@Autowired
	private StateMasterRepository stateMasterRepository;

	@Override
	@Transactional
	public int addUser(UserVO  userVO) {
		log.info("Add user method invoked");
		UserDetail userDetail = modelMapper.map(userVO, UserDetail.class);
		RoleMaster rm = userDetail.getRoleMaster();
		RoleMaster roleMaster = roleMasterRepository.findById(rm.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User role with id : " + rm.getId() +" does not exists"));
		if (userDetail.getUserAddress() != null && userDetail.getUserAddress().size()>0) {
			for (UserAddress address : userDetail.getUserAddress()) {
				if (address.getCity() != null) {
					int cityId = address.getCity().getId();
					CityMaster cm = cityMasterRepository.findById(cityId)
							.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
									"City with id : " + cityId + " does not exists"));
					address.setCity(cm);
				}
				if (address.getState() != null) {
					int stateId = address.getState().getId();
					StateMaster sm = stateMasterRepository.findById(address.getState().getId())
							.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
									"State with id : " + stateId + " does not exists"));
					address.setState(sm);
				}
				if (address.getCountry() != null) {
					int countryId = address.getCountry().getId();
					CountryMaster country = countryMasterRepository
							.findById(countryId)
							.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
									"Country with id : " + countryId + " does not exists"));
					address.setCountry(country);
				}
				address.setUser(userDetail);
			}
		}
		
		if (roleMaster != null) {
			userDetail.setRoleMaster(roleMaster);
			
		}
		userDetail = userRepository.save(userDetail);
		log.info("Add user method finisshed successfully");
		return userDetail!=null?userDetail.getId():-1;
	}

	@Override
	@Cacheable(value = "userCache")
	public List<UserVO> getAllUsers() {
		List<UserDetail> userDetailList = userRepository.findAll();
		if(userDetailList!=null) {
			return userDetailList.stream().map(u -> {return modelMapper.map(u, UserVO.class);}).collect(Collectors.toList());
		}
		return null;
	}

	@Override
	@Cacheable(key="#userId", value = "userCache")
	public UserVO getUser(int userId) {
		UserVO userVO = null;
		UserDetail userDetail = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User with id : " + userId +" does not exists"));

		if (userDetail != null) {
			userVO = modelMapper.map(userDetail, UserVO.class);
		}
		return userVO;
	}

	@Override
	@Transactional
	public int updateUser(int userId, UserVO userVO){
		UserDetail existingUserDetail = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User with id : " + userId +" does not exists"));
		UserDetail updatedUserDetail = null;
		updatedUserDetail = modelMapper.map(userVO, UserDetail.class);
		updatedUserDetail.setId(existingUserDetail.getId());
		updatedUserDetail = userRepository.save(updatedUserDetail);
		return updatedUserDetail != null ? updatedUserDetail.getId() : -1;
	}

	@Override
	@Transactional
	public void deleteUser(int userId) {
		userRepository.deleteById(userId);		
	}

	public ModelMapper getModelMapper() {
		return modelMapper;
	}

	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	

}
