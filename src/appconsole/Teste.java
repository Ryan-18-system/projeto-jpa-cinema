package appconsole;


import java.time.LocalDate;

import dao.Util;
import jakarta.persistence.EntityManager;
import modelo.Estudio;
import modelo.Filme;
import modelo.Pessoa;

public class Teste {

	private static EntityManager manager;
	
	public Teste() {
		manager = Util.conectarBanco();
		System.out.println("Cadastrando teste");

		Estudio e1, e2;
		Pessoa p1, p2, p3, p4;
		Filme f1, f2;
		manager.getTransaction().begin();
		e1 = new Estudio("Warner", "42567768000191");
		
		p1 = new Pessoa("Ryan",LocalDate.of(2002, 4, 25),"10990106403","Diretor",2000.0);
		p2 = new Pessoa("Alexandra",LocalDate.of(1976, 11, 6),"83970219415","Atriz",3000.0);
//		
//		f1 = new Filme("Batman");
//		f2 = new Filme("Avatar");
//		f1.setEstudio(e1);
//		f1.addFuncionario(p1);
//		f1.addFuncionario(p2);
//		
//		manager.persist(f1);
//		manager.getTransaction().commit();
//		
//		manager.getTransaction().begin();
//		e2 = new Estudio("Estudios Gibli", "42567768100191");
//		p3 = new Pessoa("Giovanni",LocalDate.of(1980, 4, 28),"03496491035","Ator",5000.0);
//		p4 = new Pessoa("Toinha",LocalDate.of(1974, 4, 11),"13952880035","Atriz",3000.0);
//		f2.setEstudio(e2);
//		f2.addFuncionario(p3);
//		f2.addFuncionario(p4);
//		manager.persist(f2);
		manager.remove(p1);
		manager.getTransaction().commit();
	
		
		manager.close();
		System.out.println("fim do programa");
		

	}

	public static void main(String[] args) {
		new Teste();

	}

}
