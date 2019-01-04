package com.qa.persistence.repository;

public interface FilmRepository {

	String getAllFilms();

	String createFilm(String film);

	String deleteFilm(Long id);
}
