package br.com.contmatic.empresa;

import static br.com.contmatic.constantes.Mensagens.MENSAGEM_CPF_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_CPF_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_DATA_NASCIMENTO_FUTURE;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_EMAIL_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NOME_PESSOA_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NOME_PESSOA_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NOME_PESSOA_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_RG_TAMANHO;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
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

	private ValidatorFactory factory;

	private Validator validator;

	int[] iCpf = new int[11];

	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("Iniciando os testes da classe Pessoa...");
		FixtureFactoryLoader.loadTemplates("br.com.contmatic.templates");
	}

	@Before
	public void setUp() {
		String nome = "Vitória";
		String rg = "516879541";
		String cpf = "50736121080";
		p = new Pessoa(nome, rg, cpf);
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
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

	@Test
	public void deve_indicar_que_o_metodo_toString_esta_sobrescrito_por_nao_conter_o_caractere_arroba() {
		assertThat(new Pessoa().toString(), not(containsString("@")));
	}

	@Test
	public void deve_apontar_igualdade_entre_os_objetos_pessoa() {
		String nome = p.getNome();
		String rg = p.getRg();
		String cpf = p.getCpf();
		Pessoa p2 = new Pessoa(nome, rg, cpf);
		assertEquals(p, p2);
	}

	// equals
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_ambos_tem_o_mesmo_cpf() {
		String nome = "Vitória M.";
		String rg = p.getRg();
		String cpf = p.getCpf();
		Pessoa p2 = new Pessoa(nome, rg, cpf);
		assertTrue(p.equals(p2));
	}

	@Test
	public void deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_sao_o_mesmo_objeto() {
		Pessoa p2 = p;
		assertTrue(p.equals(p2));
	}

	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_pessoa_usando_equals_sobrescrito_porque_cpf_do_objeto2_e_diferente() {
		String nome = p.getNome();
		String rg = p.getRg();
		String cpf = "30514611057";
		Pessoa p2 = new Pessoa(nome, rg, cpf);
		assertFalse(p.equals(p2));
	}

	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_objeto2_e_nulo() {
		Pessoa p2 = null;
		assertFalse(p.equals(p2));
	}

	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_os_valores_sao_diferentes() {
		String nome = "Ana";
		String rg = "547891546";
		String cpf = "34946011005";
		Pessoa p2 = new Pessoa(nome, rg, cpf);
		assertFalse(p.equals(p2));
	}

	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_sao_de_classes_diferentes() {
		assertFalse(p.equals(new Object()));
	}

	// hashcode
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_pessoa_usando_hashcode_sobrescrito() {
		String nome = p.getNome();
		String rg = p.getRg();
		String cpf = p.getCpf();
		Pessoa p2 = new Pessoa(nome, rg, cpf);
		assertTrue(p.hashCode() == p2.hashCode());
	}

	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_pessoa_usando_hashcode_sobrescrito() {
		String nome = "Amanda";
		String rg = "516879542";
		String cpf = "34946011005";
		Pessoa p2 = new Pessoa(nome, rg, cpf);
		assertNotEquals(p.hashCode(), p2.hashCode());
	}

	/*
	 * NOME
	 */
	@Test
	public void nao_deve_aceitar_nome_fora_do_padrao_da_regex_especificada() {
		p = Fixture.from(Pessoa.class).gimme("valido");
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
		
		// Fazer um teste para nomes como João, François, Vitória etc.
		
		p.setNome(nome);
//		assertEquals(nome, p.getNome());
		Set<String> erros = getErros(p);
		assertThat(p.getNome(), equalTo(nome));
		assertThat(erros, not(hasItem(MENSAGEM_NOME_PESSOA_BLANK)));
		assertThat(erros, not(hasItem(MENSAGEM_NOME_PESSOA_TAMANHO)));
		assertThat(erros, not(hasItem(MENSAGEM_NOME_PESSOA_PATTERN)));
		// melhorar o assert com um hasItems, se existir para finalidade desejada
	}

	@Test
	public void deve_aceitar_nome_apenas_com_letras() {
		p = Fixture.from(Pessoa.class).gimme("valido");
		String nome = "Vitória";
		p.setNome(nome);
		assertThat(getErros(p).isEmpty(), is(true));
	}

	@Test
	public void deve_aceitar_nome_apenas_com_letras_e_espaco() {
		p = Fixture.from(Pessoa.class).gimme("valido");
		String nome = "Vitória da Silva";
		p.setNome(nome);
		assertThat(getErros(p).isEmpty(), is(true));
	}

	@Test
	public void deve_aceitar_nome_com_letras_espaco_e_ponto() {
		p = Fixture.from(Pessoa.class).gimme("valido");
		String nome = "Vitória M. Silva";
		p.setNome(nome);
		assertThat(getErros(p).isEmpty(), is(true));
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_com_numeros() {
		p.setCpf("José 2");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_com_caracteres_especiais() {
		p.setNome("José@");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_que_nao_inicia_com_letra() {
		p.setNome(".Mariana Silva");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_de_tamanho_menor_que_2_caracteres() {
		p.setNome("S");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_de_tamanho_maior_que_100_caracteres() {
		StringBuilder sb = new StringBuilder("J");
		for (int i = 0; i < 100; i++)
			sb.append("o");
		p.setNome(sb.toString());
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_composto_unicamente_pelo_mesmo_caractere() {
		p.setNome("jjjjjjjjjjjjj");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_com_menos_de_uma_letra_independentemente_da_quantidade_de_caracteres_da_string() {
		p.setNome(" k ");
	}

	/*
	 * CPF
	 */
	@Test
	public void deve_definir_um_novo_cpf_para_a_pessoa() {
		String cpf = "55269981009";
		p.setCpf(cpf);
		assertEquals(cpf, p.getCpf());
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
		p = Fixture.from(Pessoa.class).gimme("valido");
		String cpf = "54676325070";
		p.setCpf(cpf);
		assertThat(getErros(p), not(hasItem(MENSAGEM_CPF_PATTERN)));
	}
	
	@Test
	public void deve_aceitar_cpf_valido_com_mascara() {
		p = Fixture.from(Pessoa.class).gimme("valido");
		String cpf = "546.763.250-70";
		p.setCpf(cpf);
		assertThat(getErros(p), not(hasItem(MENSAGEM_CPF_PATTERN)));
	}

	@Test
	public void nao_deve_aceitar_cpf_nulo() {
		p = Fixture.from(Pessoa.class).gimme("valido");
		p.setCpf(null);
		assertThat(getErros(p), hasItem(MENSAGEM_CPF_BLANK));
	}

	@Test
	public void nao_deve_aceitar_cpf_em_branco() {
		p = Fixture.from(Pessoa.class).gimme("valido");
		String cpf = "";
		p.setCpf(cpf);
		assertThat(getErros(p), hasItem(MENSAGEM_CPF_BLANK));
	}

	@Test
	public void nao_deve_aceitar_cpf_composto_por_menos_de_11_digitos() {
		p = Fixture.from(Pessoa.class).gimme("valido");
		String cpf = "5467632507";
		p.setCpf(cpf);
		assertThat(getErros(p), hasItem(MENSAGEM_CPF_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_cpf_composto_por_mais_de_11_digitos() {
		p = Fixture.from(Pessoa.class).gimme("valido");
		String cpf = "546763250704";
		p.setCpf(cpf);
		assertThat(getErros(p), hasItem(MENSAGEM_CPF_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_cpf_composto_por_letras() {
		p = Fixture.from(Pessoa.class).gimme("valido");
		String cpf = "abcdefghijk";
		p.setCpf(cpf);
		assertThat(getErros(p), hasItem(MENSAGEM_CPF_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_cpf_composto_por_caracteres_especiais() {
		p = Fixture.from(Pessoa.class).gimme("valido");
		String cpf = "!@#$.%&*()!@#$";
		p.setCpf(cpf);
		assertThat(getErros(p), hasItem(MENSAGEM_CPF_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_cpf_composto_por_digitos_iguais() {
		p = Fixture.from(Pessoa.class).gimme("valido");
		String cpf = "99999999999";
		p.setCpf(cpf);
		assertThat(getErros(p), hasItem(MENSAGEM_CPF_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_cpf_invalido() {
		p = Fixture.from(Pessoa.class).gimme("valido");
		String cpf = "87548965809";
		p.setCpf(cpf);
		assertThat(getErros(p), hasItem(MENSAGEM_CPF_PATTERN));
	}

	/*
	 * RG
	 */
	@Test
	public void nao_deve_aceitar_rg_com_menos_de_8_digitos() {
		p.setRg("4963592");
		assertThat(getErros(p), hasItem(MENSAGEM_RG_TAMANHO));
	}
	
	@Test
	public void nao_deve_aceitar_rg_com_mais_de_9_digitos() {
		p.setRg("4963592789");
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
		p = Fixture.from(Pessoa.class).gimme("valido");
		p.setRg("26542809");
		assertThat(getErros(p).isEmpty(), is(true));
	}

	@Test
	public void deve_aceitar_rg_com_9_digitos() {
		p = Fixture.from(Pessoa.class).gimme("valido");
		p.setRg("265428099");
		assertThat(getErros(p).isEmpty(), is(true));
	}

	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_rg_nulo() {
		p.setRg(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_rg_em_branco() {
		p.setRg("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_rg_com_letras() {
		p.setRg("abcdefghi");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_rg_com_caracteres_especiais() {
		p.setRg("@#$%&*###");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_rg_que_contenha_qualquer_caractere_estranho_a_digitos() {
		p.setRg("26.542.809-9");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_rg_composto_por_menos_de_8_caracteres() {
		p.setRg("2654280");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_rg_composto_por_mais_de_9_caracteres() {
		p.setRg("2654280995");
	}

	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_rg_composto_por_digitos_iguais() {
		p.setRg("111111111");
	}

	/*
	 * DATA NASCIMENTO
	 */
	@Test
	public void deve_aceitar_data_de_nascimento_especificada() {
		p = Fixture.from(Pessoa.class).gimme("valido");
		p.setDataNascimento(new LocalDate("1985-01-25"));
		validator.validate(p);
		assertThat(getErros(p).isEmpty(), is(true));
	}

	@Test
	public void nao_deve_aceitar_data_de_nascimento_futura() {
		p = Fixture.from(Pessoa.class).gimme("valido");
		LocalDate localDate = new LocalDate(2021, 4, 1);
		p.setDataNascimento(localDate);
		validator.validate(p);
		assertThat(getErros(p), hasItem(MENSAGEM_DATA_NASCIMENTO_FUTURE));
	}
	
	/*
	 * EMAIL
	 */
	@Test
	public void deve_aceitar_email_valido() {
		p = Fixture.from(Pessoa.class).gimme("valido");
		validator.validate(p);
		System.out.println(p);
		assertThat(getErros(p).isEmpty(), is(true));
	}
	
	@Test
	public void nao_deve_aceitar_email_invalido() {
		p = Fixture.from(Pessoa.class).gimme("valido");
		String email = "testmail@@gmail.com";
		p.setEmail(email);
		assertThat(getErros(p), hasItem(MENSAGEM_EMAIL_PATTERN));
	}
}
