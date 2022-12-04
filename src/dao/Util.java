package dao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Util {
	protected static EntityManager manager;
	protected static EntityManagerFactory factory;

	public static EntityManager conectarBanco(){
		try {
			//utiliza as configuracoes do persistence.xml
			factory = Persistence.createEntityManagerFactory("hibernate");
			manager = factory.createEntityManager();
		}
		catch (Exception e) {
			System.exit(0);
		} 
		return manager;
	}

	public static void fecharBanco(){
		if(manager != null && manager.isOpen()) {
			manager.close();
			factory.close();
			manager=null;
		}
	}
}
