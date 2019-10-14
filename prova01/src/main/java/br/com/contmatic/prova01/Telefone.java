package br.com.contmatic.prova01;

import java.util.List;

public class Telefone {
	private int codigoPais;
	private int ddd;
	private long numero;
	private String tipo;
	
	public Telefone() {
		
	}
	
	public Telefone(int codigoPais, int ddd, long numero) {
		setCodigoPais(codigoPais);
		setDdd(ddd);
		setNumero(numero);
	}

	public int getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(int codigoPais) {
		if(codigoPais < 1 || codigoPais > 999)
			throw new IllegalArgumentException("Código do país deve ser de 0 a 999.");
		
		this.codigoPais = codigoPais;
	}

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		if(ddd < 11 || ddd > 99)
			throw new IllegalArgumentException("DDD deve estar entre 11 e 99.");
		
		this.ddd = ddd;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		if(numero < 10000000 || numero > 999999999)
			throw new IllegalArgumentException("Número de telefone deve conter 8 ou 9 dígitos");
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		if(tipo == null)
			throw new NullPointerException("Tipo do telefone não pode ser nulo.");
		
		if(tipo.equals(""))
			throw new IllegalArgumentException("Tipo do telefone não pode estar em branco.");
		
		int qtdeLetras = 0;
		for(int i=0; i<tipo.length(); i++)
			if(Character.isAlphabetic(tipo.charAt(i)))
				qtdeLetras++;
		
		if(qtdeLetras < 3 || tipo.length() > 55)
			throw new IllegalArgumentException("Tipo do telefone deve ter no mínimo 3 e no máximo 55 caracteres, e ter ao menos 3 letras.");
		
		for(int i=0; i<tipo.length(); i++)
			if(!Character.isAlphabetic(tipo.charAt(i)) &&
					(tipo.charAt(i) != ' '))
				throw new IllegalArgumentException("Tipo do telefone pode ser composto apenas por letras e espaços.");
		
		String temp = tipo.toLowerCase();
		char primeiro = temp.charAt(0);
		
		int repetidos = 0;
		for(int i=0; i<tipo.length()-1; i++) {
			if(primeiro == temp.charAt(i+1))
				repetidos++;
			else
				break;
		}
		if(repetidos == tipo.length()-1)
			throw new IllegalArgumentException("Tipo do telefone não pode ser composto unicamente pelo mesmo caractere.");
		
		this.tipo = tipo;
	}
	
	public boolean cadastrar(List<Telefone> telefones) {
		if(telefones == null)
			throw new NullPointerException("A lista de telefones passada como parâmetro não pode ser nula.");
		
		for(int i=0; i<telefones.size(); i++)
			if(telefones.get(i).equals(this))
				throw new IllegalArgumentException("A lista de telefone passada como parâmetro já possui este telefone.");
		
		return telefones.add(this);
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
