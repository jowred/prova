package br.com.contmatic.constantes;

// TODO: Auto-generated Javadoc
/**
 * The Class Regex.
 */
public final class Regex {

	/**
	 * Instantiates a new regex.
	 */
	private Regex() {
		
	}
	
	/** The Constant VOGAIS_ACENTUADAS. */
	private static final String VOGAIS_ACENTUADAS = "áàãêéèóôõíú";
	
	/** The Constant VOGAIS_ACENTUADAS_UPPER. */
	private static final String VOGAIS_ACENTUADAS_UPPER = "ÁÀÃÊÉÈÓÔÕÍÚ";
	
	/** The Constant REGEX_COMUM_NOME. */
	private static final String REGEX_COMUM_NOME = "^[A-Za-z" + VOGAIS_ACENTUADAS + VOGAIS_ACENTUADAS_UPPER + "][a-zA-Zç\\s\\-" + VOGAIS_ACENTUADAS + "\\.,]*[a-zA-Z\\." + VOGAIS_ACENTUADAS + "]$";
	
	/** The Constant REGEX_NOME_PESSOA. */
	public static final String REGEX_NOME_PESSOA = "^[A-Z" + VOGAIS_ACENTUADAS_UPPER + "][a-zA-Zç\\s\\-" + VOGAIS_ACENTUADAS + "\\.,]*[a-zA-Z\\." + VOGAIS_ACENTUADAS + "]$";
	
	/** The Constant REGEX_RG. */
	public static final String REGEX_RG = "^\\d{8,9}";
	
	/** The Constant REGEX_EMAIL. */
	public static final String REGEX_EMAIL = "\\w([a-z0-9-]+(\\.[a-z0-9-]+)*\\w)@(([a-z0-9]([a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9]([a-z0-9-]*[a-z0-9])?|\\[(\\.){3}([a-z0-9-]*[a-z0-9]:)\\])";
	
	/** The Constant REGEX_RAZAO_SOCIAL. */
	public static final String REGEX_RAZAO_SOCIAL = "^[a-zA-Z][\\w\\s\\.&,\\-ç" + VOGAIS_ACENTUADAS + "]+[a-zA-Z\\.]$";
	
	/** The Constant REGEX_NOME_FANTASIA. */
	public static final String REGEX_NOME_FANTASIA = "^[a-zA-Z][\\w\\s\\.&,@!\\-ç" + VOGAIS_ACENTUADAS + "]+[a-zA-Z\\.]$";
	
	/** The Constant REGEX_AREA_ATUACAO. */
	public static final String REGEX_AREA_ATUACAO = REGEX_COMUM_NOME;
	
	/** The Constant REGEX_NOME_DEPARTAMENTO. */
	public static final String REGEX_NOME_DEPARTAMENTO = REGEX_COMUM_NOME;
	
	/** The Constant REGEX_DESCRICAO_DEPARTAMENTO. */
	public static final String REGEX_DESCRICAO_DEPARTAMENTO = REGEX_COMUM_NOME;
	
	/** The Constant REGEX_LOGRADOURO. */
	public static final String REGEX_LOGRADOURO = "^[A-Za-z" + VOGAIS_ACENTUADAS + VOGAIS_ACENTUADAS_UPPER + "][a-zA-Zç\\-\\s" + VOGAIS_ACENTUADAS + "\\.,]*[a-zA-Z0-9\\." + VOGAIS_ACENTUADAS + "]+$";
	
	/** The Constant REGEX_BAIRRO. */
	public static final String REGEX_BAIRRO = REGEX_COMUM_NOME;
	
	/** The Constant REGEX_CIDADE. */
	public static final String REGEX_CIDADE = REGEX_COMUM_NOME;
	
	/** The Constant REGEX_PAIS. */
	public static final String REGEX_PAIS = REGEX_COMUM_NOME;
	
	/** The Constant REGEX_CEP. */
	public static final String REGEX_CEP = "\\d{5}-\\d{3}";
	
	/** The Constant REGEX_TELEFONE. */
	public static final String REGEX_TELEFONE = "\\d{8,9}";
	
	/** The Constant REGEX_PARENTESCO. */
	public static final String REGEX_PARENTESCO = "^[A-Za-z][a-zA-Zç" + VOGAIS_ACENTUADAS + "\\s]+";
	
}
