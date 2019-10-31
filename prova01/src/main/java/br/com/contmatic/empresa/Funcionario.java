package br.com.contmatic.empresa;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Funcionario extends Pessoa {
	
	private List<Dependente> dependentes;
	
	public Funcionario() {
		dependentes = new ArrayList<>();
	}
	
	public Funcionario(String nome, String rg, String cpf) {
		super(nome, rg, cpf);
		this.dependentes = new ArrayList<>();
	}
	
	public List<Dependente> getDependentes() {
		return dependentes;
	}

	public void setDependentes(List<Dependente> dependentes) {
		checkListaDependentesNula(dependentes);		
		checkListaDependentesVazia(dependentes);		
		checkNovaListaIgualAntiga(dependentes);		
		this.dependentes = dependentes;
	}

	public boolean cadastrar(Departamento depto) {
		checkDepartamentoNulo(depto);
		checkFuncionarioRepetido(depto);		
		return depto.getFuncionarios().add(this);
	}

	private void checkFuncionarioRepetido(Departamento depto) {
		List<Funcionario> f = depto.getFuncionarios();
		for(int i=0; i<f.size(); i++) {
			if(f.get(i).equals(this)) {
				throw new IllegalArgumentException("O departamento passado como parâmetro já possui este funcionário.");
			}
		}
	}

	private void checkDepartamentoNulo(Departamento depto) {
		if(depto == null) {
			throw new NullPointerException("O departamento passado como parâmetro não pode ser nulo.");
		}
	}
	
	private void checkNovaListaIgualAntiga(List<Dependente> dependentes) {
		if(this.dependentes.equals(dependentes)) {
			throw new IllegalArgumentException("A lista de dependentes a ser inserida não pode ser idêntica à atual.");
		}
	}

	private void checkListaDependentesVazia(List<Dependente> dependentes) {
		if(dependentes.isEmpty()) {
			throw new IllegalArgumentException("A lista de dependentes a ser inserida não pode estar vazia (tamanho 0).");
		}
	}

	private void checkListaDependentesNula(List<Dependente> dependentes) {
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
