package br.com.contmatic.util;

/**
 * The Class Mensagens.
 */
public final class Mensagens {

	/**
	 * Instantiates a new mensagens.
	 */
	private Mensagens() {
		
	}
	
	/////////////////////////////////////////////////////////
	/////////////////////// PESSOA //////////////////////////
	/////////////////////////////////////////////////////////
	
	/** The Constant MENSAGEM_NOME_PESSOA_BLANK. */
	public static final String MENSAGEM_NOME_PESSOA_BLANK = "Nome não pode ser nulo ou vazio.";
	
	/** The Constant MENSAGEM_NOME_PESSOA_PATTERN. */
	public static final String MENSAGEM_NOME_PESSOA_PATTERN = "Nome deve começar com letra maiúscula, conter apenas letras, pontos, vírgulas e hífens, e deve terminar com uma letra minúscula.";
	
	/** The Constant MENSAGEM_NOME_PESSOA_TAMANHO. */
	public static final String MENSAGEM_NOME_PESSOA_TAMANHO = "Nome deve ter no mínimo {min} e no máximo {max} caracteres.";
	
	/** The Constant MENSAGEM_CPF_PATTERN. */
	public static final String MENSAGEM_CPF_PATTERN = "CPF deve ser válido.";
	
	/** The Constant MENSAGEM_CPF_BLANK. */
	public static final String MENSAGEM_CPF_BLANK = "CPF não pode ser nulo ou vazio.";
	
	/** The Constant MENSAGEM_RG_BLANK. */
	public static final String MENSAGEM_RG_BLANK = "RG não pode ser nulo ou vazio.";
	
	/** The Constant MENSAGEM_RG_PATTERN. */
	public static final String MENSAGEM_RG_PATTERN = "RG deve ser composto apenas por dígitos.";
	
	/** The Constant MENSAGEM_RG_TAMANHO. */
	public static final String MENSAGEM_RG_TAMANHO = "RG deve ter 8 ou 9 dígitos.";
	
	/** The Constant MENSAGEM_DATA_NASCIMENTO_NULA. */
	public static final String MENSAGEM_DATA_NASCIMENTO_NULA = "A data de nascimento não pode ser nula.";
	
	/** The Constant MENSAGEM_DATA_NASCIMENTO_FUTURE. */
	public static final String MENSAGEM_DATA_NASCIMENTO_FUTURE = "A data de nascimento deve refletir uma data do passado.";
	
	/** The Constant MENSAGEM_EMAIL_TAMANHO. */
	public static final String MENSAGEM_EMAIL_TAMANHO = "O e-mail especificado deve ter no mínimo {min} e no máximo {max} caracteres.";
	
	/** The Constant MENSAGEM_EMAIL_PATTERN. */
	public static final String MENSAGEM_EMAIL_PATTERN = "O e-mail pode ser composto por letras, números, pontos e hífens. Exemplo: teste123@gmail.com";
	
	/** The Constant MENSAGEM_EMAIL_BLANK. */
	public static final String MENSAGEM_EMAIL_BLANK = "O e-mail não pode ser nulo ou vazio.";
	
	/////////////////////////////////////////////////////////
	/////////////////////// EMPRESA /////////////////////////
	/////////////////////////////////////////////////////////
	
	/** The Constant MENSAGEM_RAZAO_SOCIAL_BLANK. */
	public static final String MENSAGEM_RAZAO_SOCIAL_BLANK = "Razão social não pode ser nula ou vazia.";
	
	/** The Constant MENSAGEM_RAZAO_SOCIAL_PATTERN. */
	public static final String MENSAGEM_RAZAO_SOCIAL_PATTERN = "Razão social pode conter apenas letras, números, espaços, hífens (\"-\"), pontos(\".\"), E comercial (\"&\") e vírgula (\",\".)";
	
	/** The Constant MENSAGEM_RAZAO_SOCIAL_TAMANHO. */
	public static final String MENSAGEM_RAZAO_SOCIAL_TAMANHO = "Razão social deve conter de {min} a {max} caracteres.";
	
	/** The Constant MENSAGEM_NOME_FANTASIA_BLANK. */
	public static final String MENSAGEM_NOME_FANTASIA_BLANK = "Nome fantasia não pode ser nulo ou vazio.";
	
	/** The Constant MENSAGEM_NOME_FANTASIA_PATTERN. */
	public static final String MENSAGEM_NOME_FANTASIA_PATTERN = "Nome fantasia pode conter apenas letras, números, espaços e os caracteres especiais (., -, ,, &, !, @).";
	
	/** The Constant MENSAGEM_NOME_FANTASIA_TAMANHO. */
	public static final String MENSAGEM_NOME_FANTASIA_TAMANHO = "Nome fantasia deve conter de {min} a {max} caracteres.";
	
	/** The Constant MENSAGEM_CNPJ_INVALIDO. */
	public static final String MENSAGEM_CNPJ_INVALIDO = "CNPJ deve ser válido.";
	
	/** The Constant MENSAGEM_CNPJ_BLANK. */
	public static final String MENSAGEM_CNPJ_BLANK = "CNPJ não pode ser nulo ou vazio.";
	
	/** The Constant MENSAGEM_AREA_ATUACAO_BLANK. */
	public static final String MENSAGEM_AREA_ATUACAO_BLANK = "Área de atuação não pode ser nula ou vazia.";
	
	/** The Constant MENSAGEM_AREA_ATUACAO_PATTERN. */
	public static final String MENSAGEM_AREA_ATUACAO_PATTERN = "Área de atuação pode ser composta apenas por letras, espaços e pontuação.";
	
	/** The Constant MENSAGEM_AREA_ATUACAO_TAMANHO. */
	public static final String MENSAGEM_AREA_ATUACAO_TAMANHO = "Área de atuação deve conter de {min} a {max} caracteres.";
	
	/** The Constant MENSAGEM_SET_DEPARTAMENTOS_VAZIO. */
	public static final String MENSAGEM_SET_DEPARTAMENTOS_VAZIO = "A coleção de departamentos não pode ser nula ou de tamanho 0.";
	
	/** The Constant MENSAGEM_SET_TELEFONES_VAZIO. */
	public static final String MENSAGEM_SET_TELEFONES_VAZIO = "A coleção de telefones não pode ser nula ou de tamanho 0.";
	
	/** The Constant MENSAGEM_ENDERECO_NULL. */
	public static final String MENSAGEM_ENDERECO_NULL = "Endereço não pode ser nulo";
	
	/** The Constant URL_INVALIDA. */
	public static final String URL_INVALIDA = "A URL digita é inválida.";
	
	/////////////////////////////////////////////////////////
	///////////////////// DEPARTAMENTO //////////////////////
	/////////////////////////////////////////////////////////
	
	/** The Constant MENSAGEM_NOME_DEPARTAMENTO_BLANK. */
	public static final String MENSAGEM_NOME_DEPARTAMENTO_BLANK = "Nome do departamento não pode ser nulo ou vazio.";
	
	/** The Constant MENSAGEM_NOME_DEPARTAMENTO_PATTERN. */
	public static final String MENSAGEM_NOME_DEPARTAMENTO_PATTERN = "Nome do departamento pode ser composto apenas por letras, espaços e pontuação.";
	
	/** The Constant MENSAGEM_NOME_DEPARTAMENTO_TAMANHO. */
	public static final String MENSAGEM_NOME_DEPARTAMENTO_TAMANHO = "Nome do departamento deve ter de {min} a {max} caracteres.";
		
	/** The Constant MENSAGEM_DESCRICAO_DEPARTAMENTO_BLANK. */
	public static final String MENSAGEM_DESCRICAO_DEPARTAMENTO_BLANK = "Descrição do departamento não pode ser nula ou vazia.";
	
	/** The Constant MENSAGEM_DESCRICAO_DEPARTAMENTO_PATTERN. */
	public static final String MENSAGEM_DESCRICAO_DEPARTAMENTO_PATTERN = "Descrição do departamento pode ser composta apenas por letras, espaços e caracteres de pontuação (\",\", \";\", \".\").";
	
	/** The Constant MENSAGEM_DESCRICAO_DEPARTAMENTO_TAMANHO. */
	public static final String MENSAGEM_DESCRICAO_DEPARTAMENTO_TAMANHO = "Descrição deve ter de {min} a {max} caracteres.";
	
	/** The Constant MENSAGEM_SET_FUNCIONARIOS_VAZIO. */
	public static final String MENSAGEM_SET_FUNCIONARIOS_VAZIO = "A coleção de funcionários não pode estar vazia ou ser nula.";
	
	/////////////////////////////////////////////////////////
	////////////////////// DEPENDENTE ///////////////////////
	/////////////////////////////////////////////////////////
	
	/** The Constant MENSAGEM_PROVEDOR_NULL. */
	public static final String MENSAGEM_PROVEDOR_NULL = "Provedor não pode ser nulo.";
	
	/** The Constant MENSAGEM_PARENTESCO_NULO. */
	public static final String MENSAGEM_PARENTESCO_NULO = "O parentesco não pode ser nulo.";
	
	/////////////////////////////////////////////////////////
	/////////////////////// ENDEREÇO ////////////////////////
	/////////////////////////////////////////////////////////
	
	/** The Constant MENSAGEM_NUMERO_MIN. */
	public static final String MENSAGEM_NUMERO_MIN = "Número não pode ser menor que {min}";
	
	/** The Constant MENSAGEM_NUMERO_MAX. */
	public static final String MENSAGEM_NUMERO_MAX = "Número não pode ser maior que {max}";
	
	/** The Constant MENSAGEM_LOGRADOURO_BLANK. */
	public static final String MENSAGEM_LOGRADOURO_BLANK = "Logradouro não pode ser nulo ou vazio.";
	
	/** The Constant MENSAGEM_LOGRADOURO_PATTERN. */
	public static final String MENSAGEM_LOGRADOURO_PATTERN = "Nome do logradouro pode ser composto apenas por letras, espaços e pontuação.";
	
	/** The Constant MENSAGEM_LOGRADOURO_TAMANHO. */
	public static final String MENSAGEM_LOGRADOURO_TAMANHO = "Logradouro deve ter de {min} a {max} caracteres.";
	
	/** The Constant MENSAGEM_BAIRRO_BLANK. */
	public static final String MENSAGEM_BAIRRO_BLANK = "Bairro não pode ser nulo ou vazio.";
	
	/** The Constant MENSAGEM_BAIRRO_PATTERN. */
	public static final String MENSAGEM_BAIRRO_PATTERN = "Nome do bairro pode ser composto apenas por letras, espaços e pontuação.";
	
	/** The Constant MENSAGEM_BAIRRO_TAMANHO. */
	public static final String MENSAGEM_BAIRRO_TAMANHO = "Bairro deve ter de {min} a {max} caracteres.";
	
	/** The Constant MENSAGEM_CIDADE_BLANK. */
	public static final String MENSAGEM_CIDADE_BLANK = "Cidade não pode ser nula ou vazia.";
	
	/** The Constant MENSAGEM_CIDADE_PATTERN. */
	public static final String MENSAGEM_CIDADE_PATTERN = "Nome da cidade pode ser composto apenas por letras, espaços e pontuação.";
	
	/** The Constant MENSAGEM_CIDADE_TAMANHO. */
	public static final String MENSAGEM_CIDADE_TAMANHO = "Cidade deve ter de {min} a {max} caracteres.";
	
	/** The Constant MENSAGEM_UF_NULA. */
	public static final String MENSAGEM_UF_NULA = "UF não pode ser nula.";
	
	/** The Constant MENSAGEM_PAIS_BLANK. */
	public static final String MENSAGEM_PAIS_BLANK = "País não pode ser nulo ou vazio.";
	
	/** The Constant MENSAGEM_PAIS_PATTERN. */
	public static final String MENSAGEM_PAIS_PATTERN = "Nome do país pode ser composto apenas por letras, espaços e pontuação.";
	
	/** The Constant MENSAGEM_PAIS_TAMANHO. */
	public static final String MENSAGEM_PAIS_TAMANHO = "País deve ter de {min} a {max} caracteres.";
	
	/** The Constant MENSAGEM_CEP_BLANK. */
	public static final String MENSAGEM_CEP_BLANK = "CEP não pode ser nulo ou vazio.";
	
	/** The Constant MENSAGEM_CEP_PATTERN. */
	public static final String MENSAGEM_CEP_PATTERN = "CEP deve seguir o formato \"12345-123\".";
	
	/** The Constant MENSAGEM_CEP_TAMANHO. */
	public static final String MENSAGEM_CEP_TAMANHO = "CEP deve ter {max} caracteres.";
	
	/** The Constant MENSAGEM_TIPO_ENDERECO_NULO. */
	public static final String MENSAGEM_TIPO_ENDERECO_NULO = "Tipo do endereço não pode ser nulo.";
	
	/////////////////////////////////////////////////////////
	///////////////////// FUNCIONÁRIO ///////////////////////
	/////////////////////////////////////////////////////////
	
	/** The Constant MENSAGEM_SET_DEPENDENTES_VAZIO. */
	public static final String MENSAGEM_SET_DEPENDENTES_VAZIO = "A coleção de dependentes não pode ser nula ou vazia.";
	
	/** The Constant MENSAGEM_DATA_ADMISSAO_BLANK. */
	public static final String MENSAGEM_DATA_ADMISSAO_BLANK = "A data de admissão não pode ser nula.";

	/** The Constant MENSAGEM_DATA_ADMISSAO_PAST. */
	public static final String MENSAGEM_DATA_ADMISSAO_PAST = "A admissão deve refletir uma data do passado.";
	
	/** The Constant MENSAGEM_TERMINO_CONTRATO_FUTURE. */
	public static final String MENSAGEM_TERMINO_CONTRATO_FUTURE = "O término do contrato deve refletir uma data futura.";
	
	/** The Constant MENSAGEM_TERMINO_CONTRATO_BLANK. */
	public static final String MENSAGEM_TERMINO_CONTRATO_BLANK = "A data de término do contrato não pode ser nula.";
	
	/////////////////////////////////////////////////////////
	/////////////////////// TELEFONE ////////////////////////
	/////////////////////////////////////////////////////////
	
	/** The Constant MENSAGEM_TELEFONE_BLANK. */
	public static final String MENSAGEM_TELEFONE_BLANK = "O número de telefone não pode ser vazio ou nulo.";
	
	/** The Constant MENSAGEM_TELEFONE_PATTERN. */
	public static final String MENSAGEM_TELEFONE_PATTERN = "O número de telefone deve ter de 8 a 9 dígitos, sem caracteres de separação ou espaços.";
	
	/** The Constant MENSAGEM_TELEFONE_TAMANHO. */
	public static final String MENSAGEM_TELEFONE_TAMANHO = "O número de telefone deve ter de {min} a {max} dígitos.";
	
	/** The Constant MENSAGEM_CODIGO_PAIS_TAMANHO. */
	public static final String MENSAGEM_CODIGO_PAIS_TAMANHO = "O código do país deve ser no mínimo {min} e no máximo {max}.";
	
	/** The Constant MENSAGEM_DDD_TAMANHO. */
	public static final String MENSAGEM_DDD_TAMANHO = "O DDD deve ser no mínimo {min} e no máximo {max}.";
	
	/** The Constant MENSAGEM_TIPO_TELEFONE_NULO. */
	public static final String MENSAGEM_TIPO_TELEFONE_NULO = "O tipo de telefone não pode ser nulo.";
	
	/////////////////////////////////////////////////////////
	/////////////////////    SÓCIO    ///////////////////////
	/////////////////////////////////////////////////////////
	
	/** The Constant MENSAGEM_INICIO_SOCIEDADE_BLANK. */
	public static final String MENSAGEM_INICIO_SOCIEDADE_BLANK = "A data de início da sociedade não pode ser nula.";
	
	/** The Constant MENSAGEM_INICIO_SOCIEDADE_PAST. */
	public static final String MENSAGEM_INICIO_SOCIEDADE_PAST = "O início da sociedade deve refletir uma data do passado.";
		
	}
