package br.com.contmatic.empresa;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.enums.EnumTipoEndereco;

public class EnderecoTest {
	
	private Endereco end1, end2;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("Iniciando testes da classe Endereco...");
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		System.out.println("Testes da classe Endereco finalizados.");
	}
	
	@Before
	public void setUp() {
		String logradouro = "Rua Piraju";
		String bairro = "Monte Belo";
		String cidade = "São Paulo";
		String uf = "SP";
		String pais = "Brasil";
		String cep = "08587789";
		EnumTipoEndereco tipo = EnumTipoEndereco.RUA;
		
		//end1 = new Endereco(logradouro, bairro, cidade, uf, pais, cep);
		//end2 = new Endereco(logradouro, bairro, cidade, uf, pais, cep);
		end1.setNumero(901);
		end1.setTipoEndereco(tipo);
		end2.setTipoEndereco(tipo);
	}
	
	@After
	public void tearDown() {
		end1 = null;
	}
	
	@Test
	public void deve_indicar_que_o_metodo_toString_esta_sobrescrito_por_nao_conter_o_caractere_arroba() {
		assertThat(new Endereco().toString(), not(containsString("@")));
	}
	
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_endereco() {
		assertEquals(end1, end2);
	}
	
	//equals
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_endereco_usando_equals_sobrescrito_porque_cep_e_numero_sao_iguais() {
		assertTrue(end1.equals(end2));
	}
	
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_endereco_usando_equals_sobrescrito_porque_sao_o_mesmo_objeto() {
		Endereco end = end1;
		assertTrue(end1.equals(end));
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_endereco_usando_equals_sobrescrito_porque_o_numero_do_objeto2_e_diferente() {
		end2.setNumero(702);
		assertFalse(end1.equals(end2));
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_endereco_usando_equals_sobrescrito_porque_o_cep_do_objeto2_e_diferente() {
		end2.setCep("08599874");
		assertFalse(end1.equals(end2));
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_endereco_usando_equals_sobrescrito_porque_objeto2_e_nulo() {
		end2 = null;
		assertFalse(end1.equals(end2));
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_sao_de_classes_diferentes() {
		assertFalse(end1.equals(new Object()));
	}
	
	//hashcode
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_endereco_usando_hashcode_sobrescrito() {
		assertTrue(end1.hashCode() == end2.hashCode());
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_endereco_usando_hashcode_sobrescrito_porque_numero_do_objeto2_e_nulo() {
		end2.setNumero(null);
		assertFalse(end1.hashCode() == end2.hashCode());
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_endereco_usando_hashcode_sobrescrito() {
		end2.setCep("58900000");
		end2.setCidade("Cajazeiras");
		//end2.setUf("PB");
		assertNotEquals(end1.hashCode(), end2.hashCode());
	}
	
	/*
	 * LOGRADOURO
	 * */
	@Test
	public void deve_definir_um_novo_logradouro_para_o_endereco() {
		String logradouro = "Rua Uberaba";
		end1.setLogradouro(logradouro);
		assertEquals(logradouro, end1.getLogradouro());
	}
	
	@Test
	public void deve_aceitar_logradouro_apenas_com_letras() {
		end1.setLogradouro("Uberaba");
	}
	
	@Test
	public void deve_aceitar_logradouro_com_letras_e_espacos() {
		end1.setLogradouro("Rua Piraju");
	}
	
	@Test
	public void deve_aceitar_logradouro_com_letras_espaco_e_hifen() {
		end1.setLogradouro("Rua Mico-Leão-Dourado");
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_logradouro_nulo() {
		end1.setLogradouro(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_logradouro_em_branco() {
		end1.setLogradouro("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_logradouro_apenas_com_espaco() {
		end1.setLogradouro("        ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_logradouro_apenas_com_ponto() {
		end1.setLogradouro("......");
	}
	
	@Test
	public void deve_aceitar_logradouro_com_numero() {
		end1.setLogradouro("Rua 20");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_logradouro_sem_letras() {
		end1.setLogradouro("           ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_logradouro_com_caracteres_especiais() {
		end1.setLogradouro("Rua #");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_logradouro_composto_unicamente_pelo_mesmo_caractere() {
		end1.setLogradouro("AAAAAA");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_logradouro_composto_unicamente_pelo_mesmo_caractere_independentemente_de_caixa() {
		end1.setLogradouro("AAaAA");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_logradouro_de_tamanho_menor_que_2_caracteres() {
		end1.setLogradouro("X");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_logradouro_de_tamanho_maior_que_100_caracteres() {
		StringBuilder sb = new StringBuilder("Z");
		for(int i=0; i<100; i++)
			sb.append("A");
		end1.setLogradouro(sb.toString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_logradouro_com_menos_de_uma_letra_independentemente_da_quantidade_de_caracteres_da_string() {
		end1.setLogradouro("k ..");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_logradouro_que_nao_inicia_com_letra() {
		end1.setLogradouro(".Rua Uberaba");
	}
	
	/*
	 * NÚMERO
	 * */
	@Test
	public void deve_definir_um_novo_numero_para_o_endereco() {
		Integer numero = 654;
		end1.setNumero(numero);
		assertEquals(numero, end1.getNumero());
	}
	
	@Test
	public void deve_aceitar_numero_de_residencia_positivo() {
		end1.setNumero(404);
	}
	
	@Test
	public void deve_aceitar_numero_nulo_para_indicar_edificio_sem_numero() {
		end1.setNumero(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_numero_de_residencia_negativo() {
		end1.setNumero(-4545);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_numero_acima_de_99999() {
		end1.setNumero(100000);
	}
	
	/*
	 * BAIRRO
	 * */
	@Test
	public void deve_definir_um_novo_bairro_para_o_endereco() {
		String bairro = "Jardim do Carmo";
		end1.setBairro(bairro);
		assertEquals(bairro, end1.getBairro());
	}
	
	@Test
	public void deve_aceitar_bairro_com_letras_e_espacos() {
		end1.setBairro("Jardim Miray");
	}
	
	@Test
	public void deve_aceitar_bairro_com_letras_espacos_e_ponto() {
		end1.setBairro("Jd. Miray");
	}
	
	@Test
	public void deve_aceitar_bairro_com_numero() {
		end1.setBairro("Bairro 13");
	}
	
	@Test
	public void deve_aceitar_bairro_com_hifen() {
		end1.setBairro("Bairro Nove-Dez");
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_bairro_nulo() {
		end1.setBairro(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_bairro_em_branco() {
		end1.setBairro("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_bairro_apenas_com_espaco() {
		end1.setBairro("      ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_bairro_apenas_com_ponto() {
		end1.setBairro(".......");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_bairro_com_caracteres_especiais() {
		end1.setBairro("Bairr#");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_bairro_composta_unicamente_pelo_mesmo_caractere() {
		end1.setBairro("AAAAAA");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_bairro_composta_unicamente_pelo_mesmo_caractere_independentemente_de_caixa() {
		end1.setBairro("aAaAa");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_bairro_de_tamanho_menor_que_3_caracteres() {
		end1.setBairro("X");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_bairro_de_tamanho_maior_que_55_caracteres() {
		StringBuilder sb = new StringBuilder("Z");
		for(int i=0; i<55; i++)
			sb.append("A");
		end1.setBairro(sb.toString());
	}
	
	/*
	 * CIDADE
	 * */
	@Test
	public void deve_definir_uma_nova_cidade_para_o_endereco() {
		String cidade = "São Paulo";
		end1.setCidade(cidade);
		assertEquals(cidade, end1.getCidade());
	}
	
	@Test
	public void deve_aceitar_cidade_com_letras() {
		end1.setCidade("Suzano");
	}
	
	@Test
	public void deve_aceitar_cidade_com_letras_e_espacos() {
		end1.setCidade("Mogi das Cruzes");
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_cidade_nula() {
		end1.setCidade(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cidade_em_branco() {
		end1.setCidade("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cidade_apenas_com_espaco() {
		end1.setCidade("     ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cidade_apenas_com_ponto() {
		end1.setCidade(".......");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cidade_que_nao_comeca_com_letra() {
		end1.setCidade(".São Paulo");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cidade_com_caracteres_especiais() {
		end1.setCidade("S@o Paulo");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cidade_com_numeros() {
		end1.setCidade("789456");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cidade_composta_unicamente_pelo_mesmo_caractere() {
		end1.setCidade("AAAAAA");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cidade_composta_unicamente_pelo_mesmo_caractere_independentemente_de_caixa() {
		end1.setCidade("aAaAa");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cidade_de_tamanho_menor_que_3_caracteres() {
		end1.setCidade("X");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cidade_de_tamanho_maior_que_100_caracteres() {
		StringBuilder sb = new StringBuilder("Z");
		for(int i=0; i<100; i++)
			sb.append("A");
		end1.setCidade(sb.toString());
	}
	
	/*
	 * UF
	 * */
	@Test
	public void deve_definir_uma_nova_uf_para_o_endereco() {
		String uf = "SP";
		//end1.setUf(uf);
		assertEquals(uf, end1.getUf());
	}
	
	@Test
	public void deve_retornar_true_para_indicar_que_a_sigla_da_uf_foi_armazenada_em_caixa_alta() {
		String uf = "pr";
		//end1.setUf(uf);
		assertEquals(uf.toUpperCase(), end1.getUf());
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_uf_nula() {
		end1.setUf(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_uf_em_branco() {
		//end1.setUf("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_uf_apenas_com_espaco() {
		//end1.setUf("  ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_uf_apenas_com_ponto() {
		//end1.setUf("..");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_uf_com_caracteres_especiais() {
		//end1.setUf("@#");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_uf_com_numeros() {
		//end1.setUf("S8");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_uf_de_tamanho_menor_que_2_caracteres() {
		//end1.setUf("X");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_uf_de_tamanho_maior_que_2_caracteres() {
		//end1.setUf("ABC");
	}
	
	/*
	 * PAÍS
	 * */
	@Test
	public void deve_definir_um_novo_pais_para_o_endereco() {
		String pais = "Brasil";
		end1.setPais(pais);
		assertEquals(pais, end1.getPais());
	}
	
	@Test
	public void deve_aceitar_pais_com_letras_e_espacos() {
		end1.setPais("África do Sul");
	}
	
	@Test
	public void deve_aceitar_pais_com_letras_e_hifen() {
		end1.setPais("Guiné-Bissau");
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_pais_nula() {
		end1.setPais(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_pais_em_branco() {
		end1.setPais("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_pais_apenas_com_espaco() {
		end1.setPais("     ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_pais_apenas_com_ponto() {
		end1.setPais("......");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_pais_que_nao_comeca_com_letra() {
		end1.setPais(" Alemanha");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_pais_com_caracteres_especiais() {
		end1.setPais("B!@@@###");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_pais_com_numero() {
		end1.setPais("República 1");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_pais_composta_unicamente_pelo_mesmo_caractere() {
		end1.setPais("AAAAAA");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_pais_composta_unicamente_pelo_mesmo_caractere_independentemente_de_caixa() {
		end1.setPais("aAaAa");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_pais_de_tamanho_menor_que_3_caracteres() {
		end1.setPais("Ab");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_pais_de_tamanho_maior_que_100_caracteres() {
		StringBuilder sb = new StringBuilder("Z");
		for(int i=0; i<100; i++)
			sb.append("A");
		end1.setPais(sb.toString());
	}
	
	/*
	 * CEP
	 * */
	@Test
	public void deve_definir_um_novo_cep_para_a_pessoa() {
		String cep = "03314030";
		end1.setCep(cep);
		assertEquals(cep, end1.getCep());
	}
	
	@Test
	public void deve_aceitar_cep_com_8_digitos() {//RG em MG
		end1.setCep("03314030");
		assertThat(end1.getCep(), notNullValue());
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_cep_nulo() {
		end1.setCep(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cep_em_branco() {
		end1.setCep("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cep_com_letras() {
		end1.setCep("abcdefgh");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cep_com_caracteres_especiais() {
		end1.setCep("@#$%&*##");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cep_que_contenha_qualquer_caractere_estranho_a_digitos() {
		end1.setCep("03314-030");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cep_composto_por_menos_de_8_digitos() {
		end1.setCep("0331403");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cep_composto_por_mais_de_8_digitos() {
		end1.setCep("033140305");
	}
	
	/*
	 * TIPO
	 * */
	@Test
	public void deve_redefinir_o_tipo_de_endereco_do_objeto() {
		EnumTipoEndereco tipo = EnumTipoEndereco.RUA;
		end1.setTipoEndereco(tipo);
		assertThat(end1.getTipoEndereco(), equalTo(tipo));
	}
}
