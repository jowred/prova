package br.com.contmatic.prova01;

public class Endereco {
	private String logradouro;
	private int numero;
	private String bairro;
	private String cidade;
	private String uf;
	private String pais;
	private String cep;
	
	public Endereco() {
		
	}
	
	public Endereco(String logradouro, String bairro, String cidade, String uf, String pais, String cep) {
		setLogradouro(logradouro);
		setBairro(bairro);
		setCidade(cidade);
		setUf(uf);
		setPais(pais);
		setCep(cep);
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		if(logradouro == null)
			throw new NullPointerException("Logradouro não pode ser nulo.");
		
		if(logradouro.equals(""))
			throw new IllegalArgumentException("Logradouro não pode estar em branco.");
		
		int qtdeLetras = 0;
		for(int i=0; i<logradouro.length(); i++)
			if(Character.isAlphabetic(logradouro.charAt(i)))
				qtdeLetras++;
			
		if(qtdeLetras < 2 || logradouro.length() > 100)
			throw new IllegalArgumentException("Logradouro deve ter no mínimo 2 e no máximo 100 caracteres, e ter ao menos 2 letras.");
		
		for(int i=0; i<logradouro.length(); i++)
			if(!Character.isAlphabetic(logradouro.charAt(i)) &&
					!Character.isDigit(logradouro.charAt(i)) &&
					(logradouro.charAt(i) != ' ') &&
					(logradouro.charAt(i) != '-'))
				throw new IllegalArgumentException("Logradouro do endereço pode ser composto apenas por letras, números, espaços e hífen (\"-\".");
		
		String temp = logradouro.toLowerCase();
		char primeiro = temp.charAt(0);
		
		int repetidos = 0;
		for(int i=0; i<logradouro.length()-1; i++) {
			if(primeiro == temp.charAt(i+1))
				repetidos++;
			else
				break;
		}
		if(repetidos == logradouro.length()-1)
			throw new IllegalArgumentException("Logradouro do endereço não pode ser composto unicamente pelo mesmo caractere.");
		
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		if(bairro == null)
			throw new NullPointerException("Bairro não pode ser nulo.");
		
		if(bairro.equals(""))
			throw new IllegalArgumentException("Bairro não pode estar em branco.");
		
		int qtdeLetras = 0;
		for(int i=0; i<bairro.length(); i++)
			if(Character.isAlphabetic(bairro.charAt(i)))
				qtdeLetras++;
			
		if(qtdeLetras < 2 || bairro.length() > 55)
			throw new IllegalArgumentException("Bairro deve ter no mínimo 2 e no máximo 55 caracteres, e ter ao menos 2 letras.");
		
		for(int i=0; i<bairro.length(); i++)
			if(!Character.isAlphabetic(bairro.charAt(i)) &&
					!Character.isDigit(bairro.charAt(i)) &&
					(bairro.charAt(i) != '.') &&
					(bairro.charAt(i) != ' ') &&
					(bairro.charAt(i) != '-'))
				throw new IllegalArgumentException("Bairro do endereço pode ser composto apenas por letras, números, espaços, ponto(\".\") e hífen (\"-\".");
		
		String temp = bairro.toLowerCase();
		char primeiro = temp.charAt(0);
		
		int repetidos = 0;
		for(int i=0; i<bairro.length()-1; i++) {
			if(primeiro == temp.charAt(i+1))
				repetidos++;
			else
				break;
		}
		if(repetidos == bairro.length()-1)
			throw new IllegalArgumentException("Bairro do endereço não pode ser composto unicamente pelo mesmo caractere.");
		
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		if(cidade == null)
			throw new NullPointerException("Nome da cidade não pode ser nulo.");
		
		if(cidade.equals(""))
			throw new IllegalArgumentException("Nome da cidade não pode estar em branco.");
		
		int qtdeLetras = 0;
		for(int i=0; i<cidade.length(); i++)
			if(Character.isAlphabetic(cidade.charAt(i)))
				qtdeLetras++;
			
		if(qtdeLetras < 2 || cidade.length() > 100)
			throw new IllegalArgumentException("Nome da cidade deve ter no mínimo 2 e no máximo 100 caracteres, e ter ao menos 2 letras.");
		
		if(!Character.isAlphabetic(cidade.charAt(0)))
			throw new IllegalArgumentException("Nome da cidade deve obrigatoriamente começar com uma letra.");
		
		for(int i=0; i<cidade.length(); i++)
			if(!Character.isAlphabetic(cidade.charAt(i)) &&
					(cidade.charAt(i) != ' '))
				throw new IllegalArgumentException("Nome da cidade pode ser composto apenas por letras e espaços.");
		
		String temp = cidade.toLowerCase(); 
		char primeiro = temp.charAt(0);
		
		int repetidos = 0;
		for(int i=0; i<cidade.length()-1; i++) {
			if(primeiro == temp.charAt(i+1))
				repetidos++;
			else
				break;
		}
		if(repetidos == cidade.length()-1)
			throw new IllegalArgumentException("Nome da cidade não pode ser composto unicamente pelo mesmo caractere.");
		
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		if(uf == null)
			throw new NullPointerException("UF não pode ser nula.");
		
		if(uf.equals(""))
			throw new IllegalArgumentException("UF não pode estar em branco.");
		
		if(uf.length() != 2)
			throw new IllegalArgumentException("Sigla da UF deve ter exatamente 2 letras.");

		for(int i=0; i<uf.length(); i++)
			if(!Character.isAlphabetic(uf.charAt(i)))
				throw new IllegalArgumentException("Sigla da UF deve conter apenas letras.");
		
		uf = uf.toUpperCase();		
		this.uf = uf;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		if(pais == null)
			throw new NullPointerException("País não pode ser nulo.");
		
		if(pais.equals(""))
			throw new IllegalArgumentException("País não pode estar em branco.");
		
		int qtdeLetras = 0;
		for(int i=0; i<pais.length(); i++)
			if(Character.isAlphabetic(pais.charAt(i)))
				qtdeLetras++;
			
		if(qtdeLetras < 3 || pais.length() > 100)
			throw new IllegalArgumentException("Nome do país deve ter no mínimo 3 e no máximo 100 caracteres, e ter ao menos 3 letras.");
		
		if(!Character.isAlphabetic(pais.charAt(0)))
			throw new IllegalArgumentException("Nome do país deve obrigatoriamente começar com uma letra.");
		
		for(int i=0; i<pais.length(); i++)
			if(!Character.isAlphabetic(pais.charAt(i)) &&
					(pais.charAt(i) != ' ') &&
					(pais.charAt(i) != '-'))
				throw new IllegalArgumentException("Nome do país pode ser composto apenas por letras, espaços e hífen(\"-\").");
		
		String temp = pais.toLowerCase(); 
		char primeiro = temp.charAt(0);
		
		int repetidos = 0;
		for(int i=0; i<pais.length()-1; i++) {
			if(primeiro == temp.charAt(i+1))
				repetidos++;
			else
				break;
		}
		if(repetidos == pais.length()-1)
			throw new IllegalArgumentException("Nome do país não pode ser composto unicamente pelo mesmo caractere.");
		
		this.pais = pais;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		if(cep == null)
			throw new NullPointerException("CEP não pode ser nulo.");
		
		if(cep.equals(""))
			throw new IllegalArgumentException("CEP não pode estar em branco.");
		
		if(cep.length() != 8)
			throw new IllegalArgumentException("CEP precisa ter 8 dígitos.");
		
		for(int i=0; i<cep.length(); i++)
			if(!Character.isDigit(cep.charAt(i)))
				throw new IllegalArgumentException("CEP deve conter apenas dígitos.");
		
		this.cep = cep;
	}
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		if(numero < 0 || numero > 99999)
			throw new IllegalArgumentException("Número da residência deve ser positivo, de 0 a 99999. Para imóveis sem número, utilize 0.");
		
		this.numero = numero;
	}
	
	@Override
	public String toString() {
		return logradouro + ", " + bairro + ", " + cidade + " - " + uf + ", " + pais + "\n"
				+ "CEP: " + cep;
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numero;
		result = prime * result + cep.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		
		Endereco other = (Endereco)obj;
		
		if(!((cep.equals(other.cep)) && (numero == other.numero)))
			return false;
		return true;
	}
}
