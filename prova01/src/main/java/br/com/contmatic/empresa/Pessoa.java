package br.com.contmatic.empresa;

import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NOME_PESSOA_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NOME_PESSOA_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_RG_PATTERN;
import static br.com.contmatic.constantes.Numericas.ASCII_INICIO_NUMEROS;
import static br.com.contmatic.constantes.Numericas.CPF_POS_VERIF_1;
import static br.com.contmatic.constantes.Numericas.CPF_POS_VERIF_2;
import static br.com.contmatic.constantes.Numericas.CPF_SIZE;
import static br.com.contmatic.constantes.Numericas.CPF_SIZE_SEM_VERIF;
import static br.com.contmatic.constantes.Numericas.MAX_NOME;
import static br.com.contmatic.constantes.Numericas.MAX_RG;
import static br.com.contmatic.constantes.Numericas.MIN_NOME;
import static br.com.contmatic.constantes.Numericas.MIN_RG;
import static br.com.contmatic.constantes.Numericas.PRIMEIRO_INDICE;
import static br.com.contmatic.constantes.Numericas.QTDE_VERIFICADORES;
import static br.com.contmatic.constantes.Regex.REGEX_NOME_PESSOA;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
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

	@NotBlank(message = "Nome não pode ser nulo ou vazio.")
	@Length(min = MIN_NOME, max = MAX_NOME, message = MENSAGEM_NOME_PESSOA_TAMANHO)
	@Pattern(regexp = REGEX_NOME_PESSOA, message = MENSAGEM_NOME_PESSOA_PATTERN)
	private String nome;
	
	@NotBlank(message = "RG não pode ser nulo ou vazio.")
	@Length(min = MIN_RG, max = MAX_RG, message = MENSAGEM_NOME_PESSOA_TAMANHO)
	@Pattern(regexp = "^\\d{8,9}", message = MENSAGEM_RG_PATTERN)
	private String rg;
	
	@CPF(message = "CPF deve ser válido.")
	private String cpf;
	
	@NotNull(message = "A data de nascimento não pode ser nula.")
	@Past(message = "A data de nascimento deve refletir uma data do passado.")
	private LocalDate dataNascimento;
	
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
		//checkNomeTamanho(nome);		
		checkNomeComecaComLetra(nome);		
		checkNomeCaracteresValidos(nome);
		//checkNomeCompostoPorUmaUnicaLetra(nome);		
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
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
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
	
	public void validar(Validator validator) {
		Set<ConstraintViolation<Pessoa>> violations = validator.validate(this);
		if (violations.isEmpty()) {
			System.out.println("Validado com sucesso.");
		} else {
			for(ConstraintViolation<Pessoa> violation : violations) {
				System.out.println((violation.getMessage()));
			}
		}
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
