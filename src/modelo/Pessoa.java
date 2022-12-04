package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(length = 50)
	private String nome;
	@Column(length = 11)
	private String cpf;
	private String dtnascimento;
	@Column(length = 20)
	private String funcao;
	@ManyToMany(fetch = FetchType.LAZY,mappedBy = "funcionarios")
	private List<Filme> filmes = new ArrayList<>();
	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(cpf, other.cpf);
	}

	private Double cache;

	public Pessoa(String nome, String dtnascimento, String cpf, String funcao, Double cache) {
		super();
		this.nome = nome;
		this.dtnascimento = dtnascimento;
		this.funcao = funcao;
		this.cache = cache;
		this.cpf = cpf;
	}

	public Pessoa(String nome, String dtnascimento, String cpf, String funcao) {
		super();
		this.nome = nome;
		this.dtnascimento = dtnascimento;
		this.funcao = funcao;
		this.cache = 0.0;
		this.cpf = cpf;
	}

	public Pessoa() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDtnascimento() {
		return dtnascimento;
	}

	public void setDtnascimento(String dtnascimento) {
		this.dtnascimento = dtnascimento;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public List<Filme> getFilmes() {
		return filmes;
	}

	public void addFilme(Filme f) {
		this.filmes.add(f);
	}

	public Double getCache() {
		return cache;
	}

	public void setCache(Double cache) {
		this.cache = cache;
	}

	public void remover(Filme f) {
		filmes.remove(f);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<String> filmesFormatados() {
		List<String> novosFilmes = new ArrayList<>();
		for (Filme f : filmes) {
			novosFilmes.add(f.getNome());
		}
		return novosFilmes;
	}

	public String getCpf() {
		return this.cpf;
	}

	@Override
	public String toString() {
		String texto = "Nome: " + this.getNome() + "\nFunção: " + this.getFuncao() + "\nFilmes: ";
		if (this.getFilmes().isEmpty())
			texto += "Sem Filmes";
		else
			for (Filme f : this.getFilmes())
				texto += f.getNome() + ", ";
		return texto;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
