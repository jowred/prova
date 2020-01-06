package br.com.contmatic.empresa;

import static br.com.contmatic.constantes.Numericas.MAX_IDADE;
import static br.com.contmatic.constantes.Numericas.MAX_PARENTESCO;
import static br.com.contmatic.constantes.Numericas.MIN_IDADE;
import static br.com.contmatic.constantes.Numericas.MIN_PARENTESCO;
import static br.com.contmatic.constantes.Numericas.PRIMEIRO_INDICE;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;

public class Dependente extends Pessoa {
	
	@NotNull(message = "Provedor não pode ser nulo.")
	private Funcionario provedor;
	
	@Length(min = MIN_PARENTESCO, max = MAX_PARENTESCO, message = "Parentesco deve ter de {min} a {max} caracteres.")
	@NotBlank(message = "Parentesco não pode ser nulo ou vazio.")
	private String parentesco;
	
	@Min(value = MIN_IDADE, message = "Idade não pode ser menor que {min}.")
	@Max(value = MAX_IDADE, message = "Idade não pode ser maior que {max}.")
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
		checkFuncionarioNulo(fun);
		this.provedor = fun;
		return fun.getDependentes().add(this);
	}

	private void checkFuncionarioNulo(Funcionario fun) {
		if(fun == null) {
			throw new NullPointerException("O funcionário passado como parâmetro não pode ser nulo.");
		}
	}

	private void checkProvedorNulo(Funcionario provedor) {
		if(provedor == null) {
			throw new NullPointerException("Provedor não pode ser nulo.");
		}
	}
	
	private void checkParentescoCompostoPorUmaUnicaLetra(String parentesco) {
		int repetidos = contarLetrasRepetidas(parentesco);
		if(repetidos == parentesco.length() - PRIMEIRO_INDICE) {
			throw new IllegalArgumentException("Parentesco do dependente não pode ser composto unicamente pelo mesmo caractere.");
		}
	}

	private int contarLetrasRepetidas(String parentesco) {
		String temp = parentesco.toLowerCase();
		char primeiro = temp.charAt(0);		
		int repetidos = 0;
		for(int i=0; i<parentesco.length() - PRIMEIRO_INDICE; i++) {
			if(primeiro == temp.charAt(i + PRIMEIRO_INDICE)) {
				repetidos++;
			} else {
				break;
			}
		}
		return repetidos;
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
		int qtdeLetras = contarQtdeLetras(parentesco);		
		if(qtdeLetras < MIN_PARENTESCO || parentesco.length() > MAX_PARENTESCO) {
			throw new IllegalArgumentException("Parentesco do dependente deve ter no mínimo 3 e no máximo 55 caracteres, e ter ao menos 3 letras.");
		}
	}

	private int contarQtdeLetras(String parentesco) {
		int qtdeLetras = 0;
		for(int i=0; i<parentesco.length(); i++) {
			if(Character.isAlphabetic(parentesco.charAt(i))) {
				qtdeLetras++;
			}
		}
		return qtdeLetras;
	}

	private void checkParentescoVazio(String parentesco) {
		if(parentesco.equals("")) {
			throw new IllegalArgumentException("Parentesco do dependente não pode estar em branco.");
		}
	}

	private void checkParentescoNulo(String parentesco) {
		if(parentesco == null) {
			throw new NullPointerException("Parentesco do dependente não pode ser nulo.");
		}
	}
	
	private void checkIdadeValida(int idade) {
		if(idade < MIN_IDADE || idade > MAX_IDADE) {
			throw new IllegalArgumentException("Idade do dependente deve ser de 0 a 120.");
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
