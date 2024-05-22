package com.fastcampus.ch2;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class GlobalValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
//		return User.class.equals(clazz); // 검증하려는 객체가 User타입인지 확인. 밑하고 같은 기능
		return User.class.isAssignableFrom(clazz); // clazz가 User 또는 그 자손인지 확인
	}

	@Override
	public void validate(Object target, Errors errors) { 
		System.out.println("GlobalValidator.validate() is called");

		User user = (User)target;
		
		String id = user.getId();
		
//		if(id==null || "".equals(id.trim())) {
//			errors.rejectValue("id", "required");
//		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id",  "required"); // required: 필수 값인데 입력하지 않을 경우 에러코드
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "required");
		
		if(id==null || id.length() <  5 || id.length() > 12) {
			errors.rejectValue("id", "invalidLength", new String[]{"", "5", "12"}, null); // invalidLength : 유효하지 않은 길이일 경우 에러코드
		}
	}

}//class