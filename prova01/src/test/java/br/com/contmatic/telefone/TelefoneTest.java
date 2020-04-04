package br.com.contmatic.telefone;

import static br.com.contmatic.util.Mensagens.MENSAGEM_CODIGO_PAIS_TAMANHO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_DDD_TAMANHO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_TELEFONE_BLANK;
import static br.com.contmatic.util.Mensagens.MENSAGEM_TELEFONE_PATTERN;
import static br.com.contmatic.util.Mensagens.MENSAGEM_TELEFONE_TAMANHO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_TIPO_TELEFONE_NULO;
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

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.telefone.TelefoneType;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class TelefoneTest {

	private Telefone tel;

	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

	private Validator validator = factory.getValidator();

	public Set<String> getErros(Telefone telefone) {
		Set<String> erros = new HashSet<>();
		for (ConstraintViolation<Telefone> constraintViolation : validator.validate(telefone)) {
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
		tel = Fixture.from(Telefone.class).gimme("valido");
	}

	@After
	public void tearDown() {
		tel = null;
	}

	@Test
	public void deve_retornar_true_na_validacao_do_objeto_fixture() {
		assertThat(getErros(tel).isEmpty(), is(true));
	}

	@Test
	public void deve_indicar_que_o_metodo_toString_esta_sobrescrito_por_nao_conter_o_caractere_arroba() {
		assertThat(tel.toString(), not(containsString("@")));
	}

	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_telefone() {
		Telefone tel2 = Fixture.from(Telefone.class).gimme("mock");
		tel.setNumero("983456712");
		assertThat(tel, not(equalTo(tel2)));
	}

	@Test
	public void deve_apontar_igualdade_entre_os_objetos_telefone() {
		tel = Fixture.from(Telefone.class).gimme("mock");
		Telefone tel2 = Fixture.from(Telefone.class).gimme("mock");
		assertThat(tel, equalTo(tel2));
	}

	// equals
	@Test
	public void deve_apontar_igualdade_entre_usando_equals_sobrescrito_porque_ambos_possuem_os_mesmos_valores_para_seus_atributos() {
		tel = Fixture.from(Telefone.class).gimme("mock");
		Telefone tel2 = Fixture.from(Telefone.class).gimme("mock");
		assertThat(tel, equalTo(tel2));
	}

	@Test
	public void deve_apontar_igualdade_usando_equals_sobrescrito_porque_sao_o_mesmo_objeto() {
		Telefone tel2 = tel;
		assertThat(tel, equalTo(tel2));
	}

	@Test
	public void nao_deve_apontar_igualdade_usando_equals_sobrescrito_porque_ddd_do_objeto2_e_diferente() {
		tel = Fixture.from(Telefone.class).gimme("mock");
		Telefone tel2 = Fixture.from(Telefone.class).gimme("mock");
		tel2.setDdd(13);
		assertThat(tel, not(equalTo(tel2)));
	}

	@Test
	public void nao_deve_apontar_igualdade_usando_equals_sobrescrito_porque_numero_do_objeto2_e_diferente() {
		tel = Fixture.from(Telefone.class).gimme("mock");
		Telefone tel2 = Fixture.from(Telefone.class).gimme("mock");
		tel2.setNumero("985647801");
		assertThat(tel, not(equalTo(tel2)));
	}

	@Test
	public void nao_deve_apontar_igualdade_usando_equals_sobrescrito_porque_objeto2_e_nulo() {
		assertThat(tel, not(equalTo(null)));
	}

	@Test
	public void nao_deve_apontar_igualdade_usando_equals_sobrescrito_porque_os_objetos_sao_de_classes_diferentes() {
		assertThat(tel, not(equalTo(new Object())));
	}

	// hashcode
	@Test
	public void deve_apontar_igualdade_usando_hashcode_sobrescrito_porque_ambos_possuem_os_mesmos_valores_para_seus_atributos() {
		tel = Fixture.from(Telefone.class).gimme("mock");
		Telefone tel2 = Fixture.from(Telefone.class).gimme("mock");
		assertThat(tel.hashCode(), equalTo(tel2.hashCode()));
	}

	@Test
	public void nao_deve_apontar_igualdade_usando_hashcode_sobrescrito_porque_um_atributo_e_diferente() {
		tel = Fixture.from(Telefone.class).gimme("mock");
		Telefone tel2 = Fixture.from(Telefone.class).gimme("mock");
		tel2.setDdd(83);
		assertThat(tel.hashCode(), not(equalTo(tel2.hashCode())));
	}

	/*
	 * CÓDIGO DO PAÍS
	 */
	@Test
	public void deve_definir_um_codigo_de_pais_para_o_telefone() {
		int codigoPais = 1;
		tel.setCodigoPais(codigoPais);
		assertThat(tel.getCodigoPais(), equalTo(codigoPais));
		assertThat(getErros(tel), not(hasItem(MENSAGEM_CODIGO_PAIS_TAMANHO)));
	}

	@Test
	public void nao_deve_aceitar_codigo_de_pais_negativo() {
		tel.setCodigoPais(-70);
		assertThat(getErros(tel), hasItem(MENSAGEM_CODIGO_PAIS_TAMANHO));
	}

	@Test
	public void nao_deve_aceitar_codigo_de_pais_maior_que_999() {
		tel.setCodigoPais(1000);
		assertThat(getErros(tel), hasItem(MENSAGEM_CODIGO_PAIS_TAMANHO));
	}

	/*
	 * DDD
	 */
	@Test
	public void deve_definir_um_ddd_para_o_telefone() {
		int ddd = 13;
		tel.setDdd(ddd);
		assertThat(tel.getDdd(), equalTo(ddd));
		assertThat(getErros(tel), not(hasItem(MENSAGEM_DDD_TAMANHO)));
	}

	@Test
	public void nao_deve_aceitar_ddd_menor_que_11() {
		tel.setDdd(1);
		assertThat(getErros(tel), hasItem(MENSAGEM_DDD_TAMANHO));
	}

	@Test
	public void nao_deve_aceitar_ddd_maior_que_99() {
		tel.setDdd(100);
		assertThat(getErros(tel), hasItem(MENSAGEM_DDD_TAMANHO));
	}

	/*
	 * NÚMERO
	 */
	@Test
	public void deve_definir_um_numero_para_o_telefone() {
		String numero = "978541598";
		tel.setNumero(numero);
		assertThat(tel.getNumero(), equalTo(numero));
		assertThat(getErros(tel), not(hasItem(MENSAGEM_TELEFONE_BLANK)));
		assertThat(getErros(tel), not(hasItem(MENSAGEM_TELEFONE_PATTERN)));
		assertThat(getErros(tel), not(hasItem(MENSAGEM_TELEFONE_TAMANHO)));
	}

	@Test
	public void deve_aceitar_numero_com_9_digitos() {
		tel.setNumero("789456123");
		assertThat(getErros(tel), not(hasItem(MENSAGEM_TELEFONE_PATTERN)));
	}

	@Test
	public void deve_aceitar_numero_com_8_digitos() {
		tel.setNumero("78945612");
		assertThat(getErros(tel), not(hasItem(MENSAGEM_TELEFONE_PATTERN)));
	}

	@Test
	public void nao_deve_aceitar_numero_com_mais_de_9_digitos() {
		tel.setNumero("9999995474");
		assertThat(getErros(tel), hasItem(MENSAGEM_TELEFONE_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_numero_com_menos_de_8_digitos() {
		tel.setNumero("99999");
		assertThat(getErros(tel), hasItem(MENSAGEM_TELEFONE_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_numero_com_letras() {
		tel.setNumero("76443jkhd");
		assertThat(getErros(tel), hasItem(MENSAGEM_TELEFONE_PATTERN));
	}

	/*
	 * TIPO
	 */
	@Test
	public void deve_definir_um_tipo_para_o_telefone() {
		TelefoneType tipo = TelefoneType.TELEFONE_FIXO;
		tel.setTipo(tipo);
		assertThat(tel.getTipo(), equalTo(tipo));
		assertThat(tel.getTipo().getDescricao(), equalTo(tipo.getDescricao()));
		assertThat(tel.getTipo().getTamanho(), equalTo(tipo.getTamanho()));
	}

	@Test
	public void nao_deve_aceitar_tipo_de_telefone_nulo() {
		tel.setTipo(null);
		assertThat(getErros(tel), hasItem(MENSAGEM_TIPO_TELEFONE_NULO));
	}
}
