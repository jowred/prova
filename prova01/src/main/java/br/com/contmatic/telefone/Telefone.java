package br.com.contmatic.telefone;

import static br.com.contmatic.util.Mensagens.MENSAGEM_CODIGO_PAIS_TAMANHO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_DDD_TAMANHO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_TELEFONE_BLANK;
import static br.com.contmatic.util.Mensagens.MENSAGEM_TELEFONE_PATTERN;
import static br.com.contmatic.util.Mensagens.MENSAGEM_TELEFONE_TAMANHO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_TIPO_TELEFONE_NULO;
import static br.com.contmatic.util.Numericas.MAX_COD_PAIS;
import static br.com.contmatic.util.Numericas.MAX_DDD;
import static br.com.contmatic.util.Numericas.MAX_TELEFONE;
import static br.com.contmatic.util.Numericas.MIN_COD_PAIS;
import static br.com.contmatic.util.Numericas.MIN_DDD;
import static br.com.contmatic.util.Numericas.MIN_TELEFONE;
import static br.com.contmatic.util.Regex.REGEX_TELEFONE;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 * The Class Telefone.
 */
public class Telefone {
	
	/** The codigo pais. */
	@Range(min = MIN_COD_PAIS, max = MAX_COD_PAIS, message = MENSAGEM_CODIGO_PAIS_TAMANHO)
	private int codigoPais;
	
	/** The ddd. */
	@Range(min = MIN_DDD, max = MAX_DDD, message = MENSAGEM_DDD_TAMANHO)
	private int ddd;
	
	/** The numero. */
	@Length(min = MIN_TELEFONE, max = MAX_TELEFONE, message = MENSAGEM_TELEFONE_TAMANHO)
	@Pattern(regexp = REGEX_TELEFONE, message = MENSAGEM_TELEFONE_PATTERN)
	@NotNull(message = MENSAGEM_TELEFONE_BLANK)
	private String numero;
	
	/** The tipo. */
	@NotNull(message = MENSAGEM_TIPO_TELEFONE_NULO)
	private TelefoneType tipo;

	/**
	 * Instantiates a new telefone.
	 */
	public Telefone() {
		
	}
	
	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero the new numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Gets the codigo pais.
	 *
	 * @return the codigo pais
	 */
	public int getCodigoPais() {
		return codigoPais;
	}

	/**
	 * Sets the codigo pais.
	 *
	 * @param codigoPais the new codigo pais
	 */
	public void setCodigoPais(int codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * Gets the ddd.
	 *
	 * @return the ddd
	 */
	public int getDdd() {
		return ddd;
	}

	/**
	 * Sets the ddd.
	 *
	 * @param ddd the new ddd
	 */
	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public TelefoneType getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo the new tipo
	 */
	public void setTipo(TelefoneType tipo) {
		this.tipo = tipo;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(this.codigoPais)
				.append(this.ddd)
				.append(this.numero)
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Telefone other = (Telefone) obj;
		return new EqualsBuilder()
				.append(this.codigoPais, other.codigoPais)
				.append(this.ddd, other.ddd)
				.append(this.numero, other.numero)
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
