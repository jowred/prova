package br.com.contmatic.empresa;

import static br.com.contmatic.constantes.Mensagens.MENSAGEM_CPF_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_CPF_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_DATA_NASCIMENTO_FUTURE;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_DATA_NASCIMENTO_NULA;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_IDADE_MAX;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_IDADE_MIN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NOME_PESSOA_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NOME_PESSOA_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NOME_PESSOA_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_PARENTESCO_NULO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_PROVEDOR_NULL;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_RG_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_RG_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_RG_TAMANHO;
import static br.com.contmatic.enums.EnumTipoParentesco.FILHO;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.enums.EnumTipoParentesco;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class DependenteTest {
	
	Dependente dependente;
	
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

	private Validator validator = factory.getValidator();

	public Set<String> getErros(Dependente dependente) {
		Set<String> erros = new HashSet<>();
		for (ConstraintViolation<Dependente> constraintViolation : validator.validate(dependente)) {
			erros.add(constraintViolation.getMessageTemplate());
			System.out.println(constraintViolation.getMessageTemplate());
		}
		return erros;
	}
	
	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("Iniciando testes da classe Dependente...");
		FixtureFactoryLoader.loadTemplates("br.com.contmatic.templates");
	}
	
	@Before
	public void setUp() {
		dependente = Fixture.from(Dependente.class).gimme("valido");
	}
	
	@After
	public void tearDown() {
		dependente = null;
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		System.out.println("Encerrando testes da classe Dependente.");
	}
	
	@Test
	public void deve_validar_objeto_criado_com_fixture() {
		System.out.println(dependente);
		assertThat(getErros(dependente).size(), is(0));
	}
	
	@Test
	public void deve_indicar_que_o_metodo_toString_esta_sobrescrito_por_nao_conter_o_caractere_arroba() {
		assertThat(new Dependente().toString(), not(containsString("@")));
	}
	
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_dependente() {
		dependente = Fixture.from(Dependente.class).gimme("mock");
		Dependente dep = Fixture.from(Dependente.class).gimme("mock");
		assertThat(dependente, equalTo(dep));
	}
	
	//equals
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_ambos_tem_o_mesmo_cpf() {
		Dependente dep = Fixture.from(Dependente.class).gimme("valido");
		dep.setCpf(dependente.getCpf());
		assertThat(dependente, equalTo(dep));
	}
	
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_sao_o_mesmo_objeto() {
		Dependente dep = dependente;
		assertThat(dependente, equalTo(dep));
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_cpf_do_objeto2_e_diferente() {
		Dependente dep = Fixture.from(Dependente.class).gimme("valido");
		dep.setCpf("81056888164");
		assertThat(dependente, not(equalTo(dep)));
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_os_objetos_sao_de_classes_diferentes() {
		assertThat(dependente, not(equalTo(new Object())));
	}
	
	//hashcode
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_dependente_usando_hashcode_sobrescrito() {
		dependente = Fixture.from(Dependente.class).gimme("mock");
		Dependente dep = Fixture.from(Dependente.class).gimme("mock");
		assertThat(dependente.hashCode(), equalTo(dep.hashCode()));
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_hashcode_sobrescrito() {
		Dependente dep = Fixture.from(Dependente.class).gimme("valido");
		dep.setCpf("22104854016");
		assertThat(dependente.hashCode(), not(equalTo(dep.hashCode())));
	}
	
	/*
	 * NOME
	 */
	@Test
	public void nao_deve_aceitar_nome_fora_do_padrao_da_regex_especificada() {
		dependente.setNome("maria");
		assertThat(getErros(dependente), hasItem(MENSAGEM_NOME_PESSOA_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_nome_de_tamanho_diferente_do_especificado() {
		dependente.setNome("j");
		validator.validate(dependente);
		assertThat(getErros(dependente), hasItem(MENSAGEM_NOME_PESSOA_TAMANHO));
	}
	
	@Test
	public void nao_deve_aceitar_nome_nulo() {
		dependente.setNome(null);
		assertThat(getErros(dependente), hasItem(MENSAGEM_NOME_PESSOA_BLANK));
	}
	
	@Test
	public void nao_deve_aceitar_nome_em_branco() {
		dependente.setNome("");
		assertThat(getErros(dependente), hasItem(MENSAGEM_NOME_PESSOA_BLANK));
	}

	@Test
	public void deve_definir_um_novo_nome_para_a_pessoa() {
		String nome = "Maria";
		dependente.setNome(nome);
		Set<String> erros = getErros(dependente);
		assertThat(dependente.getNome(), equalTo(nome));
		assertThat(erros, not(hasItem(MENSAGEM_NOME_PESSOA_BLANK)));
		assertThat(erros, not(hasItem(MENSAGEM_NOME_PESSOA_TAMANHO)));
		assertThat(erros, not(hasItem(MENSAGEM_NOME_PESSOA_PATTERN)));
	}

	@Test
	public void deve_aceitar_nome_apenas_com_letras() {
		dependente.setNome("Vitória");
		assertThat(getErros(dependente).isEmpty(), is(true));
	}

	@Test
	public void deve_aceitar_nome_apenas_com_letras_e_espaco() {
		dependente.setNome("Vitória da Silva");
		assertThat(getErros(dependente).isEmpty(), is(true));
	}

	@Test
	public void deve_aceitar_nome_com_letras_espaco_e_ponto() {
		dependente.setNome("Vitória M. Silva");
		assertThat(getErros(dependente).isEmpty(), is(true));
	}

	@Test
	public void nao_deve_aceitar_nome_com_numeros() {
		dependente.setNome("José 2");
		assertThat(getErros(dependente), hasItem(MENSAGEM_NOME_PESSOA_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_nome_com_caracteres_especiais() {
		dependente.setNome("José@");
		assertThat(getErros(dependente), hasItem(MENSAGEM_NOME_PESSOA_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_nome_que_nao_inicia_com_letra() {
		dependente.setNome(".Mariana Silva");
		assertThat(getErros(dependente), hasItem(MENSAGEM_NOME_PESSOA_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_nome_de_tamanho_menor_que_2_caracteres() {
		dependente.setNome("S");
		assertThat(getErros(dependente), hasItem(MENSAGEM_NOME_PESSOA_PATTERN));
		assertThat(getErros(dependente), hasItem(MENSAGEM_NOME_PESSOA_TAMANHO));
	}

	@Test
	public void nao_deve_aceitar_nome_de_tamanho_maior_que_100_caracteres() {
		StringBuilder sb = new StringBuilder("J");
		for (int i = 0; i < 100; i++) {
			sb.append("o");
		}
		dependente.setNome(sb.toString());
		assertThat(getErros(dependente), hasItem(MENSAGEM_NOME_PESSOA_TAMANHO));
	}

	@Test
	public void nao_deve_aceitar_nome_com_menos_de_uma_letra_independentemente_da_quantidade_de_caracteres_da_string() {
		dependente.setNome("K    ");
		assertThat(getErros(dependente), hasItem(MENSAGEM_NOME_PESSOA_PATTERN));
	}

	/*
	 * CPF
	 */
	@Test
	public void deve_definir_um_novo_cpf_para_a_pessoa() {
		String cpf = "55269981009";
		dependente.setCpf(cpf);
		assertThat(dependente.getCpf(), equalTo(cpf));
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_CPF_PATTERN)));
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_CPF_BLANK)));		
	}

	@Test
	public void deve_aceitar_cpf_valido_sem_caracteres_especiais_estranhos_ao_padrao() {
		dependente.setCpf("54676325070");
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_CPF_PATTERN)));
	}
	
	@Test
	public void deve_aceitar_cpf_valido_com_mascara() {
		dependente.setCpf("546.763.250-70");
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_CPF_PATTERN)));
	}

	@Test
	public void nao_deve_aceitar_cpf_nulo() {
		dependente.setCpf(null);
		assertThat(getErros(dependente), hasItem(MENSAGEM_CPF_BLANK));
	}

	@Test
	public void nao_deve_aceitar_cpf_em_branco() {
		dependente.setCpf("");
		assertThat(getErros(dependente), hasItem(MENSAGEM_CPF_BLANK));
	}

	@Test
	public void nao_deve_aceitar_cpf_composto_por_menos_de_11_digitos() {
		dependente.setCpf("5467632507");
		assertThat(getErros(dependente), hasItem(MENSAGEM_CPF_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_cpf_composto_por_mais_de_11_digitos() {
		dependente.setCpf("546763250704");
		assertThat(getErros(dependente), hasItem(MENSAGEM_CPF_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_cpf_composto_por_letras() {
		dependente.setCpf("abcdefghijk");
		assertThat(getErros(dependente), hasItem(MENSAGEM_CPF_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_cpf_composto_por_caracteres_especiais() {
		dependente.setCpf("!@#$.%&*()!@#$");
		assertThat(getErros(dependente), hasItem(MENSAGEM_CPF_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_cpf_composto_por_digitos_iguais() {
		dependente.setCpf("99999999999");
		assertThat(getErros(dependente), hasItem(MENSAGEM_CPF_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_cpf_invalido() {
		dependente.setCpf("87548965809");
		assertThat(getErros(dependente), hasItem(MENSAGEM_CPF_PATTERN));
	}

	/*
	 * RG
	 */
	@Test
	public void nao_deve_aceitar_rg_com_menos_de_8_digitos() {
		dependente.setRg("4963592");
		assertThat(getErros(dependente), hasItem(MENSAGEM_RG_TAMANHO));
	}
	
	@Test
	public void nao_deve_aceitar_rg_com_mais_de_9_digitos() {
		dependente.setRg("4963592789");
		assertThat(getErros(dependente), hasItem(MENSAGEM_RG_TAMANHO));
	}

	@Test
	public void deve_definir_um_novo_rg_para_a_pessoa() {
		String rg = "789546879";
		dependente.setRg(rg);
		assertThat(rg, equalTo(dependente.getRg()));
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_RG_BLANK)));
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_RG_PATTERN)));
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_RG_TAMANHO)));
	}

	@Test
	public void deve_aceitar_rg_com_8_digitos() {// RG em MG
		dependente.setRg("26542809");
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_RG_BLANK)));
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_RG_PATTERN)));
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_RG_TAMANHO)));
	}

	@Test
	public void deve_aceitar_rg_com_9_digitos() {
		dependente.setRg("265428099");
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_RG_BLANK)));
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_RG_PATTERN)));
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_RG_TAMANHO)));
	}

	@Test
	public void nao_deve_aceitar_rg_nulo() {
		dependente.setRg(null);
		assertThat(getErros(dependente), hasItem(MENSAGEM_RG_BLANK));
	}

	@Test
	public void nao_deve_aceitar_rg_em_branco() {
		dependente.setRg("");
		assertThat(getErros(dependente), hasItem(MENSAGEM_RG_BLANK));
	}

	@Test
	public void nao_deve_aceitar_rg_com_letras() {
		dependente.setRg("abcdefghi");
		assertThat(getErros(dependente), hasItem(MENSAGEM_RG_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_rg_com_caracteres_especiais() {
		dependente.setRg("@#$%&*###");
		assertThat(getErros(dependente), hasItem(MENSAGEM_RG_PATTERN));
		
	}

	@Test
	public void nao_deve_aceitar_rg_que_contenha_qualquer_caractere_estranho_a_digitos() {
		dependente.setRg("26.542.809-9");
		assertThat(getErros(dependente), hasItem(MENSAGEM_RG_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_rg_composto_por_menos_de_8_caracteres() {
		dependente.setRg("2654280");
		assertThat(getErros(dependente), hasItem(MENSAGEM_RG_TAMANHO));
	}

	@Test
	public void nao_deve_aceitar_rg_composto_por_mais_de_9_caracteres() {
		dependente.setRg("2654280995");
		assertThat(getErros(dependente), hasItem(MENSAGEM_RG_TAMANHO));
	}

	/*
	 * DATA NASCIMENTO
	 */
	@Test
	public void deve_aceitar_data_de_nascimento_especificada() {
		LocalDate nascimento = new LocalDate("1985-01-25");
		dependente.setDataNascimento(nascimento);
		assertThat(dependente.getDataNascimento(), equalTo(nascimento));
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_DATA_NASCIMENTO_NULA)));
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_DATA_NASCIMENTO_FUTURE)));
	}

	@Test
	public void nao_deve_aceitar_data_de_nascimento_futura() {
		LocalDate localDate = new LocalDate(2021, 4, 1);
		dependente.setDataNascimento(localDate);
		assertThat(getErros(dependente), hasItem(MENSAGEM_DATA_NASCIMENTO_FUTURE));
	}
	
	@Test
	public void nao_deve_aceitar_data_de_nascimento_nula() {
		dependente.setDataNascimento(null);
		assertThat(getErros(dependente), hasItem(MENSAGEM_DATA_NASCIMENTO_NULA));
	}
	
	/*
	 * PROVEDOR
	 * */
	@Test
	public void deve_definir_um_provedor_para_o_dependente() {
		Funcionario provedor = Fixture.from(Funcionario.class).gimme("valido");
		dependente.setProvedor(provedor);
		assertThat(dependente.getProvedor(), equalTo(provedor));
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_PROVEDOR_NULL)));
	}
	
	@Test
	public void nao_deve_aceitar_provedor_nulo() {
		dependente.setProvedor(null);
		assertThat(getErros(dependente), hasItem(MENSAGEM_PROVEDOR_NULL));
	}
	
	/*
	 * PARENTESCO
	 * */
	@Test
	public void deve_definir_um_parentesco_para_o_dependente() {
		EnumTipoParentesco parentesco = FILHO;
		dependente.setParentesco(parentesco);
		assertThat(dependente.getParentesco(), equalTo(parentesco));
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_PARENTESCO_NULO)));
	}
	
	@Test
	public void nao_deve_aceitar_parentesco_nulo() {
		dependente.setParentesco(null);
		assertThat(getErros(dependente), hasItem(MENSAGEM_PARENTESCO_NULO));
	}
	
	/*
	 * IDADE
	 * */
	@Test
	public void deve_definir_uma_idade_para_o_dependente() {
		int idade = 8;
		dependente.setIdade(idade);
		assertThat(dependente.getIdade(), equalTo(idade));
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_IDADE_MIN)));
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_IDADE_MAX)));
	}
	
	@Test
	public void deve_aceitar_idade_positiva() {
		dependente.setIdade(17);
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_IDADE_MIN)));
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_IDADE_MAX)));
	}
	
	@Test
	public void deve_aceitar_idade_igual_a_0() {
		dependente.setIdade(0);
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_IDADE_MIN)));
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_IDADE_MAX)));
	}
	
	@Test
	public void nao_deve_aceitar_idade_negativa() {
		dependente.setIdade(-2);
		assertThat(getErros(dependente), hasItem(MENSAGEM_IDADE_MIN));
	}
	
	@Test
	public void nao_deve_aceitar_idade_maior_que_120() {
		dependente.setIdade(155);
		assertThat(getErros(dependente), hasItem(MENSAGEM_IDADE_MAX));
	}
}
