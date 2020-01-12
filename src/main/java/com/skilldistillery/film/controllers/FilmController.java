package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(path = "getFilmData.do", params = "filmSearch", method = RequestMethod.GET)
	public ModelAndView getFilmData(String filmSearch) {
		ModelAndView mv = new ModelAndView("WEB-INF/listFilms.jsp");
		try {
			Film databaseFilm = null;
			int filmId = Integer.parseInt(filmSearch);
			databaseFilm = filmDAO.findFilmById(filmId);
			mv.addObject("film", databaseFilm);
		} catch (NumberFormatException e) {
		}
		mv.addObject("films", filmDAO.findFilmByKeyWord(filmSearch));
		return mv;
	}
	
	@RequestMapping(path = "showFilm.do", params = "filmId", method = RequestMethod.GET)
	public ModelAndView showFilm(int filmId) {
		Film film = filmDAO.findFilmById(filmId);
		film.setActors(filmDAO.findActorsByFilmId(filmId));
		ModelAndView mv = new ModelAndView("WEB-INF/film.jsp");
		mv.addObject("film", film);
		return mv;
	}
	
	@RequestMapping(path = "NewFilmPage.do", method = RequestMethod.GET)
	public ModelAndView getFilmPage() {
		ModelAndView mv = new ModelAndView("WEB-INF/modifyFilm.jsp");
		mv.addObject("film", new Film());
		mv.addObject("redirectName", "NewFilm.do");
		return mv;

	}

	@RequestMapping(path = "modifyFilm.do", params = "filmId", method = RequestMethod.GET)
	public ModelAndView editFilm(int filmId) {
		ModelAndView mv = new ModelAndView("WEB-INF/modifyFilm.jsp");
		mv.addObject("film", filmDAO.findFilmById(filmId));
		mv.addObject("redirectName", "updateFilm.do");
		return mv;

	}

	@RequestMapping(path = "deleteFilm.do", params = "filmId", method = RequestMethod.POST)
	public ModelAndView deleteFilm(int filmId) {
		if (filmDAO.deleteFilm(filmDAO.findFilmById(filmId))) {
			ModelAndView mv = new ModelAndView("WEB-INF/home.jsp");
			return mv;
		} else {
			ModelAndView mv = new ModelAndView("WEB-INF/error.jsp");
			return mv;
		}

	}

	@RequestMapping(path = "NewFilm.do", method = RequestMethod.POST)
	public ModelAndView createFilm(Film film) {
		Film databaseFilm = filmDAO.createFilm(film);
		if (databaseFilm == null) {
			ModelAndView mv = new ModelAndView("WEB-INF/error.jsp");
			return mv;

		} else {
			ModelAndView mv = new ModelAndView("WEB-INF/film.jsp");
			mv.addObject("film", databaseFilm);
			return mv;
		}
	}

	@RequestMapping(path = "updateFilm.do", params = "filmId", method = RequestMethod.POST)
	public ModelAndView updateFilm(int filmId, Film film) {
		film.setId(filmId);
		Film databaseFilm = filmDAO.updateFilm(film);
		if (databaseFilm == null) {
			ModelAndView mv = new ModelAndView("WEB-INF/error.jsp");
			return mv;
		} else {
			ModelAndView mv = new ModelAndView("WEB-INF/home.jsp");
			mv.addObject("film", databaseFilm);
			return mv;
		}
	}

}
