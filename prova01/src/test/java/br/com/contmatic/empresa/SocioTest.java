package br.com.contmatic.empresa;

import static br.com.contmatic.constantes.Mensagens.MENSAGEM_CPF_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_CPF_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_DATA_NASCIMENTO_FUTURE;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_DATA_NASCIMENTO_NULA;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_EMAIL_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_EMAIL_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_EMAIL_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_INICIO_SOCIEDADE_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_INICIO_SOCIEDADE_PAST;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NOME_PESSOA_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NOME_PESSOA_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NOME_PESSOA_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_RG_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_RG_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_RG_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_SET_DEPENDENTES_VAZIO;
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

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

// TODO: Auto-generated Javadoc
/**
 * The Class SocioTest.
 */
public class SocioTest {

	/** The socio. */
	Socio socio;
	
	/** The factory. */
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

	/** The validator. */
	private Validator validator = factory.getValidator();
	
	/**
	 * Gets the erros.
	 *
	 * @param socio the socio
	 * @return the erros
	 */
	public Set<String> getErros(Socio socio) {
		Set<String> erros = new HashSet<>();
		for (ConstraintViolation<Socio> constraintViolation : validator.validate(socio)) {
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
		System.out.println("Iniciando testes da classe Socio...");
		FixtureFactoryLoader.loadTemplates("br.com.contmatic.templates");
	}
	
	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		socio = Fixture.from(Socio.class).gimme("valido");
	}
	
	/**
	 * Tear down.
	 */
	@After
	public void tearDown() {
		socio = null;
	}
	
	/**
	 * Tear down after class.
	 */
	@AfterClass
	public static void tearDownAfterClass() {
		System.out.println("Encerrando testes da classe Socio.");
	}
	
	/**
	 * Deve validar objeto criado com o fixture.
	 */
	@Test
	public void deve_validar_objeto_criado_com_o_fixture() {
		assertThat(getErros(socio).size(), is(0));
	}
	
	/**
	 * Deve indicar que o metodo to string esta sobrescrito por nao conter o caractere arroba.
	 */
	@Test
	public void deve_indicar_que_o_metodo_toString_esta_sobrescrito_por_nao_conter_o_caractere_arroba() {
		assertThat(new Socio().toString(), not(containsString("@")));
	}
	
	/**
	 * Deve apontar igualdade entre os objetos socio.
	 */
	//equals
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_socio() {
		socio = Fixture.from(Socio.class).gimme("mock");
		Socio s = Fixture.from(Socio.class).gimme("mock");
		assertThat(socio, equalTo(s));
	}
	
	/**
	 * Deve apontar igualdade entre os objetos usando equals sobrescrito porque ambos tem o mesmo cpf.
	 */
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_ambos_tem_o_mesmo_cpf() {
		socio = Fixture.from(Socio.class).gimme("valido");
		Socio s = Fixture.from(Socio.class).gimme("valido");
		s.setCpf(socio.getCpf());
		assertThat(socio, equalTo(s));
	}
	
	/**
	 * Deve apontar igualdade entre os objetos usando equals sobrescrito porque sao o mesmo objeto.
	 */
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_sao_o_mesmo_objeto() {
		Socio s = socio;
		assertThat(socio, equalTo(s));
	}
	
	/**
	 * Nao deve apontar igualdade entre os objetos usando equals sobrescrito porque cpf do objeto 2 e diferente.
	 */
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_cpf_do_objeto2_e_diferente() {
		Socio s = Fixture.from(Socio.class).gimme("valido");
		s.setCpf("73563445052");
		assertThat(socio, not(equalTo(s)));
	}
	
	/**
	 * Nao deve apontar igualdade entre os objetos usando equals sobrescrito porque os objetos sao de classes diferentes.
	 */
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_os_objetos_sao_de_classes_diferentes() {
		assertThat(socio, not(equalTo(new Object())));
	}
	
	/**
	 * Nao deve apontar igualdade entre os objetos usando equals sobrescrito porque o objeto 2 e nulo.
	 */
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_o_objeto2_e_nulo() {
		assertThat(socio, not(equalTo(null)));
	}
	
	/**
	 * Deve apontar igualdade entre os objetos socio usando hashcode sobrescrito.
	 */
	//hashcode
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_socio_usando_hashcode_sobrescrito() {
		socio = Fixture.from(Socio.class).gimme("mock");
		Socio s = Fixture.from(Socio.class).gimme("mock");
		assertThat(socio.hashCode(), equalTo(s.hashCode()));
	}
	
	/**
	 * Nao deve apontar igualdade entre os objetos usando hashcode sobrescrito.
	 */
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_hashcode_sobrescrito() {
		Socio s = Fixture.from(Socio.class).gimme("valido");
		s.setCpf("22104854016");
		assertThat(socio.hashCode(), not(equalTo(s.hashCode())));
	}
	
	/**
	 * Deve redefinir lista de dependentes.
	 */
	/*
	 * DEPENDENTES
	 */
	@Test
	public void deve_redefinir_lista_de_dependentes() {
		Set<Dependente> dependentes = new HashSet<Dependente>();
		Dependente dep = Fixture.from(Dependente.class).gimme("valido_for_socio");
		dependentes.add(dep);
		socio.setDependentes(dependentes);
		assertThat(socio.getDependentes(), equalTo(dependentes));
		assertThat(getErros(socio), not(hasItem(MENSAGEM_SET_DEPENDENTES_VAZIO)));
	}
	
	/**
	 * Nao deve redefinir lista de dependentes por uma lista nula.
	 */
	@Test
	public void nao_deve_redefinir_lista_de_dependentes_por_uma_lista_nula() {
		socio.setDependentes(null);
		assertThat(getErros(socio), hasItem(MENSAGEM_SET_DEPENDENTES_VAZIO));
	}
	
	/**
	 * Nao deve redefinir a lista de dependentes por uma lista que esteja vazia.
	 */
	@Test
	public void nao_deve_redefinir_a_lista_de_dependentes_por_uma_lista_que_esteja_vazia() {
		Set<Dependente> dependentes = new HashSet<Dependente>();
		socio.setDependentes(dependentes);
		assertThat(getErros(socio), hasItem(MENSAGEM_SET_DEPENDENTES_VAZIO));
	}
	
	/**
	 * Nao deve aceitar nome fora do padrao da regex especificada.
	 */
	/*
	 * NOME
	 */
	@Test
	public void nao_deve_aceitar_nome_fora_do_padrao_da_regex_especificada() {
		socio.setNome("maria");
		assertThat(getErros(socio), hasItem(MENSAGEM_NOME_PESSOA_PATTERN));
	}

	/**
	 * Nao deve aceitar nome de tamanho diferente do especificado.
	 */
	@Test
	public void nao_deve_aceitar_nome_de_tamanho_diferente_do_especificado() {
		socio.setNome("j");
		validator.validate(socio);
		assertThat(getErros(socio), hasItem(MENSAGEM_NOME_PESSOA_TAMANHO));
	}
	
	/**
	 * Nao deve aceitar nome nulo.
	 */
	@Test
	public void nao_deve_aceitar_nome_nulo() {
		socio.setNome(null);
		assertThat(getErros(socio), hasItem(MENSAGEM_NOME_PESSOA_BLANK));
	}
	
	/**
	 * Nao deve aceitar nome em branco.
	 */
	@Test
	public void nao_deve_aceitar_nome_em_branco() {
		socio.setNome("");
		assertThat(getErros(socio), hasItem(MENSAGEM_NOME_PESSOA_BLANK));
	}

	/**
	 * Deve definir um novo nome para o socio.
	 */
	@Test
	public void deve_definir_um_novo_nome_para_o_socio() {
		String nome = "Maria";
		socio.setNome(nome);
		Set<String> erros = getErros(socio);
		assertThat(socio.getNome(), equalTo(nome));
		assertThat(erros, not(hasItem(MENSAGEM_NOME_PESSOA_BLANK)));
		assertThat(erros, not(hasItem(MENSAGEM_NOME_PESSOA_TAMANHO)));
		assertThat(erros, not(hasItem(MENSAGEM_NOME_PESSOA_PATTERN)));
	}

	/**
	 * Deve aceitar nome apenas com letras.
	 */
	@Test
	public void deve_aceitar_nome_apenas_com_letras() {
		socio.setNome("Vitória");
		assertThat(getErros(socio).isEmpty(), is(true));
	}

	/**
	 * Deve aceitar nome apenas com letras e espaco.
	 */
	@Test
	public void deve_aceitar_nome_apenas_com_letras_e_espaco() {
		socio.setNome("Vitória da Silva");
		assertThat(getErros(socio).isEmpty(), is(true));
	}

	/**
	 * Deve aceitar nome com letras espaco e ponto.
	 */
	@Test
	public void deve_aceitar_nome_com_letras_espaco_e_ponto() {
		socio.setNome("Vitória M. Silva");
		assertThat(getErros(socio).isEmpty(), is(true));
	}

	/**
	 * Nao deve aceitar nome com numeros.
	 */
	@Test
	public void nao_deve_aceitar_nome_com_numeros() {
		socio.setNome("José 2");
		assertThat(getErros(socio), hasItem(MENSAGEM_NOME_PESSOA_PATTERN));
	}

	/**
	 * Nao deve aceitar nome com caracteres especiais.
	 */
	@Test
	public void nao_deve_aceitar_nome_com_caracteres_especiais() {
		socio.setNome("José@");
		assertThat(getErros(socio), hasItem(MENSAGEM_NOME_PESSOA_PATTERN));
	}

	/**
	 * Nao deve aceitar nome que nao inicia com letra.
	 */
	@Test
	public void nao_deve_aceitar_nome_que_nao_inicia_com_letra() {
		socio.setNome(".Mariana Silva");
		assertThat(getErros(socio), hasItem(MENSAGEM_NOME_PESSOA_PATTERN));
	}

	/**
	 * Nao deve aceitar nome de tamanho menor que 2 caracteres.
	 */
	@Test
	public void nao_deve_aceitar_nome_de_tamanho_menor_que_2_caracteres() {
		socio.setNome("S");
		assertThat(getErros(socio), hasItem(MENSAGEM_NOME_PESSOA_PATTERN));
		assertThat(getErros(socio), hasItem(MENSAGEM_NOME_PESSOA_TAMANHO));
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
		socio.setNome(sb.toString());
		assertThat(getErros(socio), hasItem(MENSAGEM_NOME_PESSOA_TAMANHO));
	}

	/**
	 * Nao deve aceitar nome com menos de uma letra independentemente da quantidade de caracteres da string.
	 */
	@Test
	public void nao_deve_aceitar_nome_com_menos_de_uma_letra_independentemente_da_quantidade_de_caracteres_da_string() {
		socio.setNome("K    ");
		assertThat(getErros(socio), hasItem(MENSAGEM_NOME_PESSOA_PATTERN));
	}

	/**
	 * Deve definir um novo cpf para o socio.
	 */
	/*
	 * CPF
	 */
	@Test
	public void deve_definir_um_novo_cpf_para_o_socio() {
		String cpf = "55269981009";
		socio.setCpf(cpf);
		assertThat(socio.getCpf(), equalTo(cpf));
		assertThat(getErros(socio), not(hasItem(MENSAGEM_CPF_PATTERN)));
		assertThat(getErros(socio), not(hasItem(MENSAGEM_CPF_BLANK)));		
	}

	/**
	 * Deve aceitar cpf valido sem caracteres especiais estranhos ao padrao.
	 */
	@Test
	public void deve_aceitar_cpf_valido_sem_caracteres_especiais_estranhos_ao_padrao() {
		socio.setCpf("54676325070");
		assertThat(getErros(socio), not(hasItem(MENSAGEM_CPF_PATTERN)));
	}
	
	/**
	 * Deve aceitar cpf valido com mascara.
	 */
	@Test
	public void deve_aceitar_cpf_valido_com_mascara() {
		socio.setCpf("546.763.250-70");
		assertThat(getErros(socio), not(hasItem(MENSAGEM_CPF_PATTERN)));
	}

	/**
	 * Nao deve aceitar cpf nulo.
	 */
	@Test
	public void nao_deve_aceitar_cpf_nulo() {
		socio.setCpf(null);
		assertThat(getErros(socio), hasItem(MENSAGEM_CPF_BLANK));
	}

	/**
	 * Nao deve aceitar cpf em branco.
	 */
	@Test
	public void nao_deve_aceitar_cpf_em_branco() {
		socio.setCpf("");
		assertThat(getErros(socio), hasItem(MENSAGEM_CPF_BLANK));
	}

	/**
	 * Nao deve aceitar cpf composto por menos de 11 digitos.
	 */
	@Test
	public void nao_deve_aceitar_cpf_composto_por_menos_de_11_digitos() {
		socio.setCpf("5467632507");
		assertThat(getErros(socio), hasItem(MENSAGEM_CPF_PATTERN));
	}

	/**
	 * Nao deve aceitar cpf composto por mais de 11 digitos.
	 */
	@Test
	public void nao_deve_aceitar_cpf_composto_por_mais_de_11_digitos() {
		socio.setCpf("546763250704");
		assertThat(getErros(socio), hasItem(MENSAGEM_CPF_PATTERN));
	}

	/**
	 * Nao deve aceitar cpf composto por letras.
	 */
	@Test
	public void nao_deve_aceitar_cpf_composto_por_letras() {
		socio.setCpf("abcdefghijk");
		assertThat(getErros(socio), hasItem(MENSAGEM_CPF_PATTERN));
	}

	/**
	 * Nao deve aceitar cpf composto por caracteres especiais.
	 */
	@Test
	public void nao_deve_aceitar_cpf_composto_por_caracteres_especiais() {
		socio.setCpf("!@#$.%&*()!@#$");
		assertThat(getErros(socio), hasItem(MENSAGEM_CPF_PATTERN));
	}

	/**
	 * Nao deve aceitar cpf composto por digitos iguais.
	 */
	@Test
	public void nao_deve_aceitar_cpf_composto_por_digitos_iguais() {
		socio.setCpf("99999999999");
		assertThat(getErros(socio), hasItem(MENSAGEM_CPF_PATTERN));
	}

	/**
	 * Nao deve aceitar cpf invalido.
	 */
	@Test
	public void nao_deve_aceitar_cpf_invalido() {
		socio.setCpf("87548965809");
		assertThat(getErros(socio), hasItem(MENSAGEM_CPF_PATTERN));
	}

	/**
	 * Nao deve aceitar rg com menos de 8 digitos.
	 */
	/*
	 * RG
	 */
	@Test
	public void nao_deve_aceitar_rg_com_menos_de_8_digitos() {
		socio.setRg("4963592");
		assertThat(getErros(socio), hasItem(MENSAGEM_RG_TAMANHO));
	}
	
	/**
	 * Nao deve aceitar rg com mais de 9 digitos.
	 */
	@Test
	public void nao_deve_aceitar_rg_com_mais_de_9_digitos() {
		socio.setRg("4963592789");
		assertThat(getErros(socio), hasItem(MENSAGEM_RG_TAMANHO));
	}

	/**
	 * Deve definir um novo rg para o socio.
	 */
	@Test
	public void deve_definir_um_novo_rg_para_o_socio() {
		String rg = "789546879";
		socio.setRg(rg);
		assertThat(rg, equalTo(socio.getRg()));
		assertThat(getErros(socio), not(hasItem(MENSAGEM_RG_BLANK)));
		assertThat(getErros(socio), not(hasItem(MENSAGEM_RG_PATTERN)));
		assertThat(getErros(socio), not(hasItem(MENSAGEM_RG_TAMANHO)));
	}

	/**
	 * Deve aceitar rg com 8 digitos.
	 */
	@Test
	public void deve_aceitar_rg_com_8_digitos() {// RG em MG
		socio.setRg("26542809");
		assertThat(getErros(socio), not(hasItem(MENSAGEM_RG_BLANK)));
		assertThat(getErros(socio), not(hasItem(MENSAGEM_RG_PATTERN)));
		assertThat(getErros(socio), not(hasItem(MENSAGEM_RG_TAMANHO)));
	}

	/**
	 * Deve aceitar rg com 9 digitos.
	 */
	@Test
	public void deve_aceitar_rg_com_9_digitos() {
		socio.setRg("265428099");
		assertThat(getErros(socio), not(hasItem(MENSAGEM_RG_BLANK)));
		assertThat(getErros(socio), not(hasItem(MENSAGEM_RG_PATTERN)));
		assertThat(getErros(socio), not(hasItem(MENSAGEM_RG_TAMANHO)));
	}

	/**
	 * Nao deve aceitar rg nulo.
	 */
	@Test
	public void nao_deve_aceitar_rg_nulo() {
		socio.setRg(null);
		assertThat(getErros(socio), hasItem(MENSAGEM_RG_BLANK));
	}

	/**
	 * Nao deve aceitar rg em branco.
	 */
	@Test
	public void nao_deve_aceitar_rg_em_branco() {
		socio.setRg("");
		assertThat(getErros(socio), hasItem(MENSAGEM_RG_BLANK));
	}

	/**
	 * Nao deve aceitar rg com letras.
	 */
	@Test
	public void nao_deve_aceitar_rg_com_letras() {
		socio.setRg("abcdefghi");
		assertThat(getErros(socio), hasItem(MENSAGEM_RG_PATTERN));
	}

	/**
	 * Nao deve aceitar rg com caracteres especiais.
	 */
	@Test
	public void nao_deve_aceitar_rg_com_caracteres_especiais() {
		socio.setRg("@#$%&*###");
		assertThat(getErros(socio), hasItem(MENSAGEM_RG_PATTERN));
		
	}

	/**
	 * Nao deve aceitar rg que contenha qualquer caractere estranho a digitos.
	 */
	@Test
	public void nao_deve_aceitar_rg_que_contenha_qualquer_caractere_estranho_a_digitos() {
		socio.setRg("26.542.809-9");
		assertThat(getErros(socio), hasItem(MENSAGEM_RG_PATTERN));
	}

	/**
	 * Nao deve aceitar rg composto por menos de 8 caracteres.
	 */
	@Test
	public void nao_deve_aceitar_rg_composto_por_menos_de_8_caracteres() {
		socio.setRg("2654280");
		assertThat(getErros(socio), hasItem(MENSAGEM_RG_TAMANHO));
	}

	/**
	 * Nao deve aceitar rg composto por mais de 9 caracteres.
	 */
	@Test
	public void nao_deve_aceitar_rg_composto_por_mais_de_9_caracteres() {
		socio.setRg("2654280995");
		assertThat(getErros(socio), hasItem(MENSAGEM_RG_TAMANHO));
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
		socio.setDataNascimento(nascimento);
		assertThat(socio.getDataNascimento(), equalTo(nascimento));
		assertThat(getErros(socio), not(hasItem(MENSAGEM_DATA_NASCIMENTO_NULA)));
		assertThat(getErros(socio), not(hasItem(MENSAGEM_DATA_NASCIMENTO_FUTURE)));
	}

	/**
	 * Nao deve aceitar data de nascimento futura.
	 */
	@Test
	public void nao_deve_aceitar_data_de_nascimento_futura() {
		LocalDate localDate = new LocalDate(2021, 4, 1);
		socio.setDataNascimento(localDate);
		assertThat(getErros(socio), hasItem(MENSAGEM_DATA_NASCIMENTO_FUTURE));
	}
	
	/**
	 * Nao deve aceitar data de nascimento nula.
	 */
	@Test
	public void nao_deve_aceitar_data_de_nascimento_nula() {
		socio.setDataNascimento(null);
		assertThat(getErros(socio), hasItem(MENSAGEM_DATA_NASCIMENTO_NULA));
	}
	
	/**
	 * Deve redefinir email valido.
	 */
	/*
	 * E-MAIL
	 */
	@Test
	public void deve_redefinir_email_valido() {
		String email = "java@oracle.com";
		socio.setEmail(email);
		assertThat(socio.getEmail(), equalTo(email));
		assertThat(getErros(socio), not(hasItem(MENSAGEM_EMAIL_BLANK)));
		assertThat(getErros(socio), not(hasItem(MENSAGEM_EMAIL_PATTERN)));
		assertThat(getErros(socio), not(hasItem(MENSAGEM_EMAIL_TAMANHO)));
	}
	
	/**
	 * Deve aceitar email valido.
	 */
	@Test
	public void deve_aceitar_email_valido() {
		socio.setEmail("abc@gmail.com");
		assertThat(getErros(socio), not(hasItem(MENSAGEM_EMAIL_BLANK)));
		assertThat(getErros(socio), not(hasItem(MENSAGEM_EMAIL_PATTERN)));
		assertThat(getErros(socio), not(hasItem(MENSAGEM_EMAIL_TAMANHO)));
	}
	
	/**
	 * Nao deve aceitar email invalido.
	 */
	@Test
	public void nao_deve_aceitar_email_invalido() {
		String email = "testmail@@gmail.com";
		socio.setEmail(email);
		assertThat(getErros(socio), hasItem(MENSAGEM_EMAIL_PATTERN));
	}
	
	/**
	 * Nao deve aceitar email nulo.
	 */
	@Test
	public void nao_deve_aceitar_email_nulo() {
		socio.setEmail(null);
		assertThat(getErros(socio), hasItem(MENSAGEM_EMAIL_BLANK));
	}

	/**
	 * Nao deve aceitar email com menos de 6 caracteres.
	 */
	@Test
	public void nao_deve_aceitar_email_com_menos_de_6_caracteres() {
		socio.setEmail("a@a");
		assertThat(getErros(socio), hasItem(MENSAGEM_EMAIL_TAMANHO));
	}
	
	/**
	 * Nao deve aceitar email com mais de 100 caracteres.
	 */
	@Test
	public void nao_deve_aceitar_email_com_mais_de_100_caracteres() {
		StringBuilder sb = new StringBuilder("J");
		for (int i = 0; i < 100; i++) {
			sb.append("o");
		}
		socio.setEmail(sb.toString());
		assertThat(getErros(socio), hasItem(MENSAGEM_EMAIL_TAMANHO));
	}
	
	/**
	 * Deve aceitar data de admissao especificada.
	 */
	/*
	 * DATA DE INÍCIO DA SOCIEDADE
	 */
	@Test
	public void deve_aceitar_data_de_admissao_especificada() {
		LocalDate admissao = new LocalDate(2015, 01, 25);
		socio.setInicioSociedade(admissao);
		assertThat(socio.getInicioSociedade(), equalTo(admissao));
		assertThat(getErros(socio), not(hasItem(MENSAGEM_INICIO_SOCIEDADE_BLANK)));
		assertThat(getErros(socio), not(hasItem(MENSAGEM_INICIO_SOCIEDADE_PAST)));
	}

	/**
	 * Nao deve aceitar data de admissao futura.
	 */
	@Test
	public void nao_deve_aceitar_data_de_admissao_futura() {
		LocalDate terminoContrato = new LocalDate(2040, 4, 1);
		socio.setInicioSociedade(terminoContrato);
		assertThat(getErros(socio), hasItem(MENSAGEM_INICIO_SOCIEDADE_PAST));
	}
	
	/**
	 * Nao deve aceitar data de admissao nula.
	 */
	@Test
	public void nao_deve_aceitar_data_de_admissao_nula() {
		socio.setInicioSociedade(null);
		assertThat(getErros(socio), hasItem(MENSAGEM_INICIO_SOCIEDADE_BLANK));
	}
	
}
