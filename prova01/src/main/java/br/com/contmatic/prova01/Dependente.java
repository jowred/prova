package br.com.contmatic.prova01;

import java.util.List;

public class Dependente extends Pessoa {
	private Funcionario provedor;
	private String parentesco;
	private int idade;
	
	public Dependente() {
		provedor = new Funcionario();
		setParentesco("Não declarado");
	}

	public Dependente(String nome, String rg, String cpf) {
		super(nome, rg, cpf);
		provedor = new Funcionario();
	}
	
	public Funcionario getProvedor() {
		return provedor;
	}

	public void setProvedor(Funcionario provedor) {
		if(provedor == null)
			throw new NullPointerException("Provedor não pode ser nulo.");
		
		this.provedor = provedor;
	}

	public String getParentesco() {
		return parentesco;
	}

	public void setParentesco(String parentesco) {
		if(parentesco == null)
			throw new NullPointerException("Parentesco do dependente não pode ser nulo.");
		
		if(parentesco.equals(""))
			throw new IllegalArgumentException("Parentesco do dependente não pode estar em branco.");
		
		int qtdeLetras = 0;
		for(int i=0; i<parentesco.length(); i++)
			if(Character.isAlphabetic(parentesco.charAt(i)))
				qtdeLetras++;
		
		if(qtdeLetras < 3 || parentesco.length() > 55)
			throw new IllegalArgumentException("Parentesco do dependente deve ter no mínimo 3 e no máximo 55 caracteres, e ter ao menos 3 letras.");
		
		for(int i=0; i<parentesco.length(); i++)
			if(!Character.isAlphabetic(parentesco.charAt(i)) &&
					(parentesco.charAt(i) != ' '))
				throw new IllegalArgumentException("Parentesco do dependente pode ser composto apenas por letras e espaços.");
		
		String temp = parentesco.toLowerCase();
		char primeiro = temp.charAt(0);
		
		int repetidos = 0;
		for(int i=0; i<parentesco.length()-1; i++) {
			if(primeiro == temp.charAt(i+1))
				repetidos++;
			else
				break;
		}
		if(repetidos == parentesco.length()-1)
			throw new IllegalArgumentException("Parentesco do dependente não pode ser composto unicamente pelo mesmo caractere.");
		
		this.parentesco = parentesco;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		if(idade < 0 || idade > 120)
			throw new IllegalArgumentException("Idade do dependente deve ser de 0 a 120.");
		
		this.idade = idade;
	}

	@Override
	public String toString() {
		return super.toString() + "\n"
				+ "Idade: " + idade + "\n"
				+ "Provedor: " + provedor.getNome() + "\n"
				+ "Parentesco: " + parentesco;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + idade;
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

	public boolean cadastrar(Funcionario fun) {
		if(fun == null)
			throw new NullPointerException("O funcionário passado como parâmetro não pode ser nulo.");
		
		List<Dependente> dep = fun.getDependentes();
		for(int i=0; i<fun.getDependentes().size(); i++)
			if(dep.get(i).equals(this))
				throw new IllegalArgumentException("O funcionário passado como parâmetro já possui este dependente cadastrado.");
		
		provedor = fun;
		return fun.getDependentes().add(this);
	}
}
