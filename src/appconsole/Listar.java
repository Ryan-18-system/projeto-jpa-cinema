package appconsole;

import java.time.LocalDate;
import java.util.List;

import modelo.Estudio;
import modelo.Filme;
import modelo.Pessoa;
import regra.negocio.Fachada;

public class Listar {
	public Listar() {
		try {
			Fachada.inicializar();
			System.out.println("\n---------listagem de Atores/Atrizes -----");
			for(Pessoa p : Fachada.listarAtores()) 
				System.out.println(p);
			
			System.out.println("\n---------listagem de Filmes -----");
			for(Filme f : Fachada.listarFilmes()) 
				System.out.println(f);

			System.out.println("\n---------listagem de Estúdios ");
			for(Estudio e : Fachada.listarEstudios()) {
				e.getFilmes();
				System.out.println(e.toString());
			}
				
		} catch (Exception e) {
			System.out.println("--->"+e.getMessage());
		}	

		Fachada.finalizar();
	}

	public static void main (String[] args) 
	{
		new Listar();
	}

}
