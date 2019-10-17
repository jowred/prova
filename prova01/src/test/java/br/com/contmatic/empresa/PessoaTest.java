package br.com.contmatic.empresa;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.empresa.Pessoa;

public class PessoaTest {
	private Pessoa p;
	int[] iCpf = new int[11];
	
	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("Iniciando os testes da classe Pessoa...");
	}
	
	@Before
	public void setUp() {
		String nome = "Vitória";
		String rg = "516879541";
		String cpf = "50736121080";
		p = new Pessoa(nome, rg, cpf);
	}
	
	@After
	public void tearDown() {
		p = null;
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		System.out.println("Testes da classe Pessoa concluídos.");
	}
	
	@Test
	public void deve_retornar_false_para_indicar_que_o_metodo_toString_esta_sobrescrito() {
		assertFalse(new Pessoa().toString().contains("["));
	}
	
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_pessoa() {
		String nome = p.getNome();
		String rg = p.getRg();
		String cpf = p.getCpf();
		Pessoa p2 = new Pessoa(nome, rg, cpf);
		assertEquals(p, p2);
	}
	//equals
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
	
	//hashcode
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
	 * */
	@Test
	public void deve_definir_um_novo_nome_para_a_pessoa() {
		String nome = "Maria";
		p.setNome(nome);
		assertEquals(nome, p.getNome());
	}
	
	@Test
	public void deve_aceitar_nome_apenas_com_letras() {
		p.setNome("Vitória");
	}
	
	@Test
	public void deve_aceitar_nome_apenas_com_letras_e_espaco() {
		p.setNome("Vitória da Silva");
	}
	
	@Test
	public void deve_aceitar_nome_com_letras_espaco_e_ponto() {
		p.setNome("Vitória M. Silva");
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_nome_nulo() {
		p.setNome(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_em_branco() {
		p.setNome("");
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
		for(int i=0; i<100; i++)
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
	 * */
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
		for(int i=0; i<9; i++)
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
		for(int i=0; i<9; i++)
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
		for(int i=0; i<9; i++)
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
		for(int i=0; i<9; i++)
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
	public void deve_aceitar_cpf_valido_sem_caracteres_especiais() {
		p.setCpf("54676325070");
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_cpf_nulo() {
		p.setCpf(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_em_branco() {
		p.setCpf("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_composto_por_menos_de_11_digitos() {
		p.setCpf("5467632507");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_composto_por_mais_de_11_digitos() {
		p.setCpf("546763250704");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_composto_por_letras() {
		p.setCpf("abcdefghijk");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_composto_por_caracteres_especiais() {
		p.setCpf("!@#$.%&*()!@#$");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_que_contenha_qualquer_caractere_estranho_a_digitos() {
		p.setCpf("546.763.250-70");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_composto_por_digitos_iguais() {
		p.setCpf("99999999999");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cpf_invalido() {
		p.setCpf("87548965809");
	}
	
	/*
	 * RG
	 * */
	@Test
	public void deve_definir_um_novo_rg_para_a_pessoa() {
		String rg = "789546879";
		p.setRg(rg);
		assertEquals(rg, p.getRg());
	}
	
	@Test
	public void deve_aceitar_rg_com_8_digitos() {//RG em MG
		p.setRg("26542809");
	}
	
	@Test
	public void deve_aceitar_rg_com_9_digitos() {
		p.setRg("265428099");
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
}
