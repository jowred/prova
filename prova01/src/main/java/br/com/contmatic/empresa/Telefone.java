package br.com.contmatic.empresa;

import static br.com.contmatic.empresa.Constantes.MAX_COD_PAIS;
import static br.com.contmatic.empresa.Constantes.MAX_DDD;
import static br.com.contmatic.empresa.Constantes.MAX_TIPO;
import static br.com.contmatic.empresa.Constantes.MIN_COD_PAIS;
import static br.com.contmatic.empresa.Constantes.MIN_DDD;
import static br.com.contmatic.empresa.Constantes.MIN_TIPO;
import static br.com.contmatic.empresa.Constantes.PRIMEIRO_INDICE;
import static br.com.contmatic.empresa.Constantes.PRIMEIRO_NUM_TEL;
import static br.com.contmatic.empresa.Constantes.ULTIMO_NUM_TEL;

import java.util.List;

public class Telefone {
	
	private int codigoPais;
	
	private int ddd;
	
	private long numero;
	
	private String tipo;
	
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		checkTipoNulo(tipo);		
		checkTipoVazio(tipo);	
		checkTipoQuantidadeLetras(tipo);		
		checkTipoCaracteresValidos(tipo);		
		checkTipoCompostoPorApenasUmaLetra(tipo);		
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
	
	private void checkTipoCompostoPorApenasUmaLetra(String tipo) {
		int repetidos = contarLetrasRepetidas(tipo);
		if(repetidos == tipo.length() - PRIMEIRO_INDICE) {
			throw new IllegalArgumentException("Tipo do telefone não pode ser composto unicamente pelo mesmo caractere.");
		}
	}

	private int contarLetrasRepetidas(String tipo) {
		String temp = tipo.toLowerCase();
		char primeiro = temp.charAt(0);		
		int repetidos = 0;
		for(int i=0; i<tipo.length() - PRIMEIRO_INDICE; i++) {
			if(primeiro == temp.charAt(i + PRIMEIRO_INDICE)) {
				repetidos++;
			} else {
				break;
			}
		}
		return repetidos;
	}

	private void checkTipoCaracteresValidos(String tipo) {
		for(int i=0; i<tipo.length(); i++) {
			if(!Character.isAlphabetic(tipo.charAt(i)) &&
					(tipo.charAt(i) != ' ')) {
				throw new IllegalArgumentException("Tipo do telefone pode ser composto apenas por letras e espaços.");
			}
		}
	}

	private void checkTipoQuantidadeLetras(String tipo) {
		int qtdeLetras = contarLetras(tipo);
		if(qtdeLetras < MIN_TIPO || tipo.length() > MAX_TIPO) {
			throw new IllegalArgumentException("Tipo do telefone deve ter no mínimo 3 e no máximo 55 caracteres, e ter ao menos 3 letras.");
		}
	}

	private int contarLetras(String tipo) {
		int qtdeLetras = 0;
		for(int i=0; i<tipo.length(); i++) {
			if(Character.isAlphabetic(tipo.charAt(i))) {
				qtdeLetras++;
			}
		}
		return qtdeLetras;
	}

	private void checkTipoVazio(String tipo) {
		if(tipo.equals("")) {
			throw new IllegalArgumentException("Tipo do telefone não pode estar em branco.");
		}
	}

	private void checkTipoNulo(String tipo) {
		if(tipo == null) {
			throw new NullPointerException("Tipo do telefone não pode ser nulo.");
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigoPais;
		result = prime * result + ddd;
		result = prime * result + (int)(numero ^ (numero >>> 32));
		return result;
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
		if (codigoPais != other.codigoPais)
			return false;
		if (ddd != other.ddd)
			return false;
		if (numero != other.numero)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "+" + codigoPais + " " + ddd + numero + "\n"
				+ "Tipo: " + tipo;
	}
}
