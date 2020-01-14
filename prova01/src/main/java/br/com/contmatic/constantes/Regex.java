package br.com.contmatic.constantes;

public class Regex {

	Regex() {
		
	}
	
	public static final String REGEX_NOME_PESSOA = "^[A-Z][-a-zA-Z .,áãêéóôõíú]+[a-z]$";
	
	public static final String REGEX_RG = "^\\d{8,9}";
	
	public static final String REGEX_RAZAO_SOCIAL = "^[a-zA-Z][\\w .&,áãêéóôõíú-]+";
	
	public static final String REGEX_NOME_FANTASIA = "^[a-zA-Z][\\w .&,@!áãêéóôõíú-]+";
}
