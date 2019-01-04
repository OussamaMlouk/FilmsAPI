package com.qa.business.service;

public interface FilmService {
	
	String getAllFilms();
	
	String addFilm(String film);
	
	String deleteFilm(Long id);

}
