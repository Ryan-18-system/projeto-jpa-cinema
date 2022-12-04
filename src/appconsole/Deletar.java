package appconsole;

import regra.negocio.Fachada;

public class Deletar {
	public Deletar() {
		System.out.println("apagando...");
		try {
			Fachada.inicializar();

			//Fachada.apagarFilme("The Batman");
			Fachada.apagarFilmePorId(3);
			System.out.println("apagou projetoX");

		} catch (Exception e) {
			System.out.println("--->" + e.getMessage());
		}

		Fachada.finalizar();
	}

	public static void main(String[] args) {
		new Deletar();
	}
}
