package com.ashish.product.model;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class UserVO {	
	private Integer id;
	private String name;
	private String emailId;
	private String mobileNumber;	
	private RoleMasterVO userRole;
}