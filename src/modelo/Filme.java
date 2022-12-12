package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Filme {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(length = 50)
	private String nome;
	private LocalDate dtLancamento;
	@ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
	private Estudio estudio;
	@ManyToMany(fetch = FetchType.LAZY,cascade ={CascadeType.MERGE,CascadeType.PERSIST})
	private List<Pessoa> funcionarios = new ArrayList<>();

	public Filme(String nome) {
		super();
		this.nome = nome;
		this.dtLancamento = LocalDate.now();

	}

	public Filme() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public LocalDate getDtLancamento() {
		return dtLancamento;
	}

	public void setDtLancamento(LocalDate dtLancamento) {
		this.dtLancamento = dtLancamento;
	}

	public Estudio getEstudio() {
		return estudio;
	}

	public void setEstudio(Estudio estudio) {
		this.estudio = estudio;
	}

	public void addFuncionario(Pessoa a) {
		this.funcionarios.add(a);
	}

	public List<Pessoa> getFuncionarios() {
		return this.funcionarios;
	}

	public void removerPessoa(Pessoa p) {
		funcionarios.remove(p);
	}

	public void removerEstudio() {
		estudio = null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Pessoa localizarAtor(String cpf) {
		for (Pessoa p : funcionarios) {
			if (p.getCpf() == cpf)
				return p;
		}
		return null;
	}

	public List<String> funcionariosFormatados() {
		List<String> novosFuncionarios = new ArrayList<>();
		for (Pessoa p : funcionarios) {
			novosFuncionarios.add(p.getNome());
		}
		return novosFuncionarios;
	}

	@Override
	public String toString() {
		String texto = "Nome: " + this.getNome() + "\ndata de lançamento " + this.getDtLancamento() + "\nFuncionario: ";
		if (this.getFuncionarios().isEmpty())
			texto += "Sem funcionarios";
		else
			for (Pessoa a : this.getFuncionarios())
				texto += a.getNome() + ": " + a.getFuncao() + ", ";
		return texto;
	}

}
