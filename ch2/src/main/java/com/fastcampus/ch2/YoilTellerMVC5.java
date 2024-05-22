package com.fastcampus.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

// 년월일을 입력하면 요일을 알려주는 프로그램
@Controller
public class YoilTellerMVC5 { 
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex) {//예외처리
		ex.printStackTrace();
		return "yoilError";
	}
	
	@RequestMapping("/getYoilMVC5") // http://localhost/ch2/getYoilMVC5?year=2021&month=10&day=1
//	public String main(@ModelAttribute("myDate") MyDate date, Model model) throws IOException {	// 아래와 동일
	public String main(@ModelAttribute MyDate date, Model model) throws IOException {	//@ModelAttribute 생략가능
		
		// 1. 유효성 검사
		if(!isValid(date)) {
			return "yoilError";
		}
		
		// 2. 요일 계산
//		char yoil=getYoil(date);
		
		// 3. 계산한 결과를 model에 저장
//		model.addAttribute("myDate", date);
//		model.addAttribute("yoil", yoil);
		
		// 4. 결과를 보여줄 view를 지정
		return "yoil"; //WEB-INF/views/yoil.jsp

	}//main
	
	private boolean isValid(MyDate date) {
		return isValid(date.getYear(), date.getMonth(), date.getDay());
	}

	private @ModelAttribute("yoil") char getYoil(MyDate date) { //@ModelAttribute("yoil") char 반환타입에 적용. key값을 "yoil"하고 호출결과를 값으로 모델에 저장 => main 호출X
		return getYoil(date.getYear(), date.getMonth(), date.getDay());
	}

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance(); // Calendar 인스턴스
		cal.set(year, month - 1, day);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // DAY_OF_WEEK : 요일을 숫자로 1:일요일 ~ 7:토요일
		return " 일월화수목금토".charAt(dayOfWeek);
	}

	private boolean isValid(int year, int month, int day) {
		if(year==-1 || month==-1 || day==-1) 
    		return false;
    	
    	return (1<=month && month<=12) && (1<=day && day<=31); // 간단히 체크 
	}

}//class