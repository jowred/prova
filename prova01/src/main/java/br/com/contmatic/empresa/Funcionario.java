package br.com.contmatic.empresa;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.LocalDate;

public class Funcionario extends Pessoa {
	
	@NotEmpty(message = "A coleção de dependentes não pode ser nula ou vazia (tamanho 0).")
	private Set<Dependente> dependentes;
	
	@NotNull(message = "A data de admissão não pode ser nula.")
	@Past(message = "O valor deve refletir uma data do passado.")
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
