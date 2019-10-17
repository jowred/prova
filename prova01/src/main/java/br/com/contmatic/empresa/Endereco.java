package br.com.contmatic.empresa;

public class Endereco {
	
	private static final int MIN_NUM_RUA = 0;

	private static final int MAX_NUM_RUA = 99999;

	private static final int CEP = 8;

	private static final int MAX_PAIS = 100;

	private static final int MIN_PAIS = 3;

	private static final int MAX_CIDADE = 100;

	private static final int MAX_BAIRRO = 55;

	private static final int MAX_LOGRADOURO = 100;

	private static final int MIN_TEXTO = 2;

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
		this.setLogradouro(logradouro);
		this.setBairro(bairro);
		this.setCidade(cidade);
		this.setUf(uf);
		this.setPais(pais);
		this.setCep(cep);
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		checkLogradouroNulo(logradouro);
		checkLogradouroVazio(logradouro);
		checkLogradouroTamanho(logradouro);		
		checkLogradouroCaracteresValidos(logradouro);		
		checkLogradouroCompostoUnicamentePeloMesmoCaractere(logradouro);		
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		checkBairroNulo(bairro);		
		checkBairroVazio(bairro);		
		checkBairroTamanho(bairro);		
		checkBairroCaracteresValidos(bairro);		
		checkBairroCompostoUnicamentePelaMesmaLetra(bairro);
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		checkCidadeNula(cidade);		
		checkCidadeVazia(cidade);		
		checkCidadeTamanho(cidade);		
		checkCidadeComecaComLetra(cidade);		
		checkCidadeCaracteresValidos(cidade);		
		checkCidadeCompostaUnicamentePeloMesmoCaractere(cidade);		
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		checkUfNula(uf);
		checkUfVazia(uf);		
		checkUfTamanho(uf);
		checkUfCaracteresValidos(uf);		
		uf = uf.toUpperCase();
		this.uf = uf;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		checkPaisNulo(pais);		
		checkPaisVazio(pais);		
		checkPaisTamanho(pais);		
		checkPaisComecaComLetra(pais);		
		checkPaisCaracteresValidos(pais);		
		checkPaisCompostoUnicamentePeloMesmoCaractere(pais);		
		this.pais = pais;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		checkCepNulo(cep);		
		checkCepVazio(cep);		
		checkCepTamanho(cep);		
		checkCepContemApenasDigitos(cep);		
		this.cep = cep;
	}
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		checkNumeroValido(numero);		
		this.numero = numero;
	}
	
	private void checkLogradouroCompostoUnicamentePeloMesmoCaractere(String logradouro) {
		String temp = logradouro.toLowerCase();
		char primeiro = temp.charAt(0);
		int repetidos = 0;
		for(int i=0; i<logradouro.length()-1; i++) {
			if(primeiro == temp.charAt(i+1)) {
				repetidos++;
			} else {
				break;
			}
		}
		if(repetidos == logradouro.length()-1) {
			throw new IllegalArgumentException("Logradouro do endereço não pode ser composto unicamente pelo mesmo caractere.");
		}
	}

	private void checkLogradouroCaracteresValidos(String logradouro) {
		for(int i=0; i<logradouro.length(); i++) {
			if(!Character.isAlphabetic(logradouro.charAt(i)) &&
					!Character.isDigit(logradouro.charAt(i)) &&
					(logradouro.charAt(i) != ' ') &&
					(logradouro.charAt(i) != '-')) {
				throw new IllegalArgumentException("Logradouro do endereço pode ser composto apenas por letras, números, espaços e hífen (\"-\".");
			}
		}
	}

	private void checkLogradouroTamanho(String logradouro) {
		int qtdeLetras = 0;
		for(int i=0; i<logradouro.length(); i++) {
			if(Character.isAlphabetic(logradouro.charAt(i))) {
				qtdeLetras++;
			}
		}
		if(qtdeLetras < MIN_TEXTO || logradouro.length() > MAX_LOGRADOURO) {
			throw new IllegalArgumentException("Logradouro deve ter no mínimo 2 e no máximo 100 caracteres, e ter ao menos 2 letras.");
		}
	}

	private void checkLogradouroVazio(String logradouro) {
		if(logradouro.equals("")) {
			throw new IllegalArgumentException("Logradouro não pode estar em branco.");
		}
	}

	private void checkLogradouroNulo(String logradouro) {
		if(logradouro == null) {
			throw new NullPointerException("Logradouro não pode ser nulo.");
		}
	}
	
	private void checkBairroCompostoUnicamentePelaMesmaLetra(String bairro) {
		String temp = bairro.toLowerCase();
		char primeiro = temp.charAt(0);
		int repetidos = 0;
		for(int i=0; i<bairro.length()-1; i++) {
			if(primeiro == temp.charAt(i+1)) {
				repetidos++;
			} else {
				break;
			}
		}
		if(repetidos == bairro.length()-1) {
			throw new IllegalArgumentException("Bairro do endereço não pode ser composto unicamente pelo mesmo caractere.");
		}
	}

	private void checkBairroCaracteresValidos(String bairro) {
		for(int i=0; i<bairro.length(); i++) {
			if(!Character.isAlphabetic(bairro.charAt(i)) &&
					!Character.isDigit(bairro.charAt(i)) &&
					(bairro.charAt(i) != '.') &&
					(bairro.charAt(i) != ' ') &&
					(bairro.charAt(i) != '-')) {
				throw new IllegalArgumentException("Bairro do endereço pode ser composto apenas por letras, números, espaços, ponto(\".\") e hífen (\"-\".");
			}
		}
	}

	private void checkBairroTamanho(String bairro) {
		int qtdeLetras = 0;
		for(int i=0; i<bairro.length(); i++) {
			if(Character.isAlphabetic(bairro.charAt(i))) {
				qtdeLetras++;
			}
		}	
		if(qtdeLetras < MIN_TEXTO || bairro.length() > MAX_BAIRRO)
			throw new IllegalArgumentException("Bairro deve ter no mínimo 2 e no máximo 55 caracteres, e ter ao menos 2 letras.");
	}

	private void checkBairroVazio(String bairro) {
		if(bairro.equals("")) {
			throw new IllegalArgumentException("Bairro não pode estar em branco.");
		}
	}

	private void checkBairroNulo(String bairro) {
		if(bairro == null) {
			throw new NullPointerException("Bairro não pode ser nulo.");
		}
	}
	
	private void checkCidadeCompostaUnicamentePeloMesmoCaractere(String cidade) {
		String temp = cidade.toLowerCase(); 
		char primeiro = temp.charAt(0);		
		int repetidos = 0;
		for(int i=0; i<cidade.length()-1; i++) {
			if(primeiro == temp.charAt(i+1)) {
				repetidos++;
			} else {
				break;
			}
		}
		if(repetidos == cidade.length()-1) {
			throw new IllegalArgumentException("Nome da cidade não pode ser composto unicamente pelo mesmo caractere.");
		}
	}

	private void checkCidadeCaracteresValidos(String cidade) {
		for(int i=0; i<cidade.length(); i++) {
			if(!Character.isAlphabetic(cidade.charAt(i)) &&
					(cidade.charAt(i) != ' ')) {
				throw new IllegalArgumentException("Nome da cidade pode ser composto apenas por letras e espaços.");
			}
		}
	}

	private void checkCidadeComecaComLetra(String cidade) {
		if(!Character.isAlphabetic(cidade.charAt(0))) {
			throw new IllegalArgumentException("Nome da cidade deve obrigatoriamente começar com uma letra.");
		}
	}

	private void checkCidadeTamanho(String cidade) {
		int qtdeLetras = 0;
		for(int i=0; i<cidade.length(); i++) {
			if(Character.isAlphabetic(cidade.charAt(i))) {
				qtdeLetras++;
			}
		}
		if(qtdeLetras < MIN_TEXTO || cidade.length() > MAX_CIDADE) {
			throw new IllegalArgumentException("Nome da cidade deve ter no mínimo 2 e no máximo 100 caracteres, e ter ao menos 2 letras.");
		}
	}

	private void checkCidadeVazia(String cidade) {
		if(cidade.equals("")) {
			throw new IllegalArgumentException("Nome da cidade não pode estar em branco.");
		}
	}

	private void checkCidadeNula(String cidade) {
		if(cidade == null) {
			throw new NullPointerException("Nome da cidade não pode ser nulo.");
		}
	}
	
	private void checkUfCaracteresValidos(String uf) {
		for(int i=0; i<uf.length(); i++) {
			if(!Character.isAlphabetic(uf.charAt(i))) {
				throw new IllegalArgumentException("Sigla da UF deve conter apenas letras.");
			}
		}
	}

	private void checkUfTamanho(String uf) {
		if(uf.length() != 2) {
			throw new IllegalArgumentException("Sigla da UF deve ter exatamente 2 letras.");
		}
	}

	private void checkUfVazia(String uf) {
		if(uf.equals("")) {
			throw new IllegalArgumentException("UF não pode estar em branco.");
		}
	}

	private void checkUfNula(String uf) {
		if(uf == null) {
			throw new NullPointerException("UF não pode ser nula.");
		}
	}
	
	private void checkPaisCompostoUnicamentePeloMesmoCaractere(String pais) {
		String temp = pais.toLowerCase(); 
		char primeiro = temp.charAt(0);		
		int repetidos = 0;
		for(int i=0; i<pais.length()-1; i++) {
			if(primeiro == temp.charAt(i+1)) {
				repetidos++;
			} else {
				break;
			}
		}
		if(repetidos == pais.length()-1) {
			throw new IllegalArgumentException("Nome do país não pode ser composto unicamente pelo mesmo caractere.");
		}
	}

	private void checkPaisCaracteresValidos(String pais) {
		for(int i=0; i<pais.length(); i++) {
			if(!Character.isAlphabetic(pais.charAt(i)) &&
					(pais.charAt(i) != ' ') &&
					(pais.charAt(i) != '-')) {
				throw new IllegalArgumentException("Nome do país pode ser composto apenas por letras, espaços e hífen(\"-\").");
			}
		}
	}

	private void checkPaisComecaComLetra(String pais) {
		if(!Character.isAlphabetic(pais.charAt(0))) {
			throw new IllegalArgumentException("Nome do país deve obrigatoriamente começar com uma letra.");
		}
	}

	private void checkPaisTamanho(String pais) {
		int qtdeLetras = 0;
		for(int i=0; i<pais.length(); i++) {
			if(Character.isAlphabetic(pais.charAt(i))) {
				qtdeLetras++;
			}
		}			
		if(qtdeLetras < MIN_PAIS || pais.length() > MAX_PAIS) {
			throw new IllegalArgumentException("Nome do país deve ter no mínimo 3 e no máximo 100 caracteres, e ter ao menos 3 letras.");
		}
	}

	private void checkPaisVazio(String pais) {
		if(pais.equals("")) {
			throw new IllegalArgumentException("País não pode estar em branco.");
		}
	}

	private void checkPaisNulo(String pais) {
		if(pais == null) {
			throw new NullPointerException("País não pode ser nulo.");
		}
	}
	
	private void checkCepContemApenasDigitos(String cep) {
		for(int i=0; i<cep.length(); i++) {
			if(!Character.isDigit(cep.charAt(i))) {
				throw new IllegalArgumentException("CEP deve conter apenas dígitos.");
			}
		}
	}

	private void checkCepTamanho(String cep) {
		if(cep.length() != CEP) {
			throw new IllegalArgumentException("CEP precisa ter 8 dígitos.");
		}
	}

	private void checkCepVazio(String cep) {
		if(cep.equals("")) {
			throw new IllegalArgumentException("CEP não pode estar em branco.");
		}
	}

	private void checkCepNulo(String cep) {
		if(cep == null) {
			throw new NullPointerException("CEP não pode ser nulo.");
		}
	}
	
	private void checkNumeroValido(int numero) {
		if(numero < MIN_NUM_RUA || numero > MAX_NUM_RUA) {
			throw new IllegalArgumentException("Número da residência deve ser positivo, de 1 a 99999. Para imóveis sem número, utilize 0.");
		}
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
	
	@Override
	public String toString() {
		return logradouro + ", " + bairro + ", " + cidade + " - " + uf + ", " + pais + "\n"
				+ "CEP: " + cep;
	}	
}
