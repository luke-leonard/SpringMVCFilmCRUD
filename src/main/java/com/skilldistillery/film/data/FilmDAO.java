package com.skilldistillery.film.data;

import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Category;
import com.skilldistillery.film.entities.Film;


public interface FilmDAO {
	  public Film findFilmById(int filmId);
	  public List<Film> findFilmByKeyWord(String keyword);
	  public Actor findActorById(int actorId);
	  public List<Actor> findActorsByFilmId(int filmId);
	  public List<Category> findCategoryByFilmId(int filmId);
	  
	  public Film createFilm(Film film);
	  public Film updateFilm(Film film);
	  public boolean deleteFilm(Film film);
}
