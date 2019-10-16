package br.com.contmatic.prova01.empresa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.prova01.empresa.Telefone;

public class TelefoneTest {
	Telefone tel1;
	Telefone tel2;

	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("Iniciando testes da classe Telefone...");
	}
	
	@Before
	public void setUp() {
		int codigoPais = 55;
		int ddd = 11;
		long numero = 982654587;
		String tipo = "Celular"; 
		tel1 = new Telefone(codigoPais, ddd, numero);
		tel1.setTipo(tipo);
		tel2 = new Telefone();
	}
	
	@After
	public void tearDown() {
		tel1 = null;
		tel2 = null;
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		System.out.println("Encerrando os testes da classe Telefone.");
	}
	
	@Test
	public void deve_retornar_false_para_indicar_que_o_metodo_toString_esta_sobrescrito() {
		assertFalse(tel1.toString().contains("["));
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_telefone() {
		assertNotEquals(tel1, tel2);
	}
	
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_telefone() {
		tel2.setCodigoPais(tel1.getCodigoPais());
		tel2.setDdd(tel1.getDdd());
		tel2.setNumero(tel1.getNumero());
		tel2.setTipo(tel1.getTipo());
		assertEquals(tel1, tel2);
	}
	
	//equals
	@Test
	public void deve_apontar_igualdade_entre_usando_equals_sobrescrito_porque_ambos_possuem_os_mesmos_valores_para_seus_atributos() {
		tel2.setCodigoPais(tel1.getCodigoPais());
		tel2.setDdd(tel1.getDdd());
		tel2.setNumero(tel1.getNumero());
		tel2.setTipo(tel1.getTipo());
		assertTrue(tel1.equals(tel2));
	}
	
	@Test
	public void deve_apontar_igualdade_usando_equals_sobrescrito_porque_sao_o_mesmo_objeto() {
		tel2 = tel1;
		assertTrue(tel1.equals(tel2));
	}
	
	@Test
	public void nao_deve_apontar_igualdade_usando_equals_sobrescrito_porque_ddd_do_objeto2_e_diferente() {
		tel2.setCodigoPais(tel1.getCodigoPais());
		tel2.setDdd(13);
		tel2.setNumero(tel1.getNumero());
		tel2.setTipo(tel1.getTipo());
		assertFalse(tel1.equals(tel2));
	}
	
	@Test
	public void nao_deve_apontar_igualdade_usando_equals_sobrescrito_porque_numero_do_objeto2_e_diferente() {
		tel2.setCodigoPais(tel1.getCodigoPais());
		tel2.setDdd(tel1.getDdd());
		tel2.setNumero(985647801l);
		tel2.setTipo(tel1.getTipo());
		assertFalse(tel1.equals(tel2));
	}
	
	@Test
	public void nao_deve_apontar_igualdade_usando_equals_sobrescrito_porque_objeto2_e_nulo() {
		assertFalse(tel1.equals(null));
	}
	
	@Test
	public void nao_deve_apontar_igualdade_usando_equals_sobrescrito_porque_os_objetos_sao_de_classes_diferentes() {
		assertFalse(tel1.equals(new Object()));
	}
	
	//hashcode
	@Test
	public void deve_apontar_igualdade_usando_hashcode_sobrescrito_porque_ambos_possuem_os_mesmos_valores_para_seus_atributos()	{
		tel2.setCodigoPais(tel1.getCodigoPais());
		tel2.setDdd(tel1.getDdd());
		tel2.setNumero(tel1.getNumero());
		tel2.setTipo(tel1.getTipo());
		assertEquals(tel1.hashCode(), tel2.hashCode());
	}
	
	@Test
	public void nao_deve_apontar_igualdade_usando_hashcode_sobrescrito_porque_um_atributo_e_diferente()	{
		tel2.setCodigoPais(tel1.getCodigoPais());
		tel2.setDdd(83);
		tel2.setNumero(tel1.getNumero());
		tel2.setTipo(tel1.getTipo());
		assertNotEquals(tel1.hashCode(), tel2.hashCode());
	}

	/*
	 * CÓDIGO DO PAÍS
	 * */
	@Test
	public void deve_definir_um_codigo_de_pais_para_o_telefone() {
		int codigoPais = 1;
		tel1.setCodigoPais(codigoPais);
		assertEquals(codigoPais, tel1.getCodigoPais());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_codigo_de_pais_negativo() {
		tel1.setCodigoPais(-70);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_codigo_de_pais_maior_que_999() {
		tel1.setCodigoPais(1000);
	}
	
	/*
	 * DDD
	 * */
	@Test
	public void deve_definir_um_ddd_para_o_telefone() {
		int ddd = 13;
		tel1.setDdd(ddd);
		assertEquals(ddd, tel1.getDdd());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_ddd_menor_que_11() {
		tel1.setDdd(1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_ddd_maior_que_99() {
		tel1.setDdd(100);
	}
	
	/*
	 * NÚMERO
	 * */
	@Test
	public void deve_definir_um_numero_para_o_telefone() {
		long numero = 978541598;
		tel1.setNumero(numero);
		assertEquals(numero, tel1.getNumero());
	}
	
	public void deve_aceitar_numero_com_9_digitos() {
		tel1.setNumero(789456123l);
	}
	
	public void deve_aceitar_numero_com_8_digitos() {
		tel1.setNumero(78945612l);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_numero_com_mais_de_9_digitos() {
		tel1.setNumero(9999995474l);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_numero_com_menos_de_8_digitos() {
		tel1.setNumero(99999l);
	}
	
	/*
	 * TIPO
	 * */
	@Test
	public void deve_definir_um_tipo_para_o_telefone() {
		String tipo = "Celular";
		tel1.setTipo(tipo);
		assertEquals(tipo, tel1.getTipo());
	}
	
	@Test
	public void deve_aceitar_tipo_apenas_com_letras() {
		tel1.setTipo("Telefone");
	}
	
	@Test
	public void deve_aceitar_tipo_com_letras_e_espacos() {
		tel1.setTipo("Telefone fixo");
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_tipo_nulo() {
		tel1.setTipo(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_tipo_em_branco() {
		tel1.setTipo("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_tipo_apenas_com_espaco() {
		tel1.setTipo(" ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_tipo_apenas_com_ponto() {
		tel1.setTipo(".");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_tipo_com_numero() {
		tel1.setTipo("Telefone 1");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_tipo_sem_letras() {
		tel1.setTipo("           ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_tipo_com_caracteres_especiais() {
		tel1.setTipo("Telefone #");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_tipo_composto_unicamente_pelo_mesmo_caractere() {
		tel1.setTipo("AAAAAA");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_tipo_composto_unicamente_pelo_mesmo_caractere_independentemente_de_caixa() {
		tel1.setTipo("AAaAA");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_tipo_de_tamanho_menor_que_3_caracteres() {
		tel1.setTipo("XX");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_tipo_de_tamanho_maior_que_55_caracteres() {
		StringBuilder sb = new StringBuilder("Z");
		for(int i=0; i<55; i++)
			sb.append("A");
		tel1.setTipo(sb.toString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_tipo_com_menos_de_uma_letra_independentemente_da_quantidade_de_caracteres_da_string() {
		tel1.setTipo(" k ");
	}
	
	/*
	 * MÉTODOS
	 * */
	@Test(expected = NullPointerException.class)
	public void nao_deve_realizar_cadastro_em_lista_nula() {
		tel1.cadastrar(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_realizar_cadastro_de_telefone_repetido() {
//		Empresa emp = new Empresa();
//		List<Telefone> telefones = emp.getTelefones();
		List<Telefone> telefones = new ArrayList<Telefone>();
		tel1.cadastrar(telefones);
		tel1.cadastrar(telefones);
	}
	
	@Test
	public void deve_realizar_cadastro_de_telefone_ainda_nao_existente() {
		List<Telefone> telefones = new ArrayList<Telefone>();
		tel1.cadastrar(telefones);
		tel1 = new Telefone(55, 11, 987474751);
		tel1.cadastrar(telefones);
	}
}
