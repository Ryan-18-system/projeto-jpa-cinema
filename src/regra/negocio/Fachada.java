package regra.negocio;

import java.time.LocalDate;
import java.util.List;

import dao.DAO;
import dao.DAOEstudio;
import dao.DAOFilme;
import dao.DAOPessoa;
import modelo.Estudio;
import modelo.Filme;
import modelo.Pessoa;

public class Fachada {
	private Fachada() {
	}

	private static DAOEstudio daoEstudio = new DAOEstudio();
	private static DAOFilme daoFilme = new DAOFilme();
	private static DAOPessoa daoPessoa = new DAOPessoa();

	public static void inicializar() {
		DAO.open();
	}

	public static void finalizar() {
		DAO.close();
	}

	public static List<Filme> listarFilmes() {
		DAO.begin();
		List<Filme> result = daoFilme.readAll();
		DAO.commit();
		return result;
	}

	public static List<Estudio> listarEstudios() {
		DAO.begin();
		List<Estudio> result = daoEstudio.readAll();
		DAO.commit();
		return result;
	}

	public static List<Pessoa> listarAtores() {
		DAO.begin();
		List<Pessoa> result = daoPessoa.readAll();
		DAO.commit();
		return result;
	}

	public static Pessoa localizarPessoa(String cpf) throws Exception {
		DAO.begin();
		String newCpf = cpf.trim();
		Pessoa pe = daoPessoa.readByCpf(newCpf);
		if (pe != null) {
			DAO.commit();
			return pe;
		} else {
			DAO.rollback();
			throw new Exception("Este ator/atriz não existe");
		}

	}

	public static Estudio LocalizarEstudio(String cnpj) {
		DAO.begin();
		String newCnpj = cnpj.trim();
		Estudio es = daoEstudio.readByCnpj(newCnpj);
		DAO.commit();
		return es;
	}

	public static Filme LocalizarFilme(String nome) throws Exception {
		DAO.begin();
		Filme filme = daoFilme.readByNome(nome);
		if (filme != null) {
			DAO.commit();
			return filme;
		} else {
			DAO.rollback();
			throw new Exception("O filme " + nome + " não existe");
		}
	}

	public static List<Filme> LocalizarFilmesPorData(LocalDate data) throws Exception {
		DAO.begin();
		List<Filme> filmesPorData = daoFilme.readByLocalDate(data);
		DAO.commit();
		return filmesPorData;

	}
//
	public static Pessoa criarPessoa(String nome, LocalDate dtnascimento, String cpf, String funcao, Double cache)
			throws Exception {
		nome = nome.trim();
		cpf = cpf.trim();
		funcao = funcao.trim();
		DAO.begin();
		Pessoa pessoa = daoPessoa.readByCpf(cpf);
		if (pessoa != null) {
			DAO.rollback();
			throw new Exception("Atriz/Ator já existe, por favor cadastre um novo Ator/Atriz");
		}
		if (cache < 0) {
			DAO.rollback();
			throw new Exception("cache nao pode ser negativo " + cache);
		}
		if (cpf.isEmpty()) {
			DAO.rollback();
			throw new Exception("cpf não pode está vazio" + cpf);
		}

		pessoa = new Pessoa(nome, dtnascimento, cpf, funcao, cache);
		daoPessoa.create(pessoa);
		DAO.commit();
		return pessoa;
	}
//
	public static Estudio criarEstudio(String nome, String cnpj) throws Exception {
		nome = nome.trim();
		cnpj = cnpj.trim();
		DAO.begin();
		Estudio estudio = daoEstudio.readByCnpj(cnpj);
		if (estudio != null) {
			DAO.rollback();
			throw new Exception("Esse Estúdio já existe, cadastre um novo estúdio");
		}
		estudio = new Estudio(nome, cnpj);
		daoEstudio.create(estudio);
		DAO.commit();
		return estudio;
	}
//
	public static Filme criarFilme(String nome) throws Exception {
		nome = nome.trim();
		DAO.begin();
		Filme filme = daoFilme.readByNome(nome);
		if (filme != null) {
			DAO.rollback();
			throw new Exception("Esse filme já existe, cadastre um novo filme");
		}
		filme = new Filme(nome);
		daoFilme.create(filme);
		DAO.commit();
		return filme;
	}
//
	public static void addAtorAoFilme(String cpf, String nomeFilme) throws Exception {
		nomeFilme = nomeFilme.trim();
		cpf = cpf.trim();

		DAO.begin();
		// localizar ator no repositorio, usando o cpf
		Pessoa p = daoPessoa.readByCpf(cpf);
		if (p == null) {
			DAO.rollback();
			throw new Exception("N�o adicionou ator/atriz " + cpf + " - inexistente");
		}

		// localizar filme no repositorio, usando nome
		Filme filme = daoFilme.readByNome(nomeFilme);
		if (filme == null) {
			DAO.rollback();
			throw new Exception("N�o adicionou participante " + cpf + " - filme " + nomeFilme + " inexistente");
		}

		// localizar o participante dentro do filme, usando o cpf
		Pessoa pessoaAux = filme.localizarAtor(p.getCpf());
		if (pessoaAux != null) {
			DAO.rollback();
			throw new Exception("N�o adicionou participante " + cpf + " - já participa do filme" + nomeFilme);
		}
		// adicionar o ator ao filme
		filme.addFuncionario(p);
		// adicionar o filme ao ator
		p.addFilme(filme);
		// atualizar objetos no banco
		daoFilme.update(filme);
		daoPessoa.update(p);
		DAO.commit();

	}
//
	public static void addEstudioAoFilme(String cnpj, String nomeFilme) throws Exception {
		cnpj = cnpj.trim();
		nomeFilme = nomeFilme.trim();

		DAO.begin();
		Estudio estudio = daoEstudio.readByCnpj(cnpj);
		if (estudio == null) {
			DAO.rollback();
			throw new Exception("Não adicionou estúdio" + cnpj + " inexistente");
		}
		Filme filme = daoFilme.readByNome(nomeFilme);
		if (filme == null) {
			DAO.rollback();
			throw new Exception("Não adicionou estúdio" + estudio.getNome() + " ao filme, " + nomeFilme + " inexistente");
		}
		Filme filmeaux = estudio.localizarFilme(nomeFilme);
		if (filmeaux != null) {
			DAO.rollback();
			throw new Exception("Não adicionou filme ao Estúdio, " + nomeFilme + " já faz parte desse estúdio");
		}
		filme.setEstudio(estudio);
		estudio.addFilme(filme);
		daoFilme.update(filme);
		daoEstudio.update(estudio);
		DAO.commit();

	}
//
	public static void editarFuncao(String cpf, String funcao) throws Exception {
		DAO.begin();
		cpf = cpf.trim();
		funcao = funcao.trim();

		DAO.begin();
		Pessoa pessoa = daoPessoa.readByCpf(cpf);
		if (pessoa == null) {
			DAO.rollback();
			throw new Exception("Não trocou a função, pessoa inexistente: " + cpf);
		}
		pessoa.setFuncao(funcao);
		daoPessoa.update(pessoa);
		DAO.commit();

	}
//
	public static void apagarFilme(String nomeFilme) throws Exception {
		DAO.begin();
		nomeFilme = nomeFilme.trim();
		Filme filme = daoFilme.readByNome(nomeFilme);
		if (filme == null) {
			DAO.rollback();
			throw new Exception("Impossível apagar este filme, ele não existe");
		}
		Estudio estudio = filme.getEstudio();
		for (Pessoa p : filme.getFuncionarios()) {
			p.remover(filme);
			daoPessoa.update(p);
		}

		estudio.removerFilme(filme);
		daoEstudio.update(estudio);
		daoFilme.delete(filme);
		DAO.commit();
	}
//
	public static void apagarPessoa(String cpf) throws Exception {
		DAO.begin();
		cpf = cpf.trim();
		Pessoa pessoa = daoPessoa.readByCpf(cpf);
		if (pessoa == null) {
			DAO.rollback();
			throw new Exception("Impossível apagar este ator/atriz, ele/ela não existe");
		}
		for (Filme filme : pessoa.getFilmes()) {
			filme.removerPessoa(pessoa);
			daoFilme.update(filme);
		}
			
		
		daoPessoa.delete(pessoa);
		DAO.commit();
	}
//
	public static void apagarFilmePorId(Long id) throws Exception {
		DAO.begin();
		Filme filme = daoFilme.read(id);
		if (filme == null) {
			DAO.rollback();
			throw new Exception("Impossível apagar este filme, ele não existe");
		}
		Estudio estudio = filme.getEstudio();
		for (Pessoa p : filme.getFuncionarios()) {
			p.remover(filme);
			daoPessoa.update(p);
		}
		if (estudio.getFilmes().contains(filme)) {
			estudio.removerFilme(filme);
			daoEstudio.update(estudio);
		}
		daoFilme.delete(filme);
		DAO.commit();
	}
//	
	public static List<Pessoa> atrizDiretoraTrabalhamEmUmFilme()throws Exception{
		DAO.begin();
		List<Pessoa> pessoas = daoPessoa.atrizDiretoraTrabalhamEmUmFilme();
		if(pessoas.isEmpty()) {
			DAO.rollback();
			throw new Exception("Esse estúdio não possui diretoras nem atrizes");
		}
		DAO.commit();
		return pessoas;
	}
	public static Estudio pesquisarEstudioPeloFilme(String nome)throws Exception{
		DAO.begin();
		Filme filme = daoFilme.readByNome(nome);
		if(filme == null) {
			DAO.rollback();
			throw new Exception("Este Filme não existe");
		}
		DAO.commit();
		return filme.getEstudio();
		
	}
	public static List<Estudio> estudiosComMaisDe2Filmes()throws Exception{
		DAO.begin();
		List<Estudio> estudios = daoEstudio.estudiosComMaisDe2Filmes();
		if(estudios.size()==0) {
			DAO.rollback();
			throw new Exception("Não existe estúdio com mais de 2 filmes");
		}
		DAO.commit();
		return estudios;
	}

}
