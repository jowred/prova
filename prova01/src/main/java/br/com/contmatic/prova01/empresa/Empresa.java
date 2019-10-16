package br.com.contmatic.prova01.empresa;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
	
	private static final int MAX_AREA_ATUACAO = 55;

	private static final int MIN_AREA_ATUACAO = 4;

	private static final int MAX_NOME_FANTASIA = 55;

	private static final int MIN_NOME_FANTASIA = 1;

	private static final int MAX_RAZ_SOCIAL = 100;

	private static final int MIN_RAZ_SOCIAL = 5;

	private String razaoSocial;
	
	private String nomeFantasia;
	
	private String cnpj;
	
	private String areaAtuacao;
	
	private List<Departamento> departamentos;
	
	private List<Telefone> telefones;
	
	private Endereco endereco;
	
	public Empresa() {
		this.departamentos = new ArrayList<>();
		this.telefones = new ArrayList<>();
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
		checkRazaoSocialNula(razaoSocial);
		checkRazaoSocialVazia(razaoSocial);		
		checkRazaoSocialTamanho(razaoSocial);		
		checkRazaoSocialCompostaUnicamentePorUmaLetra(razaoSocial);		
		checkRazaoSocialContemLetras(razaoSocial);
		checkRazaoSocialHifenInvalido(razaoSocial);
		checkRazaoSocialCaracteresValidos(razaoSocial);
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		checkNomeFantasiaNulo(nomeFantasia);		
		checkNomeFantasiaVazio(nomeFantasia);		
		checkNomeFantasiaTamanho(nomeFantasia);
		checkNomeFantasiaCompostoUnicamentePorUmCaractere(nomeFantasia);
		checkNomeFantasiaTemLetras(nomeFantasia);
		checkNomeFantasiaHifenInvalido(nomeFantasia);
		checkNomeFantasiaCaracteresValidos(nomeFantasia);
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(String cnpj) {
		checkCnpjNulo(cnpj);		
		checkCnpjVazio(cnpj);		
		checkCnpjTamanho(cnpj);		
		checkCnpjContemApenasDigitos(cnpj);		
		checkCnpjCompostoUnicamentePeloMesmoDigito(cnpj);		
		checkCnpjValido(cnpj);
		this.cnpj = cnpj;
	}
	
	public String getAreaAtuacao() {
		return areaAtuacao;
	}

	public void setAreaAtuacao(String areaAtuacao) {
		checkAreaAtuacaoNula(areaAtuacao);		
		checkAreaAtuacaoVazia(areaAtuacao);
		checkAreaAtuacaoTamanho(areaAtuacao);		
		checkAreaAtuacaoCaracteresValidos(areaAtuacao);		
		checkAreaAtuacaoCompostaUnicamentePeloMesmoCaractere(areaAtuacao);		
		this.areaAtuacao = areaAtuacao;
	}

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		checkListaDepartamentosNula(departamentos);
		checkListaDepartamentosVazia(departamentos);		
		checkNovaListaDepartamentosIgualAntiga(departamentos);		
		this.departamentos = departamentos;
	}
	
	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		checkListaTelefonesNula(telefones);
		checkListaTelefonesVazia(telefones);		
		checkNovaListaTelefonesIgualAntiga(telefones);
		this.telefones = telefones;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		checkEnderecoNulo(endereco);
		this.endereco = endereco;
	}

	protected boolean cnpjValido(String cnpj) {
		
		int[] iCnpj = new int[14];
		
		for(int i=0; i<iCnpj.length; i++) {
			iCnpj[i] = cnpj.charAt(i) - 48;
		}
		return primeiroDigitoValido(iCnpj) && segundoDigitoValido(iCnpj);
	}
	
	protected boolean primeiroDigitoValido(int[] cnpj) {
		
		int verificador = 0;
		
		for(int i=0; i<9; i+=8) {
			verificador += 5*cnpj[i] + 4*cnpj[i+1] + 3*cnpj[i+2] + 2*cnpj[i+3];
		}
		verificador += 9*cnpj[4] + 8*cnpj[5] + 7*cnpj[6] + 6*cnpj[7];
		verificador = 11 - verificador % 11;
		verificador = ((verificador >= 10) ? 0 : verificador);
		return verificador == cnpj[12];
	}
	
	protected boolean segundoDigitoValido(int[] cnpj) {
		int verificador = 0;
		for(int i=0; i<9; i+=8) {
			verificador += 6*cnpj[i] + 5*cnpj[i+1] + 4*cnpj[i+2] + 3*cnpj[i+3];
		}
		verificador += 2*cnpj[4] + 9*cnpj[5] + 8*cnpj[6] + 7*cnpj[7];
		verificador += 2*cnpj[12];
		verificador = 11 - verificador % 11;
		verificador = ((verificador >= 10) ? 0 : verificador);
		return verificador == cnpj[13];
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
			razaoSocial.charAt(razaoSocial.length()-1) == '-') {
			throw new IllegalArgumentException("Razão social não pode começar ou terminar com hífen.");
		}
	}

	private void checkRazaoSocialContemLetras(String razaoSocial) {
		int cont = 0;
		for(int i=0; i<razaoSocial.length(); i++) {
			if(Character.isAlphabetic(razaoSocial.charAt(i))) {
				break;
			} else {
				cont++;
			}
		}
		if(cont == razaoSocial.length()) {
			throw new IllegalArgumentException("Razão social não pode conter apenas números, espaços e caracteres especiais");
		}
	}

	private void checkRazaoSocialCompostaUnicamentePorUmaLetra(String razaoSocial) {
		String temp = razaoSocial.toLowerCase();
		char primeiro = temp.charAt(0);
		int repetidos = 0;
		for(int i=0; i<razaoSocial.length()-1; i++) {
			if(primeiro == temp.charAt(i+1)) {
				repetidos++;
			}
		}
		if(repetidos == razaoSocial.length()-1) {
			throw new IllegalArgumentException("Razão social não pode ser composta unicamente pelo mesmo caractere.");
		}
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
		char primeiro = cnpj.charAt(0);
		int repetidos = 0;
		for(int i=0; i<cnpj.length()-1; i++) {
			if(primeiro == cnpj.charAt(i+1)) {
				repetidos++;
			} else {
				break;
			}
		}
		if(repetidos == cnpj.length()-1) {//Porque não precisa comparar com ele mesmo
			throw new IllegalArgumentException("CNPJ não pode ser composto por dígitos iguais.");
		}
	}

	private void checkCnpjContemApenasDigitos(String cnpj) {
		for(int i=0; i<cnpj.length(); i++) {
			if(!Character.isDigit(cnpj.charAt(i))) {
				throw new IllegalArgumentException("CNPJ deve conter apenas dígitos.");
			}
		}
	}

	private void checkCnpjTamanho(String cnpj) {
		if(cnpj.length() != 14) {
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
		char primeiro = areaAtuacao.charAt(0);
		int repetidos = 0;
		for(int i=0; i<areaAtuacao.length()-1; i++) {
			if(primeiro == areaAtuacao.charAt(i+1)) {
				repetidos++;
			} else {
				break;
			}
		}
		if(repetidos == areaAtuacao.length()-1) {
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
	
	private void checkNovaListaDepartamentosIgualAntiga(List<Departamento> departamentos) {
		if(this.departamentos.equals(departamentos)) {
			throw new IllegalArgumentException("A lista de departamentos a ser inserida não pode ser idêntica à atual.");
		}
	}

	private void checkListaDepartamentosVazia(List<Departamento> departamentos) {
		if(departamentos.isEmpty()) {
			throw new IllegalArgumentException("A lista de departamentos a ser inserida não pode estar vazia (tamanho 0).");
		}
	}

	private void checkListaDepartamentosNula(List<Departamento> departamentos) {
		if(departamentos == null) {
			throw new NullPointerException("A lista de departamentos não pode ser nula.");
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
			nomeFantasia.charAt(nomeFantasia.length()-1) == '-') {
			throw new IllegalArgumentException("Nome fantasia não pode começar ou terminar com hífen.");
		}
	}

	private void checkNomeFantasiaTemLetras(String nomeFantasia) {
		int cont = 0;
		for(int i=0; i<nomeFantasia.length(); i++) {
			if(Character.isAlphabetic(nomeFantasia.charAt(i))) {
				break;
			} else {
				cont++;
			}
		}
		if(cont == nomeFantasia.length()) {
			throw new IllegalArgumentException("Nome fantasia não pode conter apenas números, espaços e caracteres especiais");
		}
	}

	private void checkNomeFantasiaCompostoUnicamentePorUmCaractere(String nomeFantasia) {
		String temp = nomeFantasia.toLowerCase(); 
		char primeiro = temp.charAt(0);
		int repetidos = 0;
		for(int i=0; i<nomeFantasia.length()-1; i++) {
			if(primeiro == temp.charAt(i+1)) {
				repetidos++;
			} else {
				break;
			}
		}
		if(repetidos == nomeFantasia.length()-1) {
			throw new IllegalArgumentException("Nome fantasia não pode ser composto unicamente pelo mesmo caractere.");
		}
	}

	private void checkNomeFantasiaTamanho(String nomeFantasia) {
		if(nomeFantasia.length() <= MIN_NOME_FANTASIA || nomeFantasia.length() > MAX_NOME_FANTASIA) {
			throw new IllegalArgumentException("Nome fantasia deve ter no mínimo 2 e no máximo 55 caracteres, contando com os espaços.");
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
	
	private void checkNovaListaTelefonesIgualAntiga(List<Telefone> telefones) {
		if(this.telefones.equals(telefones)) {
			throw new IllegalArgumentException("A lista de telefones a ser inserida não pode ser idêntica à atual.");
		}
	}

	private void checkListaTelefonesVazia(List<Telefone> telefones) {
		if(telefones.isEmpty()) {
			throw new IllegalArgumentException("A lista de telefones a ser inserida não pode estar vazia (tamanho 0).");
		}
	}

	private void checkListaTelefonesNula(List<Telefone> telefones) {
		if(telefones == null) {
			throw new NullPointerException("A lista de telefones não pode ser nula.");
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
		final int prime = 31;
		int result = 1;
		result = prime * result + cnpj.hashCode();
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
		Empresa other = (Empresa)obj;		
		if(!cnpj.equals(other.cnpj))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "DADOS DA EMPRESA:\n"
				+ "Razão social: " + razaoSocial + "\n"
				+ "CNPJ: " + cnpj + "\n"
				+ "Área de atuação: " + areaAtuacao + "\n";
	}
}
