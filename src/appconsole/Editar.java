package appconsole;

import regra.negocio.Fachada;

public class Editar {
	public Editar() {
		System.out.println("alterando...");
		try {
			Fachada.inicializar();			
			Fachada.editarFuncao("63899854012", "diretora"); //alterando função de Ryan que era diretor para ator
			System.out.println("alterou a função para diretora");

		} catch (Exception e) {
			System.out.println("--->"+e.getMessage());
		}		
		Fachada.finalizar();
	}
	public static void main (String[] args) 
	{
		new Editar();
	}
}
