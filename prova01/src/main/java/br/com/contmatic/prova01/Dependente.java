package br.com.contmatic.prova01;

import java.util.List;

public class Dependente extends Pessoa {
	
	private static final int MAX_IDADE = 120;

	private static final int MIN_IDADE = 0;

	private Funcionario provedor;
	
	private String parentesco;
	
	private int idade;
	
	public Dependente() {
		this.provedor = new Funcionario();
		this.setParentesco("Não declarado");
	}

	public Dependente(String nome, String rg, String cpf) {
		super(nome, rg, cpf);
		this.provedor = new Funcionario();
		this.setParentesco("Não declarado");
	}
	
	public Funcionario getProvedor() {
		return provedor;
	}

	public void setProvedor(Funcionario provedor) {
		checkProvedorNulo(provedor);		
		this.provedor = provedor;
	}

	public String getParentesco() {
		return parentesco;
	}

	public void setParentesco(String parentesco) {
		checkParentescoNulo(parentesco);		
		checkParentescoVazio(parentesco);		
		checkParentescoQuantidadeLetras(parentesco);		
		checkParentescoCaracteresValidos(parentesco);
		checkParentescoCompostoPorUmaUnicaLetra(parentesco);		
		this.parentesco = parentesco;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		checkIdadeValida(idade);
		this.idade = idade;
	}

	public boolean cadastrar(Funcionario fun) {
		if(fun == null) {
			throw new NullPointerException("O funcionário passado como parâmetro não pode ser nulo.");
		}
		List<Dependente> dep = fun.getDependentes();
		for(int i=0; i<fun.getDependentes().size(); i++)
			if(dep.get(i).equals(this))
				throw new IllegalArgumentException("O funcionário passado como parâmetro já possui este dependente cadastrado.");
		this.provedor = fun;
		return fun.getDependentes().add(this);
	}
	
	private void checkProvedorNulo(Funcionario provedor) {
		if(provedor == null) {
			throw new NullPointerException("Provedor não pode ser nulo.");
		}
	}
	
	private void checkParentescoCompostoPorUmaUnicaLetra(String parentesco) {
		String temp = parentesco.toLowerCase();
		char primeiro = temp.charAt(0);		
		int repetidos = 0;
		for(int i=0; i<parentesco.length()-1; i++) {
			if(primeiro == temp.charAt(i+1)) {
				repetidos++;
			} else {
				break;
			}
		}
		if(repetidos == parentesco.length()-1) {
			throw new IllegalArgumentException("Parentesco do dependente não pode ser composto unicamente pelo mesmo caractere.");
		}
	}

	private void checkParentescoCaracteresValidos(String parentesco) {
		for(int i=0; i<parentesco.length(); i++) {
			if(!Character.isAlphabetic(parentesco.charAt(i)) &&
					(parentesco.charAt(i) != ' ')) {
				throw new IllegalArgumentException("Parentesco do dependente pode ser composto apenas por letras e espaços.");
			}
		}
	}

	private void checkParentescoQuantidadeLetras(String parentesco) {
		int qtdeLetras = 0;
		for(int i=0; i<parentesco.length(); i++)
			if(Character.isAlphabetic(parentesco.charAt(i)))
				qtdeLetras++;		
		if(qtdeLetras < 3 || parentesco.length() > 55)
			throw new IllegalArgumentException("Parentesco do dependente deve ter no mínimo 3 e no máximo 55 caracteres, e ter ao menos 3 letras.");
	}

	private void checkParentescoVazio(String parentesco) {
		if(parentesco.equals(""))
			throw new IllegalArgumentException("Parentesco do dependente não pode estar em branco.");
	}

	private void checkParentescoNulo(String parentesco) {
		if(parentesco == null)
			throw new NullPointerException("Parentesco do dependente não pode ser nulo.");
	}
	
	private void checkIdadeValida(int idade) {
		if(idade < MIN_IDADE || idade > MAX_IDADE) {
			throw new IllegalArgumentException("Idade do dependente deve ser de 0 a 120.");
		}
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
	
	@Override
	public String toString() {
		return super.toString() + "\n"
				+ "Idade: " + idade + "\n"
				+ "Provedor: " + provedor.getNome() + "\n"
				+ "Parentesco: " + parentesco;
	}
}
