package dao;



import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Filme;

public class DAOFilme extends DAO<Filme> {
	public Filme read(Object chave){
		try{
			Long id  = (Long) chave;
			TypedQuery<Filme> q = manager.createQuery("select f from Filme f where f.id = :x", Filme.class);
			q.setParameter("x", id);
			return q.getSingleResult();
			
		}catch(NoResultException e){
			return null;
		}
	}
	public List<Filme> readAll(){
		TypedQuery<Filme> query = manager.createQuery("select f from Filme f join fetch f.funcionarios", Filme.class);
		return  query.getResultList();
	}
	public Filme readByNome(String nomeFilme) {
		try {
			TypedQuery<Filme> query = manager.createQuery("select f from Filme f where f.nome = :nomeFilme",Filme.class);
			query.setParameter("nomeFilme",nomeFilme);
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
		
	}
	
	public List<Filme> readByLocalDate(LocalDate date){
		TypedQuery<Filme>query = manager.createQuery("select f from Filme f where f.dtLancamento = :date",Filme.class);
		query.setParameter("date", date);
		return query.getResultList();
	}
	
	
	
	

}
