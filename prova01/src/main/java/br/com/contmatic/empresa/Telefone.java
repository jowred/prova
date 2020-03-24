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
import static br.com.contmatic.constantes.Numericas.PRIMEIRO_NUM_TEL;
import static br.com.contmatic.constantes.Numericas.ULTIMO_NUM_TEL;
import static br.com.contmatic.constantes.Regex.REGEX_TELEFONE;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
	
//	@Min(value = MIN_COD_PAIS, message = MENSAGEM_CODIGO_PAIS_TAMANHO)
//	@Max(value = MAX_COD_PAIS, message = MENSAGEM_CODIGO_PAIS_TAMANHO)
	@Range(min = MIN_COD_PAIS, max = MAX_COD_PAIS, message = MENSAGEM_CODIGO_PAIS_TAMANHO)
	private int codigoPais; // Enum código de país
	
//	@Min(value = MIN_DDD, message = MENSAGEM_DDD_TAMANHO)
//	@Max(value = MAX_DDD, message = MENSAGEM_DDD_TAMANHO)
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
	
	public Telefone(int codigoPais, int ddd, String numero) {
		this.setCodigoPais(codigoPais);
		this.setDdd(ddd);
		this.setNumero(numero);
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
//		checkCodigoPaisValido(codigoPais);
		this.codigoPais = codigoPais;
	}

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
//		checkDddValido(ddd);
		this.ddd = ddd;
	}

	public EnumTipoTelefone getTipo() {
		return tipo;
	}

	public void setTipo(EnumTipoTelefone tipo) {
		this.tipo = tipo;
	}

	public boolean cadastrar(List<Telefone> telefones) {
//		checkListaTelefonesNula(telefones);
//		checkTelefoneRepetido(telefones);
		return telefones.add(this);
	}

	private void checkTelefoneRepetido(List<Telefone> telefones) {
		for(int i=0; i<telefones.size(); i++) {
			if(telefones.get(i).equals(this)) {
				throw new IllegalArgumentException("A lista de telefone passada como parâmetro já possui este telefone.");
			}
		}
	}

	private void checkListaTelefonesNula(List<Telefone> telefones) {
		if(telefones == null) {
			throw new NullPointerException("A lista de telefones passada como parâmetro não pode ser nula.");
		}
	}
	
	private void checkCodigoPaisValido(int codigoPais) {
		if(codigoPais < MIN_COD_PAIS || codigoPais > MAX_COD_PAIS) {
			throw new IllegalArgumentException("Código do país deve ser de 1 a 999.");
		}
	}
	
	private void checkDddValido(int ddd) {
		if(ddd < MIN_DDD || ddd > MAX_DDD) {
			throw new IllegalArgumentException("DDD deve estar entre 11 e 99.");
		}
	}
	
	private void checkNumeroValido(long numero) {
		if(numero < PRIMEIRO_NUM_TEL || numero > ULTIMO_NUM_TEL) {
			throw new IllegalArgumentException("Número de telefone deve conter 8 ou 9 dígitos");
		}
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
