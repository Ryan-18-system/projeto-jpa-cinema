package appconsole;

import java.util.List;

import modelo.Estudio;
import modelo.Filme;
import modelo.Pessoa;
import regra.negocio.Fachada;

public class Consultar {
	public Consultar() {
	
		try {
			System.out.println("Pesquisando Atrizes/Diretoras");
			Fachada.inicializar();
			List<Pessoa> funcionariasFemininas = Fachada.atrizDiretoraTrabalhamEmUmFilme();
			for(Pessoa p : funcionariasFemininas) {
				System.out.println("Atriz----> "+ p.getNome());
			}
			System.out.println("Pesquisando Estudios que tem mais de 2 filmes");
			List<Estudio> estudiosMais2Filmes = Fachada.estudiosComMaisDe2Filmes();
			for(Estudio e: estudiosMais2Filmes) {
				System.out.println(e.getNome());
			}
		}catch (Exception e) {
			System.out.println(e);
		}
		Fachada.finalizar();
	}
	public static void main(String[] args) {
		new Consultar();
	}
}
