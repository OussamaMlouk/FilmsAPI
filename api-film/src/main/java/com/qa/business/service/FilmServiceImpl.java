package com.qa.business.service;

import javax.inject.Inject;

import com.qa.persistence.repository.FilmRepository;

public class FilmServiceImpl implements FilmService {

	@Inject
	private FilmRepository repo;
	
	public String getAllFilms() {
		return repo.getAllFilms();
	}

	public String addFilm(String film) {
		if (film.equals("Titanic")) {
			return "{\"message\": \"Titanic is awful we are not adding this\"}";
		}
		return repo.createFilm(film);
	}

	public String deleteFilm(Long id) {
		return repo.deleteFilm(id);
	}
	
	public void setRepo(FilmRepository repo) {
		this.repo = repo;
	}

}
