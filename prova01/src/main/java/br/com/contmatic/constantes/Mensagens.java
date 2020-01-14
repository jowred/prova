package br.com.contmatic.constantes;

public final class Mensagens {

	private Mensagens() {
		
	}
	
	public static final String MENSAGEM_NOME_PESSOA_BLANK = "Nome não pode ser nulo ou vazio.";
	
	public static final String MENSAGEM_NOME_PESSOA_PATTERN = "Nome deve começar com letra maiúscula, conter apenas letras, pontos, vírgulas e hífens, e deve terminar com uma letra minúscula.";
	
	public static final String MENSAGEM_NOME_PESSOA_TAMANHO = "Nome deve ter no mínimo {min} e no máximo {max} caracteres";
	
	public static final String MENSAGEM_RG_BLANK = "RG não pode ser nulo ou vazio.";
	
	public static final String MENSAGEM_CPF = "CPF deve ser válido.";
	
	public static final String MENSAGEM_RG_PATTERN = "RG deve ser composto apenas por dígitos";
	
	public static final String MENSAGEM_RG_TAMANHO = "RG deve ter 8 ou 9 dígitos";
	
	public static final String MENSAGEM_DATA_NASCIMENTO_NULA = "A data de nascimento não pode ser nula.";
	
	public static final String MENSAGEM_DATA_NASCIMENTO_PAST = "A data de nascimento deve refletir uma data do passado.";
	
	public static final String MENSAGEM_RAZAO_SOCIAL_BLANK = "Razão social não pode ser nula ou vazia.";
	
	public static final String MENSAGEM_RAZAO_SOCIAL_PATTERN = "Razão social pode conter apenas letras, números, espaços, hífens (\"-\"), pontos(\".\"), E comercial (\"&\") e vírgula (\",\".";
	
	public static final String MENSAGEM_RAZAO_SOCIAL_TAMANHO = "Razão social deve conter de {min} a {max} caracteres.";
	
	public static final String MENSAGEM_NOME_FANTASIA_PATTERN = "Nome fantasia pode conter apenas letras, números, espaços e os caracteres especiais (., -, ,, &, !, @)";
	
}
