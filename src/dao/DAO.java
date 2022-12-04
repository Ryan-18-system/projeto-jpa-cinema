package dao;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;


public abstract class DAO<T> implements DAOInterface<T> {
	protected static EntityManager manager;

	//grava no arquivo de log 
	protected static final Log logger = LogFactory.getLog(DAO.class);

	public DAO(){}

	public static void open(){
		if(manager==null){		//padr�o Singleton
			manager = Util.conectarBanco();
		}
	}

	public static void close(){
		if(manager != null && manager.isOpen()) {
			EntityManagerFactory factory = manager.getEntityManagerFactory();
			manager.close();
			factory.close();
			manager=null;
		}
	}
	public void create(T obj){
		manager.persist(obj);
	}
	public abstract T read(Object chave);

	public T update(T obj){
		return manager.merge(obj);
	}
	public void delete(T obj) {
		manager.remove(obj);
	}


	@SuppressWarnings("unchecked")
	public List<T> readAll(){
		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];

		TypedQuery<T> query = manager.createQuery("select x from " + type.getSimpleName() + " x",type);
		return  query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> readAllPagination(int firstResult, int maxResults) {
		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];

		return manager.createQuery("select x from " + type.getSimpleName() + " x",type)
				.setFirstResult(firstResult - 1)
				.setMaxResults(maxResults)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public void deleteAll(){
		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];

		String tabela = type.getSimpleName();
		Query query = manager.createQuery("delete from " + tabela);
		query.executeUpdate();
	}

	public static Connection getConnectionJdbc() {
		try {
			//para acesso via jdbc
			EntityManagerFactory factory = manager.getEntityManagerFactory();
			String driver = (String) factory.getProperties().get("jakarta.persistence.jdbc.driver");
			String url = (String)	factory.getProperties().get("jakarta.persistence.jdbc.url");
			String user = (String)	factory.getProperties().get("jakarta.persistence.jdbc.user");
			String pass = (String)	factory.getProperties().get("jakarta.persistence.jdbc.password");
			Class.forName(driver);

			return DriverManager.getConnection(url, user, pass);
		} 
		catch (Exception ex) {
			return null;
		}
	}

	//----------------------- TRANSA��O   ----------------------
	public static void begin(){
		if(!manager.getTransaction().isActive())
			manager.getTransaction().begin();
	}
	public static void commit(){
		if(manager.getTransaction().isActive()){
			manager.getTransaction().commit();
			manager.clear();		// ---- esvazia o cache de objetos ----
		}
	}
	public static void rollback(){
		if(manager.getTransaction().isActive())
			manager.getTransaction().rollback();
	}

	public void lock(T obj) {
		//usado somente no controle de concorrencia persimista
		manager.lock(obj, LockModeType.PESSIMISTIC_WRITE); 
	}

}
