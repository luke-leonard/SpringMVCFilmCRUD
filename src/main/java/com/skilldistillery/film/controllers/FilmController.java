package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skilldistillery.film.data.FilmDAO;

@Controller
public class FilmController {
	@Autowired
	private FilmDAO filmDAO;
	
	@RequestMapping("home.do")
	public String doSomething() {
		System.out.println("hello");
		return "WEB-INF/home.jsp";
	}
}
