package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.data.FilmDAO;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {
	@Autowired
	private FilmDAO filmDAO;
	
	@RequestMapping("home.do")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("WEB-INF/home.jsp");
		return mv;
		
	}
	@RequestMapping(path="getFilmData.do", params="filmID", method=RequestMethod.GET)
	public ModelAndView getFilmData(int filmID) {
		ModelAndView mv = new ModelAndView("WEB-INF/film.jsp");
		mv.addObject("film", filmDAO.findFilmById(filmID));
		return mv;
		
	}
	@RequestMapping(path="NewFilmPage.do", method=RequestMethod.GET)
	public ModelAndView getFilmPage() {
		ModelAndView mv = new ModelAndView("WEB-INF/modifyFilm.jsp");
		mv.addObject("film", new Film());
		mv.addObject("redirectName", "NewFilm.do");
		return mv;
		
	}
	@RequestMapping(path="modifyFilm.do", params="filmId", method=RequestMethod.GET)
	public ModelAndView editFilm(int filmId) {
		ModelAndView mv = new ModelAndView("WEB-INF/modifyFilm.jsp");
		mv.addObject("film", filmDAO.findFilmById(filmId));
		mv.addObject("redirectName", "updateFilm.do");
		return mv;
		
	}
	@RequestMapping(path="NewFilm.do", method=RequestMethod.POST)
	public ModelAndView createFilm(Film film) {
		ModelAndView mv = new ModelAndView("WEB-INF/film.jsp");
		mv.addObject("film", filmDAO.createFilm(film));
		return mv;
		
	}
	@RequestMapping(path="updateFilm.do", method=RequestMethod.POST)
	public ModelAndView updateFilm(Film film) {
		ModelAndView mv = new ModelAndView("WEB-INF/film.jsp");
		mv.addObject("film", filmDAO.updateFilm(film));
		return mv;
		
	}

}