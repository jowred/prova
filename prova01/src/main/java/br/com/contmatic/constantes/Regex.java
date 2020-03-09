package br.com.contmatic.constantes;

public class Regex {

	Regex() {
		
	}
	
	private static final String VOGAIS_ACENTUADAS = "áãêéóôõíú";
	
	public static final String REGEX_NOME_PESSOA = "^[A-Z][a-zA-Zç\\s" + VOGAIS_ACENTUADAS + "\\.,]+[a-z]$";
	
	public static final String REGEX_RG = "^\\d{8,9}";
	
	public static final String REGEX_EMAIL = "\\w([a-z0-9-]+(\\.[a-z0-9-]+)*\\w)@(([a-z0-9]([a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9]([a-z0-9-]*[a-z0-9])?|\\[(\\.){3}([a-z0-9-]*[a-z0-9]:)\\])";
	
	public static final String REGEX_RAZAO_SOCIAL = "^[a-zA-Z][\\w\\s\\.&," + VOGAIS_ACENTUADAS + "]+";
	
	public static final String REGEX_NOME_FANTASIA = "^[a-zA-Z][\\w\\s\\.&,@!" + VOGAIS_ACENTUADAS + "]+";
	
	public static final String REGEX_AREA_ATUACAO = "^[a-zA-Z][\\w\\s\\.," + VOGAIS_ACENTUADAS + "]+";
	
	public static final String REGEX_NOME_DEPARTAMENTO = "^[A-Za-z][a-zA-Zç\\s" + VOGAIS_ACENTUADAS + "\\.,]+[a-z]$";
	
	public static final String REGEX_DESCRICAO_DEPARTAMENTO = "^[A-Za-z][a-zA-Zç\\s" + VOGAIS_ACENTUADAS + "\\.,]+[a-z]$";
}
