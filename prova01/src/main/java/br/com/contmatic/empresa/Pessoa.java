package br.com.contmatic.empresa;

import static br.com.contmatic.constantes.Mensagens.MENSAGEM_CPF_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_CPF_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_DATA_NASCIMENTO_FUTURE;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_DATA_NASCIMENTO_NULA;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_EMAIL_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_EMAIL_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_EMAIL_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NOME_PESSOA_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NOME_PESSOA_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NOME_PESSOA_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_RG_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_RG_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_RG_TAMANHO;
import static br.com.contmatic.constantes.Numericas.ASCII_INICIO_NUMEROS;
import static br.com.contmatic.constantes.Numericas.CPF_POS_VERIF_1;
import static br.com.contmatic.constantes.Numericas.CPF_POS_VERIF_2;
import static br.com.contmatic.constantes.Numericas.CPF_SIZE_SEM_VERIF;
import static br.com.contmatic.constantes.Numericas.MAX_EMAIL;
import static br.com.contmatic.constantes.Numericas.MAX_NOME;
import static br.com.contmatic.constantes.Numericas.MAX_RG;
import static br.com.contmatic.constantes.Numericas.MIN_EMAIL;
import static br.com.contmatic.constantes.Numericas.MIN_NOME;
import static br.com.contmatic.constantes.Numericas.MIN_RG;
import static br.com.contmatic.constantes.Numericas.QTDE_VERIFICADORES;
import static br.com.contmatic.constantes.Regex.REGEX_EMAIL;
import static br.com.contmatic.constantes.Regex.REGEX_NOME_PESSOA;
import static br.com.contmatic.constantes.Regex.REGEX_RG;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.joda.time.LocalDate;

public class Pessoa {

	@Length(min = MIN_NOME, max = MAX_NOME, message = MENSAGEM_NOME_PESSOA_TAMANHO)
	@Pattern(regexp = REGEX_NOME_PESSOA, message = MENSAGEM_NOME_PESSOA_PATTERN)
	@NotBlank(message = MENSAGEM_NOME_PESSOA_BLANK)
	private String nome;
	
	@Length(min = MIN_RG, max = MAX_RG, message = MENSAGEM_RG_TAMANHO)
	@Pattern(regexp = REGEX_RG, message = MENSAGEM_RG_PATTERN)
	@NotBlank(message = MENSAGEM_RG_BLANK)
	private String rg;
	
	@CPF(message = MENSAGEM_CPF_PATTERN)
	@NotBlank(message = MENSAGEM_CPF_BLANK)
	private String cpf;
	
	@Past(message = MENSAGEM_DATA_NASCIMENTO_FUTURE)
	@NotNull(message = MENSAGEM_DATA_NASCIMENTO_NULA)
	private LocalDate dataNascimento;
	
	@Length(min = MIN_EMAIL, max = MAX_EMAIL, message = MENSAGEM_EMAIL_TAMANHO)
	@Pattern(regexp = REGEX_EMAIL, message = MENSAGEM_EMAIL_PATTERN)
	@NotBlank(message = MENSAGEM_EMAIL_BLANK)
	private String email;
	
	public Pessoa() {
		
	}
	
	public Pessoa(String nome, String rg, String cpf) {
		this.setNome(nome);
		this.setRg(rg);
		this.setCpf(cpf);
	}
	
	public Pessoa(String nome, String rg, String cpf, LocalDate dataNascimento, String email) {
		this(nome, rg, cpf);
		this.setDataNascimento(dataNascimento);
		this.setEmail(email);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {	
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.toLowerCase();
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
