package br.com.contmatic.empresa;

import static br.com.contmatic.constantes.Mensagens.MENSAGEM_CPF_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_CPF_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_DATA_NASCIMENTO_FUTURE;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_DATA_NASCIMENTO_NULA;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_IDADE_MAX;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_IDADE_MIN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NOME_PESSOA_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NOME_PESSOA_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NOME_PESSOA_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_PARENTESCO_NULO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_PROVEDOR_NULL;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_RG_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_RG_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_RG_TAMANHO;
import static br.com.contmatic.constantes.Numericas.MAX_IDADE;
import static br.com.contmatic.constantes.Numericas.MAX_NOME;
import static br.com.contmatic.constantes.Numericas.MAX_PARENTESCO;
import static br.com.contmatic.constantes.Numericas.MAX_RG;
import static br.com.contmatic.constantes.Numericas.MIN_IDADE;
import static br.com.contmatic.constantes.Numericas.MIN_NOME;
import static br.com.contmatic.constantes.Numericas.MIN_PARENTESCO;
import static br.com.contmatic.constantes.Numericas.MIN_RG;
import static br.com.contmatic.constantes.Numericas.PRIMEIRO_INDICE;
import static br.com.contmatic.constantes.Regex.REGEX_NOME_PESSOA;
import static br.com.contmatic.constantes.Regex.REGEX_RG;
import static br.com.contmatic.enums.EnumTipoParentesco.NAO_DECLARADO;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.joda.time.LocalDate;

import br.com.contmatic.enums.EnumTipoParentesco;

public class Dependente {
	
	@Length(min = MIN_NOME, max = MAX_NOME, message = MENSAGEM_NOME_PESSOA_TAMANHO)
	@Pattern(regexp = REGEX_NOME_PESSOA, message = MENSAGEM_NOME_PESSOA_PATTERN)
	@NotBlank(message = MENSAGEM_NOME_PESSOA_BLANK)
	private String nome;
	
	@Length(min = MIN_RG, max = MAX_RG, message = MENSAGEM_RG_TAMANHO)
	@Pattern(regexp = REGEX_RG, message = MENSAGEM_RG_PATTERN)
	@NotBlank(message = MENSAGEM_RG_BLANK)
	private String rg;
	
	@CPF(message = MENSAGEM_CPF_PATTERN)
	@NotBlank(message = MENSAGEM_CPF_BLANK)
	private String cpf;
	
	@Past(message = MENSAGEM_DATA_NASCIMENTO_FUTURE)
	@NotNull(message = MENSAGEM_DATA_NASCIMENTO_NULA)
	private LocalDate dataNascimento;
	
//	@Length(min = MIN_EMAIL, max = MAX_EMAIL, message = MENSAGEM_EMAIL_TAMANHO)
//	@Pattern(regexp = REGEX_EMAIL, message = MENSAGEM_EMAIL_PATTERN)
//	@NotBlank(message = MENSAGEM_EMAIL_BLANK)
//	private String email;
	
	@NotNull(message = MENSAGEM_PROVEDOR_NULL)
	private Funcionario provedor;
	
	@NotNull(message = MENSAGEM_PARENTESCO_NULO)
	private EnumTipoParentesco parentesco;
	
	@Min(value = MIN_IDADE, message = MENSAGEM_IDADE_MIN)
	@Max(value = MAX_IDADE, message = MENSAGEM_IDADE_MAX)
	private int idade;
	
	public Dependente() {
		this.provedor = new Funcionario();
		this.setParentesco(NAO_DECLARADO);
	}

	public Dependente(String nome, String rg, String cpf) {
		this.setNome(nome);
		this.setRg(rg);
		this.setCpf(cpf);
		this.provedor = new Funcionario();
		this.setParentesco(NAO_DECLARADO);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public EnumTipoParentesco getParentesco() {
		return parentesco;
	}

	public void setParentesco(EnumTipoParentesco parentesco) {
		this.parentesco = parentesco;
	}
	
	public Funcionario getProvedor() {
		return provedor;
	}

	public void setProvedor(Funcionario provedor) {
//		checkProvedorNulo(provedor);		
		this.provedor = provedor;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
//		checkIdadeValida(idade);
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
		return new HashCodeBuilder()
				.append(this.cpf)
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Dependente other = (Dependente)obj;
		return new EqualsBuilder()
				.append(this.cpf, other.cpf)
				.isEquals();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
