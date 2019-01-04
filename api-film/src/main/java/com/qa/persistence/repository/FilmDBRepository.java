package com.qa.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.*;

import com.qa.persistence.domain.Film;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class FilmDBRepository implements FilmRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;

	public String getAllFilms() {
		Query query = manager.createQuery("SELECT f FROM Film f");
		Collection<Film> films = (Collection<Film>) query.getResultList();
		return util.getJSONForObject(films);
	}

	@Transactional(REQUIRED)
	public String createFilm(String film) {
		Film aFilm = util.getObjectForJSON(film, Film.class);
		manager.persist(aFilm);
		return "{\"message\": \"account has be successfully added\"}";
	}

	@Transactional(REQUIRED)
	public String deleteFilm(Long id) {
		Film filmInDB = findFilm(id);
		if (filmInDB != null) {
			manager.remove(filmInDB);
		}
		return "{\"message\": \"account successfully deleted\"}";
	}

	private Film findFilm(Long id) {
		return manager.find(Film.class, id);
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

}
