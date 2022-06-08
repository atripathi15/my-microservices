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

import com.ecomm.user.entity.RoleMaster;
import com.ecomm.user.entity.UserDetail;
import com.ecomm.user.model.UserVO;
import com.ecomm.user.repository.RoleMasterRepository;
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

	@Override
	@Transactional
	public int addUser(UserVO  userVO) {
		log.info("Add user method invoked");
		UserDetail userDetail = modelMapper.map(userVO, UserDetail.class);
		RoleMaster rm = userDetail.getRoleMaster();
		RoleMaster roleMaster = roleMasterRepository.findById(rm.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User role with id : " + rm.getId() +" does not exists"));
		if (roleMaster != null) {
			userDetail.setRoleMaster(roleMaster);
			userDetail = userRepository.save(userDetail);
		}
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
