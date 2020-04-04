package br.com.contmatic.endereco;

import static br.com.contmatic.endereco.EstadosBrasileirosType.SP;
import static br.com.contmatic.util.Mensagens.MENSAGEM_BAIRRO_BLANK;
import static br.com.contmatic.util.Mensagens.MENSAGEM_BAIRRO_PATTERN;
import static br.com.contmatic.util.Mensagens.MENSAGEM_BAIRRO_TAMANHO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_CEP_BLANK;
import static br.com.contmatic.util.Mensagens.MENSAGEM_CEP_PATTERN;
import static br.com.contmatic.util.Mensagens.MENSAGEM_CEP_TAMANHO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_CIDADE_BLANK;
import static br.com.contmatic.util.Mensagens.MENSAGEM_CIDADE_PATTERN;
import static br.com.contmatic.util.Mensagens.MENSAGEM_CIDADE_TAMANHO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_LOGRADOURO_BLANK;
import static br.com.contmatic.util.Mensagens.MENSAGEM_LOGRADOURO_PATTERN;
import static br.com.contmatic.util.Mensagens.MENSAGEM_LOGRADOURO_TAMANHO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_NUMERO_MAX;
import static br.com.contmatic.util.Mensagens.MENSAGEM_NUMERO_MIN;
import static br.com.contmatic.util.Mensagens.MENSAGEM_PAIS_BLANK;
import static br.com.contmatic.util.Mensagens.MENSAGEM_PAIS_PATTERN;
import static br.com.contmatic.util.Mensagens.MENSAGEM_PAIS_TAMANHO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_TIPO_ENDERECO_NULO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_UF_NULA;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.endereco.EnderecoType;
import br.com.contmatic.endereco.EstadosBrasileirosType;
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
		}
		return erros;
	}

	@BeforeClass
	public static void setUpBeforeClass() {
		FixtureFactoryLoader.loadTemplates("br.com.contmatic.templates");
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
	public void deve_validar_objeto_do_fixture() {
		assertThat(getErros(end).size(), is(0));
	}

	@Test
	public void deve_indicar_que_o_metodo_toString_esta_sobrescrito_por_nao_conter_o_caractere_arroba() {
		assertThat(new Endereco().toString(), not(containsString("@")));
	}

	@Test
	public void deve_apontar_igualdade_entre_os_objetos_endereco() {
		end = Fixture.from(Endereco.class).gimme("mock");
		Endereco end2 = Fixture.from(Endereco.class).gimme("mock");
		assertThat(end, equalTo(end2));
	}

	// equals
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_endereco_usando_equals_sobrescrito_porque_cep_e_numero_sao_iguais() {
		Endereco end2 = Fixture.from(Endereco.class).gimme("valido");
		end2.setCep(end.getCep());
		end2.setNumero(end.getNumero());
		assertThat(end, equalTo(end2));
	}

	@Test
	public void deve_apontar_igualdade_entre_os_objetos_endereco_usando_equals_sobrescrito_porque_sao_o_mesmo_objeto() {
		Endereco end2 = end;
		assertThat(end, equalTo(end2));
	}

	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_endereco_usando_equals_sobrescrito_porque_o_numero_do_objeto2_e_diferente() {
		end = Fixture.from(Endereco.class).gimme("mock");
		Endereco end2 = Fixture.from(Endereco.class).gimme("mock");
		end2.setNumero(702);
		assertThat(end, not(equalTo(end2)));
	}

	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_endereco_usando_equals_sobrescrito_porque_o_cep_do_objeto2_e_diferente() {
		end = Fixture.from(Endereco.class).gimme("mock");
		Endereco end2 = Fixture.from(Endereco.class).gimme("mock");
		end2.setCep("08599874");
		assertThat(end, not(equalTo(end2)));
	}

	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_endereco_usando_equals_sobrescrito_porque_objeto2_e_nulo() {
		assertThat(end, not(equalTo(null)));
	}

	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_sao_de_classes_diferentes() {
		assertThat(end, not(equalTo(new Object())));
	}

	// hashcode
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_endereco_usando_hashcode_sobrescrito() {
		end = Fixture.from(Endereco.class).gimme("mock");
		Endereco end2 = Fixture.from(Endereco.class).gimme("mock");
		assertThat(end.hashCode(), equalTo(end2.hashCode()));
	}

	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_endereco_usando_hashcode_sobrescrito_porque_numero_do_objeto2_e_nulo() {
		end = Fixture.from(Endereco.class).gimme("mock");
		Endereco end2 = Fixture.from(Endereco.class).gimme("mock");
		end2.setNumero(null);
		assertThat(end.hashCode(), not(equalTo(end2.hashCode())));
	}

	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_endereco_usando_hashcode_sobrescrito() {
		end = Fixture.from(Endereco.class).gimme("mock");
		Endereco end2 = Fixture.from(Endereco.class).gimme("mock");
		end2.setCep("58900000");
		end2.setCidade("Cajazeiras");
		assertThat(end.hashCode(), not(equalTo(end2.hashCode())));
	}

	/*
	 * LOGRADOURO
	 */
	@Test
	public void deve_definir_um_novo_logradouro_para_o_endereco() {
		String logradouro = "Rua Uberaba";
		end.setLogradouro(logradouro);
		assertThat(end.getLogradouro(), equalTo(logradouro));
		assertThat(getErros(end), not(hasItem(MENSAGEM_LOGRADOURO_BLANK)));
		assertThat(getErros(end), not(hasItem(MENSAGEM_LOGRADOURO_PATTERN)));
		assertThat(getErros(end), not(hasItem(MENSAGEM_LOGRADOURO_TAMANHO)));
	}

	@Test
	public void deve_aceitar_logradouro_apenas_com_letras() {
		end.setLogradouro("Uberaba");
		assertThat(getErros(end), not(hasItem(MENSAGEM_LOGRADOURO_PATTERN)));
	}

	@Test
	public void deve_aceitar_logradouro_com_letras_e_espacos() {
		end.setLogradouro("Rua Piraju");
		assertThat(getErros(end), not(hasItem(MENSAGEM_LOGRADOURO_PATTERN)));
	}

	@Test
	public void deve_aceitar_logradouro_com_letras_espaco_e_hifen() {
		end.setLogradouro("Rua Mico-Leão-Dourado");
		assertThat(getErros(end), not(hasItem(MENSAGEM_LOGRADOURO_PATTERN)));

	}

	@Test
	public void nao_deve_aceitar_logradouro_nulo() {
		end.setLogradouro(null);
		assertThat(getErros(end), hasItem(MENSAGEM_LOGRADOURO_BLANK));
	}

	@Test
	public void nao_deve_aceitar_logradouro_em_branco() {
		end.setLogradouro("");
		assertThat(getErros(end), hasItem(MENSAGEM_LOGRADOURO_BLANK));
	}

	@Test
	public void nao_deve_aceitar_logradouro_apenas_com_espaco() {
		end.setLogradouro("        ");
		assertThat(getErros(end), hasItem(MENSAGEM_LOGRADOURO_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_logradouro_apenas_com_ponto() {
		end.setLogradouro("......");
		assertThat(getErros(end), hasItem(MENSAGEM_LOGRADOURO_PATTERN));
	}

	@Test
	public void deve_aceitar_logradouro_com_numero() {
		end.setLogradouro("Rua 20");
		assertThat(getErros(end), not(hasItem(MENSAGEM_LOGRADOURO_PATTERN)));
	}

	@Test
	public void nao_deve_aceitar_logradouro_sem_letras() {
		end.setLogradouro("           ");
		assertThat(getErros(end), hasItem(MENSAGEM_LOGRADOURO_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_logradouro_com_caracteres_especiais() {
		end.setLogradouro("Rua #");
		assertThat(getErros(end), hasItem(MENSAGEM_LOGRADOURO_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_logradouro_de_tamanho_menor_que_2_caracteres() {
		end.setLogradouro("X");
		assertThat(getErros(end), hasItem(MENSAGEM_LOGRADOURO_TAMANHO));
	}

	@Test
	public void nao_deve_aceitar_logradouro_de_tamanho_maior_que_100_caracteres() {
		StringBuilder sb = new StringBuilder("Z");
		for (int i = 0; i < 100; i++)
			sb.append("A");
		end.setLogradouro(sb.toString());
		assertThat(getErros(end), hasItem(MENSAGEM_LOGRADOURO_TAMANHO));
	}

	@Test
	public void nao_deve_aceitar_logradouro_que_nao_inicia_com_letra() {
		end.setLogradouro(".Rua Uberaba");
		assertThat(getErros(end), hasItem(MENSAGEM_LOGRADOURO_PATTERN));
	}

	/*
	 * NÚMERO
	 */
	@Test
	public void deve_definir_um_novo_numero_para_o_endereco() {
		Integer numero = 654;
		end.setNumero(numero);
		assertThat(end.getNumero(), equalTo(numero));
		assertThat(getErros(end), not(hasItem(MENSAGEM_NUMERO_MIN)));
		assertThat(getErros(end), not(hasItem(MENSAGEM_NUMERO_MAX)));
	}

	@Test
	public void deve_aceitar_numero_de_residencia_positivo() {
		end.setNumero(404);
		assertThat(getErros(end), not(hasItem(MENSAGEM_NUMERO_MIN)));
		assertThat(getErros(end), not(hasItem(MENSAGEM_NUMERO_MAX)));
	}

	@Test
	public void deve_aceitar_numero_nulo_para_indicar_edificio_sem_numero() {
		end.setNumero(null);
		assertThat(getErros(end).size(), is(0));
	}

	@Test
	public void nao_deve_aceitar_numero_de_residencia_negativo() {
		end.setNumero(-4545);
		assertThat(getErros(end), hasItem(MENSAGEM_NUMERO_MIN));
	}

	@Test
	public void nao_deve_aceitar_numero_acima_de_99999() {
		end.setNumero(100000);
		assertThat(getErros(end), hasItem(MENSAGEM_NUMERO_MAX));
	}

	/*
	 * BAIRRO
	 */
	@Test
	public void deve_definir_um_novo_bairro_para_o_endereco() {
		String bairro = "Jardim do Carmo";
		end.setBairro(bairro);
		assertThat(end.getBairro(), equalTo(bairro));
		assertThat(getErros(end), not(hasItem(MENSAGEM_BAIRRO_BLANK)));
		assertThat(getErros(end), not(hasItem(MENSAGEM_BAIRRO_PATTERN)));
		assertThat(getErros(end), not(hasItem(MENSAGEM_BAIRRO_TAMANHO)));
	}

	@Test
	public void deve_aceitar_bairro_com_letras_e_espacos() {
		end.setBairro("Jardim Miray");
		assertThat(getErros(end), not(hasItem(MENSAGEM_BAIRRO_PATTERN)));
	}

	@Test
	public void deve_aceitar_bairro_com_letras_espacos_e_ponto() {
		end.setBairro("Jd. Miray");
		assertThat(getErros(end), not(hasItem(MENSAGEM_BAIRRO_PATTERN)));
	}

	@Test
	public void deve_aceitar_bairro_com_hifen() {
		end.setBairro("Bairro Nove-Dez");
		assertThat(getErros(end), not(hasItem(MENSAGEM_BAIRRO_PATTERN)));
	}

	@Test
	public void nao_deve_aceitar_bairro_nulo() {
		end.setBairro(null);
		assertThat(getErros(end), hasItem(MENSAGEM_BAIRRO_BLANK));
	}

	@Test
	public void nao_deve_aceitar_bairro_em_branco() {
		end.setBairro("");
		assertThat(getErros(end), hasItem(MENSAGEM_BAIRRO_BLANK));
	}

	@Test
	public void nao_deve_aceitar_bairro_apenas_com_espaco() {
		end.setBairro("      ");
		assertThat(getErros(end), hasItem(MENSAGEM_BAIRRO_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_bairro_apenas_com_ponto() {
		end.setBairro(".......");
		assertThat(getErros(end), hasItem(MENSAGEM_BAIRRO_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_bairro_com_caracteres_especiais() {
		end.setBairro("Bairr#");
		assertThat(getErros(end), hasItem(MENSAGEM_BAIRRO_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_bairro_de_tamanho_menor_que_3_caracteres() {
		end.setBairro("X");
		assertThat(getErros(end), hasItem(MENSAGEM_BAIRRO_TAMANHO));
	}

	@Test
	public void nao_deve_aceitar_bairro_de_tamanho_maior_que_55_caracteres() {
		StringBuilder sb = new StringBuilder("Z");
		for (int i = 0; i < 55; i++)
			sb.append("A");
		end.setBairro(sb.toString());
		assertThat(getErros(end), hasItem(MENSAGEM_BAIRRO_TAMANHO));
	}

	/*
	 * CIDADE
	 */
	@Test
	public void deve_definir_uma_nova_cidade_para_o_endereco() {
		String cidade = "São Paulo";
		end.setCidade(cidade);
		assertThat(end.getCidade(), equalTo(cidade));
		assertThat(getErros(end), not(hasItem(MENSAGEM_CIDADE_BLANK)));
		assertThat(getErros(end), not(hasItem(MENSAGEM_CIDADE_PATTERN)));
		assertThat(getErros(end), not(hasItem(MENSAGEM_CIDADE_TAMANHO)));
	}

	@Test
	public void deve_aceitar_cidade_com_letras() {
		end.setCidade("Suzano");
		assertThat(getErros(end), not(hasItem(MENSAGEM_CIDADE_PATTERN)));
	}

	@Test
	public void deve_aceitar_cidade_com_letras_e_espacos() {
		end.setCidade("Mogi das Cruzes");
		assertThat(getErros(end), not(hasItem(MENSAGEM_CIDADE_PATTERN)));
	}

	@Test
	public void nao_deve_aceitar_cidade_nula() {
		end.setCidade(null);
		assertThat(getErros(end), hasItem(MENSAGEM_CIDADE_BLANK));
	}

	@Test
	public void nao_deve_aceitar_cidade_em_branco() {
		end.setCidade("");
		assertThat(getErros(end), hasItem(MENSAGEM_CIDADE_BLANK));
	}

	@Test
	public void nao_deve_aceitar_cidade_apenas_com_espaco() {
		end.setCidade("     ");
		assertThat(getErros(end), hasItem(MENSAGEM_CIDADE_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_cidade_apenas_com_ponto() {
		end.setCidade(".......");
		assertThat(getErros(end), hasItem(MENSAGEM_CIDADE_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_cidade_que_nao_comeca_com_letra() {
		end.setCidade(".São Paulo");
		assertThat(getErros(end), hasItem(MENSAGEM_CIDADE_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_cidade_com_caracteres_especiais() {
		end.setCidade("S@o Paulo");
		assertThat(getErros(end), hasItem(MENSAGEM_CIDADE_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_cidade_com_numeros() {
		end.setCidade("789456");
		assertThat(getErros(end), hasItem(MENSAGEM_CIDADE_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_cidade_de_tamanho_menor_que_3_caracteres() {
		end.setCidade("X");
		assertThat(getErros(end), hasItem(MENSAGEM_CIDADE_TAMANHO));
	}

	@Test
	public void nao_deve_aceitar_cidade_de_tamanho_maior_que_100_caracteres() {
		StringBuilder sb = new StringBuilder("Z");
		for (int i = 0; i < 100; i++)
			sb.append("A");
		end.setCidade(sb.toString());
		assertThat(getErros(end), hasItem(MENSAGEM_CIDADE_TAMANHO));
	}

	/*
	 * UF
	 */
	@Test
	public void deve_definir_uma_nova_uf_para_o_endereco() {
		EstadosBrasileirosType uf = SP;
		end.setUf(uf);
		assertThat(end.getUf(), equalTo(uf));
		assertThat(getErros(end), not(hasItem(MENSAGEM_UF_NULA)));
		assertThat(end.getUf().getNome(), equalTo(uf.getNome()));
	}

	@Test
	public void nao_deve_aceitar_uf_nula() {
		end.setUf(null);
		assertThat(getErros(end), hasItem(MENSAGEM_UF_NULA));
	}

	/*
	 * PAÍS
	 */
	@Test
	public void deve_definir_um_novo_pais_para_o_endereco() {
		String pais = "Brasil";
		end.setPais(pais);
		assertThat(end.getPais(), equalTo(pais));
		assertThat(getErros(end), not(hasItem(MENSAGEM_PAIS_BLANK)));
		assertThat(getErros(end), not(hasItem(MENSAGEM_PAIS_PATTERN)));
		assertThat(getErros(end), not(hasItem(MENSAGEM_PAIS_TAMANHO)));
	}

	@Test
	public void deve_aceitar_pais_com_letras_e_espacos() {
		end.setPais("África do Sul");
		assertThat(getErros(end), not(hasItem(MENSAGEM_PAIS_PATTERN)));
	}

	@Test
	public void deve_aceitar_pais_com_letras_e_hifen() {
		end.setPais("Guiné-Bissau");
		assertThat(getErros(end), not(hasItem(MENSAGEM_PAIS_PATTERN)));
	}

	@Test
	public void nao_deve_aceitar_pais_nula() {
		end.setPais(null);
		assertThat(getErros(end), hasItem(MENSAGEM_PAIS_BLANK));
	}

	@Test
	public void nao_deve_aceitar_pais_em_branco() {
		end.setPais("");
		assertThat(getErros(end), hasItem(MENSAGEM_PAIS_BLANK));
	}

	@Test
	public void nao_deve_aceitar_pais_apenas_com_espaco() {
		end.setPais("     ");
		assertThat(getErros(end), hasItem(MENSAGEM_PAIS_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_pais_apenas_com_ponto() {
		end.setPais("......");
		assertThat(getErros(end), hasItem(MENSAGEM_PAIS_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_pais_que_nao_comeca_com_letra() {
		end.setPais(" Alemanha");
		assertThat(getErros(end), hasItem(MENSAGEM_PAIS_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_pais_com_caracteres_especiais() {
		end.setPais("B!@@@###");
		assertThat(getErros(end), hasItem(MENSAGEM_PAIS_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_pais_com_numero() {
		end.setPais("República 1");
		assertThat(getErros(end), hasItem(MENSAGEM_PAIS_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_pais_de_tamanho_menor_que_3_caracteres() {
		end.setPais("Ab");
		assertThat(getErros(end), hasItem(MENSAGEM_PAIS_TAMANHO));
	}

	@Test
	public void nao_deve_aceitar_pais_de_tamanho_maior_que_100_caracteres() {
		StringBuilder sb = new StringBuilder("Z");
		for (int i = 0; i < 100; i++)
			sb.append("A");
		end.setPais(sb.toString());
		assertThat(getErros(end), hasItem(MENSAGEM_PAIS_TAMANHO));
	}

	/*
	 * CEP
	 */
	@Test
	public void deve_definir_um_novo_cep_para_a_pessoa() {
		String cep = "03314-030";
		end.setCep(cep);
		assertThat(end.getCep(), equalTo(cep));
		assertThat(getErros(end), not(hasItem(MENSAGEM_CEP_BLANK)));
		assertThat(getErros(end), not(hasItem(MENSAGEM_CEP_PATTERN)));
		assertThat(getErros(end), not(hasItem(MENSAGEM_CEP_TAMANHO)));
	}

	@Test
	public void deve_aceitar_cep_com_8_digitos() {
		end.setCep("03314-030");
		assertThat(end.getCep(), notNullValue());
		assertThat(getErros(end), not(hasItem(MENSAGEM_CEP_PATTERN)));
	}

	@Test
	public void nao_deve_aceitar_cep_nulo() {
		end.setCep(null);
		assertThat(getErros(end), hasItem(MENSAGEM_CEP_BLANK));
	}

	@Test
	public void nao_deve_aceitar_cep_em_branco() {
		end.setCep("");
		assertThat(getErros(end), hasItem(MENSAGEM_CEP_BLANK));
	}

	@Test
	public void nao_deve_aceitar_cep_com_letras() {
		end.setCep("abcdefgh");
		assertThat(getErros(end), hasItem(MENSAGEM_CEP_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_cep_com_caracteres_especiais() {
		end.setCep("@#$%&*##");
		assertThat(getErros(end), hasItem(MENSAGEM_CEP_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_cep_composto_por_menos_de_8_caracteres() {
		end.setCep("0331-403");
		assertThat(getErros(end), hasItem(MENSAGEM_CEP_TAMANHO));
	}

	@Test
	public void nao_deve_aceitar_cep_composto_por_mais_de_8_caracteres() {
		end.setCep("033140-305");
		assertThat(getErros(end), hasItem(MENSAGEM_CEP_TAMANHO));
	}

	/*
	 * TIPO
	 */
	@Test
	public void deve_redefinir_o_tipo_de_endereco_do_objeto() {
		EnderecoType tipo = EnderecoType.RUA;
		end.setTipoEndereco(tipo);
		assertThat(end.getTipoEndereco(), equalTo(tipo));
		assertThat(end.getTipoEndereco().getDescricao(), equalTo(tipo.getDescricao()));
		assertThat(getErros(end), not(hasItem(MENSAGEM_TIPO_ENDERECO_NULO)));
	}

	@Test
	public void nao_deve_aceitar_tipo_de_endereco_nulo() {
		end.setTipoEndereco(null);
		assertThat(getErros(end), hasItem(MENSAGEM_TIPO_ENDERECO_NULO));
	}
}
