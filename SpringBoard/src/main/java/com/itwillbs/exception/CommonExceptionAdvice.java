package com.itwillbs.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 *  보조기능(예외처리)를 구현한 객체
 */
//@ControllerAdvice : 컨트롤러에서 발생하는 예외를 처리하는 객체
@ControllerAdvice // 컨트롤러는 아니지만 컨트롤러에서 쓰던 거처럼 동작 가능
public class CommonExceptionAdvice {
	
	
	private static final Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);
	
	//@ExceptionHandler(Exception.class)
	// => () 괄호안에 있는 예외를 처리하는 동작(매핑)
	//@ExceptionHandler(NullPointerException.class)
	//@ExceptionHandler(ArrayIndexOutOfBoundsException.class)
	@ExceptionHandler(Exception.class)
	public String CommonException(Exception e,Model model) {
		logger.debug("CommonException() 실행");
		
		e.printStackTrace();
		model.addAttribute("e", e);
		
		return "commons";
	}
	
//	@ExceptionHandler(Exception.class)
//	public ModelAndView CommonException2(Exception e,Model model) {
//		logger.debug("CommonException2() 실행");
//		
//		e.printStackTrace();
//		//model.addAttribute("e", e);
//		//return "commons";
//		
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("commons"); // == return "commons";
//		mav.addObject("e", e);
//		
//		return mav;
//	}
	
}
