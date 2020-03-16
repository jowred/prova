package br.com.contmatic.constantes;

public class Regex {

	Regex() {
		
	}
	
	private static final String VOGAIS_ACENTUADAS = "áãêéóôõíú";
	
	private static final String REGEX_COMUM_NOME = "^[A-Za-z][a-zA-Zç\\s" + VOGAIS_ACENTUADAS + "\\.,]+[a-z]$";
	
	public static final String REGEX_NOME_PESSOA = "^[A-Z][a-zA-Zç\\s" + VOGAIS_ACENTUADAS + "\\.,]+[a-z]$";
	
	public static final String REGEX_RG = "^\\d{8,9}";
	
	public static final String REGEX_EMAIL = "\\w([a-z0-9-]+(\\.[a-z0-9-]+)*\\w)@(([a-z0-9]([a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9]([a-z0-9-]*[a-z0-9])?|\\[(\\.){3}([a-z0-9-]*[a-z0-9]:)\\])";
	
	public static final String REGEX_RAZAO_SOCIAL = "^[a-zA-Z][\\w\\s\\.&,\\-ç" + VOGAIS_ACENTUADAS + "]+[a-z\\.]$";
	
	public static final String REGEX_NOME_FANTASIA = "^[a-zA-Z][\\w\\s\\.&,@!\\-ç" + VOGAIS_ACENTUADAS + "]+[a-z\\.]$";
	
	public static final String REGEX_AREA_ATUACAO = REGEX_COMUM_NOME;
	
	public static final String REGEX_NOME_DEPARTAMENTO = REGEX_COMUM_NOME;
	
	public static final String REGEX_DESCRICAO_DEPARTAMENTO = REGEX_COMUM_NOME;
	
	public static final String REGEX_BAIRRO = REGEX_COMUM_NOME;
	
	public static final String REGEX_CIDADE = REGEX_COMUM_NOME;
	
	public static final String REGEX_PAIS = REGEX_COMUM_NOME;
	
	public static final String REGEX_CEP = "\\d{5}-\\d{3}";
	
	public static final String REGEX_TELEFONE = "\\d{8,9}";
	
	public static final String REGEX_PARENTESCO = "^[A-Za-z][a-zA-Zç" + VOGAIS_ACENTUADAS + "\\s]+";
	
}
