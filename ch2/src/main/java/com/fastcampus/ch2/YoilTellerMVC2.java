package com.fastcampus.ch2;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class YoilTellerMVC2 {
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex) {
		return "yoilError";
	}
	
    @RequestMapping("/getYoilMVC2") // http://localhost/ch2/getYoilMVC2
    public String main(@RequestParam(required=true) int year, 
    		@RequestParam(required=true) int month, 
    		@RequestParam(required=true) int day, Model model) {
 
        // 1. 유효성 검사
    	if(!isValid(year, month, day)) 
    		return "yoilError";  // 유효하지 않으면, /WEB-INF/views/yoilError.jsp로 이동
    	
        // 2. 처리
    	char yoil = getYoil(year, month, day);

        // 3. Model에 작업 결과 저장
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("day", day);
        model.addAttribute("yoil", yoil);
        
        // 4. 작업 결과를 보여줄 View의 이름을 반환
        return "yoil"; // /WEB-INF/views/yoil.jsp
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
} 
