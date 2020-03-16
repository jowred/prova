package br.com.contmatic.empresa;

import static br.com.contmatic.constantes.Mensagens.MENSAGEM_CPF_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_CPF_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_DATA_NASCIMENTO_FUTURE;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_EMAIL_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NOME_PESSOA_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NOME_PESSOA_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NOME_PESSOA_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_RG_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_RG_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_RG_TAMANHO;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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

public class PessoaTest {

	private Pessoa p;

	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

	private Validator validator = factory.getValidator();

	int[] iCpf = new int[11];

	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("Iniciando os testes da classe Pessoa...");
		FixtureFactoryLoader.loadTemplates("br.com.contmatic.templates");
	}

	@Before
	public void setUp() {
		p = Fixture.from(Pessoa.class).gimme("valido");
	}

	@After
	public void tearDown() {
		p = null;
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		System.out.println("Testes da classe Pessoa concluídos.");
	}

	public Set<String> getErros(Pessoa pessoa) {
		Set<String> erros = new HashSet<>();
		for (ConstraintViolation<Pessoa> constraintViolation : validator.validate(pessoa)) {
			erros.add(constraintViolation.getMessageTemplate());
			System.out.println(constraintViolation.getMessageTemplate()); // Retorna o template, sem converter {mix}
																			// para o valor mínimo
		}
		return erros;
	}
	
	//toString
	@Test
	public void deve_indicar_que_o_metodo_toString_esta_sobrescrito_por_nao_conter_o_caractere_arroba() {
		assertThat(new Pessoa().toString(), not(containsString("@")));
	}

	// equals
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_ambos_tem_o_mesmo_cpf() {
		Pessoa p2 = Fixture.from(Pessoa.class).gimme("valido");
		p2.setCpf(p.getCpf());
		assertThat(p, equalTo(p2));
	}

	@Test
	public void deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_sao_o_mesmo_objeto() {
		Pessoa p2 = p;
		assertThat(p, equalTo(p2));
	}

	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_pessoa_usando_equals_sobrescrito_porque_cpf_do_objeto2_e_diferente() {
		Pessoa p2 = Fixture.from(Pessoa.class).gimme("valido");
		String cpf = "30514611057";
		p2.setCpf(cpf);
		assertThat(p, not(equalTo(p2)));
	}

	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_objeto2_e_nulo() {
		Pessoa p2 = null;
		assertThat(p, not(equalTo(p2)));
	}

	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_os_valores_dos_atributos_sao_distintos() {
		Pessoa p2 = Fixture.from(Pessoa.class).gimme("valido");
		String nome = "Ana";
		String rg = "547891546";
		String cpf = "34946011005";
		p2.setNome(nome);
		p2.setRg(rg);
		p2.setCpf(cpf);
		assertThat(p, not(equalTo(p2)));
	}

	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_sao_de_classes_diferentes() {
		assertThat(p, not(equalTo(new Object())));
	}

	// hashcode
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_pessoa_usando_hashcode_sobrescrito() {
		Pessoa p2 = Fixture.from(Pessoa.class).gimme("valido");
		p2.setCpf(p.getCpf());
		assertThat(p.hashCode(), equalTo(p2.hashCode()));
	}

	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_pessoa_usando_hashcode_sobrescrito() {
		Pessoa p2 = Fixture.from(Pessoa.class).gimme("valido");
		String cpf = "34946011005";
		p2.setCpf(cpf);
		assertThat(p.hashCode(), not(equalTo(p2.hashCode())));
	}

	/*
	 * NOME
	 */
	@Test
	public void nao_deve_aceitar_nome_fora_do_padrao_da_regex_especificada() {
		String nome = "maria";
		p.setNome(nome);
		validator.validate(p);
		assertThat(getErros(p), hasItem(MENSAGEM_NOME_PESSOA_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_nome_de_tamanho_diferente_do_especificado() {
		String nome = "j";
		p.setNome(nome);
		validator.validate(p);
		assertThat(getErros(p), hasItem(MENSAGEM_NOME_PESSOA_TAMANHO));
	}
	
	@Test
	public void nao_deve_aceitar_nome_nulo() {
		p.setNome(null);
		assertThat(getErros(p), hasItem(MENSAGEM_NOME_PESSOA_BLANK));
	}
	
	@Test
	public void nao_deve_aceitar_nome_em_branco() {
		p.setNome("");
		assertThat(getErros(p), hasItem(MENSAGEM_NOME_PESSOA_BLANK));
	}

	@Test
	public void deve_definir_um_novo_nome_para_a_pessoa() {
		String nome = "Maria";
		p.setNome(nome);
		Set<String> erros = getErros(p);
		assertThat(p.getNome(), equalTo(nome));
		assertThat(erros, not(hasItem(MENSAGEM_NOME_PESSOA_BLANK)));
		assertThat(erros, not(hasItem(MENSAGEM_NOME_PESSOA_TAMANHO)));
		assertThat(erros, not(hasItem(MENSAGEM_NOME_PESSOA_PATTERN)));
	}

	@Test
	public void deve_aceitar_nome_apenas_com_letras() {
		String nome = "Vitória";
		p.setNome(nome);
		assertThat(getErros(p).isEmpty(), is(true));
	}

	@Test
	public void deve_aceitar_nome_apenas_com_letras_e_espaco() {
		String nome = "Vitória da Silva";
		p.setNome(nome);
		assertThat(getErros(p).isEmpty(), is(true));
	}

	@Test
	public void deve_aceitar_nome_com_letras_espaco_e_ponto() {
		String nome = "Vitória M. Silva";
		p.setNome(nome);
		assertThat(getErros(p).isEmpty(), is(true));
	}

	@Test
	public void nao_deve_aceitar_nome_com_numeros() {
		String nome = "José 2";
		p.setNome(nome);
		assertThat(getErros(p), hasItem(MENSAGEM_NOME_PESSOA_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_nome_com_caracteres_especiais() {
		String nome = "José@";
		p.setNome(nome);
		assertThat(getErros(p), hasItem(MENSAGEM_NOME_PESSOA_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_nome_que_nao_inicia_com_letra() {
		String nome = ".Mariana Silva";
		p.setNome(nome);
		assertThat(getErros(p), hasItem(MENSAGEM_NOME_PESSOA_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_nome_de_tamanho_menor_que_2_caracteres() {
		String nome = "S";
		p.setNome(nome);
		assertThat(getErros(p), hasItem(MENSAGEM_NOME_PESSOA_PATTERN));
		assertThat(getErros(p), hasItem(MENSAGEM_NOME_PESSOA_TAMANHO));
	}

	@Test
	public void nao_deve_aceitar_nome_de_tamanho_maior_que_100_caracteres() {
		StringBuilder sb = new StringBuilder("J");
		for (int i = 0; i < 100; i++) {
			sb.append("o");
		}
		p.setNome(sb.toString());
		assertThat(getErros(p), hasItem(MENSAGEM_NOME_PESSOA_TAMANHO));
	}

	@Test
	public void nao_deve_aceitar_nome_com_menos_de_uma_letra_independentemente_da_quantidade_de_caracteres_da_string() {
		String nome = "K    ";
		p.setNome(nome);
		assertThat(getErros(p), hasItem(MENSAGEM_NOME_PESSOA_PATTERN));
	}

	/*
	 * CPF
	 */
	@Test
	public void deve_definir_um_novo_cpf_para_a_pessoa() {
		String cpf = "55269981009";
		p.setCpf(cpf);
		assertThat(getErros(p).isEmpty(), is(true));
	}

	@Test
	public void deve_retornar_true_na_validacao_do_primeiro_digito_verificador_do_cpf() {
		String cpf = "72330358067";
		int[] verif = new int[2];
		verif[0] = cpf.charAt(9) - 48;
		verif[1] = cpf.charAt(10) - 48;
		int[] iCpf = new int[9];
		for (int i = 0; i < 9; i++)
			iCpf[i] = cpf.charAt(i) - 48;
		assertTrue(p.primeiroDigitoValido(iCpf, verif[0]));
	}

	@Test
	public void deve_retornar_false_na_validacao_do_primeiro_digito_verificador_do_cpf() {
		String cpf = "12312312312";
		int[] verif = new int[2];
		verif[0] = cpf.charAt(9) - 48;
		verif[1] = cpf.charAt(10) - 48;
		int[] iCpf = new int[9];
		for (int i = 0; i < 9; i++)
			iCpf[i] = cpf.charAt(i) - 48;
		assertFalse(p.primeiroDigitoValido(iCpf, verif[0]));
	}

	@Test
	public void deve_retornar_true_na_validacao_do_segundo_digito_verificador_do_cpf() {
		String cpf = "72330358067";
		int[] verif = new int[2];
		verif[0] = cpf.charAt(9) - 48;
		verif[1] = cpf.charAt(10) - 48;
		int[] iCpf = new int[9];
		for (int i = 0; i < 9; i++)
			iCpf[i] = cpf.charAt(i) - 48;
		assertTrue(p.segundoDigitoValido(iCpf, verif));
	}

	@Test
	public void deve_retornar_false_na_validacao_do_segundo_digito_verificador_do_cpf() {
		String cpf = "12312312312";
		int[] verif = new int[2];
		verif[0] = cpf.charAt(9) - 48;
		verif[1] = cpf.charAt(10) - 48;
		int[] iCpf = new int[9];
		for (int i = 0; i < 9; i++)
			iCpf[i] = cpf.charAt(i) - 48;
		assertFalse(p.segundoDigitoValido(iCpf, verif));
	}

	@Test
	public void deve_retornar_true_na_validacao_do_cpf() {
		assertTrue(p.cpfValido("72330358067"));
	}

	@Test
	public void deve_retornar_false_na_validacao_do_cpf_porque_digito_verificador_1_e_invalido() {
		assertFalse(p.cpfValido("72330358047"));
	}

	@Test
	public void deve_retornar_false_na_validacao_do_cpf_porque_digito_verificador_2_e_invalido() {
		assertFalse(p.cpfValido("72330358069"));
	}

	@Test
	public void deve_retornar_false_na_validacao_do_cpf_porque_ambos_os_digitos_verificadores_sao_invalidos() {
		assertFalse(p.cpfValido("72330358011"));
	}

	@Test
	public void deve_aceitar_cpf_valido_sem_caracteres_especiais_estranhos_ao_padrao() {
		String cpf = "54676325070";
		p.setCpf(cpf);
		assertThat(getErros(p), not(hasItem(MENSAGEM_CPF_PATTERN)));
	}
	
	@Test
	public void deve_aceitar_cpf_valido_com_mascara() {
		String cpf = "546.763.250-70";
		p.setCpf(cpf);
		assertThat(getErros(p), not(hasItem(MENSAGEM_CPF_PATTERN)));
	}

	@Test
	public void nao_deve_aceitar_cpf_nulo() {
		p.setCpf(null);
		assertThat(getErros(p), hasItem(MENSAGEM_CPF_BLANK));
	}

	@Test
	public void nao_deve_aceitar_cpf_em_branco() {
		String cpf = "";
		p.setCpf(cpf);
		assertThat(getErros(p), hasItem(MENSAGEM_CPF_BLANK));
	}

	@Test
	public void nao_deve_aceitar_cpf_composto_por_menos_de_11_digitos() {
		String cpf = "5467632507";
		p.setCpf(cpf);
		assertThat(getErros(p), hasItem(MENSAGEM_CPF_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_cpf_composto_por_mais_de_11_digitos() {
		String cpf = "546763250704";
		p.setCpf(cpf);
		assertThat(getErros(p), hasItem(MENSAGEM_CPF_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_cpf_composto_por_letras() {
		String cpf = "abcdefghijk";
		p.setCpf(cpf);
		assertThat(getErros(p), hasItem(MENSAGEM_CPF_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_cpf_composto_por_caracteres_especiais() {
		String cpf = "!@#$.%&*()!@#$";
		p.setCpf(cpf);
		assertThat(getErros(p), hasItem(MENSAGEM_CPF_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_cpf_composto_por_digitos_iguais() {
		String cpf = "99999999999";
		p.setCpf(cpf);
		assertThat(getErros(p), hasItem(MENSAGEM_CPF_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_cpf_invalido() {
		String cpf = "87548965809";
		p.setCpf(cpf);
		assertThat(getErros(p), hasItem(MENSAGEM_CPF_PATTERN));
	}

	/*
	 * RG
	 */
	@Test
	public void nao_deve_aceitar_rg_com_menos_de_8_digitos() {
		String rg = "4963592";
		p.setRg(rg);
		assertThat(getErros(p), hasItem(MENSAGEM_RG_TAMANHO));
	}
	
	@Test
	public void nao_deve_aceitar_rg_com_mais_de_9_digitos() {
		String rg = "4963592789";
		p.setRg(rg);
		assertThat(getErros(p), hasItem(MENSAGEM_RG_TAMANHO));
	}

	@Test
	public void deve_definir_um_novo_rg_para_a_pessoa() {
		String rg = "789546879";
		p.setRg(rg);
		assertEquals(rg, p.getRg());
	}

	@Test
	public void deve_aceitar_rg_com_8_digitos() {// RG em MG
		String rg = "26542809";
		p.setRg(rg);
		assertThat(getErros(p).isEmpty(), is(true));
	}

	@Test
	public void deve_aceitar_rg_com_9_digitos() {
		String rg = "265428099";
		p.setRg(rg);
		assertThat(getErros(p).isEmpty(), is(true));
	}

	@Test
	public void nao_deve_aceitar_rg_nulo() {
		p.setRg(null);
		assertThat(getErros(p), hasItem(MENSAGEM_RG_BLANK));
	}

	@Test
	public void nao_deve_aceitar_rg_em_branco() {
		String rg = "";
		p.setRg(rg);
		assertThat(getErros(p), hasItem(MENSAGEM_RG_BLANK));
	}

	@Test
	public void nao_deve_aceitar_rg_com_letras() {
		String rg = "abcdefghi";
		p.setRg(rg);
		assertThat(getErros(p), hasItem(MENSAGEM_RG_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_rg_com_caracteres_especiais() {
		String rg = "@#$%&*###";
		p.setRg(rg);
		assertThat(getErros(p), hasItem(MENSAGEM_RG_PATTERN));
		
	}

	@Test
	public void nao_deve_aceitar_rg_que_contenha_qualquer_caractere_estranho_a_digitos() {
		String rg = "26.542.809-9";
		p.setRg(rg);
		assertThat(getErros(p), hasItem(MENSAGEM_RG_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_rg_composto_por_menos_de_8_caracteres() {
		String rg = "2654280";
		p.setRg(rg);
		assertThat(getErros(p), hasItem(MENSAGEM_RG_TAMANHO));
	}

	@Test
	public void nao_deve_aceitar_rg_composto_por_mais_de_9_caracteres() {
		String rg = "2654280995";
		p.setRg(rg);
		assertThat(getErros(p), hasItem(MENSAGEM_RG_TAMANHO));
	}

	/*
	 * DATA NASCIMENTO
	 */
	@Test
	public void deve_aceitar_data_de_nascimento_especificada() {
		p.setDataNascimento(new LocalDate("1985-01-25"));
		assertThat(getErros(p).isEmpty(), is(true));
	}

	@Test
	public void nao_deve_aceitar_data_de_nascimento_futura() {
		LocalDate localDate = new LocalDate(2021, 4, 1);
		p.setDataNascimento(localDate);
		assertThat(getErros(p), hasItem(MENSAGEM_DATA_NASCIMENTO_FUTURE));
	}
	
	/*
	 * EMAIL
	 */
	@Test
	public void deve_aceitar_email_valido() {
		String email = "java@oracle.com";
		p.setEmail(email);
		assertThat(getErros(p).isEmpty(), is(true));
	}
	
	@Test
	public void nao_deve_aceitar_email_invalido() {
		String email = "testmail@@gmail.com";
		p.setEmail(email);
		assertThat(getErros(p), hasItem(MENSAGEM_EMAIL_PATTERN));
	}
}
