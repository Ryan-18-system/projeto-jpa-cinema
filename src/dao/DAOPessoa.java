package dao;



import java.sql.Array;
import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Pessoa;

public class DAOPessoa extends DAO<Pessoa>{
	
	public Pessoa read(Object chave){
		try{
			int id  = (Integer) chave;
			TypedQuery<Pessoa> q = manager.createQuery("select p from Pessoa p where p.id = :x", Pessoa.class);
			q.setParameter("x", id);
			return q.getSingleResult();
			
		}catch(NoResultException e){
			return null;
		}
	}
	public List<Pessoa> readAll(){
		TypedQuery<Pessoa> query = manager.createQuery("select p from Pessoa p join fetch p.filmes", Pessoa.class);
		return  query.getResultList();
	}
	public Pessoa readByCpf(String cpf) {
		try {
			TypedQuery<Pessoa> query = manager.createQuery("select p from Pessoa p where p.cpf = :cpf",Pessoa.class);
			query.setParameter("cpf", cpf);
			return query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
		
	}
	
	public List<Pessoa>  atrizDiretoraTrabalhamEmUmFilme(){
		TypedQuery<Pessoa> q = manager.createQuery("select p from Pessoa p where p.funcao =:funcao1 or p.funcao =:funcao2 ", Pessoa.class);
		q.setParameter("funcao1","diretora");
		q.setParameter("funcao2", "atriz");
		return q.getResultList();
		
	}


}
