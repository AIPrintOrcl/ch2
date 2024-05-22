package com.fastcampus.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// 년월일을 입력하면 요일을 알려주는 프로그램
@Controller
public class YoilTellerMVC { // http://localhost/ch2/getYoilMVC?year=2021&month=10&day=1

	@RequestMapping("/getYoilMVC")
//	public void main(HttpServletRequest request, HttpServletResponse response) throws IOException {	
	public ModelAndView main(int year, int month, int day) throws IOException {	
		
		ModelAndView mv=new ModelAndView();
		
		// 1. 유효성 검사
		if(!isValid(year, month, day)) {
			mv.setViewName("yoilError");
			return mv;
		}
		
		// 2. 요일 계산
		getYoil(year, month, day);
		
		// 3. 계산한 결과를 model에 저장
		mv.addObject("year", year);
		mv.addObject("month", month);
		mv.addObject("day", day);
		
		// 4. 결과를 보여줄 view를 지정
		mv.setViewName("yoil");
		
		return mv; //WEB-INF/views/yoil.jsp

	}//main

	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance(); // Calendar 인스턴스
		cal.set(year, month - 1, day);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // DAY_OF_WEEK : 요일을 숫자로 1:일요일 ~ 7:토요일
		return " 일월화수목금토".charAt(dayOfWeek);
	}

	private boolean isValid(int year, int month, int day) {
		return true;
	}

}//class