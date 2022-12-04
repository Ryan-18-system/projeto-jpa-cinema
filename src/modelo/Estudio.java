package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Estudio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(length = 50)
	private String nome;
	@Column(length = 14)
	private String cnpj;
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "estudio",cascade = CascadeType.REMOVE,orphanRemoval=true)
	private List<Filme> filmes = new ArrayList<>();

	@Override
	public int hashCode() {
		return Objects.hash(cnpj);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estudio other = (Estudio) obj;
		return Objects.equals(cnpj, other.cnpj);
	}

	public Estudio(String nome, String cnpj) {
		super();
		this.cnpj = cnpj;
		this.nome = nome;
	}

	public Estudio() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public List<Filme> getFilmes() {
		return filmes;
	}

	public void addFilme(Filme filme) {
		this.filmes.add(filme);
	}

	public void removerFilme(Filme f) {
		this.filmes.remove(f);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Filme localizarFilme(String nomeFilme) {
		for (Filme f : filmes) {
			if (f.getNome() == nomeFilme)
				return f;
		}
		return null;
	}

	public List<String> filmesFormatados() {
		List<String> listaRetorno = new ArrayList<>();
		for (Filme f : filmes) {
			listaRetorno.add(f.getNome());
		}
		return listaRetorno;
	}

	@Override
	public String toString() {
		String texto = "nome: " + this.getNome() + "\nFilmes:  ";
		if (this.getFilmes().isEmpty())
			texto += "Este estúdio não possui nenhum filme";
		else
			for (Filme f : this.getFilmes())
				texto += f.getNome() + ", ";
		return texto;
	}

}
