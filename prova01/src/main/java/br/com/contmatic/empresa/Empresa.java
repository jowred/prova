package br.com.contmatic.empresa;

import static br.com.contmatic.constantes.Mensagens.MENSAGEM_AREA_ATUACAO_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_AREA_ATUACAO_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_AREA_ATUACAO_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_CNPJ_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_CNPJ_INVALIDO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_EMAIL_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_EMAIL_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_EMAIL_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_ENDERECO_NULL;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NOME_FANTASIA_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NOME_FANTASIA_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NOME_FANTASIA_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_RAZAO_SOCIAL_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_RAZAO_SOCIAL_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_RAZAO_SOCIAL_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_SET_DEPARTAMENTOS_VAZIO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_SET_TELEFONES_VAZIO;
import static br.com.contmatic.constantes.Mensagens.URL_INVALIDA;
import static br.com.contmatic.constantes.Numericas.ASCII_INICIO_NUMEROS;
import static br.com.contmatic.constantes.Numericas.CNPJ_POS_VERIF_1;
import static br.com.contmatic.constantes.Numericas.CNPJ_POS_VERIF_2;
import static br.com.contmatic.constantes.Numericas.CNPJ_SIZE;
import static br.com.contmatic.constantes.Numericas.MAX_AREA_ATUACAO;
import static br.com.contmatic.constantes.Numericas.MAX_EMAIL;
import static br.com.contmatic.constantes.Numericas.MAX_NOME_FANTASIA;
import static br.com.contmatic.constantes.Numericas.MAX_RAZ_SOCIAL;
import static br.com.contmatic.constantes.Numericas.MIN_AREA_ATUACAO;
import static br.com.contmatic.constantes.Numericas.MIN_EMAIL;
import static br.com.contmatic.constantes.Numericas.MIN_NOME_FANTASIA;
import static br.com.contmatic.constantes.Numericas.MIN_RAZ_SOCIAL;
import static br.com.contmatic.constantes.Numericas.PRIMEIRO_INDICE;
import static br.com.contmatic.constantes.Regex.REGEX_AREA_ATUACAO;
import static br.com.contmatic.constantes.Regex.REGEX_EMAIL;
import static br.com.contmatic.constantes.Regex.REGEX_NOME_FANTASIA;
import static br.com.contmatic.constantes.Regex.REGEX_RAZAO_SOCIAL;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.br.CNPJ;

public class Empresa {
	
	@Length(min = MIN_RAZ_SOCIAL, max = MAX_RAZ_SOCIAL, message = MENSAGEM_RAZAO_SOCIAL_TAMANHO)
	@Pattern(regexp = REGEX_RAZAO_SOCIAL, message = MENSAGEM_RAZAO_SOCIAL_PATTERN)
	@NotBlank(message = MENSAGEM_RAZAO_SOCIAL_BLANK)
	private String razaoSocial;
	
	@Length(min = MIN_NOME_FANTASIA, max = MAX_NOME_FANTASIA, message = MENSAGEM_NOME_FANTASIA_TAMANHO)
	@Pattern(regexp = REGEX_NOME_FANTASIA, message = MENSAGEM_NOME_FANTASIA_PATTERN)
	@NotBlank(message = MENSAGEM_NOME_FANTASIA_BLANK)
	private String nomeFantasia;
	
	@CNPJ(message = MENSAGEM_CNPJ_INVALIDO)
	@NotBlank(message = MENSAGEM_CNPJ_BLANK)
	private String cnpj;
	
	@Length(min = MIN_AREA_ATUACAO, max = MAX_AREA_ATUACAO, message = MENSAGEM_AREA_ATUACAO_TAMANHO)
	@Pattern(regexp = REGEX_AREA_ATUACAO, message = MENSAGEM_AREA_ATUACAO_PATTERN)
	@NotBlank(message = MENSAGEM_AREA_ATUACAO_BLANK )
	private String areaAtuacao;
	
	@NotEmpty(message = MENSAGEM_SET_DEPARTAMENTOS_VAZIO)
	private Set<Departamento> departamentos;
	
	@NotEmpty(message = MENSAGEM_SET_TELEFONES_VAZIO)
	private Set<Telefone> telefones;
	
	@NotNull(message = MENSAGEM_ENDERECO_NULL)
	private Endereco endereco;
	
	@Length(min = MIN_EMAIL, max = MAX_EMAIL, message = MENSAGEM_EMAIL_TAMANHO)
	@Pattern(regexp = REGEX_EMAIL, message = MENSAGEM_EMAIL_PATTERN)
	@NotBlank(message = MENSAGEM_EMAIL_BLANK)
	private String email;
	
	@URL(message = URL_INVALIDA)
	private String site;
	
	public Empresa() {
		this.departamentos = new HashSet<>();
		this.telefones = new HashSet<>();
		this.endereco = new Endereco();
	}
	
	public Empresa(String razaoSocial, String cnpj) {
		this();
		this.setRazaoSocial(razaoSocial);
		this.setCnpj(cnpj);
	}
	
	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
//		checkRazaoSocialNula(razaoSocial);
//		checkRazaoSocialVazia(razaoSocial);		
//		checkRazaoSocialTamanho(razaoSocial);		
//		checkRazaoSocialCompostaUnicamentePorUmaLetra(razaoSocial);		
//		checkRazaoSocialContemLetras(razaoSocial);
//		checkRazaoSocialHifenInvalido(razaoSocial);
//		checkRazaoSocialCaracteresValidos(razaoSocial);
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
//		checkNomeFantasiaNulo(nomeFantasia);		
//		checkNomeFantasiaVazio(nomeFantasia);		
//		checkNomeFantasiaTamanho(nomeFantasia);
//		checkNomeFantasiaCompostoUnicamentePorUmCaractere(nomeFantasia);
//		checkNomeFantasiaTemLetras(nomeFantasia);
//		checkNomeFantasiaHifenInvalido(nomeFantasia);
//		checkNomeFantasiaCaracteresValidos(nomeFantasia);
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(String cnpj) {
//		checkCnpjNulo(cnpj);		
//		checkCnpjVazio(cnpj);		
//		checkCnpjTamanho(cnpj);
//		checkCnpjContemApenasDigitos(cnpj);		
//		checkCnpjCompostoUnicamentePeloMesmoDigito(cnpj);		
//		checkCnpjValido(cnpj);
		this.cnpj = cnpj;
	}
	
	public String getAreaAtuacao() {
		return areaAtuacao;
	}

	public void setAreaAtuacao(String areaAtuacao) {
//		checkAreaAtuacaoNula(areaAtuacao);		
//		checkAreaAtuacaoVazia(areaAtuacao);
//		checkAreaAtuacaoTamanho(areaAtuacao);		
//		checkAreaAtuacaoCaracteresValidos(areaAtuacao);		
//		checkAreaAtuacaoCompostaUnicamentePeloMesmoCaractere(areaAtuacao);		
		this.areaAtuacao = areaAtuacao;
	}

	public Set<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(Set<Departamento> departamentos) {
//		checkSetDepartamentosNulo(departamentos);
//		checkSetDepartamentosVazio(departamentos);		
//		checkNovoSetDepartamentosIgualAntigo(departamentos);		
		this.departamentos = departamentos;
	}
	
	public Set<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<Telefone> telefones) {
//		checkSetTelefonesNulo(telefones);
//		checkSetTelefonesVazio(telefones);		
//		checkNovoSetTelefonesIgualAntigo(telefones);
		this.telefones = telefones;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
//		checkEnderecoNulo(endereco);
		this.endereco = endereco;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	protected boolean cnpjValido(String cnpj) {
		int[] iCnpj = converterCpfParaNumero(cnpj);
		return primeiroDigitoValido(iCnpj) && segundoDigitoValido(iCnpj);
	}

	private int[] converterCpfParaNumero(String cnpj) {
		int[] iCnpj = new int[CNPJ_SIZE];
		for(int i=0; i<iCnpj.length; i++) {
			iCnpj[i] = cnpj.charAt(i) - ASCII_INICIO_NUMEROS;
		}
		return iCnpj;
	}
	
	protected boolean primeiroDigitoValido(int[] cnpj) {
		int verificador = multiplicar4Primeiros4UltimosDigitoV1(cnpj);
		verificador += 9*cnpj[4] + 8*cnpj[5] + 7*cnpj[6] + 6*cnpj[7];
		verificador = 11 - verificador % 11;
		verificador = ((verificador >= 10) ? 0 : verificador);
		return verificador == cnpj[CNPJ_POS_VERIF_1];
	}

	private int multiplicar4Primeiros4UltimosDigitoV1(int[] cnpj) {
		int verificador = 0;
		for(int i=0; i<9; i+=8) {
			verificador += 5*cnpj[i] + 4*cnpj[i+1] + 3*cnpj[i+2] + 2*cnpj[i+3];
		}
		return verificador;
	}
	
	protected boolean segundoDigitoValido(int[] cnpj) {
		int verificador = multiplicar4Primeiros4UltimosDigitoV2(cnpj);
		verificador += 2*cnpj[4] + 9*cnpj[5] + 8*cnpj[6] + 7*cnpj[7];
		verificador += 2*cnpj[12];
		verificador = 11 - verificador % 11;
		verificador = ((verificador >= 10) ? 0 : verificador);
		return verificador == cnpj[CNPJ_POS_VERIF_2];
	}

	private int multiplicar4Primeiros4UltimosDigitoV2(int[] cnpj) {
		int verificador = 0;
		for(int i=0; i<9; i+=8) {
			verificador += 6*cnpj[i] + 5*cnpj[i+1] + 4*cnpj[i+2] + 3*cnpj[i+3];
		}
		return verificador;
	}
	
	private void checkRazaoSocialCaracteresValidos(String razaoSocial) {
		for(int i=0; i<razaoSocial.length(); i++) {
			if(!Character.isAlphabetic(razaoSocial.charAt(i)) &&
					!Character.isDigit(razaoSocial.charAt(i)) &&
					(razaoSocial.charAt(i) != ' ') &&
					(razaoSocial.charAt(i) != '-') &&
					(razaoSocial.charAt(i) != '.') &&
					(razaoSocial.charAt(i) != ',') &&
					(razaoSocial.charAt(i) != '&')) {
				throw new IllegalArgumentException("Razão social pode conter apenas letras, números, espaços, hífens (\"-\"), pontos(\".\"), E comercial (\"&\") e vírgula (\",\".");
			}
		}
	}

	private void checkRazaoSocialHifenInvalido(String razaoSocial) {
		if(razaoSocial.charAt(0) == '-' ||
			razaoSocial.charAt(razaoSocial.length() - PRIMEIRO_INDICE) == '-') {
			throw new IllegalArgumentException("Razão social não pode começar ou terminar com hífen.");
		}
	}

	private void checkRazaoSocialContemLetras(String razaoSocial) {
		int qtdeLetras = contarQtdeCaracteresNaoAlfabeticos(razaoSocial);
		if(qtdeLetras == razaoSocial.length()) {
			throw new IllegalArgumentException("Razão social não pode conter apenas números, espaços e caracteres especiais");
		}
	}

	private int contarQtdeCaracteresNaoAlfabeticos(String razaoSocial) {
		int qtdeLetras = 0;
		for(int i=0; i<razaoSocial.length(); i++) {
			if(Character.isAlphabetic(razaoSocial.charAt(i))) {
				break;
			} else {
				qtdeLetras++;
			}
		}
		return qtdeLetras;
	}

	private void checkRazaoSocialCompostaUnicamentePorUmaLetra(String razaoSocial) {
		int repetidos = contarLetrasRepetidas(razaoSocial);
		if(repetidos == razaoSocial.length() - PRIMEIRO_INDICE) {
			throw new IllegalArgumentException("Razão social não pode ser composta unicamente pelo mesmo caractere.");
		}
	}

	private int contarLetrasRepetidas(String razaoSocial) {
		String temp = razaoSocial.toLowerCase();
		char primeiro = temp.charAt(0);
		int repetidos = 0;
		for(int i=0; i<razaoSocial.length() - PRIMEIRO_INDICE; i++) {
			if(primeiro == temp.charAt(i + PRIMEIRO_INDICE)) {
				repetidos++;
			}
		}
		return repetidos;
	}

	private void checkRazaoSocialTamanho(String razaoSocial) {
		if(razaoSocial.length() < MIN_RAZ_SOCIAL || razaoSocial.length() > MAX_RAZ_SOCIAL) {
			throw new IllegalArgumentException("Razão social deve ter no mínimo 5 e no máximo 100 caracteres, contando espaços em branco.");
		}
	}

	private void checkRazaoSocialVazia(String razaoSocial) {
		if(razaoSocial.equals("")) {
			throw new IllegalArgumentException("Razão social não pode ser um espaço em branco");
		}
	}

	private void checkRazaoSocialNula(String razaoSocial) {
		if(razaoSocial == null) {
			throw new NullPointerException("Razão social não pode ser nula");
		}
	}
	
	private void checkCnpjCompostoUnicamentePeloMesmoDigito(String cnpj) {
		int repetidos = contarDigitosRepetidos(cnpj);
		if(repetidos == cnpj.length()-PRIMEIRO_INDICE) {
			throw new IllegalArgumentException("CNPJ não pode ser composto por dígitos iguais.");
		}
	}

	private int contarDigitosRepetidos(String cnpj) {
		char primeiro = cnpj.charAt(0);
		int repetidos = 0;
		for(int i=0; i<cnpj.length() - PRIMEIRO_INDICE; i++) {
			if(primeiro == cnpj.charAt(i + PRIMEIRO_INDICE)) {
				repetidos++;
			} else {
				break;
			}
		}
		return repetidos;
	}

	private void checkCnpjContemApenasDigitos(String cnpj) {
		for(int i=0; i<CNPJ_SIZE; i++) {
			if(!Character.isDigit(cnpj.charAt(i))) {
				throw new IllegalArgumentException("CNPJ deve conter apenas dígitos.");
			}
		}
	}

	private void checkCnpjTamanho(String cnpj) {
		if(cnpj.length() != CNPJ_SIZE) {
			throw new IllegalArgumentException("CNPJ deve conter 14 dígitos.");
		}
	}

	private void checkCnpjVazio(String cnpj) {
		if(cnpj.equals("")) {
			throw new IllegalArgumentException("CNPJ não pode ser em branco.");
		}
	}

	private void checkCnpjNulo(String cnpj) {
		if(cnpj == null) {
			throw new NullPointerException("CNPJ não pode ser nulo.");
		}
	}
	
	private void checkAreaAtuacaoCompostaUnicamentePeloMesmoCaractere(String areaAtuacao) {
		int repetidos = contarDigitosRepetidos(areaAtuacao);
		if(repetidos == areaAtuacao.length() - PRIMEIRO_INDICE) {
			throw new IllegalArgumentException("Área de atuação não pode ser composto unicamente pelo mesmo caractere.");
		}
	}

	private void checkAreaAtuacaoCaracteresValidos(String areaAtuacao) {
		for(int i=0; i<areaAtuacao.length(); i++) {
			if(!Character.isAlphabetic(areaAtuacao.charAt(i)) &&
					(areaAtuacao.charAt(i) != ' ')) {
				throw new IllegalArgumentException("Área de atuação deve conter apenas letras e espaços.");
			}
		}
	}

	private void checkAreaAtuacaoTamanho(String areaAtuacao) {
		if(areaAtuacao.length() < MIN_AREA_ATUACAO || areaAtuacao.length() > MAX_AREA_ATUACAO) {
			throw new IllegalArgumentException("Área de atuação deve ter no mínio 4 e no máximo 55 caracteres.");
		}
	}

	private void checkAreaAtuacaoVazia(String areaAtuacao) {
		if(areaAtuacao.equals("")) {
			throw new IllegalArgumentException("Área de atuação não pode ser em branco.");
		}
	}

	private void checkAreaAtuacaoNula(String areaAtuacao) {
		if(areaAtuacao == null) {
			throw new NullPointerException("Área de atuação não pode ser nula.");
		}
	}
	
	private void checkNovoSetDepartamentosIgualAntigo(Set<Departamento> departamentos) {
		if(this.departamentos.equals(departamentos)) {
			throw new IllegalArgumentException("O set de departamentos a ser inserido não pode ser idêntico ao atual.");
		}
	}

	private void checkSetDepartamentosVazio(Set<Departamento> departamentos) {
		if(departamentos.isEmpty()) {
			throw new IllegalArgumentException("O set de departamentos a ser inserido não pode estar vazio (tamanho 0).");
		}
	}

	private void checkSetDepartamentosNulo(Set<Departamento> departamentos) {
		if(departamentos == null) {
			throw new NullPointerException("O set de departamentos não pode ser nulo.");
		}
	}
	
	private void checkNomeFantasiaCaracteresValidos(String nomeFantasia) {
		for(int i=0; i<nomeFantasia.length(); i++) {
			if(!Character.isAlphabetic(nomeFantasia.charAt(i)) &&
					!Character.isDigit(nomeFantasia.charAt(i)) &&
					(nomeFantasia.charAt(i) != ' ') &&
					(nomeFantasia.charAt(i) != '-') &&
					(nomeFantasia.charAt(i) != '.') &&
					(nomeFantasia.charAt(i) != ',') &&
					(nomeFantasia.charAt(i) != '&') &&
					(nomeFantasia.charAt(i) != '!') &&
					(nomeFantasia.charAt(i) != '@')) {
				throw new IllegalArgumentException("Nome fantasia pode conter apenas letras, números, espaços, hífens (\"-\"), pontos (\".\"), E comercial (\"&\") e vírgula (\",\").");
			}
		}
	}

	private void checkNomeFantasiaHifenInvalido(String nomeFantasia) {
		if(nomeFantasia.charAt(0) == '-' ||
			nomeFantasia.charAt(nomeFantasia.length() - 1) == '-') {
			throw new IllegalArgumentException("Nome fantasia não pode começar ou terminar com hífen.");
		}
	}

	private void checkNomeFantasiaTemLetras(String nomeFantasia) {
		int cont = contarQtdeCaracteresNaoAlfabeticos(nomeFantasia);
		if(cont == nomeFantasia.length()) {
			throw new IllegalArgumentException("Nome fantasia não pode conter apenas números, espaços e caracteres especiais");
		}
	}

	private void checkNomeFantasiaCompostoUnicamentePorUmCaractere(String nomeFantasia) {
		int repetidos = contarLetrasRepetidas(nomeFantasia);
		if(repetidos == nomeFantasia.length() - PRIMEIRO_INDICE) {
			throw new IllegalArgumentException("Nome fantasia não pode ser composto unicamente pelo mesmo caractere.");
		}
	}

	private void checkNomeFantasiaTamanho(String nomeFantasia) {
		if(nomeFantasia.length() < MIN_NOME_FANTASIA || nomeFantasia.length() > MAX_NOME_FANTASIA) {
			throw new IllegalArgumentException("Nome fantasia deve ter no mínimo 1 e no máximo 55 caracteres, contando com os espaços.");
		}
	}

	private void checkNomeFantasiaVazio(String nomeFantasia) {
		if(nomeFantasia.equals("")) {
			throw new IllegalArgumentException("Nome fantasia não pode ser um espaço em branco");
		}
	}

	private void checkNomeFantasiaNulo(String nomeFantasia) {
		if(nomeFantasia == null) {
			throw new NullPointerException("Nome fantasia não pode ser nulo");
		}
	}
	
	private void checkNovoSetTelefonesIgualAntigo(Set<Telefone> telefones) {
		if(this.telefones.equals(telefones)) {
			throw new IllegalArgumentException("O set de telefones a ser inserido não pode ser idêntico ao atual.");
		}
	}

	private void checkSetTelefonesVazio(Set<Telefone> telefones) {
		if(telefones.isEmpty()) {
			throw new IllegalArgumentException("O set de telefones a ser inserido não pode estar vazio (tamanho 0).");
		}
	}

	private void checkSetTelefonesNulo(Set<Telefone> telefones) {
		if(telefones == null) {
			throw new NullPointerException("O set de telefones não pode ser nulo.");
		}
	}
	
	private void checkCnpjValido(String cnpj) {
		if(!cnpjValido(cnpj)) {
			throw new IllegalArgumentException("CNPJ não é válido.");
		}
	}
	
	private void checkEnderecoNulo(Endereco endereco) {
		if(endereco == null) {
			throw new NullPointerException("Endereço não pode ser nulo");
		}
	}
	
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(this.cnpj)
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
		Empresa other = (Empresa)obj;		
		return new EqualsBuilder()
				.append(this.cnpj, other.cnpj)
				.isEquals();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
