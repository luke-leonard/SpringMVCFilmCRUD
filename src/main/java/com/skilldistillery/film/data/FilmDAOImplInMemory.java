package com.skilldistillery.film.data;

import java.util.List;

import org.springframework.stereotype.Component;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Category;
import com.skilldistillery.film.entities.Film;

@Component
public class FilmDAOImplInMemory implements FilmDAO {

	@Override
	public List<Category> findCategoryByFilmId(int filmId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Film findFilmById(int filmId) {
		return null;
	}

	@Override
	public List<Film> findFilmByKeyWord(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Actor findActorById(int actorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Film createFilm(Film film) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Film updateFilm(Film film) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteFilm(Film film) {
		// TODO Auto-generated method stub
		return false;
	}


}
