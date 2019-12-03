package com.uf.cad.entities.repositories;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.uf.cad.entities.Uf;

@Stateless
public class UfRepository {

	@PersistenceContext(unitName = "uf_PU")
	EntityManager em;

	public List getAllUfs() {
		return em.createNamedQuery("UF.findAll", Uf.class).getResultList();
	}

	public Uf findById(Long id) {
		return em.find(Uf.class, id);
	}

	public void create(Uf uf) {
		em.persist(uf);
	}

	public void update(Uf uf) {
		em.merge(uf);
	}

	public void delete(Uf uf) {
		if (!em.contains(uf)) {
			uf = em.merge(uf);
		}

		em.remove(uf);
	}

}
