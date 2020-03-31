package br.com.contmatic.empresa;

import static br.com.contmatic.constantes.Mensagens.MENSAGEM_DESCRICAO_DEPARTAMENTO_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_DESCRICAO_DEPARTAMENTO_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_DESCRICAO_DEPARTAMENTO_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NOME_DEPARTAMENTO_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NOME_DEPARTAMENTO_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NOME_DEPARTAMENTO_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_SET_FUNCIONARIOS_VAZIO;
import static br.com.contmatic.constantes.Numericas.MAX_DESCRICAO;
import static br.com.contmatic.constantes.Numericas.MAX_NOME_DEPTO;
import static br.com.contmatic.constantes.Numericas.MIN_DESCRICAO;
import static br.com.contmatic.constantes.Numericas.MIN_NOME_DEPTO;
import static br.com.contmatic.constantes.Regex.REGEX_DESCRICAO_DEPARTAMENTO;
import static br.com.contmatic.constantes.Regex.REGEX_NOME_DEPARTAMENTO;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;

// TODO: Auto-generated Javadoc
/**
 * The Class Departamento.
 */
public class Departamento {
	
	/** The nome. */
	@Length(min = MIN_NOME_DEPTO, max = MAX_NOME_DEPTO, message = MENSAGEM_NOME_DEPARTAMENTO_TAMANHO)
	@Pattern(regexp = REGEX_NOME_DEPARTAMENTO, message = MENSAGEM_NOME_DEPARTAMENTO_PATTERN)
	@NotBlank(message = MENSAGEM_NOME_DEPARTAMENTO_BLANK)
	private String nome;
	
	/** The descricao. */
	@Length(min = MIN_DESCRICAO, max = MAX_DESCRICAO, message = MENSAGEM_DESCRICAO_DEPARTAMENTO_TAMANHO)
	@Pattern(regexp = REGEX_DESCRICAO_DEPARTAMENTO, message = MENSAGEM_DESCRICAO_DEPARTAMENTO_PATTERN)
	@NotBlank(message = MENSAGEM_DESCRICAO_DEPARTAMENTO_BLANK)
	private String descricao;
	
	/** The funcionarios. */
	@NotEmpty(message = MENSAGEM_SET_FUNCIONARIOS_VAZIO)
	private Set<Funcionario> funcionarios;
	
	/**
	 * Instantiates a new departamento.
	 */
	public Departamento() {
		this.funcionarios = new HashSet<>();
	}
	
	/**
	 * Instantiates a new departamento.
	 *
	 * @param nome the nome
	 * @param descricao the descricao
	 */
	public Departamento(String nome, String descricao) {
		this();
		this.setNome(nome);
		this.setDescricao(descricao);
	}
	
	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Sets the nome.
	 *
	 * @param nome the new nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Gets the descricao.
	 *
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Sets the descricao.
	 *
	 * @param descricao the new descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Gets the funcionarios.
	 *
	 * @return the funcionarios
	 */
	public Set<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	/**
	 * Sets the funcionarios.
	 *
	 * @param funcionarios the new funcionarios
	 */
	public void setFuncionarios(Set<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(this.descricao)
				.append(this.funcionarios)
				.append(this.nome)
				.toHashCode();
	}
	
	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento)obj;
		return new EqualsBuilder()
				.append(this.descricao, other.descricao)
				.append(this.funcionarios, other.funcionarios)
				.append(this.nome, other.nome)
				.isEquals();
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
