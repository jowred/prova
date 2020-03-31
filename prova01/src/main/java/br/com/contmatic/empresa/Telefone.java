package br.com.contmatic.empresa;

import static br.com.contmatic.constantes.Mensagens.MENSAGEM_CODIGO_PAIS_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_DDD_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_TELEFONE_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_TELEFONE_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_TELEFONE_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_TIPO_TELEFONE_NULO;
import static br.com.contmatic.constantes.Numericas.MAX_COD_PAIS;
import static br.com.contmatic.constantes.Numericas.MAX_DDD;
import static br.com.contmatic.constantes.Numericas.MAX_TELEFONE;
import static br.com.contmatic.constantes.Numericas.MIN_COD_PAIS;
import static br.com.contmatic.constantes.Numericas.MIN_DDD;
import static br.com.contmatic.constantes.Numericas.MIN_TELEFONE;
import static br.com.contmatic.constantes.Regex.REGEX_TELEFONE;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import br.com.contmatic.enums.EnumTipoTelefone;

public class Telefone {
	
	@Range(min = MIN_COD_PAIS, max = MAX_COD_PAIS, message = MENSAGEM_CODIGO_PAIS_TAMANHO)
	private int codigoPais;
	
	@Range(min = MIN_DDD, max = MAX_DDD, message = MENSAGEM_DDD_TAMANHO)
	private int ddd;
	
	@Length(min = MIN_TELEFONE, max = MAX_TELEFONE, message = MENSAGEM_TELEFONE_TAMANHO)
	@Pattern(regexp = REGEX_TELEFONE, message = MENSAGEM_TELEFONE_PATTERN)
	@NotNull(message = MENSAGEM_TELEFONE_BLANK)
	private String numero;
	
	@NotNull(message = MENSAGEM_TIPO_TELEFONE_NULO)
	private EnumTipoTelefone tipo;

	public Telefone() {
		
	}
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public int getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(int codigoPais) {
		this.codigoPais = codigoPais;
	}

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public EnumTipoTelefone getTipo() {
		return tipo;
	}

	public void setTipo(EnumTipoTelefone tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(this.codigoPais)
				.append(this.ddd)
				.append(this.numero)
				.toHashCode();
	}

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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
