package br.com.contmatic.empresa;

import static br.com.contmatic.util.Mensagens.MENSAGEM_AREA_ATUACAO_BLANK;
import static br.com.contmatic.util.Mensagens.MENSAGEM_AREA_ATUACAO_PATTERN;
import static br.com.contmatic.util.Mensagens.MENSAGEM_AREA_ATUACAO_TAMANHO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_CNPJ_BLANK;
import static br.com.contmatic.util.Mensagens.MENSAGEM_CNPJ_INVALIDO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_EMAIL_BLANK;
import static br.com.contmatic.util.Mensagens.MENSAGEM_EMAIL_PATTERN;
import static br.com.contmatic.util.Mensagens.MENSAGEM_EMAIL_TAMANHO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_ENDERECO_NULL;
import static br.com.contmatic.util.Mensagens.MENSAGEM_NOME_FANTASIA_BLANK;
import static br.com.contmatic.util.Mensagens.MENSAGEM_NOME_FANTASIA_PATTERN;
import static br.com.contmatic.util.Mensagens.MENSAGEM_NOME_FANTASIA_TAMANHO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_RAZAO_SOCIAL_BLANK;
import static br.com.contmatic.util.Mensagens.MENSAGEM_RAZAO_SOCIAL_PATTERN;
import static br.com.contmatic.util.Mensagens.MENSAGEM_RAZAO_SOCIAL_TAMANHO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_SET_DEPARTAMENTOS_VAZIO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_SET_TELEFONES_VAZIO;
import static br.com.contmatic.util.Mensagens.URL_INVALIDA;
import static br.com.contmatic.util.Numericas.MAX_AREA_ATUACAO;
import static br.com.contmatic.util.Numericas.MAX_EMAIL;
import static br.com.contmatic.util.Numericas.MAX_NOME_FANTASIA;
import static br.com.contmatic.util.Numericas.MAX_RAZ_SOCIAL;
import static br.com.contmatic.util.Numericas.MIN_AREA_ATUACAO;
import static br.com.contmatic.util.Numericas.MIN_EMAIL;
import static br.com.contmatic.util.Numericas.MIN_NOME_FANTASIA;
import static br.com.contmatic.util.Numericas.MIN_RAZ_SOCIAL;
import static br.com.contmatic.util.Regex.REGEX_AREA_ATUACAO;
import static br.com.contmatic.util.Regex.REGEX_EMAIL;
import static br.com.contmatic.util.Regex.REGEX_NOME_FANTASIA;
import static br.com.contmatic.util.Regex.REGEX_RAZAO_SOCIAL;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.br.CNPJ;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;

/**
 * The Class Empresa.
 */
public class Empresa {
	
	/** The razao social. */
	@Length(min = MIN_RAZ_SOCIAL, max = MAX_RAZ_SOCIAL, message = MENSAGEM_RAZAO_SOCIAL_TAMANHO)
	@Pattern(regexp = REGEX_RAZAO_SOCIAL, message = MENSAGEM_RAZAO_SOCIAL_PATTERN)
	@NotBlank(message = MENSAGEM_RAZAO_SOCIAL_BLANK)
	private String razaoSocial;
	
	/** The nome fantasia. */
	@Length(min = MIN_NOME_FANTASIA, max = MAX_NOME_FANTASIA, message = MENSAGEM_NOME_FANTASIA_TAMANHO)
	@Pattern(regexp = REGEX_NOME_FANTASIA, message = MENSAGEM_NOME_FANTASIA_PATTERN)
	@NotBlank(message = MENSAGEM_NOME_FANTASIA_BLANK)
	private String nomeFantasia;
	
	/** The cnpj. */
	@CNPJ(message = MENSAGEM_CNPJ_INVALIDO)
	@NotBlank(message = MENSAGEM_CNPJ_BLANK)
	private String cnpj;
	
	/** The area atuacao. */
	@Length(min = MIN_AREA_ATUACAO, max = MAX_AREA_ATUACAO, message = MENSAGEM_AREA_ATUACAO_TAMANHO)
	@Pattern(regexp = REGEX_AREA_ATUACAO, message = MENSAGEM_AREA_ATUACAO_PATTERN)
	@NotBlank(message = MENSAGEM_AREA_ATUACAO_BLANK )
	private String areaAtuacao;
	
	/** The departamentos. */
	@NotEmpty(message = MENSAGEM_SET_DEPARTAMENTOS_VAZIO)
	private Set<Departamento> departamentos;
	
	/** The telefones. */
	@NotEmpty(message = MENSAGEM_SET_TELEFONES_VAZIO)
	private Set<Telefone> telefones;
	
	/** The endereco. */
	@NotNull(message = MENSAGEM_ENDERECO_NULL)
	private Endereco endereco;
	
	/** The email. */
	@Length(min = MIN_EMAIL, max = MAX_EMAIL, message = MENSAGEM_EMAIL_TAMANHO)
	@Pattern(regexp = REGEX_EMAIL, message = MENSAGEM_EMAIL_PATTERN)
	@NotBlank(message = MENSAGEM_EMAIL_BLANK)
	private String email;
	
	/** The site. */
	@URL(message = URL_INVALIDA)
	private String site;
	
	/**
	 * Instantiates a new empresa.
	 */
	public Empresa() {
		this.departamentos = new HashSet<>();
		this.telefones = new HashSet<>();
		this.endereco = new Endereco();
	}
	
	/**
	 * Gets the razao social.
	 *
	 * @return the razao social
	 */
	public String getRazaoSocial() {
		return razaoSocial;
	}

	/**
	 * Sets the razao social.
	 *
	 * @param razaoSocial the new razao social
	 */
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	/**
	 * Gets the nome fantasia.
	 *
	 * @return the nome fantasia
	 */
	public String getNomeFantasia() {
		return nomeFantasia;
	}

	/**
	 * Sets the nome fantasia.
	 *
	 * @param nomeFantasia the new nome fantasia
	 */
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	/**
	 * Gets the cnpj.
	 *
	 * @return the cnpj
	 */
	public String getCnpj() {
		return cnpj;
	}
	
	/**
	 * Sets the cnpj.
	 *
	 * @param cnpj the new cnpj
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	/**
	 * Gets the area atuacao.
	 *
	 * @return the area atuacao
	 */
	public String getAreaAtuacao() {
		return areaAtuacao;
	}

	/**
	 * Sets the area atuacao.
	 *
	 * @param areaAtuacao the new area atuacao
	 */
	public void setAreaAtuacao(String areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}

	/**
	 * Gets the departamentos.
	 *
	 * @return the departamentos
	 */
	public Set<Departamento> getDepartamentos() {
		return departamentos;
	}

	/**
	 * Sets the departamentos.
	 *
	 * @param departamentos the new departamentos
	 */
	public void setDepartamentos(Set<Departamento> departamentos) {
		this.departamentos = departamentos;
	}
	
	/**
	 * Gets the telefones.
	 *
	 * @return the telefones
	 */
	public Set<Telefone> getTelefones() {
		return telefones;
	}

	/**
	 * Sets the telefones.
	 *
	 * @param telefones the new telefones
	 */
	public void setTelefones(Set<Telefone> telefones) {
		this.telefones = telefones;
	}

	/**
	 * Gets the endereco.
	 *
	 * @return the endereco
	 */
	public Endereco getEndereco() {
		return endereco;
	}

	/**
	 * Sets the endereco.
	 *
	 * @param endereco the new endereco
	 */
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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
	 * Gets the site.
	 *
	 * @return the site
	 */
	public String getSite() {
		return site;
	}

	/**
	 * Sets the site.
	 *
	 * @param site the new site
	 */
	public void setSite(String site) {
		this.site = site;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(this.cnpj)
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
		Empresa other = (Empresa)obj;		
		return new EqualsBuilder()
				.append(this.cnpj, other.cnpj)
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
