package com.ahsan.validation.domain;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		User user = (User)target;
		if(user.getName().length() == 0) {
			errors.rejectValue("name", "error.user.name");
		}
		if(user.getPassword().length() < 8) {
			errors.rejectValue("password", "error.user.password");
		}
		if(user.getEmail().length() == 0) {
			errors.rejectValue("email", "error.user.email");
		}
		if(user.getPhone().length() == 0) {
			errors.rejectValue("phone", "error.user.phone");
		}
		if(errors.hasErrors()) {
			errors.reject("error.user");
		}
	}

}