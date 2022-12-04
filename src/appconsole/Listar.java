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
			Pessoa pessoa = Fachada.localizarPessoa("10990106403");
			System.out.println(pessoa.getNome());
			
			Estudio estudio = Fachada.LocalizarEstudio("42567768000191");
			System.out.println(estudio.getNome());
			
			List<Filme> filmesPorData = Fachada.LocalizarFilmesPorData(LocalDate.of(2022, 12, 3));
			for(Filme f: filmesPorData) {
				System.out.println(f.getNome());
			}
//			System.out.println("\n---------listagem de Atores/Atrizes -----");
//			for(Pessoa p : Fachada.listarAtores()) 
//				System.out.println(p);
//			
//			System.out.println("\n---------listagem de Filmes -----");
//			for(Filme f : Fachada.listarFilmes()) 
//				System.out.println(f);
//
//			System.out.println("\n---------listagem de Estúdios ");
//			for(Estudio e : Fachada.listarEstudios()) 
//				System.out.println(e);
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
