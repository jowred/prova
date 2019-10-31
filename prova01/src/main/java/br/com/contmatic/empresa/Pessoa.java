package br.com.contmatic.empresa;

import static br.com.contmatic.empresa.Constantes.ASCII_INICIO_NUMEROS;
import static br.com.contmatic.empresa.Constantes.CPF_POS_VERIF_1;
import static br.com.contmatic.empresa.Constantes.CPF_POS_VERIF_2;
import static br.com.contmatic.empresa.Constantes.CPF_SIZE;
import static br.com.contmatic.empresa.Constantes.CPF_SIZE_SEM_VERIF;
import static br.com.contmatic.empresa.Constantes.MAX_NOME;
import static br.com.contmatic.empresa.Constantes.MAX_RG;
import static br.com.contmatic.empresa.Constantes.MIN_NOME;
import static br.com.contmatic.empresa.Constantes.MIN_RG;
import static br.com.contmatic.empresa.Constantes.PRIMEIRO_INDICE;
import static br.com.contmatic.empresa.Constantes.QTDE_VERIFICADORES;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Pessoa {

	private String nome;
	
	private String rg;
	
	private String cpf;
	
	public Pessoa() {
		
	}
	
	public Pessoa(String nome, String rg, String cpf) {
		this.setNome(nome);
		this.setRg(rg);
		this.setCpf(cpf);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		checkNomeNull(nome);		
		checkNomeVazio(nome);		
		checkNomeTamanho(nome);		
		checkNomeComecaComLetra(nome);		
		checkNomeCaracteresValidos(nome);
		checkNomeCompostoPorUmaUnicaLetra(nome);		
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		checkRgNulo(rg);		
		checkRgVazio(rg);		
		checkRgTamanho(rg);		
		checkRgContemApenasDigitos(rg);		
		checkRgDigitosRepetidos(rg);
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		checkCpfNulo(cpf);		
		checkCpfVazio(cpf);		
		checkCpfTamanho(cpf);
		checkCpfContemApenasDigitos(cpf);		
		checkCpfCompostoPorDigitosIguais(cpf);		
		checkCpfValido(cpf);
		this.cpf = cpf;
	}
	
	private void checkNomeCompostoPorUmaUnicaLetra(String nome) {
		int repetidos = contarLetrasRepetidas(nome);
		if(repetidos == nome.length() - PRIMEIRO_INDICE) {
			throw new IllegalArgumentException("Nome não pode ser composto unicamente pelo mesmo caractere.");
		}
	}

	private int contarLetrasRepetidas(String nome) {
		String temp = nome.toLowerCase();
		char primeiro = temp.charAt(0);		
		int repetidos = 0;
		for(int i=0; i<nome.length() - PRIMEIRO_INDICE; i++) {
			if(primeiro == temp.charAt(i + PRIMEIRO_INDICE)) {
				repetidos++;
			} else {
				break;
			}
		}
		return repetidos;
	}

	private void checkNomeCaracteresValidos(String nome) {
		for(int i=0; i<nome.length(); i++) {
			if(!Character.isAlphabetic(nome.charAt(i)) &&
					(nome.charAt(i) != ' ') &&
					(nome.charAt(i) != '.')) {
				throw new IllegalArgumentException("Nome pode ser composto apenas por letras e espaços.");
			}
		}
	}

	private void checkNomeComecaComLetra(String nome) {
		if(!Character.isAlphabetic(nome.charAt(0))) {
			throw new IllegalArgumentException("Nome deve obrigatoriamente começar com uma letra.");
		}
	}

	private void checkNomeTamanho(String nome) {
		int qtdeLetras = contarQtdeLetras(nome);
		if(qtdeLetras < MIN_NOME || nome.length() > MAX_NOME) {
			throw new IllegalArgumentException("Nome deve ter no mínimo 2 e no máximo 100 caracteres, e ter ao menos 2 letras.");
		}
	}

	private int contarQtdeLetras(String nome) {
		int qtdeLetras = 0;
		for(int i=0; i<nome.length(); i++) {
			if(Character.isAlphabetic(nome.charAt(i))) {
				qtdeLetras++;
			}
		}
		return qtdeLetras;
	}

	private void checkNomeVazio(String nome) {
		if(nome.equals("")) {
			throw new IllegalArgumentException("Nome não pode estar em branco.");
		}
	}

	private void checkNomeNull(String nome) {
		if(nome == null) {
			throw new NullPointerException("Nome não pode ser nulo.");
		}
	}
	
	private void checkRgDigitosRepetidos(String rg) {
		int repetidos = contarDigitosRepetidos(rg);
		if(repetidos == rg.length() - PRIMEIRO_INDICE) {
			throw new IllegalArgumentException("RG não pode ser composto por dígitos iguais.");
		}
	}

	private void checkRgContemApenasDigitos(String rg) {
		for(int i=0; i<rg.length(); i++) {
			if(!Character.isDigit(rg.charAt(i))) {
				throw new IllegalArgumentException("RG deve conter apenas dígitos.");
			}
		}
	}

	private void checkRgTamanho(String rg) {
		if(rg.length() < MIN_RG || rg.length() > MAX_RG) {
			throw new IllegalArgumentException("RG precisa ter 8 ou 9 dígitos.");
		}
	}

	private void checkRgVazio(String rg) {
		if(rg.equals("")) {
			throw new IllegalArgumentException("RG não pode estar em branco.");
		}
	}

	private void checkRgNulo(String rg) {
		if(rg == null) {
			throw new NullPointerException("RG não pode ser nulo.");
		}
	}
	
	private void checkCpfCompostoPorDigitosIguais(String cpf) {
		int repetidos = contarDigitosRepetidos(cpf);
		if(repetidos == CPF_SIZE - PRIMEIRO_INDICE) {
			throw new IllegalArgumentException("CPF não pode ser composto por dígitos iguais.");
		}
	}

	private int contarDigitosRepetidos(String num) {
		char primeiro = num.charAt(0);
		int repetidos = 0;
		for(int i=0; i<num.length() - PRIMEIRO_INDICE; i++) {
			if(primeiro == num.charAt(i + PRIMEIRO_INDICE)) {
				repetidos++;
			} else {
				break;
			}
		}
		return repetidos;
	}

	private void checkCpfContemApenasDigitos(String cpf) {
		for(int i=0; i<CPF_SIZE; i++) {
			if(!Character.isDigit(cpf.charAt(i))) {
				throw new IllegalArgumentException("CPF deve conter apenas dígitos.");
			}
		}
	}

	private void checkCpfTamanho(String cpf) {
		if(cpf.length() != CPF_SIZE) {
			throw new IllegalArgumentException("CPF precisa ter 11 dígitos.");
		}
	}

	private void checkCpfVazio(String cpf) {
		if(cpf.equals("")) {
			throw new IllegalArgumentException("CPF não pode ser deixado em branco.");
		}
	}

	private void checkCpfNulo(String cpf) {
		if(cpf == null) {
			throw new NullPointerException("CPF não pode ser nulo.");
		}
	}
	
	private void checkCpfValido(String cpf) {
		if(!cpfValido(cpf)) {
			throw new IllegalArgumentException("CPF inválido.");
		}
	}
	
	protected boolean primeiroDigitoValido(int[] cpf, int digitoVerif) {
		int somatorio = somatorioDigitoV1(cpf);
		int verificador = 11 - (somatorio % 11);
		verificador = ((verificador >= 10) ? 0 : verificador);
		return verificador == digitoVerif;
	}

	private int somatorioDigitoV1(int[] cpf) {
		int somatorio = 0;
		for(int i=0; i<cpf.length; i++) {
			somatorio += cpf[i] * (10 - i);
		}
		return somatorio;
	}
	
	protected boolean segundoDigitoValido(int[] cpf, int[] digitoVerif) {
		int somatorio = somatorioDigitoV2(cpf);
		somatorio += digitoVerif[0] * 2;
		int verificador = 11 - (somatorio % 11);
		verificador = ((verificador >= 10) ? 0 : verificador);
		return verificador == digitoVerif[1];
	}

	private int somatorioDigitoV2(int[] cpf) {
		int somatorio = 0;
		for(int i=0; i<cpf.length; i++) {
			somatorio += cpf[i] * (11 - i);
		}
		return somatorio;
	}
	
	protected boolean cpfValido(String cpf) {		 
		int[] verif = converterVerificadoresDeCharParaNumero(cpf);
		int[] iCpf = converterDigitosDeCharParaNumero(cpf);
		return primeiroDigitoValido(iCpf, verif[0]) && segundoDigitoValido(iCpf, verif);
	}

	private int[] converterVerificadoresDeCharParaNumero(String cpf) {
		int[] verif = new int[QTDE_VERIFICADORES];
		verif[0] = cpf.charAt(CPF_POS_VERIF_1) - ASCII_INICIO_NUMEROS;
		verif[1] = cpf.charAt(CPF_POS_VERIF_2) - ASCII_INICIO_NUMEROS;
		return verif;
	}

	private int[] converterDigitosDeCharParaNumero(String cpf) {
		int[] iCpf = new int[CPF_SIZE_SEM_VERIF];
		for(int i=0; i < CPF_SIZE_SEM_VERIF; i++) {
			iCpf[i] = cpf.charAt(i) - ASCII_INICIO_NUMEROS;
		}
		return iCpf;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(this.cpf)
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
		Pessoa other = (Pessoa)obj;
		return new EqualsBuilder()
				.append(this.cpf, other.cpf)
				.isEquals();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
