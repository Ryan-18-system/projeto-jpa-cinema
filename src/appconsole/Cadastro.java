package appconsole;

import java.time.LocalDate;

import regra.negocio.Fachada;

public class Cadastro {
	public Cadastro() {
		System.out.println("cadastrando...");
		try {
			Fachada.inicializar();
			System.out.println("Cadastrando Atrizes e Atores.....");
			Fachada.criarPessoa("Laisa Galleti",LocalDate.of(2002, 7, 10), "63899854012", "atriz", 60000.0);
			Fachada.criarPessoa("Maria Rita",LocalDate.of(1999, 2, 24), "34930011000", "atriz", 40000.0);
			Fachada.criarPessoa("João Paulo",LocalDate.of(1998, 2, 24), "86897118052", "ator", 1000.0);
			Fachada.criarPessoa("Pedrinho",LocalDate.of(2000, 5, 22), "54697755006", "ator", 100.0);
			Fachada.criarPessoa("Paola",LocalDate.of(1969, 12, 10), "81175072001", "atriz", 70000.0);
			Fachada.criarPessoa("Ryan Nóbrega",LocalDate.of(2002, 4, 25), "07137978002", "diretor", 80000.0);
			
			System.out.println("Cadastrando Filmes.....");
			Fachada.criarFilme("simplesmente acontece");
			Fachada.criarFilme("The Batman");
			Fachada.criarFilme("Projeto X");
			Fachada.criarFilme("Homem de aço");
			
		
			
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
			Fachada.addEstudioAoFilme("98251140000137", "Projeto X");
			Fachada.addEstudioAoFilme("98251140000137", "The Batman");
			Fachada.addEstudioAoFilme("15700043000157", "Homem de aço");
			Fachada.addEstudioAoFilme("13933688000104", "simplesmente acontece");
			
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
