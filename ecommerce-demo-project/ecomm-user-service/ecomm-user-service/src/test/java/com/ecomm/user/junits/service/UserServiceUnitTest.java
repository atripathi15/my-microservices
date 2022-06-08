package com.ecomm.user.junits.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.web.server.ResponseStatusException;

import com.ecomm.user.entity.RoleMaster;
import com.ecomm.user.entity.UserDetail;
import com.ecomm.user.junits.util.UnitTestUtil;
import com.ecomm.user.model.UserVO;
import com.ecomm.user.repository.RoleMasterRepository;
import com.ecomm.user.repository.UserRepository;
import com.ecomm.user.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class UserServiceUnitTest {
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private RoleMasterRepository roleMasterRepository;
	
	@InjectMocks
	private UserServiceImpl userServiceImpl;	
	
	private UnitTestUtil unitTestUtil;
	
	private ModelMapper modelMapper;
	
	private List<UserDetail> userDetailList;
	
	private UserVO userVO;
	

	@BeforeAll
	public void setUp() {
		unitTestUtil = new UnitTestUtil();
		modelMapper = new ModelMapper();
	}
	
	@BeforeEach 
	public void init() {
		System.out.println("in init method");
		if (userDetailList == null) {
			userDetailList = unitTestUtil.populateUserList();
		}
		if (userServiceImpl.getModelMapper() == null) {
			userServiceImpl.setModelMapper(modelMapper);
		}
		if(userVO == null) {
			userVO = unitTestUtil.populateUserVO();
		}
	}
	
	@AfterEach 
    public void teardown() {
		System.out.println("in teardown method");
    }
	

	@Test
	public void getAllUsersSuccess() {		
		Mockito.when(this.userRepository.findAll()).thenReturn(userDetailList);
		List<UserVO> users = userServiceImpl.getAllUsers();
		assertThat(users).isNotNull().hasSize(2);
	}
	
	@Test
	public void getUserSuccess() {	
		Optional<UserDetail> userDetailOptional = Optional.of(userDetailList.get(0));
		Mockito.when(this.userRepository.findById(1)).thenReturn(userDetailOptional);
		UserVO result = userServiceImpl.getUser(1);
		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(1);
	}
	
	@Test
	public void getUserFailure() {	
		Optional<UserDetail> userDetailOptional = Optional.ofNullable(null);
		Mockito.when(this.userRepository.findById(1)).thenReturn(userDetailOptional);
		assertThrows(ResponseStatusException.class, () -> userServiceImpl.getUser(1));
	}
	
	@Test
	public void addUserSuccess() {
		RoleMaster rm = unitTestUtil.populateRoleMaster();
		Optional<RoleMaster> rmOptional = Optional.of(rm);
		Mockito.when(this.roleMasterRepository.findById(1)).thenReturn(rmOptional);
		UserDetail userDetail = new UserDetail();
		userDetail.setId(1);
		Mockito.when(this.userRepository.save(any(UserDetail.class))).thenReturn(userDetail);
		int result = userServiceImpl.addUser(userVO);
		assertThat(result).isEqualTo(1);
	}
	
	@Test
	public void addUserException() {
		Optional<RoleMaster> rmOptional = Optional.ofNullable(null);
		Mockito.when(this.roleMasterRepository.findById(1)).thenReturn(rmOptional);		
		assertThrows(ResponseStatusException.class, () -> userServiceImpl.addUser(userVO));
	}
	
	@Test
	public void updateUserSuccess() {
		Optional<UserDetail> userDetailOptional = Optional.of(userDetailList.get(0));
		Mockito.when(this.userRepository.findById(1)).thenReturn(userDetailOptional);
		UserDetail userDetail = new UserDetail();
		userDetail.setId(1);
		Mockito.when(this.userRepository.save(any(UserDetail.class))).thenReturn(userDetail);
		int result = userServiceImpl.updateUser(1, userVO);
		assertThat(result).isEqualTo(1);
	}
	
	@Test
	public void updateUserException() {
		Optional<UserDetail> userDetailOptional = Optional.ofNullable(null);
		Mockito.when(this.userRepository.findById(1)).thenReturn(userDetailOptional);			
		assertThrows(ResponseStatusException.class, () -> userServiceImpl.updateUser(1,userVO));
	}
	
	@Test
	public void deleteUserSuccess() {
		Mockito.doNothing().when(this.userRepository).deleteById(any(Integer.class));
		userServiceImpl.deleteUser(1);
	}

}
