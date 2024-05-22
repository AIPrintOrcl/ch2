package com.fastcampus.ch2;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ExceptionController {
	
	@ExceptionHandler({NullPointerException.class, FileNotFoundException.class}) // NullPointerException 모든 예외처리를 별도의 예외처리할 메서드를 생성하여 관리한다.
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 상태코드를 200 -> 500으로 변경
	public String catcher2(Exception ex, Model m) {
		m.addAttribute("ex", ex);
		return "error";
	}
	
	@ExceptionHandler(Exception.class) // Exception 모든 예외처리를 별도의 예외처리할 메서드를 생성하여 관리한다.
	public String catcher(Exception ex, Model m) {
//		m.addAttribute("ex", ex); // error.jsp에 isErrorPage="true"을 하면 모델을 통해서 예외 객체를 전달하지 않아도 예외객체를 사용 가능하다.
		return "error";
	}
	
	@RequestMapping("/ex")
	public String main() throws Exception {
		throw new Exception("예외가 발생했습니다.");
	}
	
	@RequestMapping("/ex2")
	public String main2() throws Exception {
		throw new FileNotFoundException("예외가 발생했습니다.");
	}
	
}//class
