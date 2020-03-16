package br.com.contmatic.empresa;

import static br.com.contmatic.constantes.Mensagens.MENSAGEM_DATA_ADMISSAO_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_DATA_ADMISSAO_PAST;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_SET_DEPENDENTES_VAZIO;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.LocalDate;

public class Funcionario extends Pessoa {
	
	@NotEmpty(message = MENSAGEM_SET_DEPENDENTES_VAZIO)
	private Set<Dependente> dependentes;
	// Fazer uma verificação usando o Guava para saber se a lista a ser inserida é igual à atual.
	
	@Past(message = MENSAGEM_DATA_ADMISSAO_PAST)
	@NotNull(message = MENSAGEM_DATA_ADMISSAO_BLANK)
	private LocalDate dataAdmissao;
	
	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(LocalDate dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public Funcionario() {
		dependentes = new HashSet<>();
	}
	
	public Funcionario(String nome, String rg, String cpf) {
		super(nome, rg, cpf);
		this.dependentes = new HashSet<>();
	}
	
	public Set<Dependente> getDependentes() {
		return dependentes;
	}

	public void setDependentes(Set<Dependente> dependentes) {
		checkSetDependentesNulo(dependentes);		
		checkSetDependentesVazio(dependentes);		
		checkNovoSetIgualAntigo(dependentes);		
		this.dependentes = dependentes;
	}

	public boolean cadastrar(Departamento depto) {
		checkDepartamentoNulo(depto);
		return depto.getFuncionarios().add(this);
	}

	private void checkDepartamentoNulo(Departamento depto) {
		if(depto == null) {
			throw new NullPointerException("O departamento passado como parâmetro não pode ser nulo.");
		}
	}
	
	private void checkNovoSetIgualAntigo(Set<Dependente> dependentes) {
		if(this.dependentes.equals(dependentes)) {
			throw new IllegalArgumentException("A lista de dependentes a ser inserida não pode ser idêntica à atual.");
		}
	}

	private void checkSetDependentesVazio(Set<Dependente> dependentes) {
		if(dependentes.isEmpty()) {
			throw new IllegalArgumentException("A lista de dependentes a ser inserida não pode estar vazia (tamanho 0).");
		}
	}

	private void checkSetDependentesNulo(Set<Dependente> dependentes) {
		if(dependentes == null) {
			throw new NullPointerException("A lista de dependentes não pode ser nula.");
		}
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
