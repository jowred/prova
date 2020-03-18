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

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.enums.EnumTipoEndereco;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class EnderecoTest {
	
	private Endereco end;
	
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	
	private Validator validator = factory.getValidator();
	
	public Set<String> getErros(Endereco end) {
		Set<String> erros = new HashSet<>();
		for (ConstraintViolation<Endereco> constraintViolation : validator.validate(end)) {
			erros.add(constraintViolation.getMessageTemplate());
			System.out.println(constraintViolation.getMessageTemplate());
		}
		return erros;
	}
	
	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("Iniciando testes da classe Endereco...");
		FixtureFactoryLoader.loadTemplates("br.com.contmatic.templates");
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		System.out.println("Testes da classe Endereco finalizados.");
	}
	
	@Before
	public void setUp() {
		end = Fixture.from(Endereco.class).gimme("valido");
	}
	
	@After
	public void tearDown() {
		end = null;
	}
	
	@Test
	public void deve_indicar_que_o_metodo_toString_esta_sobrescrito_por_nao_conter_o_caractere_arroba() {
		assertThat(new Endereco().toString(), not(containsString("@")));
	}
	
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_endereco() {
		Endereco end2 = Fixture.from(Endereco.class).gimme("mock");
		assertEquals(end, end2);
	}
	
	//equals
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_endereco_usando_equals_sobrescrito_porque_cep_e_numero_sao_iguais() {
		Endereco end2 = Fixture.from(Endereco.class).gimme("mock");
		assertTrue(end.equals(end2));
	}
	
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_endereco_usando_equals_sobrescrito_porque_sao_o_mesmo_objeto() {
		Endereco end2 = Fixture.from(Endereco.class).gimme("mock");
		Endereco end = end2;
		assertTrue(end.equals(end2));
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_endereco_usando_equals_sobrescrito_porque_o_numero_do_objeto2_e_diferente() {
		Endereco end2 = Fixture.from(Endereco.class).gimme("mock");
		end2.setNumero(702);
		assertFalse(end.equals(end2));
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_endereco_usando_equals_sobrescrito_porque_o_cep_do_objeto2_e_diferente() {
		Endereco end2 = Fixture.from(Endereco.class).gimme("mock");
		end2.setCep("08599874");
		assertFalse(end.equals(end2));
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_endereco_usando_equals_sobrescrito_porque_objeto2_e_nulo() {
		Endereco end2 = Fixture.from(Endereco.class).gimme("mock");
		end2 = null;
		assertFalse(end.equals(end2));
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_sao_de_classes_diferentes() {
		assertFalse(end.equals(new Object()));
	}
	
	//hashcode
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_endereco_usando_hashcode_sobrescrito() {
		Endereco end2 = Fixture.from(Endereco.class).gimme("mock");
		assertTrue(end.hashCode() == end2.hashCode());
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_endereco_usando_hashcode_sobrescrito_porque_numero_do_objeto2_e_nulo() {
		Endereco end2 = Fixture.from(Endereco.class).gimme("mock");
		end2.setNumero(null);
		assertFalse(end.hashCode() == end2.hashCode());
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_endereco_usando_hashcode_sobrescrito() {
		Endereco end2 = Fixture.from(Endereco.class).gimme("mock");
		end2.setCep("58900000");
		end2.setCidade("Cajazeiras");
		//end2.setUf("PB");
		assertNotEquals(end.hashCode(), end2.hashCode());
	}
	
	/*
	 * LOGRADOURO
	 * */
	@Test
	public void deve_definir_um_novo_logradouro_para_o_endereco() {
		String logradouro = "Rua Uberaba";
		end.setLogradouro(logradouro);
		assertEquals(logradouro, end.getLogradouro());
	}
	
	@Test
	public void deve_aceitar_logradouro_apenas_com_letras() {
		end.setLogradouro("Uberaba");
	}
	
	@Test
	public void deve_aceitar_logradouro_com_letras_e_espacos() {
		end.setLogradouro("Rua Piraju");
	}
	
	@Test
	public void deve_aceitar_logradouro_com_letras_espaco_e_hifen() {
		end.setLogradouro("Rua Mico-Leão-Dourado");
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_logradouro_nulo() {
		end.setLogradouro(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_logradouro_em_branco() {
		end.setLogradouro("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_logradouro_apenas_com_espaco() {
		end.setLogradouro("        ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_logradouro_apenas_com_ponto() {
		end.setLogradouro("......");
	}
	
	@Test
	public void deve_aceitar_logradouro_com_numero() {
		end.setLogradouro("Rua 20");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_logradouro_sem_letras() {
		end.setLogradouro("           ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_logradouro_com_caracteres_especiais() {
		end.setLogradouro("Rua #");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_logradouro_composto_unicamente_pelo_mesmo_caractere() {
		end.setLogradouro("AAAAAA");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_logradouro_composto_unicamente_pelo_mesmo_caractere_independentemente_de_caixa() {
		end.setLogradouro("AAaAA");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_logradouro_de_tamanho_menor_que_2_caracteres() {
		end.setLogradouro("X");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_logradouro_de_tamanho_maior_que_100_caracteres() {
		StringBuilder sb = new StringBuilder("Z");
		for(int i=0; i<100; i++)
			sb.append("A");
		end.setLogradouro(sb.toString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_logradouro_com_menos_de_uma_letra_independentemente_da_quantidade_de_caracteres_da_string() {
		end.setLogradouro("k ..");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_logradouro_que_nao_inicia_com_letra() {
		end.setLogradouro(".Rua Uberaba");
	}
	
	/*
	 * NÚMERO
	 * */
	@Test
	public void deve_definir_um_novo_numero_para_o_endereco() {
		Integer numero = 654;
		end.setNumero(numero);
		assertEquals(numero, end.getNumero());
	}
	
	@Test
	public void deve_aceitar_numero_de_residencia_positivo() {
		end.setNumero(404);
	}
	
	@Test
	public void deve_aceitar_numero_nulo_para_indicar_edificio_sem_numero() {
		end.setNumero(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_numero_de_residencia_negativo() {
		end.setNumero(-4545);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_numero_acima_de_99999() {
		end.setNumero(100000);
	}
	
	/*
	 * BAIRRO
	 * */
	@Test
	public void deve_definir_um_novo_bairro_para_o_endereco() {
		String bairro = "Jardim do Carmo";
		end.setBairro(bairro);
		assertEquals(bairro, end.getBairro());
	}
	
	@Test
	public void deve_aceitar_bairro_com_letras_e_espacos() {
		end.setBairro("Jardim Miray");
	}
	
	@Test
	public void deve_aceitar_bairro_com_letras_espacos_e_ponto() {
		end.setBairro("Jd. Miray");
	}
	
	@Test
	public void deve_aceitar_bairro_com_numero() {
		end.setBairro("Bairro 13");
	}
	
	@Test
	public void deve_aceitar_bairro_com_hifen() {
		end.setBairro("Bairro Nove-Dez");
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_bairro_nulo() {
		end.setBairro(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_bairro_em_branco() {
		end.setBairro("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_bairro_apenas_com_espaco() {
		end.setBairro("      ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_bairro_apenas_com_ponto() {
		end.setBairro(".......");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_bairro_com_caracteres_especiais() {
		end.setBairro("Bairr#");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_bairro_composta_unicamente_pelo_mesmo_caractere() {
		end.setBairro("AAAAAA");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_bairro_composta_unicamente_pelo_mesmo_caractere_independentemente_de_caixa() {
		end.setBairro("aAaAa");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_bairro_de_tamanho_menor_que_3_caracteres() {
		end.setBairro("X");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_bairro_de_tamanho_maior_que_55_caracteres() {
		StringBuilder sb = new StringBuilder("Z");
		for(int i=0; i<55; i++)
			sb.append("A");
		end.setBairro(sb.toString());
	}
	
	/*
	 * CIDADE
	 * */
	@Test
	public void deve_definir_uma_nova_cidade_para_o_endereco() {
		String cidade = "São Paulo";
		end.setCidade(cidade);
		assertEquals(cidade, end.getCidade());
	}
	
	@Test
	public void deve_aceitar_cidade_com_letras() {
		end.setCidade("Suzano");
	}
	
	@Test
	public void deve_aceitar_cidade_com_letras_e_espacos() {
		end.setCidade("Mogi das Cruzes");
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_cidade_nula() {
		end.setCidade(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cidade_em_branco() {
		end.setCidade("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cidade_apenas_com_espaco() {
		end.setCidade("     ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cidade_apenas_com_ponto() {
		end.setCidade(".......");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cidade_que_nao_comeca_com_letra() {
		end.setCidade(".São Paulo");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cidade_com_caracteres_especiais() {
		end.setCidade("S@o Paulo");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cidade_com_numeros() {
		end.setCidade("789456");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cidade_composta_unicamente_pelo_mesmo_caractere() {
		end.setCidade("AAAAAA");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cidade_composta_unicamente_pelo_mesmo_caractere_independentemente_de_caixa() {
		end.setCidade("aAaAa");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cidade_de_tamanho_menor_que_3_caracteres() {
		end.setCidade("X");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cidade_de_tamanho_maior_que_100_caracteres() {
		StringBuilder sb = new StringBuilder("Z");
		for(int i=0; i<100; i++)
			sb.append("A");
		end.setCidade(sb.toString());
	}
	
	/*
	 * UF
	 * */
	@Test
	public void deve_definir_uma_nova_uf_para_o_endereco() {
		String uf = "SP";
		//end1.setUf(uf);
		assertEquals(uf, end.getUf());
	}
	
	@Test
	public void deve_retornar_true_para_indicar_que_a_sigla_da_uf_foi_armazenada_em_caixa_alta() {
		String uf = "pr";
		//end1.setUf(uf);
		assertEquals(uf.toUpperCase(), end.getUf());
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_uf_nula() {
		end.setUf(null);
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
		end.setPais(pais);
		assertEquals(pais, end.getPais());
	}
	
	@Test
	public void deve_aceitar_pais_com_letras_e_espacos() {
		end.setPais("África do Sul");
	}
	
	@Test
	public void deve_aceitar_pais_com_letras_e_hifen() {
		end.setPais("Guiné-Bissau");
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_pais_nula() {
		end.setPais(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_pais_em_branco() {
		end.setPais("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_pais_apenas_com_espaco() {
		end.setPais("     ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_pais_apenas_com_ponto() {
		end.setPais("......");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_pais_que_nao_comeca_com_letra() {
		end.setPais(" Alemanha");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_pais_com_caracteres_especiais() {
		end.setPais("B!@@@###");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_pais_com_numero() {
		end.setPais("República 1");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_pais_composta_unicamente_pelo_mesmo_caractere() {
		end.setPais("AAAAAA");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_pais_composta_unicamente_pelo_mesmo_caractere_independentemente_de_caixa() {
		end.setPais("aAaAa");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_pais_de_tamanho_menor_que_3_caracteres() {
		end.setPais("Ab");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_pais_de_tamanho_maior_que_100_caracteres() {
		StringBuilder sb = new StringBuilder("Z");
		for(int i=0; i<100; i++)
			sb.append("A");
		end.setPais(sb.toString());
	}
	
	/*
	 * CEP
	 * */
	@Test
	public void deve_definir_um_novo_cep_para_a_pessoa() {
		String cep = "03314030";
		end.setCep(cep);
		assertEquals(cep, end.getCep());
	}
	
	@Test
	public void deve_aceitar_cep_com_8_digitos() {//RG em MG
		end.setCep("03314030");
		assertThat(end.getCep(), notNullValue());
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_cep_nulo() {
		end.setCep(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cep_em_branco() {
		end.setCep("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cep_com_letras() {
		end.setCep("abcdefgh");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cep_com_caracteres_especiais() {
		end.setCep("@#$%&*##");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cep_que_contenha_qualquer_caractere_estranho_a_digitos() {
		end.setCep("03314-030");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cep_composto_por_menos_de_8_digitos() {
		end.setCep("0331403");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_cep_composto_por_mais_de_8_digitos() {
		end.setCep("033140305");
	}
	
	/*
	 * TIPO
	 * */
	@Test
	public void deve_redefinir_o_tipo_de_endereco_do_objeto() {
		EnumTipoEndereco tipo = EnumTipoEndereco.RUA;
		end.setTipoEndereco(tipo);
		assertThat(end.getTipoEndereco(), equalTo(tipo));
	}
}
