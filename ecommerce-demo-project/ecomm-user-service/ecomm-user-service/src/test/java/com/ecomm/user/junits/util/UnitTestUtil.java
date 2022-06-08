package com.ecomm.user.junits.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ecomm.user.entity.RoleMaster;
import com.ecomm.user.entity.UserDetail;
import com.ecomm.user.model.RoleMasterVO;
import com.ecomm.user.model.UserVO;

@Component
public class UnitTestUtil {
	
	public List<UserDetail> populateUserList() {
		List<UserDetail> userDetailList = new ArrayList<>();
		RoleMaster role = new RoleMaster();
		role.setId(1);
		role.setName("BUYER");
		
		UserDetail userDetail1 = new UserDetail();
		userDetail1.setId(1);
		userDetail1.setName("test1");
		userDetail1.setEmailId("test@test.com");
		userDetail1.setMobileNumber("9988998877");
		userDetail1.setRoleMaster(role);
		
		UserDetail userDetail2 = new UserDetail();
		userDetail2.setId(1);
		userDetail2.setName("test2");
		userDetail2.setEmailId("test2@test.com");
		userDetail2.setMobileNumber("1111111111");
		userDetail2.setRoleMaster(role);
		
		userDetailList.add(userDetail1);
		userDetailList.add(userDetail2);
		return userDetailList;
	}
	
	public UserVO populateUserVO() {
		RoleMasterVO role = new RoleMasterVO();
		role.setId(1);
		role.setName("BUYER");
		UserVO userVO = new UserVO();
		userVO.setId(1);
		userVO.setName("test1");
		userVO.setEmailId("test@test.com");
		userVO.setMobileNumber("9988998877");
		userVO.setUserRole(role);
		return userVO;
	}
	
	public List<UserVO> populateUserVOList() {
		List<UserVO> userList = new ArrayList<>();
		RoleMasterVO role = new RoleMasterVO();
		role.setId(1);
		role.setName("BUYER");
		UserVO userVO = new UserVO();
		userVO.setId(1);
		userVO.setName("test1");
		userVO.setEmailId("test@test.com");
		userVO.setMobileNumber("9988998877");
		userVO.setUserRole(role);
		
		UserVO userVO2 = new UserVO();
		userVO2.setId(2);
		userVO2.setName("test2");
		userVO2.setEmailId("test2@test.com");
		userVO2.setMobileNumber("9988998800");
		userVO2.setUserRole(role);
		
		userList.add(userVO);
		userList.add(userVO2);
		
		return userList;
	}
	
	public RoleMaster populateRoleMaster() {
		RoleMaster role = new RoleMaster();
		role.setId(1);
		role.setName("BUYER");
		return role;
	}

}
