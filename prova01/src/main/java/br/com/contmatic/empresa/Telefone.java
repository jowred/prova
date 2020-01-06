package br.com.contmatic.empresa;

import static br.com.contmatic.constantes.Numericas.MAX_COD_PAIS;
import static br.com.contmatic.constantes.Numericas.MAX_DDD;
import static br.com.contmatic.constantes.Numericas.MIN_COD_PAIS;
import static br.com.contmatic.constantes.Numericas.MIN_DDD;
import static br.com.contmatic.constantes.Numericas.PRIMEIRO_NUM_TEL;
import static br.com.contmatic.constantes.Numericas.ULTIMO_NUM_TEL;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import br.com.contmatic.enums.EnumTipoTelefone;

public class Telefone {
	
	private int codigoPais;
	
	private int ddd;
	
	private long numero;
		
	private EnumTipoTelefone tipo;

	public Telefone() {
		
	}
	
	public Telefone(int codigoPais, int ddd, long numero) {
		this.setCodigoPais(codigoPais);
		this.setDdd(ddd);
		this.setNumero(numero);
	}

	public int getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(int codigoPais) {
		checkCodigoPaisValido(codigoPais);
		this.codigoPais = codigoPais;
	}

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		checkDddValido(ddd);
		this.ddd = ddd;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		checkNumeroValido(numero);
		this.numero = numero;
	}
	
	public EnumTipoTelefone getTipo() {
		return tipo;
	}

	public void setTipo(EnumTipoTelefone tipo) {
		this.tipo = tipo;
	}

	public boolean cadastrar(List<Telefone> telefones) {
		checkListaTelefonesNula(telefones);
		checkTelefoneRepetido(telefones);
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
