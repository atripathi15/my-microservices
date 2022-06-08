package com.ecomm.user.junits.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ecomm.user.controller.UserController;
import com.ecomm.user.junits.util.UnitTestUtil;
import com.ecomm.user.model.UserVO;
import com.ecomm.user.service.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	@MockBean
	private UserService userService;
	
	@Autowired
	private MockMvc mockMvc;
	
    private static UnitTestUtil unitTestUtil;
	
	private static ModelMapper modelMapper;
	
	private static List<UserVO> userList;
	
	private UserVO userVO;
	

	@BeforeAll
	public static void setUp() {
		unitTestUtil = new UnitTestUtil();
		modelMapper = new ModelMapper();
		userList = unitTestUtil.populateUserVOList();
	}
	
	@BeforeEach 
	public void init() {
		System.out.println("in init method");
		
	}
	
	@Test
	void  getAllUsersSuccess() throws Exception {
		List<Integer> userIds = new ArrayList<>();
		userIds.add(userList.get(0).getId());
		userIds.add(userList.get(1).getId());
		Mockito.when(userService.getAllUsers()).thenReturn(userList);
		final String link = "/api/v1/user";
		this.mockMvc.perform(MockMvcRequestBuilders
				.get(link)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(userList.size())))
				.andExpect(jsonPath("$.[*].id", is(userIds)));
	}
	
	@Test
	void  getUserSuccess() throws Exception {
		Integer userId = 1;
		Mockito.when(userService.getUser(1)).thenReturn(userList.get(0));
		final String link = "/api/v1/user/"+userId;
		this.mockMvc.perform(MockMvcRequestBuilders
				.get(link)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.[*].id".toString(),is("1")));
	}

}
