package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.data.FilmDAO;

@Controller
public class FilmController {
	@Autowired
	private FilmDAO filmDAO;
	
//	@RequestMapping("home.do")
//	public String doSomething() {
//		System.out.println("hello");
//		return "WEB-INF/home.jsp";
//	}
	
	@RequestMapping("home.do")
	public ModelAndView getFilmByID() {
		ModelAndView mv = new ModelAndView("WEB-INF/home.jsp");
		mv.addObject("data", filmDAO.findFilmById(1));
		return mv;
		
	}
	@RequestMapping("getFilmData.do")
	public ModelAndView getFilmData() {
		ModelAndView mv = new ModelAndView("WEB-INF/home.jsp");
		mv.addObject("data", filmDAO.findFilmById(1));
		return mv;
		
	}

}
