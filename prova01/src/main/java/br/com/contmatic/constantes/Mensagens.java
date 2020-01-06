package br.com.contmatic.constantes;

public final class Mensagens {

	private Mensagens() {
		
	}
	
	public static final String MENSAGEM_NOME_PESSOA_PATTERN = "Nome deve começar com letra maiúscula, conter apenas letras, pontos, vírgulas e hífens, e deve terminar com uma letra minúscula.";
	
	public static final String MENSAGEM_NOME_PESSOA_TAMANHO = "Nome deve ter no mínimo {min} e no máximo {max} caracteres";
	
	public static final String MENSAGEM_RG_PATTERN = "RG deve ser composto apenas por dígitos";
	
	public static final String MENSAGEM_RG_TAMANHO = "RG deve ter 8 ou 9 dígitos";
	
}
