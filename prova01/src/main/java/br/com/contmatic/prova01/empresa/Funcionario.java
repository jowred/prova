package br.com.contmatic.prova01.empresa;

import java.util.ArrayList;
import java.util.List;

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
		if(depto == null)
			throw new NullPointerException("O departamento passado como parâmetro não pode ser nulo.");
		
		List<Funcionario> f = depto.getFuncionarios();
		for(int i=0; i<depto.getFuncionarios().size(); i++)
			if(f.get(i).equals(this))
				throw new IllegalArgumentException("O departamento passado como parâmetro já possui este funcionário.");
		
		return depto.getFuncionarios().add(this);
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
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + dependentes.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(!super.equals(obj))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
