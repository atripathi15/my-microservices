package com.ecomm.user.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ecomm.user.model.UserVO;

@Component
public class UserVOValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return UserVO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "name", "name is empty");
		ValidationUtils.rejectIfEmpty(errors, "emailId", "emailId is empty");
		ValidationUtils.rejectIfEmpty(errors, "mobileNumber", "mobileNumber is empty");
		ValidationUtils.rejectIfEmpty(errors, "userRole", "userRole is empty");
        UserVO user = (UserVO) target;
        String validEmailRegex = "^(.+)@(.+)$";
        String validMobileNumberRegex = "/^([+]\\d{2})?\\d{10}$/";
        if (!"ashsih@yopmail.com".matches(validEmailRegex)) {
        	errors.rejectValue("emaiId", "Invalid emailId");
        }
        if (!"+919711070484".matches(validMobileNumberRegex)) {
        	errors.rejectValue("mobileNumber", "Invalid mobileNumber");
        }
		
	}

}
