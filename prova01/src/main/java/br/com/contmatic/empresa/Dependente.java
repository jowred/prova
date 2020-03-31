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
import static br.com.contmatic.constantes.Numericas.MAX_RG;
import static br.com.contmatic.constantes.Numericas.MIN_IDADE;
import static br.com.contmatic.constantes.Numericas.MIN_NOME;
import static br.com.contmatic.constantes.Numericas.MIN_RG;
import static br.com.contmatic.constantes.Regex.REGEX_NOME_PESSOA;
import static br.com.contmatic.constantes.Regex.REGEX_RG;
import static br.com.contmatic.enums.EnumTipoParentesco.NAO_DECLARADO;

import javax.validation.Valid;
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

// TODO: Auto-generated Javadoc
/**
 * The Class Dependente.
 */
public class Dependente {
	
	/** The nome. */
	@Length(min = MIN_NOME, max = MAX_NOME, message = MENSAGEM_NOME_PESSOA_TAMANHO)
	@Pattern(regexp = REGEX_NOME_PESSOA, message = MENSAGEM_NOME_PESSOA_PATTERN)
	@NotBlank(message = MENSAGEM_NOME_PESSOA_BLANK)
	private String nome;
	
	/** The rg. */
	@Length(min = MIN_RG, max = MAX_RG, message = MENSAGEM_RG_TAMANHO)
	@Pattern(regexp = REGEX_RG, message = MENSAGEM_RG_PATTERN)
	@NotBlank(message = MENSAGEM_RG_BLANK)
	private String rg;
	
	/** The cpf. */
	@CPF(message = MENSAGEM_CPF_PATTERN)
	@NotBlank(message = MENSAGEM_CPF_BLANK)
	private String cpf;
	
	/** The data nascimento. */
	@Past(message = MENSAGEM_DATA_NASCIMENTO_FUTURE)
	@NotNull(message = MENSAGEM_DATA_NASCIMENTO_NULA)
	private LocalDate dataNascimento;
	
	/** The provedor. */
	@Valid
	@NotNull(message = MENSAGEM_PROVEDOR_NULL)
	private Provedor provedor;
	
	/** The parentesco. */
	@NotNull(message = MENSAGEM_PARENTESCO_NULO)
	private EnumTipoParentesco parentesco;
	
	/** The idade. */
	@Min(value = MIN_IDADE, message = MENSAGEM_IDADE_MIN)
	@Max(value = MAX_IDADE, message = MENSAGEM_IDADE_MAX)
	private int idade;
	
	/**
	 * Instantiates a new dependente.
	 */
	public Dependente() {
		this.setParentesco(NAO_DECLARADO);
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
	 * Gets the rg.
	 *
	 * @return the rg
	 */
	public String getRg() {
		return rg;
	}

	/**
	 * Sets the rg.
	 *
	 * @param rg the new rg
	 */
	public void setRg(String rg) {
		this.rg = rg;
	}

	/**
	 * Gets the cpf.
	 *
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * Sets the cpf.
	 *
	 * @param cpf the new cpf
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * Gets the data nascimento.
	 *
	 * @return the data nascimento
	 */
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * Sets the data nascimento.
	 *
	 * @param dataNascimento the new data nascimento
	 */
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/**
	 * Gets the parentesco.
	 *
	 * @return the parentesco
	 */
	public EnumTipoParentesco getParentesco() {
		return parentesco;
	}

	/**
	 * Sets the parentesco.
	 *
	 * @param parentesco the new parentesco
	 */
	public void setParentesco(EnumTipoParentesco parentesco) {
		this.parentesco = parentesco;
	}
	
	/**
	 * Gets the provedor.
	 *
	 * @return the provedor
	 */
	public Provedor getProvedor() {
		return provedor;
	}

	/**
	 * Sets the provedor.
	 *
	 * @param provedor the new provedor
	 */
	public void setProvedor(Provedor provedor) {
		this.provedor = provedor;
	}

	/**
	 * Gets the idade.
	 *
	 * @return the idade
	 */
	public int getIdade() {
		return idade;
	}

	/**
	 * Sets the idade.
	 *
	 * @param idade the new idade
	 */
	public void setIdade(int idade) {
		this.idade = idade;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(this.cpf)
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
		Dependente other = (Dependente)obj;
		return new EqualsBuilder()
				.append(this.cpf, other.cpf)
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
