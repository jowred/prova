package br.com.contmatic.empresa;

import static br.com.contmatic.endereco.EstadosBrasileirosType.SP;
import static br.com.contmatic.util.Mensagens.MENSAGEM_AREA_ATUACAO_BLANK;
import static br.com.contmatic.util.Mensagens.MENSAGEM_AREA_ATUACAO_PATTERN;
import static br.com.contmatic.util.Mensagens.MENSAGEM_AREA_ATUACAO_TAMANHO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_CNPJ_BLANK;
import static br.com.contmatic.util.Mensagens.MENSAGEM_CNPJ_INVALIDO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_EMAIL_BLANK;
import static br.com.contmatic.util.Mensagens.MENSAGEM_EMAIL_PATTERN;
import static br.com.contmatic.util.Mensagens.MENSAGEM_EMAIL_TAMANHO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_ENDERECO_NULL;
import static br.com.contmatic.util.Mensagens.MENSAGEM_NOME_FANTASIA_BLANK;
import static br.com.contmatic.util.Mensagens.MENSAGEM_NOME_FANTASIA_PATTERN;
import static br.com.contmatic.util.Mensagens.MENSAGEM_NOME_FANTASIA_TAMANHO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_RAZAO_SOCIAL_BLANK;
import static br.com.contmatic.util.Mensagens.MENSAGEM_RAZAO_SOCIAL_PATTERN;
import static br.com.contmatic.util.Mensagens.MENSAGEM_RAZAO_SOCIAL_TAMANHO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_SET_DEPARTAMENTOS_VAZIO;
import static br.com.contmatic.util.Mensagens.MENSAGEM_SET_TELEFONES_VAZIO;
import static br.com.contmatic.util.Mensagens.URL_INVALIDA;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.endereco.EstadosBrasileirosType;
import br.com.contmatic.telefone.Telefone;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class EmpresaTest {

	private Empresa emp1;

	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

	private Validator validator = factory.getValidator();

	public Set<String> getErros(Empresa empresa) {
		Set<String> erros = new HashSet<>();
		for (ConstraintViolation<Empresa> constraintViolation : validator.validate(empresa)) {
			erros.add(constraintViolation.getMessageTemplate());
		}
		return erros;
	}

	@Test
	public void deve_retornar_true_na_validacao_do_objeto_fixture() {
		assertThat(getErros(emp1).size(), is(0));
	}

	@Test(timeout = 1000)
	public void deve_executar_sem_exceder_o_limite_de_tempo() {
		Departamento dep;
		List<Departamento> deptos = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			dep = new Departamento("Departamento X", "Departamento " + i + 1);
			deptos.add(dep);
		}
		assertThat(deptos.size(), not(equalTo(null)));
	}

	@BeforeClass
	public static void setUpBeforeClass() {
		FixtureFactoryLoader.loadTemplates("br.com.contmatic.templates");
	}

	@Before
	public void setUp() {
		emp1 = Fixture.from(Empresa.class).gimme("valido");
	}

	@After
	public void tearDown() {
		emp1 = null;
	}

	@Test
	@Ignore("Exemplo de teste ignorado")
	public void nao_deve_confirmar_a_presenca_dessa_segunda_string_na_razao_social_do_objeto_usado_em_toda_a_classe_de_teste() {
		assertThat(emp1.getRazaoSocial(), is(not("Nestlé")));
	}

	@Test
	public void deve_indicar_que_o_metodo_toString_esta_sobrescrito_por_nao_conter_o_caractere_arroba() {
		assertThat(new Empresa().toString(), not(containsString("@")));
	}

	@Test
	public void deve_apontar_igualdade_entre_os_objetos_empresa() {
		emp1 = Fixture.from(Empresa.class).gimme("mock");
		Empresa emp2 = Fixture.from(Empresa.class).gimme("mock");
		assertThat(emp1, equalTo(emp2));
	}

	// equals
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_ambos_tem_o_mesmo_cnpj() {
		Empresa emp2 = Fixture.from(Empresa.class).gimme("valido");
		emp2.setCnpj(emp1.getCnpj());
		assertThat(emp1, equalTo(emp2));
	}

	@Test
	public void deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_sao_o_mesmo_objeto() {
		Empresa emp2 = emp1;
		assertThat(emp1, equalTo(emp2));
	}

	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_cnpj_do_objeto2_e_diferente() {
		Empresa emp2 = Fixture.from(Empresa.class).gimme("valido");
		String cnpj = "55062400000113";
		emp2.setCnpj(cnpj);
		assertThat(emp1, not(equalTo(emp2)));
	}

	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_objeto2_e_nulo() {
		Empresa emp2 = null;
		assertThat(emp1, not(equalTo(emp2)));
	}

	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_sao_de_classes_diferentes() {
		assertThat(emp1, not(equalTo(new Object())));
	}

	// hashCode
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_empresa_usando_hashcode_sobrescrito() {
		emp1 = Fixture.from(Empresa.class).gimme("mock");
		Empresa emp2 = Fixture.from(Empresa.class).gimme("mock");
		assertThat(emp1.hashCode(), equalTo(emp2.hashCode()));
	}

	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_empresa_usando_hashcode_sobrescrito() {
		Empresa emp2 = Fixture.from(Empresa.class).gimme("valido");
		emp2.setCnpj("45997418000153");
		assertThat(emp1.hashCode(), not(equalTo(emp2.hashCode())));
	}

	/*
	 * RAZÃO SOCIAL
	 */
	@Test
	public void nao_deve_aceitar_razao_social_fora_da_regex_especificada() {
		String razaoSocial = "@ Indústrias Ldta.";
		emp1.setRazaoSocial(razaoSocial);
		assertThat(getErros(emp1), hasItem(MENSAGEM_RAZAO_SOCIAL_PATTERN));
	}

	@Test
	public void deve_definir_uma_nova_razao_social_para_a_empresa() {
		String razaoSocial = "Coca-Cola Indústrias Ltda.";
		emp1.setRazaoSocial(razaoSocial);
		assertThat(emp1.getRazaoSocial(), equalTo(razaoSocial));
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_RAZAO_SOCIAL_BLANK)));
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_RAZAO_SOCIAL_PATTERN)));
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_RAZAO_SOCIAL_TAMANHO)));
	}

	@Test
	public void deve_aceitar_razao_social_apenas_com_letras() {
		emp1.setRazaoSocial("Nintendo");
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_RAZAO_SOCIAL_PATTERN)));
	}

	@Test
	public void deve_aceitar_razao_social_com_letras_e_hifen() {
		emp1.setRazaoSocial("Colgate-Palmolive");
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_RAZAO_SOCIAL_PATTERN)));
	}

	@Test
	public void deve_aceitar_razao_social_com_letras_e_numeros() {
		emp1.setRazaoSocial("Taxis 123 S.A.");
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_RAZAO_SOCIAL_PATTERN)));
	}

	@Test
	public void deve_aceitar_razao_social_com_letras_e_espaco() {
		emp1.setRazaoSocial("Itaú Unibanco");
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_RAZAO_SOCIAL_PATTERN)));
	}

	@Test
	public void deve_aceitar_razao_social_com_letras_hifen_e_espaco() {
		emp1.setRazaoSocial("Hi-Tech Indústria Tecnológica");
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_RAZAO_SOCIAL_PATTERN)));
	}

	@Test
	public void deve_aceitar_razao_social_com_letras_hifen_espaco_e_ponto() {
		emp1.setRazaoSocial("Hi-Tech Indústria Tecnológica Ltda.");
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_RAZAO_SOCIAL_PATTERN)));
	}

	@Test
	public void deve_aceitar_razao_social_com_letras_numeros_espaco_e_ponto() {
		emp1.setRazaoSocial("SEGA 12 Indústria de Jogos Eletrônicos Ltda.");
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_RAZAO_SOCIAL_PATTERN)));
	}

	@Test
	public void deve_aceitar_razao_social_com_letras_numeros_hifen_espaco_e_ponto() {
		emp1.setRazaoSocial("ABC-123 Alimentos Ltda.");
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_RAZAO_SOCIAL_PATTERN)));
	}

	@Test
	public void deve_aceitar_razao_social_com_ponto_entre_caracteres() {
		emp1.setRazaoSocial("Instituto A. Senna");
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_RAZAO_SOCIAL_PATTERN)));
	}

	@Test
	public void deve_aceitar_razao_social_com_numeros_entre_letras() {
		emp1.setRazaoSocial("Se7e Belo");
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_RAZAO_SOCIAL_PATTERN)));
	}

	@Test
	public void deve_aceitar_razao_social_com_mais_de_um_ponto() {
		emp1.setRazaoSocial("Itaú Unibanco S.A.");
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_RAZAO_SOCIAL_PATTERN)));
	}

	@Test
	public void deve_aceitar_razao_social_com_E_comercial() {
		emp1.setRazaoSocial("Dolce & Gabanna do Brasil Ltda.");
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_RAZAO_SOCIAL_PATTERN)));
	}

	@Test
	public void deve_aceitar_razao_social_com_E_comercial_e_virgula() {
		emp1.setRazaoSocial("Dolce & Gabbana do Brasil Comércio, Importação e Participações Ltda.");
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_RAZAO_SOCIAL_PATTERN)));
	}

	@Test
	public void nao_deve_aceitar_razao_social_nula() {
		emp1.setRazaoSocial(null);
		assertThat(getErros(emp1), hasItem(MENSAGEM_RAZAO_SOCIAL_BLANK));
	}

	@Test
	public void nao_deve_aceitar_razao_social_em_branco() {
		emp1.setRazaoSocial("");
		assertThat(getErros(emp1), hasItem(MENSAGEM_RAZAO_SOCIAL_BLANK));
	}

	@Test
	public void nao_deve_aceitar_razao_social_apenas_com_espaco() {
		emp1.setRazaoSocial(" ");
		assertThat(getErros(emp1), hasItem(MENSAGEM_RAZAO_SOCIAL_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_razao_social_apenas_com_ponto() {
		emp1.setRazaoSocial(".");
		assertThat(getErros(emp1), hasItem(MENSAGEM_RAZAO_SOCIAL_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_razao_social_apenas_com_caracteres_especiais() {
		emp1.setRazaoSocial("!@(/.+**/-*");
		assertThat(getErros(emp1), hasItem(MENSAGEM_RAZAO_SOCIAL_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_razao_social_com_letras_e_caracteres_especiais_diferentes() {
		emp1.setRazaoSocial("Boom!@(*#");
		assertThat(getErros(emp1), hasItem(MENSAGEM_RAZAO_SOCIAL_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_razao_social_com_hifen_no_inicio() {
		emp1.setRazaoSocial("-Arcos Dourados Indústria de Alimentos Ltda.");
		assertThat(getErros(emp1), hasItem(MENSAGEM_RAZAO_SOCIAL_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_razao_social_com_hifen_no_fim() {
		emp1.setRazaoSocial("Arcos Dourados Indústria de Alimentos Ltda.-");
		assertThat(getErros(emp1), hasItem(MENSAGEM_RAZAO_SOCIAL_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_razao_social_apenas_com_numeros() {
		emp1.setRazaoSocial("123456789");
		assertThat(getErros(emp1), hasItem(MENSAGEM_RAZAO_SOCIAL_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_razao_social_apenas_com_numeros_e_hifen() {
		emp1.setRazaoSocial("12345-6789");
		assertThat(getErros(emp1), hasItem(MENSAGEM_RAZAO_SOCIAL_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_razao_social_de_tamanho_menor_que_2_caracteres() {
		emp1.setRazaoSocial("A");
		assertThat(getErros(emp1), hasItem(MENSAGEM_RAZAO_SOCIAL_TAMANHO));
	}

	@Test
	public void nao_deve_aceitar_razao_social_de_tamanho_maior_que_100_caracteres() {
		StringBuilder sb = new StringBuilder("B");
		for (int i = 0; i < 100; i++)
			sb.append("A");
		emp1.setRazaoSocial(sb.toString());
		assertThat(getErros(emp1), hasItem(MENSAGEM_RAZAO_SOCIAL_TAMANHO));
	}

	/*
	 * NOME FANTASIA
	 */
	@Test
	public void deve_definir_um_novo_nome_fantasia_para_a_empresa() {
		String nomeFantasia = "Coca-Cola";
		emp1.setNomeFantasia(nomeFantasia);
		assertThat(emp1.getNomeFantasia(), equalTo(nomeFantasia));
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_NOME_FANTASIA_BLANK)));
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_NOME_FANTASIA_PATTERN)));
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_NOME_FANTASIA_TAMANHO)));
	}

	@Test
	public void deve_aceitar_nome_fantasia_apenas_com_letras() {
		emp1.setNomeFantasia("PlayStation");
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_NOME_FANTASIA_PATTERN)));
	}

	@Test
	public void deve_aceitar_nome_fantasia_com_letras_e_hifen() {
		emp1.setNomeFantasia("General-Motors");
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_NOME_FANTASIA_PATTERN)));
	}

	@Test
	public void deve_aceitar_nome_fantasia_com_letras_e_numeros() {
		emp1.setNomeFantasia("Hao123 Corp.");
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_NOME_FANTASIA_PATTERN)));
	}

	@Test
	public void deve_aceitar_nome_fantasia_com_letras_e_espaco() {
		emp1.setNomeFantasia("Banco do Brasil");
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_NOME_FANTASIA_PATTERN)));
	}

	@Test
	public void deve_aceitar_nome_fantasia_com_letras_hifen_e_espaco() {
		emp1.setNomeFantasia("Coca-Cola Coffee Plus");
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_NOME_FANTASIA_PATTERN)));
	}

	@Test
	public void deve_aceitar_nome_fantasia_com_letras_hifen_espaco_e_ponto() {
		emp1.setNomeFantasia("M. Martins - Advocacia");
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_NOME_FANTASIA_PATTERN)));
	}

	@Test
	public void deve_aceitar_nome_fantasia_com_letras_numeros_espaco_e_ponto() {
		emp1.setNomeFantasia("Rapi10 - A Massa da Galera");
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_NOME_FANTASIA_PATTERN)));
	}

	@Test
	public void deve_aceitar_nome_fantasia_com_letras_numeros_hifen_espaco_e_ponto() {
		emp1.setNomeFantasia("ABC-123 Alimentos Ltda.");
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_NOME_FANTASIA_PATTERN)));
	}

	@Test
	public void deve_aceitar_nome_fantasia_com_ponto_entre_caracteres() {
		emp1.setNomeFantasia("Instituto A. Senna");
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_NOME_FANTASIA_PATTERN)));
	}

	@Test
	public void deve_aceitar_nome_fantasia_com_numeros_entre_letras() {
		emp1.setNomeFantasia("Se7e Belo");
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_NOME_FANTASIA_PATTERN)));
	}

	@Test
	public void deve_aceitar_nome_fantasia_com_mais_de_um_ponto() {
		emp1.setNomeFantasia("Itaú Unibanco S.A.");
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_NOME_FANTASIA_PATTERN)));
	}

	@Test
	public void deve_aceitar_nome_fantasia_com_E_comercial() {
		emp1.setNomeFantasia("Dolce & Gabanna");
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_NOME_FANTASIA_PATTERN)));
	}

	@Test
	public void deve_aceitar_nome_fantasia_com_virgula() {
		emp1.setNomeFantasia("Du, Dudu & Edu");
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_NOME_FANTASIA_PATTERN)));
	}

	@Test
	public void deve_aceitar_nome_fantasia_com_exclamacao() {
		emp1.setNomeFantasia("Big Boom! Inc.");
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_NOME_FANTASIA_PATTERN)));
	}

	@Test
	public void deve_aceitar_nome_fantasia_com_arroba() {
		emp1.setNomeFantasia("L@n House do João");
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_NOME_FANTASIA_PATTERN)));
	}

	@Test
	public void nao_deve_aceitar_nome_fantasia_nulo() {
		emp1.setNomeFantasia(null);
		assertThat(getErros(emp1), hasItem(MENSAGEM_NOME_FANTASIA_BLANK));
	}

	@Test
	public void nao_deve_aceitar_nome_fantasia_em_branco() {
		emp1.setNomeFantasia("");
		assertThat(getErros(emp1), hasItem(MENSAGEM_NOME_FANTASIA_BLANK));
	}

	@Test
	public void nao_deve_aceitar_nome_fantasia_apenas_com_espaco() {
		emp1.setNomeFantasia(" ");
		assertThat(getErros(emp1), hasItem(MENSAGEM_NOME_FANTASIA_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_nome_fantasia_apenas_com_ponto() {
		emp1.setNomeFantasia(".");
		assertThat(getErros(emp1), hasItem(MENSAGEM_NOME_FANTASIA_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_nome_fantasia_apenas_com_caracteres_especiais() {
		emp1.setNomeFantasia("!@(*");
		assertThat(getErros(emp1), hasItem(MENSAGEM_NOME_FANTASIA_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_nome_fantasia_com_letras_e_caracteres_especiais_diferentes() {
		emp1.setNomeFantasia("Boom¨¨");
		assertThat(getErros(emp1), hasItem(MENSAGEM_NOME_FANTASIA_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_nome_fantasia_com_hifen_no_inicio() {
		emp1.setNomeFantasia("-Arcos Dourados Indústria de Alimentos Ltda.");
		assertThat(getErros(emp1), hasItem(MENSAGEM_NOME_FANTASIA_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_nome_fantasia_com_hifen_no_fim() {
		emp1.setNomeFantasia("Arcos Dourados Indústria de Alimentos Ltda.-");
		assertThat(getErros(emp1), hasItem(MENSAGEM_NOME_FANTASIA_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_nome_fantasia_apenas_com_numeros() {
		emp1.setNomeFantasia("123456789");
		assertThat(getErros(emp1), hasItem(MENSAGEM_NOME_FANTASIA_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_nome_fantasia_apenas_com_numeros_e_hifen() {
		emp1.setNomeFantasia("12345-6789");
		assertThat(getErros(emp1), hasItem(MENSAGEM_NOME_FANTASIA_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_nome_fantasia_de_tamanho_menor_que_2_caracteres() {
		emp1.setNomeFantasia("A");
		assertThat(getErros(emp1), hasItem(MENSAGEM_NOME_FANTASIA_TAMANHO));
	}

	@Test
	public void nao_deve_aceitar_nome_fantasia_de_tamanho_maior_que_55_caracteres() {
		StringBuilder sb = new StringBuilder("Z");
		for (int i = 0; i < 55; i++)
			sb.append("A");
		emp1.setNomeFantasia(sb.toString());
		assertThat(getErros(emp1), hasItem(MENSAGEM_NOME_FANTASIA_TAMANHO));
	}

	/*
	 * CNPJ
	 */
	@Test
	public void deve_definir_um_novo_cnpj_para_a_empresa() {
		String cnpj = "45997418000153";
		emp1.setCnpj(cnpj);
		assertThat(emp1.getCnpj(), equalTo(cnpj));
	}

	@Test
	public void deve_aceitar_cnpj_valido_sem_caracteres_especiais() {
		emp1.setCnpj("61365284000104");
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_CNPJ_INVALIDO)));
	}

	@Test
	public void deve_aceitar_cnpj_valido_com_mascara() {
		emp1.setCnpj("61.365.284/0001-04");
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_CNPJ_INVALIDO)));
	}

	@Test
	public void nao_deve_aceitar_cnpj_nulo() {
		emp1.setCnpj(null);
		assertThat(getErros(emp1), hasItem(MENSAGEM_CNPJ_BLANK));
	}

	@Test
	public void nao_deve_aceitar_cnpj_em_branco() {
		emp1.setCnpj("");
		assertThat(getErros(emp1), hasItem(MENSAGEM_CNPJ_BLANK));
	}

	@Test
	public void nao_deve_aceitar_cnpj_composto_por_menos_de_14_digitos() {
		emp1.setCnpj("123456789");
		assertThat(getErros(emp1), hasItem(MENSAGEM_CNPJ_INVALIDO));
	}

	@Test
	public void nao_deve_aceitar_cnpj_composto_por_mais_de_14_digitos() {
		emp1.setCnpj("123456789123456");
		assertThat(getErros(emp1), hasItem(MENSAGEM_CNPJ_INVALIDO));
	}

	@Test
	public void nao_deve_aceitar_cnpj_composto_por_letras() {
		emp1.setCnpj("abcdefghijklmn");
		assertThat(getErros(emp1), hasItem(MENSAGEM_CNPJ_INVALIDO));
	}

	@Test
	public void nao_deve_aceitar_cnpj_composto_por_caracteres_especiais() {
		emp1.setCnpj("!@#$.%&*()!@#$");
		assertThat(getErros(emp1), hasItem(MENSAGEM_CNPJ_INVALIDO));
	}

	@Test
	public void nao_deve_aceitar_cnpj_que_contenha_qualquer_caractere_estranho_a_digitos() {
		emp1.setCnpj("6136528400010a");
		assertThat(getErros(emp1), hasItem(MENSAGEM_CNPJ_INVALIDO));
	}

	@Test
	public void nao_deve_aceitar_cnpj_composto_por_digitos_iguais() {
		emp1.setCnpj("99999999999999");
		assertThat(getErros(emp1), hasItem(MENSAGEM_CNPJ_INVALIDO));
	}

	@Test
	public void nao_deve_aceitar_cnpj_invalido() {
		emp1.setCnpj("48785214000105");
		assertThat(getErros(emp1), hasItem(MENSAGEM_CNPJ_INVALIDO));
	}

	/*
	 * ÁREA DE ATUAÇÃO
	 */
	@Test
	public void deve_definir_uma_nova_area_de_atuacao_para_a_empresa() {
		String areaAtuacao = "Indústria alimentícia";
		emp1.setAreaAtuacao(areaAtuacao);
		assertThat(emp1.getAreaAtuacao(), equalTo(areaAtuacao));
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_AREA_ATUACAO_BLANK)));
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_AREA_ATUACAO_PATTERN)));
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_AREA_ATUACAO_TAMANHO)));
	}

	@Test
	public void deve_aceitar_area_de_atuacao_apenas_com_letras() {
		emp1.setAreaAtuacao("Educação");
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_AREA_ATUACAO_PATTERN)));
	}

	@Test
	public void deve_aceitar_area_de_atuacao_com_letras_e_espacos() {
		emp1.setAreaAtuacao("Setor alimentício");
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_AREA_ATUACAO_PATTERN)));
	}

	@Test
	public void nao_deve_aceitar_area_de_atuacao_nula() {
		emp1.setAreaAtuacao(null);
		assertThat(getErros(emp1), hasItem(MENSAGEM_AREA_ATUACAO_BLANK));
	}

	@Test
	public void nao_deve_aceitar_area_de_atuacao_em_branco() {
		emp1.setAreaAtuacao("");
		assertThat(getErros(emp1), hasItem(MENSAGEM_AREA_ATUACAO_BLANK));
	}

	@Test
	public void nao_deve_aceitar_area_de_atuacao_apenas_com_espaco() {
		emp1.setAreaAtuacao(" ");
		assertThat(getErros(emp1), hasItem(MENSAGEM_AREA_ATUACAO_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_area_de_atuacao_apenas_com_ponto() {
		emp1.setAreaAtuacao(".");
		assertThat(getErros(emp1), hasItem(MENSAGEM_AREA_ATUACAO_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_area_de_atuacao_apenas_com_caracteres_especiais() {
		emp1.setAreaAtuacao("@#$$$$#%");
		assertThat(getErros(emp1), hasItem(MENSAGEM_AREA_ATUACAO_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_area_de_atuacao_com_menos_de_2_caracteres() {
		emp1.setAreaAtuacao("a");
		assertThat(getErros(emp1), hasItem(MENSAGEM_AREA_ATUACAO_TAMANHO));
	}

	@Test
	public void nao_deve_aceitar_area_de_atuacao_com_mais_de_55_caracteres() {
		StringBuilder sb = new StringBuilder("B");
		for (int i = 0; i < 55; i++) {
			sb.append("A");
		}
		emp1.setAreaAtuacao(sb.toString());
		assertThat(getErros(emp1), hasItem(MENSAGEM_AREA_ATUACAO_TAMANHO));
	}

	@Test
	public void nao_deve_aceitar_area_de_atuacao_com_numeros() {
		emp1.setAreaAtuacao("Indústria de fármacos 1");
		assertThat(getErros(emp1), hasItem(MENSAGEM_AREA_ATUACAO_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_area_de_atuacao_com_caracteres_especiais() {
		emp1.setAreaAtuacao("Indústria @automotiva");
		assertThat(getErros(emp1), hasItem(MENSAGEM_AREA_ATUACAO_PATTERN));
	}

	/*
	 * TELEFONES
	 */
	@Test
	public void deve_indicar_que_a_lista_de_telefones_e_inicializada_na_construcao_do_objeto() {
		Empresa e1 = Fixture.from(Empresa.class).gimme("mock");
		assertThat(e1.getTelefones(), not(equalTo(null)));
	}

	@Test
	public void deve_redefinir_a_colecao_de_telefones() {
		Set<Telefone> telefones = new HashSet<Telefone>();
		for (int i = 0; i < 10; i++) {
			Telefone telefone = Fixture.from(Telefone.class).gimme("valido");
			telefones.add(telefone);
		}
		emp1.setTelefones(telefones);
		assertThat(emp1.getTelefones(), equalTo(telefones));
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_SET_TELEFONES_VAZIO)));
	}

	@Test
	public void nao_deve_redefinir_a_lista_de_telefones_por_uma_lista_nula() {
		emp1.setTelefones(null);
		assertThat(getErros(emp1), hasItem(MENSAGEM_SET_TELEFONES_VAZIO));
	}

	@Test
	public void nao_deve_redefinir_a_lista_de_telefones_por_uma_lista_que_esteja_vazia() {
		Set<Telefone> telefones = new HashSet<Telefone>();
		emp1.setTelefones(telefones);
		assertThat(getErros(emp1), hasItem(MENSAGEM_SET_TELEFONES_VAZIO));
	}

	/*
	 * DEPARTAMENTOS
	 */
	@Test
	public void deve_indicar_que_a_lista_de_departamentos_e_inicializada_na_construcao_do_objeto() {
		Empresa e1 = Fixture.from(Empresa.class).gimme("mock");
		assertThat(e1.getDepartamentos(), not(equalTo(null)));
	}

	@Test
	public void deve_redefinir_a_lista_de_departamentos() {
		Set<Departamento> departamentos = new HashSet<Departamento>();
		for (int i = 0; i < 10; i++) {
			Departamento d = new Departamento();
			d.setDescricao("Departamento " + i + 1);
			departamentos.add(d);
		}
		emp1.setDepartamentos(departamentos);
		assertThat(emp1.getDepartamentos(), equalTo(departamentos));
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_SET_DEPARTAMENTOS_VAZIO)));
	}

	@Test
	public void nao_deve_redefinir_a_lista_de_departamentos_por_uma_lista_nula() {
		emp1.setDepartamentos(null);
		assertThat(getErros(emp1), hasItem(MENSAGEM_SET_DEPARTAMENTOS_VAZIO));
	}

	@Test
	public void nao_deve_redefinir_a_lista_de_departamentos_por_uma_lista_que_esteja_vazia() {
		Set<Departamento> departamentos = new HashSet<Departamento>();
		emp1.setDepartamentos(departamentos);
		assertThat(getErros(emp1), hasItem(MENSAGEM_SET_DEPARTAMENTOS_VAZIO));
	}

	/*
	 * ENDERECO
	 */
	@Test
	public void deve_definir_endereco_para_o_objeto_empresa() {
		String logradouro = "Rua Piraju";
		String bairro = "Monte Belo";
		String cidade = "São Paulo";
		EstadosBrasileirosType uf = SP;
		String pais = "Brasil";
		String cep = "08587789";
		Endereco endereco = new Endereco(logradouro, bairro, cidade, uf, pais, cep);
		emp1.setEndereco(endereco);
		assertThat(emp1.getEndereco(), equalTo(endereco));
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_ENDERECO_NULL)));
	}

	@Test
	public void nao_deve_aceitar_endereco_nulo() {
		emp1.setEndereco(null);
		assertThat(getErros(emp1), hasItem(MENSAGEM_ENDERECO_NULL));
	}

	/*
	 * E-MAIL
	 */
	@Test
	public void deve_redefinir_email_valido() {
		String email = "java@oracle.com";
		emp1.setEmail(email);
		assertThat(emp1.getEmail(), equalTo(email));
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_EMAIL_BLANK)));
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_EMAIL_PATTERN)));
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_EMAIL_TAMANHO)));
	}

	@Test
	public void deve_aceitar_email_valido() {
		emp1.setEmail("abc@gmail.com");
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_EMAIL_BLANK)));
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_EMAIL_PATTERN)));
		assertThat(getErros(emp1), not(hasItem(MENSAGEM_EMAIL_TAMANHO)));
	}

	@Test
	public void nao_deve_aceitar_email_invalido() {
		String email = "testmail@@gmail.com";
		emp1.setEmail(email);
		assertThat(getErros(emp1), hasItem(MENSAGEM_EMAIL_PATTERN));
	}

	@Test
	public void nao_deve_aceitar_email_nulo() {
		emp1.setEmail(null);
		assertThat(getErros(emp1), hasItem(MENSAGEM_EMAIL_BLANK));
	}

	@Test
	public void nao_deve_aceitar_email_com_menos_de_6_caracteres() {
		emp1.setEmail("a@a");
		assertThat(getErros(emp1), hasItem(MENSAGEM_EMAIL_TAMANHO));
	}

	@Test
	public void nao_deve_aceitar_email_com_mais_de_100_caracteres() {
		StringBuilder sb = new StringBuilder("J");
		for (int i = 0; i < 100; i++) {
			sb.append("o");
		}
		emp1.setEmail(sb.toString());
		assertThat(getErros(emp1), hasItem(MENSAGEM_EMAIL_TAMANHO));
	}

	/*
	 * SITE
	 */
	@Test
	public void deve_redefinir_um_novo_site() {
		String site = "https://www.meusite.com.br";
		emp1.setSite(site);
		assertThat(emp1.getSite(), equalTo(site));
		assertThat(getErros(emp1), not(hasItem(URL_INVALIDA)));
	}

	@Test
	public void deve_aceitar_url_valida_para_o_site() {
		emp1.setSite("https://www.minhaempresa.com");
		assertThat(getErros(emp1), not(hasItem(URL_INVALIDA)));
	}

	@Test
	public void nao_deve_aceitar_url_invalida_para_o_site() {
		emp1.setSite("httpz://www.minhaempresa.com");
		assertThat(getErros(emp1), hasItem(URL_INVALIDA));
	}
}