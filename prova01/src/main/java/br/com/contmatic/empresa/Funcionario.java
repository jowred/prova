package br.com.contmatic.empresa;

import static br.com.contmatic.util.Mensagens.MENSAGEM_CPF_BLANK;
import static br.com.contmatic.util.Mensagens.MENSAGEM_CPF_PATTERN;
import static br.com.contmatic.util.Mensagens.MENSAGEM_DATA_ADMISSAO_BLANK;
import static br.com.contmatic.util.Mensagens.MENSAGEM_DATA_ADMISSAO_PAST;
import static br.com.contmatic.util.Mensagens.MENSAGEM_DATA_NASCIMENTO_FUTURE;
import static br.com.contmatic.util.Mensagens.MENSAGEM_DATA_NASCIMENTO_NULA;
import static br.com.contmatic.util.Mensagens.MENSAGEM_EMAIL_BLANK;
import static br.com.contmatic.util.Mensagens.MENSAGEM_EMAIL_PATTERN;
import static br.com.contmatic.util.Mensagens.MENSAGEM_EMAIL_TAMANHO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_NOME_PESSOA_BLANK;
import static br.com.contmatic.util.Mensagens.MENSAGEM_NOME_PESSOA_PATTERN;
import static br.com.contmatic.util.Mensagens.MENSAGEM_NOME_PESSOA_TAMANHO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_RG_BLANK;
import static br.com.contmatic.util.Mensagens.MENSAGEM_RG_PATTERN;
import static br.com.contmatic.util.Mensagens.MENSAGEM_RG_TAMANHO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_SET_DEPENDENTES_VAZIO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_TERMINO_CONTRATO_BLANK;
import static br.com.contmatic.util.Mensagens.MENSAGEM_TERMINO_CONTRATO_FUTURE;
import static br.com.contmatic.util.Numericas.MAX_EMAIL;
import static br.com.contmatic.util.Numericas.MAX_NOME;
import static br.com.contmatic.util.Numericas.MAX_RG;
import static br.com.contmatic.util.Numericas.MIN_EMAIL;
import static br.com.contmatic.util.Numericas.MIN_NOME;
import static br.com.contmatic.util.Numericas.MIN_RG;
import static br.com.contmatic.util.Regex.REGEX_EMAIL;
import static br.com.contmatic.util.Regex.REGEX_NOME_PESSOA;
import static br.com.contmatic.util.Regex.REGEX_RG;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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

/**
 * The Class Funcionario.
 */
public class Funcionario implements Provedor {
	
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
	
	/** The email. */
	@Email(message = MENSAGEM_EMAIL_PATTERN)
	@Length(min = MIN_EMAIL, max = MAX_EMAIL, message = MENSAGEM_EMAIL_TAMANHO)
	@Pattern(regexp = REGEX_EMAIL, message = MENSAGEM_EMAIL_PATTERN)
	@NotBlank(message = MENSAGEM_EMAIL_BLANK)
	private String email;
	
	/** The dependentes. */
	@NotEmpty(message = MENSAGEM_SET_DEPENDENTES_VAZIO)
	private Set<Dependente> dependentes;
	
	/** The data admissao. */
	@Past(message = MENSAGEM_DATA_ADMISSAO_PAST)
	@NotNull(message = MENSAGEM_DATA_ADMISSAO_BLANK)
	private LocalDate dataAdmissao;
	
	/** The termino contrato. */
	@Future(message = MENSAGEM_TERMINO_CONTRATO_FUTURE)
	@NotNull(message = MENSAGEM_TERMINO_CONTRATO_BLANK)
	private LocalDate terminoContrato;
	
	/**
	 * Instantiates a new funcionario.
	 */
	public Funcionario() {
		dependentes = new HashSet<>();
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
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the dependentes.
	 *
	 * @return the dependentes
	 */
	public Set<Dependente> getDependentes() {
		return dependentes;
	}

	/**
	 * Sets the dependentes.
	 *
	 * @param dependentes the new dependentes
	 */
	public void setDependentes(Set<Dependente> dependentes) {
		this.dependentes = dependentes;
	}
	
	/**
	 * Gets the data admissao.
	 *
	 * @return the data admissao
	 */
	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}

	/**
	 * Sets the data admissao.
	 *
	 * @param dataAdmissao the new data admissao
	 */
	public void setDataAdmissao(LocalDate dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	
	/**
	 * Gets the termino contrato.
	 *
	 * @return the termino contrato
	 */
	public LocalDate getTerminoContrato() {
		return terminoContrato;
	}

	/**
	 * Sets the termino contrato.
	 *
	 * @param terminoContrato the new termino contrato
	 */
	public void setTerminoContrato(LocalDate terminoContrato) {
		this.terminoContrato = terminoContrato;
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
		Funcionario other = (Funcionario)obj;
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
