package com.ecomm.user.service;

import java.util.List;

import com.ecomm.user.model.UserVO;

public interface UserService {

	int addUser(UserVO userVO);

	List<UserVO> getAllUsers();

	UserVO getUser(int userId);

	int updateUser(int userId, UserVO userVO);

	void deleteUser(int userId);

}
