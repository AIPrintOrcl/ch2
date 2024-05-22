package com.fastcampus.ch2;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {
	
	@InitBinder
	public void toDate(WebDataBinder binder) { // 커스텀 변환기. 날짜형식을 바꾸는 메서드.
		ConversionService conversionService = binder.getConversionService();
//		System.out.println("conversionService="+conversionService); // 기본 ConversionService를 출력
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // 형식 지정
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false)); // Date로 변환할 때 CustomDateEditor로 이용
		binder.registerCustomEditor(String[].class, new StringArrayPropertyEditor("#")); // String[] 중 #을 기준으로 구분.
//		binder.setValidator(new UserValidator()); // UserValidator를 WebDataBinder의 로컬 validator로 등록
//		binder.addValidators(new UserValidator()); // 글로벌 Validator와 로컬 Validator를 동시에 적용.
		List<Validator> validatorList = binder.getValidators();
		System.out.println("validatorList="+validatorList);
	}
	
	@RequestMapping(value="/register/add", method= {RequestMethod.GET, RequestMethod.POST}) // 신규회원 가입 화면
	public String register() {
		return "registerForm"; // WEB-INF/views/registerForm.jsp
	}
	
//	@RequestMapping(value="/register/save", method=RequestMethod.POST) // POST방식으로만 회원가입 가능하도록
	@PostMapping("/register/save") // 스프링 4.3부터
	public String save(@Valid User user, BindingResult result, Model m) throws Exception {
		System.out.println("result="+result);
		System.out.println("user="+user);
		
		
		// 수동 검증 - Validator를 직접 생성하고, validate()를 직접 호출
//		UserValidator userValidator = new UserValidator();
//		userValidator.validate(user, result); // BindingResult는 Errors의 자손
		
		// User객체를 검증한 결과 에러가 있으면, registerForm을 이용해서 에러를 보여줘야함.
		if(result.hasErrors()) {
			return "registerForm";
		}
		
//		// 1. 유효성 검사
//		if(!isValid(user)) {
//			String msg = URLEncoder.encode("id를 잘못입력하셨습니다.", "utf-8"); // 주소창에 한글이 깨지는 것을 인코딩 시킨다.
//			
//			m.addAttribute("mag", msg);
//			return "forward:/register/add"; // 밑에 있는 기능과 같다.
//			//return "redirect:/register/add?msg="+msg; // URL재작성(rewriting) : url을 내가 고친다.
//		}
		
		// 2. DB에 신규회원 정보를 저장
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return true;
	}
	
}//class
