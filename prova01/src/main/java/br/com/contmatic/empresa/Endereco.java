package br.com.contmatic.empresa;

import static br.com.contmatic.constantes.Mensagens.MENSAGEM_BAIRRO_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_BAIRRO_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_BAIRRO_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_CEP_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_CEP_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_CEP_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_CIDADE_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_CIDADE_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_CIDADE_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NUMERO_MAX;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NUMERO_MIN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_PAIS_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_PAIS_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_PAIS_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_TIPO_ENDERECO_NULO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_UF_NULA;
import static br.com.contmatic.constantes.Numericas.CEP_SIZE;
import static br.com.contmatic.constantes.Numericas.MAX_BAIRRO;
import static br.com.contmatic.constantes.Numericas.MAX_CIDADE;
import static br.com.contmatic.constantes.Numericas.MAX_LOGRADOURO;
import static br.com.contmatic.constantes.Numericas.MAX_NUM_RUA;
import static br.com.contmatic.constantes.Numericas.MAX_PAIS;
import static br.com.contmatic.constantes.Numericas.MIN_BAIRRO;
import static br.com.contmatic.constantes.Numericas.MIN_CIDADE;
import static br.com.contmatic.constantes.Numericas.MIN_NUM_RUA;
import static br.com.contmatic.constantes.Numericas.MIN_PAIS;
import static br.com.contmatic.constantes.Numericas.MIN_TEXTO;
import static br.com.contmatic.constantes.Numericas.PRIMEIRO_INDICE;
import static br.com.contmatic.constantes.Regex.REGEX_BAIRRO;
import static br.com.contmatic.constantes.Regex.REGEX_CEP;
import static br.com.contmatic.constantes.Regex.REGEX_CIDADE;
import static br.com.contmatic.constantes.Regex.REGEX_PAIS;

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

import br.com.contmatic.enums.EnumEstadosBrasileiros;
import br.com.contmatic.enums.EnumTipoEndereco;

public class Endereco {

	@NotBlank(message = "Logradouro não pode ser nulo")
	private String logradouro;
	
	@Min(value = MIN_NUM_RUA, message = MENSAGEM_NUMERO_MIN)
	@Max(value = MAX_NUM_RUA, message = MENSAGEM_NUMERO_MAX)
	private Integer numero;
	
	@Length(min = MIN_BAIRRO, max = MAX_BAIRRO, message = MENSAGEM_BAIRRO_TAMANHO)
	@Pattern(regexp = REGEX_BAIRRO, message = MENSAGEM_BAIRRO_PATTERN)
	@NotBlank(message = MENSAGEM_BAIRRO_BLANK)
	private String bairro;
	
	@Length(min = MIN_CIDADE, max = MAX_CIDADE, message = MENSAGEM_CIDADE_TAMANHO)
	@Pattern(regexp = REGEX_CIDADE, message = MENSAGEM_CIDADE_PATTERN)
	@NotBlank(message = MENSAGEM_CIDADE_BLANK)
	private String cidade;
	
	@NotNull(message = MENSAGEM_UF_NULA)
	private EnumEstadosBrasileiros uf;
	
	@Length(min = MIN_PAIS, max = MAX_PAIS, message = MENSAGEM_PAIS_TAMANHO)
	@Pattern(regexp = REGEX_PAIS, message = MENSAGEM_PAIS_PATTERN)
	@NotBlank(message = MENSAGEM_PAIS_BLANK)
	private String pais;
	
	@Length(min = CEP_SIZE, max = CEP_SIZE, message = MENSAGEM_CEP_TAMANHO)
	@Pattern(regexp = REGEX_CEP, message = MENSAGEM_CEP_PATTERN)
	@NotBlank(message = MENSAGEM_CEP_BLANK)
	private String cep;
	
	@NotNull(message = MENSAGEM_TIPO_ENDERECO_NULO)
	private EnumTipoEndereco tipo;
	
	public Endereco() {
		this.setTipoEndereco(EnumTipoEndereco.RUA);
	}

	public Endereco(String logradouro, String bairro, String cidade, EnumEstadosBrasileiros uf, String pais, String cep) {
		this.setLogradouro(logradouro);
		this.setBairro(bairro);
		this.setCidade(cidade);
		this.setUf(uf);
		this.setPais(pais);
		this.setCep(cep);
		this.setTipoEndereco(EnumTipoEndereco.RUA);
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

	public EnumEstadosBrasileiros getUf() {
		return uf;
	}

	public void setUf(EnumEstadosBrasileiros uf) {
//		checkUfNula(uf);
//		checkUfVazia(uf);		
//		checkUfTamanho(uf);
//		checkUfCaracteresValidos(uf);		
//		uf = uf.toUpperCase();
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
	
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		checkNumeroValido(numero);		
		this.numero = numero;
	}
	
	public EnumTipoEndereco getTipoEndereco() {
		return tipo;
	}

	public void setTipoEndereco(EnumTipoEndereco tipoEndereco) {
		this.tipo = tipoEndereco;
	}
	
	private void checkLogradouroCompostoUnicamentePeloMesmoCaractere(String logradouro) {
		int repetidos = contarLetrasRepetidas(logradouro);
		if(repetidos == logradouro.length() - PRIMEIRO_INDICE) {
			throw new IllegalArgumentException("Logradouro do endereço não pode ser composto unicamente pelo mesmo caractere.");
		}
	}

	private int contarLetrasRepetidas(String logradouro) {
		String temp = logradouro.toLowerCase();
		char primeiro = temp.charAt(0);
		int repetidos = 0;
		for(int i=0; i<logradouro.length() - PRIMEIRO_INDICE; i++) {
			if(primeiro == temp.charAt(i + PRIMEIRO_INDICE)) {
				repetidos++;
			} else {
				break;
			}
		}
		return repetidos;
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
		int qtdeLetras = contarQtdeLetras(logradouro);
		if(qtdeLetras < MIN_TEXTO || logradouro.length() > MAX_LOGRADOURO) {
			throw new IllegalArgumentException("Logradouro deve ter no mínimo 2 e no máximo 100 caracteres, e ter ao menos 2 letras.");
		}
	}

	private int contarQtdeLetras(String str) {
		int qtdeLetras = 0;
		for(int i=0; i<str.length(); i++) {
			if(Character.isAlphabetic(str.charAt(i))) {
				qtdeLetras++;
			}
		}
		return qtdeLetras;
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
		int repetidos = contarLetrasRepetidas(bairro);
		if(repetidos == bairro.length() - PRIMEIRO_INDICE) {
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
		int qtdeLetras = contarQtdeLetras(bairro);	
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
		int repetidos = contarLetrasRepetidas(cidade);
		if(repetidos == cidade.length() - PRIMEIRO_INDICE) {
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
		int qtdeLetras = contarQtdeLetras(cidade);
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
		int repetidos = contarLetrasRepetidas(pais);
		if(repetidos == pais.length() - PRIMEIRO_INDICE) {
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
		int qtdeLetras = contarQtdeLetras(pais);			
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
		if(cep.length() != CEP_SIZE) {
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
	
	private void checkNumeroValido(Integer numero) {
		if(numero != null) {
			if(numero < MIN_NUM_RUA || numero > MAX_NUM_RUA) {
				throw new IllegalArgumentException("Número da residência deve ser positivo, de 1 a 99999. Para imóveis sem número, atribua null.");
			}
		}
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(this.numero)
				.append(this.cep)
				.toHashCode();
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
		return new EqualsBuilder()
				.append(this.numero, other.numero)
				.append(this.cep, other.cep)
				.isEquals();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}	
}
