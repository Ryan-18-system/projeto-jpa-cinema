package appconsole;

import java.util.List;

import modelo.Estudio;
import modelo.Pessoa;
import regra.negocio.Fachada;

public class Consultar {
	public Consultar() {
	
		try {
			System.out.println("Pesquisando se o estúdio possuem atrizes/diretoras");
			Fachada.inicializar();
			List<Pessoa> atrizes = Fachada.atrizDiretoraTrabalhamEmUmEstudio("universal pictures", "diretora");
			for(Pessoa p : atrizes) {
				System.out.println(p.getNome() + " - " + p.getFuncao());
			}
			System.out.println("Pesquisando estúdio pelo Filme");
			Estudio estudio = Fachada.pesquisarEstudioPeloFilme("simplesmente acontece");
			System.out.println(estudio.getNome());
		}catch (Exception e) {
			System.out.println(e);
		}
		Fachada.finalizar();
	}
	public static void main(String[] args) {
		new Consultar();
	}
}
