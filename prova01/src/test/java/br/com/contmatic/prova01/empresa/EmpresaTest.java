package br.com.contmatic.prova01.empresa;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import br.com.contmatic.prova01.empresa.Departamento;
import br.com.contmatic.prova01.empresa.Empresa;
import br.com.contmatic.prova01.empresa.Endereco;
import br.com.contmatic.prova01.empresa.Telefone;

public class EmpresaTest {
	
	Empresa emp1;
	
	int[] iCnpj = new int[14];
	
	@Test(timeout = 1000)
	public void deve_executar_sem_exceder_o_limite_de_tempo() {
		Departamento dep;
		for(int i=0; i<100; i++) {
			dep = new Departamento("Departamento X", "Departamento " + i+1);
			dep.cadastrar(emp1);
		}
	}
	
	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("Iniciando os testes da classe Empresa...");
	}
	
	@Before
	public void setUp() {
		String razaoSocial = "PepsiCo do Brasil Ltda.";
		String nomeFantasia = "PepsiCo";
		String cnpj = "31565104000177";
		String areaAtuacao = "Alimentos";
		
		emp1 = new Empresa(razaoSocial, cnpj);
		emp1.setNomeFantasia(nomeFantasia);
		emp1.setAreaAtuacao(areaAtuacao);
	}
	
	@After
	public void tearDown() {
		emp1 = null;
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		System.out.println("Testes da classe Empresa concluídos.");
	}
	
	@Test
	public void deve_confirmar_a_presenca_das_strings_na_razao_social_do_objeto_usado_em_toda_a_classe_de_teste() {
		assertThat(emp1.getRazaoSocial(), both(containsString("PepsiCo")).and(containsString("Ltda.")));
	}
	
	@Test
	public void deve_confirmar_a_presenca_da_string_no_nome_fantasia_do_objeto_usado_em_toda_a_classe_de_teste() {
		assertThat(emp1.getNomeFantasia(), containsString("Pepsi"));
	}
	
	@Test
	public void deve_confirmar_o_cnpj_do_objeto_usado_em_toda_a_classe_de_teste() {
		assertThat(emp1.getCnpj(), containsString("31565104000177"));
	}
	
	@Test
	public void nao_deve_confirmar_a_presenca_da_string_na_razao_social_do_objeto_usado_em_toda_a_classe_de_teste() {
		assertThat(emp1.getRazaoSocial(), is(not("Coca-Cola")));
	}
	
	@Ignore("Exemplo de teste ignorado")
	@Test
	public void nao_deve_confirmar_a_presenca_dessa_segunda_string_na_razao_social_do_objeto_usado_em_toda_a_classe_de_teste() {
		assertThat(emp1.getRazaoSocial(), is(not("Nestlé")));
	}
	
	@Test
	public void deve_confirmar_a_presenca_da_string_na_area_de_atuacao_do_objeto_usado_em_toda_a_classe_de_teste() {
		assertThat(emp1.getAreaAtuacao(), containsString("Alimentos"));
	}
	
	@Test
	public void deve_retornar_false_para_indicar_que_o_metodo_toString_esta_sobrescrito() {
		assertFalse(new Empresa().toString().contains("["));
	}
	
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_empresa() {
		String razaoSocial = emp1.getRazaoSocial();
		String nomeFantasia = emp1.getNomeFantasia();
		String cnpj = emp1.getCnpj();
		String areaAtuacao = emp1.getAreaAtuacao();
		Empresa emp2 = new Empresa(razaoSocial, cnpj);
		emp2.setNomeFantasia(nomeFantasia);
		emp2.setAreaAtuacao(areaAtuacao);
		assertEquals(emp1, emp2);
	}
	
	//equals
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_ambos_tem_o_mesmo_cnpj() {
		String razaoSocial = "PepsiCo Ltda.";
		String nomeFantasia = "PepsiCo";
		String cnpj = emp1.getCnpj();
		String areaAtuacao = "Indústria alimentícia";
		Empresa emp2 = new Empresa(razaoSocial, cnpj);
		emp2.setNomeFantasia(nomeFantasia);
		emp2.setAreaAtuacao(areaAtuacao);
		assertTrue(emp1.equals(emp2));
	}
	
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_sao_o_mesmo_objeto() {
		Empresa emp2 = emp1;
		assertTrue(emp1.equals(emp2));
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_cnpj_do_objeto2_e_diferente() {
		String razaoSocial = "PepsiCo Ltda.";
		String nomeFantasia = "Pepsi";
		String areaAtuacao = "Alimentos";
		String cnpj = "55062400000113";
		Empresa emp2 = new Empresa(razaoSocial, cnpj);
		emp2.setNomeFantasia(nomeFantasia);
		emp2.setAreaAtuacao(areaAtuacao);
		assertFalse(emp1.equals(emp2));
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_objeto2_e_nulo() {
		Empresa emp2 = null;
		assertFalse(emp1.equals(emp2));
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_sao_de_classes_diferentes() {
		assertFalse(emp1.equals(new Object()));
	}
	
	//hashCode
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_empresa_usando_hashcode_sobrescrito() {
		String razaoSocial = emp1.getRazaoSocial();
		String nomeFantasia = emp1.getNomeFantasia();
		String areaAtuacao = emp1.getAreaAtuacao();
		String cnpj = emp1.getCnpj();
		Empresa emp2 = new Empresa(razaoSocial, cnpj);
		emp2.setNomeFantasia(nomeFantasia);
		emp2.setAreaAtuacao(areaAtuacao);
		assertEquals(emp1.hashCode(), emp2.hashCode());
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_empresa_usando_hashcode_sobrescrito() {
		String razaoSocial = "Coca-Cola Ltda.";
		String nomeFantasia = "Coca-Cola";
		String areaAtuacao = "Bebidas";
		String cnpj = "45997418000153";
		Empresa emp2 = new Empresa(razaoSocial, cnpj);
		emp2.setNomeFantasia(nomeFantasia);
		emp2.setAreaAtuacao(areaAtuacao);
		assertNotEquals(emp1.hashCode(), emp2.hashCode());
	}
	
	/*
	 * RAZÃO SOCIAL
	 * */
	@Test
	public void deve_definir_uma_nova_razao_social_para_a_empresa() {
		String razaoSocial = "Coca-Cola Indústrias Ltda.";
		emp1.setRazaoSocial(razaoSocial);
		assertEquals(razaoSocial, emp1.getRazaoSocial());
	}
	
	@Test
	public void deve_aceitar_razao_social_apenas_com_letras() {
		emp1.setRazaoSocial("Nintendo");
	}
	
	@Test
	public void deve_aceitar_razao_social_com_letras_e_hifen() {
		emp1.setRazaoSocial("Colgate-Palmolive");
	}
	
	@Test
	public void deve_aceitar_razao_social_com_letras_e_numeros() {
		emp1.setRazaoSocial("99Taxis");
	}
	
	@Test
	public void deve_aceitar_razao_social_com_letras_e_espaco() {
		emp1.setRazaoSocial("Itaú Unibanco");
	}
	
	@Test
	public void deve_aceitar_razao_social_com_letras_hifen_e_espaco() {
		emp1.setRazaoSocial("Hi-Tech Indústria Tecnológica");
	}
	
	@Test
	public void deve_aceitar_razao_social_com_letras_hifen_espaco_e_ponto() {
		emp1.setRazaoSocial("Hi-Tech Indústria Tecnológica Ltda.");
	}
	
	@Test
	public void deve_aceitar_razao_social_com_letras_numeros_espaco_e_ponto() {
		emp1.setRazaoSocial("SEGA 12 Indústria de Jogos Eletrônicos Ltda.");
	}
	
	@Test
	public void deve_aceitar_razao_social_com_letras_numeros_hifen_espaco_e_ponto() {
		emp1.setRazaoSocial("ABC-123 Alimentos Ltda.");
	}
	
	@Test
	public void deve_aceitar_razao_social_com_ponto_entre_caracteres() {
		emp1.setRazaoSocial("Instituto A. Senna");
	}
	
	@Test
	public void deve_aceitar_razao_social_com_numeros_entre_letras() {
		emp1.setRazaoSocial("Se7e Belo");
	}
	
	@Test
	public void deve_aceitar_razao_social_com_mais_de_um_ponto() {
		emp1.setRazaoSocial("Itaú Unibanco S.A.");
	}
	
	@Test
	public void deve_aceitar_razao_social_com_E_comercial() {
		emp1.setRazaoSocial("Dolce & Gabanna do Brasil Ltda.");
	}
	
	@Test
	public void deve_aceitar_razao_social_com_E_comercial_e_virgula() {
		emp1.setRazaoSocial("Dolce & Gabbana do Brasil Comércio, Importação e Participações Ltda.");
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_razao_social_nula() {
		emp1.setRazaoSocial(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_razao_social_em_branco() {
		emp1.setRazaoSocial("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_razao_social_apenas_com_espaco() {
		emp1.setRazaoSocial(" ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_razao_social_apenas_com_ponto() {
		emp1.setRazaoSocial(".");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_razao_social_apenas_com_caracteres_especiais() {
		emp1.setRazaoSocial("!@(/.+**/-*");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_razao_social_com_letras_e_caracteres_especiais_diferentes() {
		emp1.setRazaoSocial("Boom!@(*#");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_razao_social_com_hifen_no_inicio() {
		emp1.setRazaoSocial("-Arcos Dourados Indústria de Alimentos Ltda.");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_razao_social_com_hifen_no_fim() {
		emp1.setRazaoSocial("Arcos Dourados Indústria de Alimentos Ltda.-");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_razao_social_apenas_com_numeros() {
		emp1.setRazaoSocial("123456789");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_razao_social_apenas_com_numeros_e_hifen() {
		emp1.setRazaoSocial("12345-6789");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_razao_social_de_tamanho_menor_que_5_caracteres() {
		emp1.setRazaoSocial("ABCD");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_razao_social_composta_unicamente_pelo_mesmo_caractere() {
		emp1.setRazaoSocial("aaaaaaaaaa");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_razao_social_composta_unicamente_pelo_mesmo_caractere_independentemente_de_caixa() {
		emp1.setRazaoSocial("AaaAaaAaaa");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_razao_social_de_tamanho_maior_que_100_caracteres() {
		StringBuilder sb = new StringBuilder("B");
		for(int i=0; i<100; i++)
			sb.append("A");
		emp1.setRazaoSocial(sb.toString());
	}
	
	/*
	 * NOME FANTASIA
	 * */
	@Test
	public void deve_definir_um_novo_nome_fantasia_para_a_empresa() {
		String nomeFantasia = "Coca-Cola";
		emp1.setNomeFantasia(nomeFantasia);
		assertEquals(nomeFantasia, emp1.getNomeFantasia());
	}
	
	@Test
	public void deve_aceitar_nome_fantasia_apenas_com_letras() {
		emp1.setNomeFantasia("PlayStation");
	}
	
	@Test
	public void deve_aceitar_nome_fantasia_com_letras_e_hifen() {
		emp1.setNomeFantasia("General-Motors");
	}
	
	@Test
	public void deve_aceitar_nome_fantasia_com_letras_e_numeros() {
		emp1.setNomeFantasia("Hao123");
	}
	
	@Test
	public void deve_aceitar_nome_fantasia_com_letras_e_espaco() {
		emp1.setNomeFantasia("Banco do Brasil");
	}
	
	@Test
	public void deve_aceitar_nome_fantasia_com_letras_hifen_e_espaco() {
		emp1.setNomeFantasia("Coca-Cola Coffee Plus");
	}
	
	@Test
	public void deve_aceitar_nome_fantasia_com_letras_hifen_espaco_e_ponto() {
		emp1.setNomeFantasia("M. Martins - Advocacia");
	}
	
	@Test
	public void deve_aceitar_nome_fantasia_com_letras_numeros_espaco_e_ponto() {
		emp1.setNomeFantasia("Rapi10 - A Massa da Galera");
	}
	
	@Test
	public void deve_aceitar_nome_fantasia_com_letras_numeros_hifen_espaco_e_ponto() {
		emp1.setNomeFantasia("ABC-123 Alimentos Ltda.");
	}
	
	@Test
	public void deve_aceitar_nome_fantasia_com_ponto_entre_caracteres() {
		emp1.setNomeFantasia("Instituto A. Senna");
	}
	
	@Test
	public void deve_aceitar_nome_fantasia_com_numeros_entre_letras() {
		emp1.setNomeFantasia("Se7e Belo");
	}
	
	@Test
	public void deve_aceitar_nome_fantasia_com_mais_de_um_ponto() {
		emp1.setNomeFantasia("Itaú Unibanco S.A.");
	}
	
	@Test
	public void deve_aceitar_nome_fantasia_com_E_comercial() {
		emp1.setNomeFantasia("Dolce & Gabanna");
	}
	
	/**/
	@Test
	public void deve_aceitar_nome_fantasia_com_virgula() {
		emp1.setNomeFantasia("Du, Dudu & Edu");
	}
	
	@Test
	public void deve_aceitar_nome_fantasia_com_exclamacao() {
		emp1.setNomeFantasia("Big Boom!");
	}
	
	@Test
	public void deve_aceitar_nome_fantasia_com_arroba() {
		emp1.setNomeFantasia("L@n House do João");
	}
	/**/
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_nome_fantasia_nulo() {
		emp1.setNomeFantasia(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_fantasia_em_branco() {
		emp1.setNomeFantasia("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_fantasia_apenas_com_espaco() {
		emp1.setNomeFantasia(" ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_fantasia_apenas_com_ponto() {
		emp1.setNomeFantasia(".");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_fantasia_apenas_com_caracteres_especiais() {
		emp1.setNomeFantasia("!@(*");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_fantasia_com_letras_e_caracteres_especiais_diferentes() {
		emp1.setNomeFantasia("Boom¨¨");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_fantasia_com_hifen_no_inicio() {
		emp1.setNomeFantasia("-Arcos Dourados Indústria de Alimentos Ltda.");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_fantasia_com_hifen_no_fim() {
		emp1.setNomeFantasia("Arcos Dourados Indústria de Alimentos Ltda.-");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_fantasia_apenas_com_numeros() {
		emp1.setNomeFantasia("123456789");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_fantasia_apenas_com_numeros_e_hifen() {
		emp1.setNomeFantasia("12345-6789");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_fantasia_de_tamanho_menor_que_2_caracteres() {
		emp1.setNomeFantasia("A");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_fantasia_composto_unicamente_pelo_mesmo_caractere() {
		emp1.setNomeFantasia("aaaaaaaaaa");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_fantasia_composto_unicamente_pelo_mesmo_caractere_independentemente_de_caixa() {
		emp1.setNomeFantasia("aaaAaaAaaA");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_fantasia_de_tamanho_maior_que_55_caracteres() {
		StringBuilder sb = new StringBuilder("Z");
		for(int i=0; i<55; i++)
			sb.append("A");
		emp1.setNomeFantasia(sb.toString());
	}
	
	/*
	 * CNPJ
	 * */
	@Test
	public void deve_definir_um_novo_cnpj_para_a_empresa() {
		String cnpj = "45997418000153";
		emp1.setCnpj(cnpj);
		assertEquals(cnpj, emp1.getCnpj());
	}
	
	@Test
	public void deve_retornar_true_na_validacao_do_primeiro_digito_verificador_do_cnpj() {
		String cnpj = "61365284000104";
		for(int i=0; i<iCnpj.length; i++)
			iCnpj[i] = cnpj.charAt(i) - 48;
		assertTrue(emp1.primeiroDigitoValido(iCnpj));
	}
	
	@Test
	public void deve_retornar_false_na_validacao_do_primeiro_digito_verificador_do_cnpj() {
		String cnpj = "38837760000110";
		for(int i=0; i<iCnpj.length; i++)
			iCnpj[i] = cnpj.charAt(i) - 48;
		assertFalse(emp1.primeiroDigitoValido(iCnpj));
	}
	
	@Test
	public void deve_retornar_true_na_validacao_do_segundo_digito_verificador_do_cnpj() {
		String cnpj = "38837760000120";
		for(int i=0; i<iCnpj.length; i++)
			iCnpj[i] = cnpj.charAt(i) - 48;
		assertTrue(emp1.segundoDigitoValido(iCnpj));
	}
	
	@Test
	public void deve_retornar_false_na_validacao_do_segundo_digito_verificador_do_cnpj() {
		String cnpj = "99999999999991";
		for(int i=0; i<iCnpj.length; i++)
			iCnpj[i] = cnpj.charAt(i) - 48;
		assertFalse(emp1.segundoDigitoValido(iCnpj));
	}
	
	@Test
	public void deve_retornar_true_na_validacao_do_cnpj() {
		assertTrue(emp1.cnpjValido("61365284000104"));
	}
	
	@Test
	public void deve_retornar_false_na_validacao_do_cnpj_porque_digito_verificador_1_e_invalido() {
		assertFalse(emp1.cnpjValido("38837760000110"));
	}
	
	@Test
	public void deve_retornar_false_na_validacao_do_cnpj_porque_digito_verificador_2_e_invalido() {
		assertFalse(emp1.cnpjValido("38837760000122"));
	}
	
	@Test
	public void deve_retornar_false_na_validacao_do_cnpj_porque_ambos_os_digitos_verificadores_sao_invalidos() {
		assertFalse(emp1.cnpjValido("38837760000122"));
	}
	
	@Test
	public void deve_aceitar_cnpj_valido_sem_caracteres_especiais() {
		emp1.setCnpj("61365284000104");
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_cnpj_nulo() {
		emp1.setCnpj(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cnpj_em_branco() {
		emp1.setCnpj("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cnpj_composto_por_menos_de_14_digitos() {
		emp1.setCnpj("123456789");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cnpj_composto_por_mais_de_14_digitos() {
		emp1.setCnpj("123456789123456");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cnpj_composto_por_letras() {
		emp1.setCnpj("abcdefghijklmn");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cnpj_composto_por_caracteres_especiais() {
		emp1.setCnpj("!@#$.%&*()!@#$");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cnpj_que_contenha_qualquer_caractere_estranho_a_digitos() {
		emp1.setCnpj("61.365.284/0001-04");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cnpj_composto_por_digitos_iguais() {
		emp1.setCnpj("99999999999999");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cnpj_invalido() {
		emp1.setCnpj("48785214000105");
	}
	
	/*
	 * ÁREA DE ATUAÇÃO
	 * */
	@Test
	public void deve_definir_uma_nova_area_de_atuacao_para_a_empresa() {
		String areaAtuacao = "Indústria alimentícia";
		emp1.setAreaAtuacao(areaAtuacao);
		assertEquals(areaAtuacao, emp1.getAreaAtuacao());
	}
	
	@Test
	public void deve_aceitar_area_de_atuacao_apenas_com_letras() {
		emp1.setAreaAtuacao("Educação");
	}
	
	@Test
	public void deve_aceitar_area_de_atuacao_com_letras_e_espacos() {
		emp1.setAreaAtuacao("Setor alimentício");
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_area_de_atuacao_nula() {
		emp1.setAreaAtuacao(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_area_de_atuacao_em_branco() {
		emp1.setAreaAtuacao("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_area_de_atuacao_apenas_com_espaco() {
		emp1.setAreaAtuacao(" ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_area_de_atuacao_apenas_com_ponto() {
		emp1.setAreaAtuacao(".");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_area_de_atuacao_apenas_com_caracteres_especiais() {
		emp1.setAreaAtuacao("@#$$$$#%");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_area_de_atuacao_com_menos_de_4_caracteres() {
		emp1.setAreaAtuacao("abc");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_area_de_atuacao_com_mais_de_55_caracteres() {
		emp1.setAreaAtuacao("abcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghij");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_area_de_atuacao_com_numeros() {
		emp1.setAreaAtuacao("Indústria de fármacos 1");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_area_de_atuacao_com_caracteres_especiais() {
		emp1.setAreaAtuacao("Indústria @automotiva");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_area_de_atuacao_composta_unicamente_pelo_mesmo_caractere() {
		emp1.setAreaAtuacao("aaaaaaaaaaaaaaaaaa");
	}
	
	/*
	 * TELEFONES
	 * */
	@Test
	public void deve_indicar_que_a_lista_de_telefones_e_inicializada_na_construcao_do_objeto() {
		Empresa e1 = new Empresa();
		assertNotNull(e1.getTelefones());
	}
	
	@Test
	public void deve_realizar_cadastro_de_telefones() {
		Telefone tel = new Telefone(55, 11, 987654321);
		tel.setTipo("Celular");
		emp1.getTelefones().add(tel);
		assertEquals(tel, emp1.getTelefones().get(0));
	}
	
	@Test
	public void deve_redefinir_a_lista_de_telefones() {
		List<Telefone> telefones = new ArrayList<Telefone>();
		for(int i=0; i<10; i++) {
			Telefone tel = new Telefone(55, 11, 987654321+i);
			tel.setTipo("Celular");
			telefones.add(tel);
		}
		emp1.setTelefones(telefones);
		assertEquals(telefones, emp1.getTelefones());
	}

	@Test(expected = NullPointerException.class)
	public void nao_deve_redefinir_a_lista_de_telefones_por_uma_lista_nula() {
		List<Telefone> telefones = null;
		emp1.setTelefones(telefones);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_redefinir_a_lista_de_telefones_por_uma_lista_igual() {
		List<Telefone> telefones = new ArrayList<Telefone>();
		for(int i=0; i<10; i++) {
			Telefone tel = new Telefone(55, 11, 987654321+i);
			tel.setTipo("Celular");
			telefones.add(tel);
		}
		emp1.setTelefones(telefones);
		emp1.setTelefones(telefones);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_redefinir_a_lista_de_telefones_por_uma_lista_que_esteja_vazia() {
		List<Telefone> telefones = new ArrayList<Telefone>();
		emp1.setTelefones(telefones);
	}
	
	/*
	 * DEPARTAMENTOS
	 * */
	@Test
	public void deve_indicar_que_a_lista_de_departamentos_e_inicializada_na_construcao_do_objeto() {
		Empresa e1 = new Empresa();
		assertNotNull(e1.getDepartamentos());
	}
	
	@Test
	public void deve_realizar_cadastro_de_departamentos() {
		Departamento d = new Departamento();
		d.setNome("Recursos Humanos");
		d.setDescricao("Departamento que administra questões relativas aos colaboradores");
		emp1.getDepartamentos().add(d);
		assertEquals(d, emp1.getDepartamentos().get(0));
	}
	
	@Test
	public void deve_redefinir_a_lista_de_departamentos() {
		List<Departamento> departamentos = new ArrayList<Departamento>();
		for(int i=0; i<10; i++) {
			Departamento d = new Departamento();
			d.setDescricao("Departamento " + i+1);
			departamentos.add(d);
		}
		emp1.setDepartamentos(departamentos);
		assertEquals(departamentos, emp1.getDepartamentos());
	}

	@Test(expected = NullPointerException.class)
	public void nao_deve_redefinir_a_lista_de_departamentos_por_uma_lista_nula() {
		List<Departamento> departamentos = null;
		emp1.setDepartamentos(departamentos);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_redefinir_a_lista_de_departamentos_por_uma_lista_igual() {
		List<Departamento> departamentos = new ArrayList<Departamento>();
		for(int i=0; i<10; i++) {
			Departamento d = new Departamento();
			d.setDescricao("Departamento " + i+1);
			departamentos.add(d);
		}
		emp1.setDepartamentos(departamentos);
		emp1.setDepartamentos(departamentos);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_redefinir_a_lista_de_departamentos_por_uma_lista_que_esteja_vazia() {
		List<Departamento> departamentos = new ArrayList<Departamento>();
		emp1.setDepartamentos(departamentos);
	}
	
	/*
	 * ENDERECO
	 * */
	@Test
	public void deve_definir_endereco_para_o_objeto_empresa() {
		String logradouro = "Rua Piraju";
		String bairro = "Monte Belo";
		String cidade = "São Paulo";
		String uf = "SP";
		String pais = "Brasil";
		String cep = "08587789";
		
		Endereco endereco = new Endereco(logradouro, bairro, cidade, uf, pais, cep);
		emp1.setEndereco(endereco);
		assertEquals(endereco, emp1.getEndereco());
	}
	
	@Test
	public void deve_aceitar_endereco_valido() {
		String logradouro = "Rua Piraju";
		String bairro = "Monte Belo";
		String cidade = "São Paulo";
		String uf = "SP";
		String pais = "Brasil";
		String cep = "08587789";
		
		Endereco endereco = new Endereco(logradouro, bairro, cidade, uf, pais, cep);
		emp1.setEndereco(endereco);
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_endereco_nulo() {
		emp1.setEndereco(null);
	}
}