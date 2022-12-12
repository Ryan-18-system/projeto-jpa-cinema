package dao;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Estudio;

public class DAOEstudio extends DAO<Estudio> {
	public Estudio read(Object chave) {
		try {
			int id = (Integer) chave;
			TypedQuery<Estudio> q = manager.createQuery("select e from Estudio e where e.id = :x", Estudio.class);
			q.setParameter("x", id);
			return q.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Estudio> readAll() {
		TypedQuery<Estudio> query = manager.createQuery("from Estudio e join fetch e.filmes", Estudio.class);
		return query.getResultList();
	}

	public Estudio readByCnpj(String cnpj) {
		try {
			TypedQuery<Estudio> query = manager.createQuery("select e from Estudio e where e.cnpj = :cnpj",
					Estudio.class);
			query.setParameter("cnpj", cnpj);
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}

	}
	public List<Estudio> estudiosComMaisDe2Filmes(){
		TypedQuery<Estudio> query = manager.createQuery("select e from Estudio e where size(e.filmes) >= 2",Estudio.class);
		return query.getResultList();
	}

}