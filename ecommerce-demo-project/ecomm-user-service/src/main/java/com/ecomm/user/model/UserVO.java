package com.ecomm.user.model;

import java.io.Serializable;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVO implements Serializable {
	
	private static final long serialVersionUID = 7988112012213868566L;
	private Integer id;
	@NotBlank(message = "Name is mandatory")
	private String name;
	@NotBlank(message = "emailId is mandatory")
	@Email(message = "Invalid email")
	private String emailId;
	@NotBlank(message = "mobileNumber is mandatory")
	private String mobileNumber;	
	@Valid
	@NotNull(message = "userRole is mandatory")
	private RoleMasterVO userRole;
	private Set<UserAddressVO> userAddress;
}
