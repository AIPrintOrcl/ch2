package com.fastcampus.ch2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// 년월일을 입력하면 요일을 알려주는 프로그램
@Controller
public class YoilTeller {

//	public static void main(String[] args) {
	@RequestMapping("/getYoil")
	public static void main(HttpServletRequest request, HttpServletResponse response) throws IOException {	// http://localhost:8080/ch2/getYoil?year=2021&month=10&day=1
		// 1. 입력
		String year= request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		
		// 2. 작업
		int yyyy = Integer.parseInt(year);
		int mm = Integer.parseInt(month);
		int dd = Integer.parseInt(day);
		
		Calendar cal = Calendar.getInstance(); // Calendar 인스턴스
		cal.set(yyyy, mm - 1, dd);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // DAY_OF_WEEK : 요일을 숫자로 1:일요일 ~ 7:토요일
		char yoil = " 일월화수목금토".charAt(dayOfWeek);
		
		// 3. 출력
		response.setContentType("text/html"); // 브라우저에 출력할 내용의 타입이 뭔지 알려줘야한다.
		response.setCharacterEncoding("utf-8"); // 브라우저에 출력할 인코딩이 뭔지 알려줘야한다. 한글깨짐 방지
		PrintWriter out = response.getWriter(); // response객체에서 브라우져로의 출력 스트림을 얻는다.
		out.println(year + "년 " + month + "월" + day + "일은");
		out.println(yoil + "요일입니다.");

	}//main

}//class