package appconsole;

import regra.negocio.Fachada;

public class Cadastro {
	public Cadastro() {
		System.out.println("cadastrando...");
		try {
			Fachada.inicializar();
			System.out.println("Cadastrando Atrizes e Atores.....");
			Fachada.criarPessoa("Laisa Galleti", "10/07/2002", "63899854012", "atriz", 60000.0);
			Fachada.criarPessoa("Maria Rita", "24/02/1999", "34930011000", "atriz", 40000.0);
			Fachada.criarPessoa("João Paulo", "24/02/1998", "86897118052", "ator", 1000.0);
			Fachada.criarPessoa("Pedrinho", "22/05/2000", "54697755006", "ator", 100.0);
			Fachada.criarPessoa("Paola", "10/12/1969", "81175072001", "atriz", 70000.0);
			Fachada.criarPessoa("Ryan Nóbrega", "25/04/2002", "07137978002", "diretor", 80000.0);
			
			System.out.println("Cadastrando Filmes.....");
			Fachada.criarFilme("simplesmente acontece", "24/05/2014");
			Fachada.criarFilme("The Batman", "23/01/2012");
			Fachada.criarFilme("Projeto X", "12/02/2010");
			Fachada.criarFilme("Homem de aço", "13/12/2007");
			
		
			
			System.out.println("Cadastrando Estudio.....");
			Fachada.criarEstudio("warner bros", "98251140000137");
			Fachada.criarEstudio("universal pictures", "13933688000104");
			Fachada.criarEstudio("sony pictures", "15700043000157");
			/*==========================================================*/
			System.out.println("Adicionando filmes aos atores.....");
			
			Fachada.addAtorAoFilme("63899854012", "simplesmente acontece");
			Fachada.addAtorAoFilme("34930011000", "simplesmente acontece");
			Fachada.addAtorAoFilme("86897118052", "simplesmente acontece");
			Fachada.addAtorAoFilme("07137978002", "simplesmente acontece");
			
			Fachada.addAtorAoFilme("54697755006", "The Batman");
			Fachada.addAtorAoFilme("07137978002", "The Batman");
			Fachada.addAtorAoFilme("81175072001", "The Batman");
			Fachada.addAtorAoFilme("63899854012", "The Batman");
			Fachada.addAtorAoFilme("86897118052", "The Batman");
			
			Fachada.addAtorAoFilme("63899854012", "Projeto X");
			Fachada.addAtorAoFilme("07137978002", "Projeto X");
			Fachada.addAtorAoFilme("81175072001", "Projeto X");
			Fachada.addAtorAoFilme("54697755006", "Projeto X");
			Fachada.addAtorAoFilme("34930011000", "Projeto X");
			Fachada.addAtorAoFilme("86897118052", "Projeto X");
			
			
			Fachada.addAtorAoFilme("63899854012", "Homem de aço");
			Fachada.addAtorAoFilme("07137978002", "Homem de aço");
			Fachada.addAtorAoFilme("86897118052", "Homem de aço");
			Fachada.addAtorAoFilme("54697755006", "Homem de aço");
			/*==========================================================*/
			
			
			System.out.println("Adicionando Estudio aos Filmes.....");
			Fachada.addEstudioAoFilme("warner bros", "Projeto X");
			Fachada.addEstudioAoFilme("warner bros", "The Batman");
			Fachada.addEstudioAoFilme("sony pictures", "Homem de aço");
			Fachada.addEstudioAoFilme("universal pictures", "simplesmente acontece");
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Fachada.finalizar();
	}
	public static void main (String[] args) 
	{
		new Cadastro();
	}
}
