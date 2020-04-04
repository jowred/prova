package br.com.contmatic.endereco;

import static br.com.contmatic.util.Mensagens.MENSAGEM_BAIRRO_BLANK;
import static br.com.contmatic.util.Mensagens.MENSAGEM_BAIRRO_PATTERN;
import static br.com.contmatic.util.Mensagens.MENSAGEM_BAIRRO_TAMANHO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_CEP_BLANK;
import static br.com.contmatic.util.Mensagens.MENSAGEM_CEP_PATTERN;
import static br.com.contmatic.util.Mensagens.MENSAGEM_CEP_TAMANHO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_CIDADE_BLANK;
import static br.com.contmatic.util.Mensagens.MENSAGEM_CIDADE_PATTERN;
import static br.com.contmatic.util.Mensagens.MENSAGEM_CIDADE_TAMANHO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_LOGRADOURO_BLANK;
import static br.com.contmatic.util.Mensagens.MENSAGEM_LOGRADOURO_PATTERN;
import static br.com.contmatic.util.Mensagens.MENSAGEM_LOGRADOURO_TAMANHO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_NUMERO_MAX;
import static br.com.contmatic.util.Mensagens.MENSAGEM_NUMERO_MIN;
import static br.com.contmatic.util.Mensagens.MENSAGEM_PAIS_BLANK;
import static br.com.contmatic.util.Mensagens.MENSAGEM_PAIS_PATTERN;
import static br.com.contmatic.util.Mensagens.MENSAGEM_PAIS_TAMANHO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_TIPO_ENDERECO_NULO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_UF_NULA;
import static br.com.contmatic.util.Numericas.CEP_SIZE;
import static br.com.contmatic.util.Numericas.MAX_BAIRRO;
import static br.com.contmatic.util.Numericas.MAX_CIDADE;
import static br.com.contmatic.util.Numericas.MAX_LOGRADOURO;
import static br.com.contmatic.util.Numericas.MAX_NUM_RUA;
import static br.com.contmatic.util.Numericas.MAX_PAIS;
import static br.com.contmatic.util.Numericas.MIN_BAIRRO;
import static br.com.contmatic.util.Numericas.MIN_CIDADE;
import static br.com.contmatic.util.Numericas.MIN_LOGRADOURO;
import static br.com.contmatic.util.Numericas.MIN_NUM_RUA;
import static br.com.contmatic.util.Numericas.MIN_PAIS;
import static br.com.contmatic.util.Regex.REGEX_BAIRRO;
import static br.com.contmatic.util.Regex.REGEX_CEP;
import static br.com.contmatic.util.Regex.REGEX_CIDADE;
import static br.com.contmatic.util.Regex.REGEX_LOGRADOURO;
import static br.com.contmatic.util.Regex.REGEX_PAIS;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;

/**
 * The Class Endereco.
 */
public class Endereco {

	/** The logradouro. */
	@Length(min = MIN_LOGRADOURO, max = MAX_LOGRADOURO, message = MENSAGEM_LOGRADOURO_TAMANHO)
	@Pattern(regexp = REGEX_LOGRADOURO, message = MENSAGEM_LOGRADOURO_PATTERN)
	@NotBlank(message = MENSAGEM_LOGRADOURO_BLANK)
	private String logradouro;
	
	/** The numero. */
	@Min(value = MIN_NUM_RUA, message = MENSAGEM_NUMERO_MIN)
	@Max(value = MAX_NUM_RUA, message = MENSAGEM_NUMERO_MAX)
	private Integer numero;
	
	/** The bairro. */
	@Length(min = MIN_BAIRRO, max = MAX_BAIRRO, message = MENSAGEM_BAIRRO_TAMANHO)
	@Pattern(regexp = REGEX_BAIRRO, message = MENSAGEM_BAIRRO_PATTERN)
	@NotBlank(message = MENSAGEM_BAIRRO_BLANK)
	private String bairro;
	
	/** The cidade. */
	@Length(min = MIN_CIDADE, max = MAX_CIDADE, message = MENSAGEM_CIDADE_TAMANHO)
	@Pattern(regexp = REGEX_CIDADE, message = MENSAGEM_CIDADE_PATTERN)
	@NotBlank(message = MENSAGEM_CIDADE_BLANK)
	private String cidade;
	
	/** The uf. */
	@NotNull(message = MENSAGEM_UF_NULA)
	private EstadosBrasileirosType uf;
	
	/** The pais. */
	@Length(min = MIN_PAIS, max = MAX_PAIS, message = MENSAGEM_PAIS_TAMANHO)
	@Pattern(regexp = REGEX_PAIS, message = MENSAGEM_PAIS_PATTERN)
	@NotBlank(message = MENSAGEM_PAIS_BLANK)
	private String pais;
	
	/** The cep. */
	@Length(min = CEP_SIZE, max = CEP_SIZE, message = MENSAGEM_CEP_TAMANHO)
	@Pattern(regexp = REGEX_CEP, message = MENSAGEM_CEP_PATTERN)
	@NotBlank(message = MENSAGEM_CEP_BLANK)
	private String cep;
	
	/** The tipo. */
	@NotNull(message = MENSAGEM_TIPO_ENDERECO_NULO)
	private EnderecoType tipo;
	
	/**
	 * Instantiates a new endereco.
	 */
	public Endereco() {
		this.setTipoEndereco(EnderecoType.RUA);
	}

	/**
	 * Instantiates a new endereco.
	 *
	 * @param logradouro the logradouro
	 * @param bairro the bairro
	 * @param cidade the cidade
	 * @param uf the uf
	 * @param pais the pais
	 * @param cep the cep
	 */
	public Endereco(String logradouro, String bairro, String cidade, EstadosBrasileirosType uf, String pais, String cep) {
		this.setLogradouro(logradouro);
		this.setBairro(bairro);
		this.setCidade(cidade);
		this.setUf(uf);
		this.setPais(pais);
		this.setCep(cep);
		this.setTipoEndereco(EnderecoType.RUA);
	}

	/**
	 * Gets the logradouro.
	 *
	 * @return the logradouro
	 */
	public String getLogradouro() {
		return logradouro;
	}

	/**
	 * Sets the logradouro.
	 *
	 * @param logradouro the new logradouro
	 */
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	/**
	 * Gets the bairro.
	 *
	 * @return the bairro
	 */
	public String getBairro() {
		return bairro;
	}

	/**
	 * Sets the bairro.
	 *
	 * @param bairro the new bairro
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
	 * Gets the cidade.
	 *
	 * @return the cidade
	 */
	public String getCidade() {
		return cidade;
	}

	/**
	 * Sets the cidade.
	 *
	 * @param cidade the new cidade
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	/**
	 * Gets the uf.
	 *
	 * @return the uf
	 */
	public EstadosBrasileirosType getUf() {
		return uf;
	}

	/**
	 * Sets the uf.
	 *
	 * @param uf the new uf
	 */
	public void setUf(EstadosBrasileirosType uf) {
		this.uf = uf;
	}

	/**
	 * Gets the pais.
	 *
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * Sets the pais.
	 *
	 * @param pais the new pais
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * Gets the cep.
	 *
	 * @return the cep
	 */
	public String getCep() {
		return cep;
	}

	/**
	 * Sets the cep.
	 *
	 * @param cep the new cep
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public Integer getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero the new numero
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	/**
	 * Gets the tipo endereco.
	 *
	 * @return the tipo endereco
	 */
	public EnderecoType getTipoEndereco() {
		return tipo;
	}

	/**
	 * Sets the tipo endereco.
	 *
	 * @param tipoEndereco the new tipo endereco
	 */
	public void setTipoEndereco(EnderecoType tipoEndereco) {
		this.tipo = tipoEndereco;
	}
	
	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(this.numero)
				.append(this.cep)
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
		Endereco other = (Endereco)obj;
		return new EqualsBuilder()
				.append(this.numero, other.numero)
				.append(this.cep, other.cep)
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
