package br.com.contmatic.empresa;

import static br.com.contmatic.constantes.Mensagens.MENSAGEM_CODIGO_PAIS_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_DDD_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_TELEFONE_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_TELEFONE_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_TELEFONE_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_TIPO_TELEFONE_NULO;
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
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.enums.EnumTipoTelefone;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

// TODO: Auto-generated Javadoc
/**
 * The Class TelefoneTest.
 */
public class TelefoneTest {
	
	/** The tel. */
	Telefone tel;
	
	/** The factory. */
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	
	/** The validator. */
	private Validator validator = factory.getValidator();

	/**
	 * Gets the erros.
	 *
	 * @param telefone the telefone
	 * @return the erros
	 */
	public Set<String> getErros(Telefone telefone) {
		Set<String> erros = new HashSet<>();
		for (ConstraintViolation<Telefone> constraintViolation : validator.validate(telefone)) {
			erros.add(constraintViolation.getMessageTemplate());
			System.out.println(constraintViolation.getMessageTemplate()); // Retorna o template, sem converter {min}
																			// para o valor mínimo
		}
		return erros;
	}
	
	/**
	 * Sets the up before class.
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("Iniciando testes da classe Telefone...");
		FixtureFactoryLoader.loadTemplates("br.com.contmatic.templates");
	}
	
	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		tel = Fixture.from(Telefone.class).gimme("valido");
	}
	
	/**
	 * Tear down.
	 */
	@After
	public void tearDown() {
		tel = null;
	}
	
	/**
	 * Tear down after class.
	 */
	@AfterClass
	public static void tearDownAfterClass() {
		System.out.println("Encerrando os testes da classe Telefone.");
	}
	
	/**
	 * Deve retornar true na validacao do objeto fixture.
	 */
	@Test
	public void deve_retornar_true_na_validacao_do_objeto_fixture() {
		assertThat(getErros(tel).isEmpty(), is(true));
	}
	
	/**
	 * Deve indicar que o metodo to string esta sobrescrito por nao conter o caractere arroba.
	 */
	@Test
	public void deve_indicar_que_o_metodo_toString_esta_sobrescrito_por_nao_conter_o_caractere_arroba() {
		assertThat(tel.toString(), not(containsString("@")));
	}
	
	/**
	 * Nao deve apontar igualdade entre os objetos telefone.
	 */
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_telefone() {
		Telefone tel2 = Fixture.from(Telefone.class).gimme("mock");
		tel.setNumero("983456712");
		assertThat(tel, not(equalTo(tel2)));
	}
	
	/**
	 * Deve apontar igualdade entre os objetos telefone.
	 */
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_telefone() {
		tel = Fixture.from(Telefone.class).gimme("mock");
		Telefone tel2 = Fixture.from(Telefone.class).gimme("mock");
		assertThat(tel, equalTo(tel2));
	}
	
	/**
	 * Deve apontar igualdade entre usando equals sobrescrito porque ambos possuem os mesmos valores para seus atributos.
	 */
	//equals
	@Test
	public void deve_apontar_igualdade_entre_usando_equals_sobrescrito_porque_ambos_possuem_os_mesmos_valores_para_seus_atributos() {
		tel = Fixture.from(Telefone.class).gimme("mock");
		Telefone tel2 = Fixture.from(Telefone.class).gimme("mock");
		assertThat(tel, equalTo(tel2));
	}
	
	/**
	 * Deve apontar igualdade usando equals sobrescrito porque sao o mesmo objeto.
	 */
	@Test
	public void deve_apontar_igualdade_usando_equals_sobrescrito_porque_sao_o_mesmo_objeto() {
		Telefone tel2 = tel;
		assertThat(tel, equalTo(tel2));
	}
	
	/**
	 * Nao deve apontar igualdade usando equals sobrescrito porque ddd do objeto 2 e diferente.
	 */
	@Test
	public void nao_deve_apontar_igualdade_usando_equals_sobrescrito_porque_ddd_do_objeto2_e_diferente() {
		tel = Fixture.from(Telefone.class).gimme("mock");
		Telefone tel2 = Fixture.from(Telefone.class).gimme("mock");
		tel2.setDdd(13);
		assertThat(tel, not(equalTo(tel2)));
	}
	
	/**
	 * Nao deve apontar igualdade usando equals sobrescrito porque numero do objeto 2 e diferente.
	 */
	@Test
	public void nao_deve_apontar_igualdade_usando_equals_sobrescrito_porque_numero_do_objeto2_e_diferente() {
		tel = Fixture.from(Telefone.class).gimme("mock");
		Telefone tel2 = Fixture.from(Telefone.class).gimme("mock");
		tel2.setNumero("985647801");
		assertThat(tel, not(equalTo(tel2)));
	}
	
	/**
	 * Nao deve apontar igualdade usando equals sobrescrito porque objeto 2 e nulo.
	 */
	@Test
	public void nao_deve_apontar_igualdade_usando_equals_sobrescrito_porque_objeto2_e_nulo() {
		assertThat(tel, not(equalTo(null)));
	}
	
	/**
	 * Nao deve apontar igualdade usando equals sobrescrito porque os objetos sao de classes diferentes.
	 */
	@Test
	public void nao_deve_apontar_igualdade_usando_equals_sobrescrito_porque_os_objetos_sao_de_classes_diferentes() {
		assertThat(tel, not(equalTo(new Object())));
	}
	
	/**
	 * Deve apontar igualdade usando hashcode sobrescrito porque ambos possuem os mesmos valores para seus atributos.
	 */
	//hashcode
	@Test
	public void deve_apontar_igualdade_usando_hashcode_sobrescrito_porque_ambos_possuem_os_mesmos_valores_para_seus_atributos()	{
		tel = Fixture.from(Telefone.class).gimme("mock");
		Telefone tel2 = Fixture.from(Telefone.class).gimme("mock");
		assertThat(tel.hashCode(), equalTo(tel2.hashCode()));
	}
	
	/**
	 * Nao deve apontar igualdade usando hashcode sobrescrito porque um atributo e diferente.
	 */
	@Test
	public void nao_deve_apontar_igualdade_usando_hashcode_sobrescrito_porque_um_atributo_e_diferente()	{
		tel = Fixture.from(Telefone.class).gimme("mock");
		Telefone tel2 = Fixture.from(Telefone.class).gimme("mock");
		tel2.setDdd(83);
		assertThat(tel.hashCode(), not(equalTo(tel2.hashCode())));
	}

	/**
	 * Deve definir um codigo de pais para o telefone.
	 */
	/*
	 * CÓDIGO DO PAÍS
	 * */
	@Test
	public void deve_definir_um_codigo_de_pais_para_o_telefone() {
		int codigoPais = 1;
		tel.setCodigoPais(codigoPais);
		assertThat(tel.getCodigoPais(), equalTo(codigoPais));
		assertThat(getErros(tel), not(hasItem(MENSAGEM_CODIGO_PAIS_TAMANHO)));
	}
	
	/**
	 * Nao deve aceitar codigo de pais negativo.
	 */
	@Test
	public void nao_deve_aceitar_codigo_de_pais_negativo() {
		tel.setCodigoPais(-70);
		assertThat(getErros(tel), hasItem(MENSAGEM_CODIGO_PAIS_TAMANHO));
	}
	
	/**
	 * Nao deve aceitar codigo de pais maior que 999.
	 */
	@Test
	public void nao_deve_aceitar_codigo_de_pais_maior_que_999() {
		tel.setCodigoPais(1000);
		assertThat(getErros(tel), hasItem(MENSAGEM_CODIGO_PAIS_TAMANHO));
	}
	
	/**
	 * Deve definir um ddd para o telefone.
	 */
	/*
	 * DDD
	 * */
	@Test
	public void deve_definir_um_ddd_para_o_telefone() {
		int ddd = 13;
		tel.setDdd(ddd);
		assertThat(tel.getDdd(), equalTo(ddd));
		assertThat(getErros(tel), not(hasItem(MENSAGEM_DDD_TAMANHO)));
	}
	
	/**
	 * Nao deve aceitar ddd menor que 11.
	 */
	@Test
	public void nao_deve_aceitar_ddd_menor_que_11() {
		tel.setDdd(1);
		assertThat(getErros(tel), hasItem(MENSAGEM_DDD_TAMANHO));
	}
	
	/**
	 * Nao deve aceitar ddd maior que 99.
	 */
	@Test
	public void nao_deve_aceitar_ddd_maior_que_99() {
		tel.setDdd(100);
		assertThat(getErros(tel), hasItem(MENSAGEM_DDD_TAMANHO));
	}
	
	/**
	 * Deve definir um numero para o telefone.
	 */
	/*
	 * NÚMERO
	 * */
	@Test
	public void deve_definir_um_numero_para_o_telefone() {
		String numero = "978541598";
		tel.setNumero(numero);
		assertThat(tel.getNumero(), equalTo(numero));
		assertThat(getErros(tel), not(hasItem(MENSAGEM_TELEFONE_BLANK)));
		assertThat(getErros(tel), not(hasItem(MENSAGEM_TELEFONE_PATTERN)));
		assertThat(getErros(tel), not(hasItem(MENSAGEM_TELEFONE_TAMANHO)));
	}
	
	/**
	 * Deve aceitar numero com 9 digitos.
	 */
	@Test
	public void deve_aceitar_numero_com_9_digitos() {
		tel.setNumero("789456123");
		assertThat(getErros(tel), not(hasItem(MENSAGEM_TELEFONE_PATTERN)));
	}
	
	/**
	 * Deve aceitar numero com 8 digitos.
	 */
	@Test
	public void deve_aceitar_numero_com_8_digitos() {
		tel.setNumero("78945612");
		assertThat(getErros(tel), not(hasItem(MENSAGEM_TELEFONE_PATTERN)));
	}
	
	/**
	 * Nao deve aceitar numero com mais de 9 digitos.
	 */
	@Test
	public void nao_deve_aceitar_numero_com_mais_de_9_digitos() {
		tel.setNumero("9999995474");
		assertThat(getErros(tel), hasItem(MENSAGEM_TELEFONE_PATTERN));
	}
	
	/**
	 * Nao deve aceitar numero com menos de 8 digitos.
	 */
	@Test
	public void nao_deve_aceitar_numero_com_menos_de_8_digitos() {
		tel.setNumero("99999");
		assertThat(getErros(tel), hasItem(MENSAGEM_TELEFONE_PATTERN));
	}
	
	/**
	 * Nao deve aceitar numero com letras.
	 */
	@Test
	public void nao_deve_aceitar_numero_com_letras() {
		tel.setNumero("76443jkhd");
		assertThat(getErros(tel), hasItem(MENSAGEM_TELEFONE_PATTERN));
	}
	
	/**
	 * Deve definir um tipo para o telefone.
	 */
	/*
	 * TIPO
	 * */
	@Test
	public void deve_definir_um_tipo_para_o_telefone() {
		EnumTipoTelefone tipo = EnumTipoTelefone.TELEFONE_FIXO;
		tel.setTipo(tipo);
		assertThat(tel.getTipo(), equalTo(tipo));
	}
	
	/**
	 * Nao deve aceitar tipo de telefone nulo.
	 */
	@Test
	public void nao_deve_aceitar_tipo_de_telefone_nulo() {
		tel.setTipo(null);
		assertThat(getErros(tel), hasItem(MENSAGEM_TIPO_TELEFONE_NULO));
	}
}
