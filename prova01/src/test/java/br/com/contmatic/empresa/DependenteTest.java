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

// TODO: Auto-generated Javadoc
/**
 * The Class DependenteTest.
 */
public class DependenteTest {
	
	/** The dependente. */
	Dependente dependente;
	
	/** The factory. */
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

	/** The validator. */
	private Validator validator = factory.getValidator();

	/**
	 * Gets the erros.
	 *
	 * @param dependente the dependente
	 * @return the erros
	 */
	public Set<String> getErros(Dependente dependente) {
		Set<String> erros = new HashSet<>();
		for (ConstraintViolation<Dependente> constraintViolation : validator.validate(dependente)) {
			erros.add(constraintViolation.getMessageTemplate());
			System.out.println(constraintViolation.getMessageTemplate());
		}
		return erros;
	}
	
	/**
	 * Sets the up before class.
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("Iniciando testes da classe Dependente...");
		FixtureFactoryLoader.loadTemplates("br.com.contmatic.templates");
	}
	
	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		dependente = Fixture.from(Dependente.class).gimme("valido");
	}
	
	/**
	 * Tear down.
	 */
	@After
	public void tearDown() {
		dependente = null;
	}
	
	/**
	 * Tear down after class.
	 */
	@AfterClass
	public static void tearDownAfterClass() {
		System.out.println("Encerrando testes da classe Dependente.");
	}
	
	/**
	 * Deve validar objeto criado com fixture.
	 */
	@Test
	public void deve_validar_objeto_criado_com_fixture() {
		System.out.println(dependente);
		assertThat(getErros(dependente).size(), is(0));
	}
	
	/**
	 * Deve indicar que o metodo to string esta sobrescrito por nao conter o caractere arroba.
	 */
	@Test
	public void deve_indicar_que_o_metodo_toString_esta_sobrescrito_por_nao_conter_o_caractere_arroba() {
		assertThat(new Dependente().toString(), not(containsString("@")));
	}
	
	/**
	 * Deve apontar igualdade entre os objetos dependente.
	 */
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_dependente() {
		dependente = Fixture.from(Dependente.class).gimme("mock");
		Dependente dep = Fixture.from(Dependente.class).gimme("mock");
		assertThat(dependente, equalTo(dep));
	}
	
	/**
	 * Deve apontar igualdade entre os objetos usando equals sobrescrito porque ambos tem o mesmo cpf.
	 */
	//equals
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_ambos_tem_o_mesmo_cpf() {
		Dependente dep = Fixture.from(Dependente.class).gimme("valido");
		dep.setCpf(dependente.getCpf());
		assertThat(dependente, equalTo(dep));
	}
	
	/**
	 * Deve apontar igualdade entre os objetos usando equals sobrescrito porque sao o mesmo objeto.
	 */
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_sao_o_mesmo_objeto() {
		Dependente dep = dependente;
		assertThat(dependente, equalTo(dep));
	}
	
	/**
	 * Nao deve apontar igualdade entre os objetos usando equals sobrescrito porque cpf do objeto 2 e diferente.
	 */
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_cpf_do_objeto2_e_diferente() {
		Dependente dep = Fixture.from(Dependente.class).gimme("valido");
		dep.setCpf("81056888164");
		assertThat(dependente, not(equalTo(dep)));
	}
	
	/**
	 * Nao deve apontar igualdade entre os objetos usando equals sobrescrito porque os objetos sao de classes diferentes.
	 */
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_os_objetos_sao_de_classes_diferentes() {
		assertThat(dependente, not(equalTo(new Object())));
	}
	
	/**
	 * Nao deve apontar igualdade entre os objetos usando equals sobrescrito porque o objeto 2 e nulo.
	 */
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_o_objeto2_e_nulo() {
		assertThat(dependente, not(equalTo(null)));
	}
	
	/**
	 * Deve apontar igualdade entre os objetos dependente usando hashcode sobrescrito.
	 */
	//hashcode
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_dependente_usando_hashcode_sobrescrito() {
		dependente = Fixture.from(Dependente.class).gimme("mock");
		Dependente dep = Fixture.from(Dependente.class).gimme("mock");
		assertThat(dependente.hashCode(), equalTo(dep.hashCode()));
	}
	
	/**
	 * Nao deve apontar igualdade entre os objetos usando hashcode sobrescrito.
	 */
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_hashcode_sobrescrito() {
		Dependente dep = Fixture.from(Dependente.class).gimme("valido");
		dep.setCpf("22104854016");
		assertThat(dependente.hashCode(), not(equalTo(dep.hashCode())));
	}
	
	/**
	 * Nao deve aceitar nome fora do padrao da regex especificada.
	 */
	/*
	 * NOME
	 */
	@Test
	public void nao_deve_aceitar_nome_fora_do_padrao_da_regex_especificada() {
		dependente.setNome("maria");
		assertThat(getErros(dependente), hasItem(MENSAGEM_NOME_PESSOA_PATTERN));
	}

	/**
	 * Nao deve aceitar nome de tamanho diferente do especificado.
	 */
	@Test
	public void nao_deve_aceitar_nome_de_tamanho_diferente_do_especificado() {
		dependente.setNome("j");
		validator.validate(dependente);
		assertThat(getErros(dependente), hasItem(MENSAGEM_NOME_PESSOA_TAMANHO));
	}
	
	/**
	 * Nao deve aceitar nome nulo.
	 */
	@Test
	public void nao_deve_aceitar_nome_nulo() {
		dependente.setNome(null);
		assertThat(getErros(dependente), hasItem(MENSAGEM_NOME_PESSOA_BLANK));
	}
	
	/**
	 * Nao deve aceitar nome em branco.
	 */
	@Test
	public void nao_deve_aceitar_nome_em_branco() {
		dependente.setNome("");
		assertThat(getErros(dependente), hasItem(MENSAGEM_NOME_PESSOA_BLANK));
	}

	/**
	 * Deve definir um novo nome para o dependente.
	 */
	@Test
	public void deve_definir_um_novo_nome_para_o_dependente() {
		String nome = "Maria";
		dependente.setNome(nome);
		Set<String> erros = getErros(dependente);
		assertThat(dependente.getNome(), equalTo(nome));
		assertThat(erros, not(hasItem(MENSAGEM_NOME_PESSOA_BLANK)));
		assertThat(erros, not(hasItem(MENSAGEM_NOME_PESSOA_TAMANHO)));
		assertThat(erros, not(hasItem(MENSAGEM_NOME_PESSOA_PATTERN)));
	}

	/**
	 * Deve aceitar nome apenas com letras.
	 */
	@Test
	public void deve_aceitar_nome_apenas_com_letras() {
		dependente.setNome("Vitória");
		assertThat(getErros(dependente).isEmpty(), is(true));
	}

	/**
	 * Deve aceitar nome apenas com letras e espaco.
	 */
	@Test
	public void deve_aceitar_nome_apenas_com_letras_e_espaco() {
		dependente.setNome("Vitória da Silva");
		assertThat(getErros(dependente).isEmpty(), is(true));
	}

	/**
	 * Deve aceitar nome com letras espaco e ponto.
	 */
	@Test
	public void deve_aceitar_nome_com_letras_espaco_e_ponto() {
		dependente.setNome("Vitória M. Silva");
		assertThat(getErros(dependente).isEmpty(), is(true));
	}

	/**
	 * Nao deve aceitar nome com numeros.
	 */
	@Test
	public void nao_deve_aceitar_nome_com_numeros() {
		dependente.setNome("José 2");
		assertThat(getErros(dependente), hasItem(MENSAGEM_NOME_PESSOA_PATTERN));
	}

	/**
	 * Nao deve aceitar nome com caracteres especiais.
	 */
	@Test
	public void nao_deve_aceitar_nome_com_caracteres_especiais() {
		dependente.setNome("José@");
		assertThat(getErros(dependente), hasItem(MENSAGEM_NOME_PESSOA_PATTERN));
	}

	/**
	 * Nao deve aceitar nome que nao inicia com letra.
	 */
	@Test
	public void nao_deve_aceitar_nome_que_nao_inicia_com_letra() {
		dependente.setNome(".Mariana Silva");
		assertThat(getErros(dependente), hasItem(MENSAGEM_NOME_PESSOA_PATTERN));
	}

	/**
	 * Nao deve aceitar nome de tamanho menor que 2 caracteres.
	 */
	@Test
	public void nao_deve_aceitar_nome_de_tamanho_menor_que_2_caracteres() {
		dependente.setNome("S");
		assertThat(getErros(dependente), hasItem(MENSAGEM_NOME_PESSOA_PATTERN));
		assertThat(getErros(dependente), hasItem(MENSAGEM_NOME_PESSOA_TAMANHO));
	}

	/**
	 * Nao deve aceitar nome de tamanho maior que 100 caracteres.
	 */
	@Test
	public void nao_deve_aceitar_nome_de_tamanho_maior_que_100_caracteres() {
		StringBuilder sb = new StringBuilder("J");
		for (int i = 0; i < 100; i++) {
			sb.append("o");
		}
		dependente.setNome(sb.toString());
		assertThat(getErros(dependente), hasItem(MENSAGEM_NOME_PESSOA_TAMANHO));
	}

	/**
	 * Nao deve aceitar nome com menos de uma letra independentemente da quantidade de caracteres da string.
	 */
	@Test
	public void nao_deve_aceitar_nome_com_menos_de_uma_letra_independentemente_da_quantidade_de_caracteres_da_string() {
		dependente.setNome("K    ");
		assertThat(getErros(dependente), hasItem(MENSAGEM_NOME_PESSOA_PATTERN));
	}

	/**
	 * Deve definir um novo cpf para o dependente.
	 */
	/*
	 * CPF
	 */
	@Test
	public void deve_definir_um_novo_cpf_para_o_dependente() {
		String cpf = "55269981009";
		dependente.setCpf(cpf);
		assertThat(dependente.getCpf(), equalTo(cpf));
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_CPF_PATTERN)));
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_CPF_BLANK)));		
	}

	/**
	 * Deve aceitar cpf valido sem caracteres especiais estranhos ao padrao.
	 */
	@Test
	public void deve_aceitar_cpf_valido_sem_caracteres_especiais_estranhos_ao_padrao() {
		dependente.setCpf("54676325070");
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_CPF_PATTERN)));
	}
	
	/**
	 * Deve aceitar cpf valido com mascara.
	 */
	@Test
	public void deve_aceitar_cpf_valido_com_mascara() {
		dependente.setCpf("546.763.250-70");
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_CPF_PATTERN)));
	}

	/**
	 * Nao deve aceitar cpf nulo.
	 */
	@Test
	public void nao_deve_aceitar_cpf_nulo() {
		dependente.setCpf(null);
		assertThat(getErros(dependente), hasItem(MENSAGEM_CPF_BLANK));
	}

	/**
	 * Nao deve aceitar cpf em branco.
	 */
	@Test
	public void nao_deve_aceitar_cpf_em_branco() {
		dependente.setCpf("");
		assertThat(getErros(dependente), hasItem(MENSAGEM_CPF_BLANK));
	}

	/**
	 * Nao deve aceitar cpf composto por menos de 11 digitos.
	 */
	@Test
	public void nao_deve_aceitar_cpf_composto_por_menos_de_11_digitos() {
		dependente.setCpf("5467632507");
		assertThat(getErros(dependente), hasItem(MENSAGEM_CPF_PATTERN));
	}

	/**
	 * Nao deve aceitar cpf composto por mais de 11 digitos.
	 */
	@Test
	public void nao_deve_aceitar_cpf_composto_por_mais_de_11_digitos() {
		dependente.setCpf("546763250704");
		assertThat(getErros(dependente), hasItem(MENSAGEM_CPF_PATTERN));
	}

	/**
	 * Nao deve aceitar cpf composto por letras.
	 */
	@Test
	public void nao_deve_aceitar_cpf_composto_por_letras() {
		dependente.setCpf("abcdefghijk");
		assertThat(getErros(dependente), hasItem(MENSAGEM_CPF_PATTERN));
	}

	/**
	 * Nao deve aceitar cpf composto por caracteres especiais.
	 */
	@Test
	public void nao_deve_aceitar_cpf_composto_por_caracteres_especiais() {
		dependente.setCpf("!@#$.%&*()!@#$");
		assertThat(getErros(dependente), hasItem(MENSAGEM_CPF_PATTERN));
	}

	/**
	 * Nao deve aceitar cpf composto por digitos iguais.
	 */
	@Test
	public void nao_deve_aceitar_cpf_composto_por_digitos_iguais() {
		dependente.setCpf("99999999999");
		assertThat(getErros(dependente), hasItem(MENSAGEM_CPF_PATTERN));
	}

	/**
	 * Nao deve aceitar cpf invalido.
	 */
	@Test
	public void nao_deve_aceitar_cpf_invalido() {
		dependente.setCpf("87548965809");
		assertThat(getErros(dependente), hasItem(MENSAGEM_CPF_PATTERN));
	}

	/**
	 * Nao deve aceitar rg com menos de 8 digitos.
	 */
	/*
	 * RG
	 */
	@Test
	public void nao_deve_aceitar_rg_com_menos_de_8_digitos() {
		dependente.setRg("4963592");
		assertThat(getErros(dependente), hasItem(MENSAGEM_RG_TAMANHO));
	}
	
	/**
	 * Nao deve aceitar rg com mais de 9 digitos.
	 */
	@Test
	public void nao_deve_aceitar_rg_com_mais_de_9_digitos() {
		dependente.setRg("4963592789");
		assertThat(getErros(dependente), hasItem(MENSAGEM_RG_TAMANHO));
	}

	/**
	 * Deve definir um novo rg para o dependente.
	 */
	@Test
	public void deve_definir_um_novo_rg_para_o_dependente() {
		String rg = "789546879";
		dependente.setRg(rg);
		assertThat(rg, equalTo(dependente.getRg()));
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_RG_BLANK)));
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_RG_PATTERN)));
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_RG_TAMANHO)));
	}

	/**
	 * Deve aceitar rg com 8 digitos.
	 */
	@Test
	public void deve_aceitar_rg_com_8_digitos() {// RG em MG
		dependente.setRg("26542809");
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_RG_BLANK)));
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_RG_PATTERN)));
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_RG_TAMANHO)));
	}

	/**
	 * Deve aceitar rg com 9 digitos.
	 */
	@Test
	public void deve_aceitar_rg_com_9_digitos() {
		dependente.setRg("265428099");
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_RG_BLANK)));
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_RG_PATTERN)));
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_RG_TAMANHO)));
	}

	/**
	 * Nao deve aceitar rg nulo.
	 */
	@Test
	public void nao_deve_aceitar_rg_nulo() {
		dependente.setRg(null);
		assertThat(getErros(dependente), hasItem(MENSAGEM_RG_BLANK));
	}

	/**
	 * Nao deve aceitar rg em branco.
	 */
	@Test
	public void nao_deve_aceitar_rg_em_branco() {
		dependente.setRg("");
		assertThat(getErros(dependente), hasItem(MENSAGEM_RG_BLANK));
	}

	/**
	 * Nao deve aceitar rg com letras.
	 */
	@Test
	public void nao_deve_aceitar_rg_com_letras() {
		dependente.setRg("abcdefghi");
		assertThat(getErros(dependente), hasItem(MENSAGEM_RG_PATTERN));
	}

	/**
	 * Nao deve aceitar rg com caracteres especiais.
	 */
	@Test
	public void nao_deve_aceitar_rg_com_caracteres_especiais() {
		dependente.setRg("@#$%&*###");
		assertThat(getErros(dependente), hasItem(MENSAGEM_RG_PATTERN));
		
	}

	/**
	 * Nao deve aceitar rg que contenha qualquer caractere estranho a digitos.
	 */
	@Test
	public void nao_deve_aceitar_rg_que_contenha_qualquer_caractere_estranho_a_digitos() {
		dependente.setRg("26.542.809-9");
		assertThat(getErros(dependente), hasItem(MENSAGEM_RG_PATTERN));
	}

	/**
	 * Nao deve aceitar rg composto por menos de 8 caracteres.
	 */
	@Test
	public void nao_deve_aceitar_rg_composto_por_menos_de_8_caracteres() {
		dependente.setRg("2654280");
		assertThat(getErros(dependente), hasItem(MENSAGEM_RG_TAMANHO));
	}

	/**
	 * Nao deve aceitar rg composto por mais de 9 caracteres.
	 */
	@Test
	public void nao_deve_aceitar_rg_composto_por_mais_de_9_caracteres() {
		dependente.setRg("2654280995");
		assertThat(getErros(dependente), hasItem(MENSAGEM_RG_TAMANHO));
	}

	/**
	 * Deve aceitar data de nascimento especificada.
	 */
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

	/**
	 * Nao deve aceitar data de nascimento futura.
	 */
	@Test
	public void nao_deve_aceitar_data_de_nascimento_futura() {
		LocalDate localDate = new LocalDate(2021, 4, 1);
		dependente.setDataNascimento(localDate);
		assertThat(getErros(dependente), hasItem(MENSAGEM_DATA_NASCIMENTO_FUTURE));
	}
	
	/**
	 * Nao deve aceitar data de nascimento nula.
	 */
	@Test
	public void nao_deve_aceitar_data_de_nascimento_nula() {
		dependente.setDataNascimento(null);
		assertThat(getErros(dependente), hasItem(MENSAGEM_DATA_NASCIMENTO_NULA));
	}
	
	/**
	 * Deve definir um provedor funcionario para o dependente.
	 */
	/*
	 * PROVEDOR
	 * */
	@Test
	public void deve_definir_um_provedor_funcionario_para_o_dependente() {
		Funcionario provedor = Fixture.from(Funcionario.class).gimme("valido");
		dependente.setProvedor(provedor);
		assertThat(dependente.getProvedor(), equalTo((Provedor) provedor));
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_PROVEDOR_NULL)));
	}
	
	/**
	 * Deve definir um provedor socio para o dependente.
	 */
	@Test
	public void deve_definir_um_provedor_socio_para_o_dependente() {
		Socio provedor = Fixture.from(Socio.class).gimme("valido");
		dependente.setProvedor(provedor);
		assertThat(dependente.getProvedor(), equalTo((Provedor) provedor));
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_PROVEDOR_NULL)));
	}
	
	/**
	 * Nao deve aceitar provedor nulo.
	 */
	@Test
	public void nao_deve_aceitar_provedor_nulo() {
		dependente.setProvedor(null);
		assertThat(getErros(dependente), hasItem(MENSAGEM_PROVEDOR_NULL));
	}
	
	/**
	 * Deve definir um parentesco para o dependente.
	 */
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
	
	/**
	 * Nao deve aceitar parentesco nulo.
	 */
	@Test
	public void nao_deve_aceitar_parentesco_nulo() {
		dependente.setParentesco(null);
		assertThat(getErros(dependente), hasItem(MENSAGEM_PARENTESCO_NULO));
	}
	
	/**
	 * Deve definir uma idade para o dependente.
	 */
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
	
	/**
	 * Deve aceitar idade positiva.
	 */
	@Test
	public void deve_aceitar_idade_positiva() {
		dependente.setIdade(17);
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_IDADE_MIN)));
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_IDADE_MAX)));
	}
	
	/**
	 * Deve aceitar idade igual a 0.
	 */
	@Test
	public void deve_aceitar_idade_igual_a_0() {
		dependente.setIdade(0);
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_IDADE_MIN)));
		assertThat(getErros(dependente), not(hasItem(MENSAGEM_IDADE_MAX)));
	}
	
	/**
	 * Nao deve aceitar idade negativa.
	 */
	@Test
	public void nao_deve_aceitar_idade_negativa() {
		dependente.setIdade(-2);
		assertThat(getErros(dependente), hasItem(MENSAGEM_IDADE_MIN));
	}
	
	/**
	 * Nao deve aceitar idade maior que 120.
	 */
	@Test
	public void nao_deve_aceitar_idade_maior_que_120() {
		dependente.setIdade(155);
		assertThat(getErros(dependente), hasItem(MENSAGEM_IDADE_MAX));
	}
}
