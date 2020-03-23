package br.com.contmatic.empresa;

import static br.com.contmatic.constantes.Mensagens.MENSAGEM_CPF_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_CPF_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_DATA_ADMISSAO_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_DATA_ADMISSAO_PAST;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_DATA_NASCIMENTO_FUTURE;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_DATA_NASCIMENTO_NULA;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_EMAIL_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_EMAIL_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_EMAIL_TAMANHO;
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

public class FuncionarioTest {
	
	Funcionario f;
	
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

	private Validator validator = factory.getValidator();
	
	public Set<String> getErros(Funcionario funcionario) {
		Set<String> erros = new HashSet<>();
		for (ConstraintViolation<Funcionario> constraintViolation : validator.validate(funcionario)) {
			erros.add(constraintViolation.getMessageTemplate());
			System.out.println(constraintViolation.getMessageTemplate());
		}
		return erros;
	}
	
	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("Iniciando testes da classe Funcionario...");
		FixtureFactoryLoader.loadTemplates("br.com.contmatic.templates");
	}
	
	@Before
	public void setUp() {
		f = Fixture.from(Funcionario.class).gimme("valido");
	}
	
	@After
	public void tearDown() {
		f = null;
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		System.out.println("Encerrando testes da classe Funcionario.");
	}
	
	@Test
	public void deve_validar_objeto_criado_com_o_fixture() {
		assertThat(getErros(f).size(), is(0));
	}
	
	@Test
	public void deve_indicar_que_o_metodo_toString_esta_sobrescrito_por_nao_conter_o_caractere_arroba() {
		assertThat(new Funcionario().toString(), not(containsString("@")));
	}
	
	//equals
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_funcionario() {
		f = Fixture.from(Funcionario.class).gimme("mock");
		Funcionario fun = Fixture.from(Funcionario.class).gimme("mock");
		assertThat(f, equalTo(fun));
	}
	
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_ambos_tem_o_mesmo_cpf() {
		f = Fixture.from(Funcionario.class).gimme("valido");
		Funcionario fun = Fixture.from(Funcionario.class).gimme("valido");
		fun.setCpf(f.getCpf());
		assertThat(f, equalTo(fun));
	}
	
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_sao_o_mesmo_objeto() {
		Funcionario fun = f;
		assertThat(f, equalTo(fun));
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_cpf_do_objeto2_e_diferente() {
		Funcionario fun = Fixture.from(Funcionario.class).gimme("valido");
		fun.setCpf("73563445052");
		assertThat(f, not(equalTo(fun)));
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_os_objetos_sao_de_classes_diferentes() {
		assertThat(f, not(equalTo(new Object())));
	}
	
	//hashcode
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_funcionario_usando_hashcode_sobrescrito() {
		f = Fixture.from(Funcionario.class).gimme("mock");
		Funcionario fun = Fixture.from(Funcionario.class).gimme("mock");
		assertThat(f.hashCode(), equalTo(fun.hashCode()));
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_hashcode_sobrescrito() {
		Funcionario fun = Fixture.from(Funcionario.class).gimme("valido");
		fun.setCpf("22104854016");
		assertThat(f.hashCode(), not(equalTo(fun.hashCode())));
	}
	
	@Test
	public void deve_redefinir_lista_de_dependentes() {
		Set<Dependente> dependentes = new HashSet<Dependente>();
		Dependente dep = Fixture.from(Dependente.class).gimme("valido");
		dependentes.add(dep);
		f.setDependentes(dependentes);
		assertThat(f.getDependentes(), equalTo(dependentes));
		assertThat(getErros(f), not(hasItem(MENSAGEM_SET_DEPENDENTES_VAZIO)));
	}
	
	@Test
	public void nao_deve_redefinir_lista_de_dependentes_por_uma_lista_nula() {
		f.setDependentes(null);
		assertThat(getErros(f), hasItem(MENSAGEM_SET_DEPENDENTES_VAZIO));
	}
	
	@Test
	public void nao_deve_redefinir_a_lista_de_dependentes_por_uma_lista_que_esteja_vazia() {
		Set<Dependente> dependentes = new HashSet<Dependente>();
		f.setDependentes(dependentes);
		assertThat(getErros(f), hasItem(MENSAGEM_SET_DEPENDENTES_VAZIO));
	}
	
	/*
	 * NOME
	 */
	@Test
	public void nao_deve_aceitar_nome_fora_do_padrao_da_regex_especificada() {
		f.setNome("maria");
		assertThat(getErros(f), hasItem(MENSAGEM_NOME_PESSOA_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_nome_de_tamanho_diferente_do_especificado() {
		f.setNome("j");
		validator.validate(f);
		assertThat(getErros(f), hasItem(MENSAGEM_NOME_PESSOA_TAMANHO));
	}
	
	@Test
	public void nao_deve_aceitar_nome_nulo() {
		f.setNome(null);
		assertThat(getErros(f), hasItem(MENSAGEM_NOME_PESSOA_BLANK));
	}
	
	@Test
	public void nao_deve_aceitar_nome_em_branco() {
		f.setNome("");
		assertThat(getErros(f), hasItem(MENSAGEM_NOME_PESSOA_BLANK));
	}

	@Test
	public void deve_definir_um_novo_nome_para_a_pessoa() {
		String nome = "Maria";
		f.setNome(nome);
		Set<String> erros = getErros(f);
		assertThat(f.getNome(), equalTo(nome));
		assertThat(erros, not(hasItem(MENSAGEM_NOME_PESSOA_BLANK)));
		assertThat(erros, not(hasItem(MENSAGEM_NOME_PESSOA_TAMANHO)));
		assertThat(erros, not(hasItem(MENSAGEM_NOME_PESSOA_PATTERN)));
	}

	@Test
	public void deve_aceitar_nome_apenas_com_letras() {
		f.setNome("Vitória");
		assertThat(getErros(f).isEmpty(), is(true));
	}

	@Test
	public void deve_aceitar_nome_apenas_com_letras_e_espaco() {
		f.setNome("Vitória da Silva");
		assertThat(getErros(f).isEmpty(), is(true));
	}

	@Test
	public void deve_aceitar_nome_com_letras_espaco_e_ponto() {
		f.setNome("Vitória M. Silva");
		assertThat(getErros(f).isEmpty(), is(true));
	}

	@Test
	public void nao_deve_aceitar_nome_com_numeros() {
		f.setNome("José 2");
		assertThat(getErros(f), hasItem(MENSAGEM_NOME_PESSOA_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_nome_com_caracteres_especiais() {
		f.setNome("José@");
		assertThat(getErros(f), hasItem(MENSAGEM_NOME_PESSOA_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_nome_que_nao_inicia_com_letra() {
		f.setNome(".Mariana Silva");
		assertThat(getErros(f), hasItem(MENSAGEM_NOME_PESSOA_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_nome_de_tamanho_menor_que_2_caracteres() {
		f.setNome("S");
		assertThat(getErros(f), hasItem(MENSAGEM_NOME_PESSOA_PATTERN));
		assertThat(getErros(f), hasItem(MENSAGEM_NOME_PESSOA_TAMANHO));
	}

	@Test
	public void nao_deve_aceitar_nome_de_tamanho_maior_que_100_caracteres() {
		StringBuilder sb = new StringBuilder("J");
		for (int i = 0; i < 100; i++) {
			sb.append("o");
		}
		f.setNome(sb.toString());
		assertThat(getErros(f), hasItem(MENSAGEM_NOME_PESSOA_TAMANHO));
	}

	@Test
	public void nao_deve_aceitar_nome_com_menos_de_uma_letra_independentemente_da_quantidade_de_caracteres_da_string() {
		f.setNome("K    ");
		assertThat(getErros(f), hasItem(MENSAGEM_NOME_PESSOA_PATTERN));
	}

	/*
	 * CPF
	 */
	@Test
	public void deve_definir_um_novo_cpf_para_a_pessoa() {
		String cpf = "55269981009";
		f.setCpf(cpf);
		assertThat(f.getCpf(), equalTo(cpf));
		assertThat(getErros(f), not(hasItem(MENSAGEM_CPF_PATTERN)));
		assertThat(getErros(f), not(hasItem(MENSAGEM_CPF_BLANK)));		
	}

	@Test
	public void deve_aceitar_cpf_valido_sem_caracteres_especiais_estranhos_ao_padrao() {
		f.setCpf("54676325070");
		assertThat(getErros(f), not(hasItem(MENSAGEM_CPF_PATTERN)));
	}
	
	@Test
	public void deve_aceitar_cpf_valido_com_mascara() {
		f.setCpf("546.763.250-70");
		assertThat(getErros(f), not(hasItem(MENSAGEM_CPF_PATTERN)));
	}

	@Test
	public void nao_deve_aceitar_cpf_nulo() {
		f.setCpf(null);
		assertThat(getErros(f), hasItem(MENSAGEM_CPF_BLANK));
	}

	@Test
	public void nao_deve_aceitar_cpf_em_branco() {
		f.setCpf("");
		assertThat(getErros(f), hasItem(MENSAGEM_CPF_BLANK));
	}

	@Test
	public void nao_deve_aceitar_cpf_composto_por_menos_de_11_digitos() {
		f.setCpf("5467632507");
		assertThat(getErros(f), hasItem(MENSAGEM_CPF_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_cpf_composto_por_mais_de_11_digitos() {
		f.setCpf("546763250704");
		assertThat(getErros(f), hasItem(MENSAGEM_CPF_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_cpf_composto_por_letras() {
		f.setCpf("abcdefghijk");
		assertThat(getErros(f), hasItem(MENSAGEM_CPF_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_cpf_composto_por_caracteres_especiais() {
		f.setCpf("!@#$.%&*()!@#$");
		assertThat(getErros(f), hasItem(MENSAGEM_CPF_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_cpf_composto_por_digitos_iguais() {
		f.setCpf("99999999999");
		assertThat(getErros(f), hasItem(MENSAGEM_CPF_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_cpf_invalido() {
		f.setCpf("87548965809");
		assertThat(getErros(f), hasItem(MENSAGEM_CPF_PATTERN));
	}

	/*
	 * RG
	 */
	@Test
	public void nao_deve_aceitar_rg_com_menos_de_8_digitos() {
		f.setRg("4963592");
		assertThat(getErros(f), hasItem(MENSAGEM_RG_TAMANHO));
	}
	
	@Test
	public void nao_deve_aceitar_rg_com_mais_de_9_digitos() {
		f.setRg("4963592789");
		assertThat(getErros(f), hasItem(MENSAGEM_RG_TAMANHO));
	}

	@Test
	public void deve_definir_um_novo_rg_para_a_pessoa() {
		String rg = "789546879";
		f.setRg(rg);
		assertThat(rg, equalTo(f.getRg()));
		assertThat(getErros(f), not(hasItem(MENSAGEM_RG_BLANK)));
		assertThat(getErros(f), not(hasItem(MENSAGEM_RG_PATTERN)));
		assertThat(getErros(f), not(hasItem(MENSAGEM_RG_TAMANHO)));
	}

	@Test
	public void deve_aceitar_rg_com_8_digitos() {// RG em MG
		f.setRg("26542809");
		assertThat(getErros(f), not(hasItem(MENSAGEM_RG_BLANK)));
		assertThat(getErros(f), not(hasItem(MENSAGEM_RG_PATTERN)));
		assertThat(getErros(f), not(hasItem(MENSAGEM_RG_TAMANHO)));
	}

	@Test
	public void deve_aceitar_rg_com_9_digitos() {
		f.setRg("265428099");
		assertThat(getErros(f), not(hasItem(MENSAGEM_RG_BLANK)));
		assertThat(getErros(f), not(hasItem(MENSAGEM_RG_PATTERN)));
		assertThat(getErros(f), not(hasItem(MENSAGEM_RG_TAMANHO)));
	}

	@Test
	public void nao_deve_aceitar_rg_nulo() {
		f.setRg(null);
		assertThat(getErros(f), hasItem(MENSAGEM_RG_BLANK));
	}

	@Test
	public void nao_deve_aceitar_rg_em_branco() {
		f.setRg("");
		assertThat(getErros(f), hasItem(MENSAGEM_RG_BLANK));
	}

	@Test
	public void nao_deve_aceitar_rg_com_letras() {
		f.setRg("abcdefghi");
		assertThat(getErros(f), hasItem(MENSAGEM_RG_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_rg_com_caracteres_especiais() {
		f.setRg("@#$%&*###");
		assertThat(getErros(f), hasItem(MENSAGEM_RG_PATTERN));
		
	}

	@Test
	public void nao_deve_aceitar_rg_que_contenha_qualquer_caractere_estranho_a_digitos() {
		f.setRg("26.542.809-9");
		assertThat(getErros(f), hasItem(MENSAGEM_RG_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_rg_composto_por_menos_de_8_caracteres() {
		f.setRg("2654280");
		assertThat(getErros(f), hasItem(MENSAGEM_RG_TAMANHO));
	}

	@Test
	public void nao_deve_aceitar_rg_composto_por_mais_de_9_caracteres() {
		f.setRg("2654280995");
		assertThat(getErros(f), hasItem(MENSAGEM_RG_TAMANHO));
	}

	/*
	 * DATA NASCIMENTO
	 */
	@Test
	public void deve_aceitar_data_de_nascimento_especificada() {
		LocalDate nascimento = new LocalDate("1985-01-25");
		f.setDataNascimento(nascimento);
		assertThat(f.getDataNascimento(), equalTo(nascimento));
		assertThat(getErros(f), not(hasItem(MENSAGEM_DATA_NASCIMENTO_NULA)));
		assertThat(getErros(f), not(hasItem(MENSAGEM_DATA_NASCIMENTO_FUTURE)));
	}

	@Test
	public void nao_deve_aceitar_data_de_nascimento_futura() {
		LocalDate localDate = new LocalDate(2021, 4, 1);
		f.setDataNascimento(localDate);
		assertThat(getErros(f), hasItem(MENSAGEM_DATA_NASCIMENTO_FUTURE));
	}
	
	@Test
	public void nao_deve_aceitar_data_de_nascimento_nula() {
		f.setDataNascimento(null);
		assertThat(getErros(f), hasItem(MENSAGEM_DATA_NASCIMENTO_NULA));
	}
	
	/*
	 * EMAIL
	 */
	@Test
	public void deve_redefinir_email_valido() {
		String email = "java@oracle.com";
		f.setEmail(email);
		assertThat(f.getEmail(), equalTo(email));
		assertThat(getErros(f), not(hasItem(MENSAGEM_EMAIL_BLANK)));
		assertThat(getErros(f), not(hasItem(MENSAGEM_EMAIL_PATTERN)));
		assertThat(getErros(f), not(hasItem(MENSAGEM_EMAIL_TAMANHO)));
	}
	
	@Test
	public void deve_aceitar_email_valido() {
		f.setEmail("abc@gmail.com");
		assertThat(getErros(f), not(hasItem(MENSAGEM_EMAIL_BLANK)));
		assertThat(getErros(f), not(hasItem(MENSAGEM_EMAIL_PATTERN)));
		assertThat(getErros(f), not(hasItem(MENSAGEM_EMAIL_TAMANHO)));
	}
	
	@Test
	public void nao_deve_aceitar_email_invalido() {
		String email = "testmail@@gmail.com";
		f.setEmail(email);
		assertThat(getErros(f), hasItem(MENSAGEM_EMAIL_PATTERN));
	}
	
	@Test
	public void nao_deve_aceitar_email_nulo() {
		f.setEmail(null);
		assertThat(getErros(f), hasItem(MENSAGEM_EMAIL_BLANK));
	}

	@Test
	public void nao_deve_aceitar_email_com_menos_de_6_caracteres() {
		f.setEmail("a@a");
		assertThat(getErros(f), hasItem(MENSAGEM_EMAIL_TAMANHO));
	}
	
	@Test
	public void nao_deve_aceitar_email_com_mais_de_100_caracteres() {
		StringBuilder sb = new StringBuilder("J");
		for (int i = 0; i < 100; i++) {
			sb.append("o");
		}
		f.setEmail(sb.toString());
		assertThat(getErros(f), hasItem(MENSAGEM_EMAIL_TAMANHO));
	}
	
	/*
	 * DATA ADMISSAO
	 */
	@Test
	public void deve_aceitar_data_de_admissao_especificada() {
		f.setDataAdmissao(new LocalDate("2015-01-25"));
		assertThat(getErros(f), not(hasItem(MENSAGEM_DATA_ADMISSAO_PAST)));
	}

	@Test
	public void nao_deve_aceitar_data_de_admissao_futura() {
		LocalDate localDate = new LocalDate(2040, 4, 1);
		f.setDataAdmissao(localDate);
		assertThat(getErros(f), hasItem(MENSAGEM_DATA_ADMISSAO_PAST));
	}
	
	@Test
	public void nao_deve_aceitar_data_de_admissao_nula() {
		f.setDataAdmissao(null);
		assertThat(getErros(f), hasItem(MENSAGEM_DATA_ADMISSAO_BLANK));
	}
}
