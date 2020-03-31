package br.com.contmatic.empresa;

import static br.com.contmatic.constantes.Mensagens.MENSAGEM_DESCRICAO_DEPARTAMENTO_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_DESCRICAO_DEPARTAMENTO_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_DESCRICAO_DEPARTAMENTO_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NOME_DEPARTAMENTO_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NOME_DEPARTAMENTO_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NOME_DEPARTAMENTO_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_SET_FUNCIONARIOS_VAZIO;
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

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

// TODO: Auto-generated Javadoc
/**
 * The Class DepartamentoTest.
 */
public class DepartamentoTest {
	
	/** The emp. */
	Empresa emp;
	
	/** The depto. */
	Departamento depto;
	
	/** The factory. */
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	
	/** The validator. */
	private Validator validator = factory.getValidator();
	
	/**
	 * Gets the erros.
	 *
	 * @param depto the depto
	 * @return the erros
	 */
	public Set<String> getErros(Departamento depto) {
		Set<String> erros = new HashSet<>();
		for (ConstraintViolation<Departamento> constraintViolation : validator.validate(depto)) {
			erros.add(constraintViolation.getMessageTemplate());
			System.out.println(constraintViolation.getMessageTemplate());
		}
		return erros;
	}

	/**
	 * Sets the up before class.
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("Iniciando os testes da classe Departamento...");
		FixtureFactoryLoader.loadTemplates("br.com.contmatic.templates");
	}
	
	/**
	 * Tear down after class.
	 */
	@AfterClass
	public static void tearDownAfterClass() {
		System.out.println("Encerrando os testes da classe Departamento.");
	}
	
	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		depto = Fixture.from(Departamento.class).gimme("valido");
	}
	
	/**
	 * Tear down.
	 */
	@After
	public void tearDown() {
		emp = null;
		depto = null;
	}
	
	/**
	 * Deve validar objeto criado com fixture.
	 */
	@Test
	public void deve_validar_objeto_criado_com_fixture() {
		System.out.println(depto);
		assertThat(getErros(depto).size(), is(0));
	}
	
	/**
	 * Deve indicar que o metodo to string esta sobrescrito por nao conter o caractere arroba.
	 */
	@Test
	public void deve_indicar_que_o_metodo_toString_esta_sobrescrito_por_nao_conter_o_caractere_arroba() {
		assertThat(new Departamento().toString(), not(containsString("@")));
	}
	
	/**
	 * Deve indicar que a lista de funcionarios e inicializada na construcao do objeto.
	 */
	@Test
	public void deve_indicar_que_a_lista_de_funcionarios_e_inicializada_na_construcao_do_objeto() {
		assertThat(depto.getFuncionarios(), not(equalTo(null)));
	}
	
	/**
	 * Deve apontar igualdade entre os objetos departamento.
	 */
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_departamento() {
		depto = Fixture.from(Departamento.class).gimme("mock");
		Departamento depto2 = Fixture.from(Departamento.class).gimme("mock");
		assertThat(depto, equalTo(depto2));
	}
	
	/**
	 * Deve apontar igualdade entre os objetos departamento usando equals sobrescrito porque os valores sao iguais.
	 */
	//equals
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_departamento_usando_equals_sobrescrito_porque_os_valores_sao_iguais() {
		depto = Fixture.from(Departamento.class).gimme("mock");
		Departamento depto2 = Fixture.from(Departamento.class).gimme("mock");
		assertThat(depto, equalTo(depto2));
	}
	
	/**
	 * Deve apontar igualdade entre os objetos departamento usando equals sobrescrito porque sao o mesmo objeto.
	 */
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_departamento_usando_equals_sobrescrito_porque_sao_o_mesmo_objeto() {
		Departamento depto2 = depto;
		assertThat(depto, equalTo(depto2));
	}
		
	/**
	 * Nao deve apontar igualdade entre os objetos departamento usando equals sobrescrito porque objeto 2 e nulo.
	 */
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_departamento_usando_equals_sobrescrito_porque_objeto2_e_nulo() {
		assertThat(depto, not(equalTo(null)));
	}
	
	/**
	 * Nao deve apontar igualdade entre os objetos departamento usando equals sobrescrito porque os valores sao diferentes.
	 */
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_departamento_usando_equals_sobrescrito_porque_os_valores_sao_diferentes() {
		Departamento depto2 = Fixture.from(Departamento.class).gimme("valido");
		assertThat(depto, not(equalTo(depto2)));
	}
	
	/**
	 * Nao deve apontar igualdade entre os objetos usando equals sobrescrito porque sao de classes diferentes.
	 */
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_sao_de_classes_diferentes() {
		assertThat(depto, not(equalTo(new Object())));
	}
	
	/**
	 * Nao deve apontar igualdade entre os objetos departamento usando equals sobrescrito porque nome do objeto 2 e diferente.
	 */
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_departamento_usando_equals_sobrescrito_porque_nome_do_objeto2_e_diferente() {
		depto = Fixture.from(Departamento.class).gimme("mock");
		Departamento depto2 = Fixture.from(Departamento.class).gimme("mock");
		depto2.setNome("Rec Humanos");
		assertThat(depto, not(equalTo(depto2)));
	}
	
	/**
	 * Nao deve apontar igualdade entre os objetos departamento usando equals sobrescrito porque descricao do objeto 2 e diferente.
	 */
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_departamento_usando_equals_sobrescrito_porque_descricao_do_objeto2_e_diferente() {
		depto = Fixture.from(Departamento.class).gimme("mock");
		Departamento depto2 = Fixture.from(Departamento.class).gimme("mock");
		depto2.setDescricao("Departamento recursos humanos");
		assertThat(depto, not(equalTo(depto2)));
	}
	
	/**
	 * Nao deve apontar igualdade entre os objetos departamento usando equals sobrescrito porque lista funcionarios do objeto 2 e diferente.
	 */
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_departamento_usando_equals_sobrescrito_porque_lista_funcionarios_do_objeto2_e_diferente() {
		depto = Fixture.from(Departamento.class).gimme("mock");
		Departamento depto2 = Fixture.from(Departamento.class).gimme("mock");
		Set<Funcionario> funcionarios = new HashSet<Funcionario>();
		Funcionario fun = Fixture.from(Funcionario.class).gimme("valido");
		funcionarios.add(fun);
		depto2.setFuncionarios(funcionarios);
		assertThat(depto, not(equalTo(depto2)));
	}
	
	/**
	 * Deve apontar igualdade entre os objetos departamento usando hashcode sobrescrito.
	 */
	//hashcode
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_departamento_usando_hashcode_sobrescrito() {
		depto = Fixture.from(Departamento.class).gimme("mock");
		Departamento depto2 = Fixture.from(Departamento.class).gimme("mock");
		assertThat(depto.hashCode(), equalTo(depto2.hashCode()));
	}

	/**
	 * Nao deve apontar igualdade entre os objetos departamento usando hashcode sobrescrito.
	 */
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_departamento_usando_hashcode_sobrescrito() {
		Departamento depto2 = Fixture.from(Departamento.class).gimme("mock");
		assertThat(depto.hashCode(), not(equalTo(depto2.hashCode())));
	}
	
	
	/**
	 * Deve definir um novo nome para o departamento.
	 */
	/*
	 * NOME
	 * */
	@Test
	public void deve_definir_um_novo_nome_para_o_departamento() {
		String nome = "Tecnologia";
		depto.setNome(nome);
		assertThat(depto.getNome(), equalTo(nome));
		assertThat(getErros(depto), not(hasItem(MENSAGEM_NOME_DEPARTAMENTO_BLANK)));
		assertThat(getErros(depto), not(hasItem(MENSAGEM_NOME_DEPARTAMENTO_PATTERN)));
		assertThat(getErros(depto), not(hasItem(MENSAGEM_NOME_DEPARTAMENTO_TAMANHO)));
	}
	
	/**
	 * Deve aceitar nome apenas com letras.
	 */
	@Test
	public void deve_aceitar_nome_apenas_com_letras() {
		depto.setNome("Diretoria");
		assertThat(getErros(depto), not(hasItem(MENSAGEM_NOME_DEPARTAMENTO_BLANK)));
		assertThat(getErros(depto), not(hasItem(MENSAGEM_NOME_DEPARTAMENTO_PATTERN)));
		assertThat(getErros(depto), not(hasItem(MENSAGEM_NOME_DEPARTAMENTO_TAMANHO)));
	}
	
	/**
	 * Deve aceitar nome com letras e espacos.
	 */
	@Test
	public void deve_aceitar_nome_com_letras_e_espacos() {
		depto.setNome("Novas tecnologias");
		assertThat(getErros(depto), not(hasItem(MENSAGEM_NOME_DEPARTAMENTO_BLANK)));
		assertThat(getErros(depto), not(hasItem(MENSAGEM_NOME_DEPARTAMENTO_PATTERN)));
		assertThat(getErros(depto), not(hasItem(MENSAGEM_NOME_DEPARTAMENTO_TAMANHO)));
	}
	
	/**
	 * Nao deve aceitar nome nulo.
	 */
	@Test
	public void nao_deve_aceitar_nome_nulo() {
		depto.setNome(null);
		assertThat(getErros(depto), hasItem(MENSAGEM_NOME_DEPARTAMENTO_BLANK));
	}
	
	/**
	 * Nao deve aceitar nome em branco.
	 */
	@Test
	public void nao_deve_aceitar_nome_em_branco() {
		depto.setNome("");
		assertThat(getErros(depto), hasItem(MENSAGEM_NOME_DEPARTAMENTO_BLANK));
	}
	
	/**
	 * Nao deve aceitar nome apenas com espaco.
	 */
	@Test
	public void nao_deve_aceitar_nome_apenas_com_espaco() {
		depto.setNome(" ");
		assertThat(getErros(depto), hasItem(MENSAGEM_NOME_DEPARTAMENTO_PATTERN));
	}
	
	/**
	 * Nao deve aceitar nome apenas com ponto.
	 */
	@Test
	public void nao_deve_aceitar_nome_apenas_com_ponto() {
		depto.setNome(".");
		assertThat(getErros(depto), hasItem(MENSAGEM_NOME_DEPARTAMENTO_PATTERN));
	}
	
	/**
	 * Nao deve aceitar nome com numero.
	 */
	@Test
	public void nao_deve_aceitar_nome_com_numero() {
		depto.setNome("Departamento 1");
		assertThat(getErros(depto), hasItem(MENSAGEM_NOME_DEPARTAMENTO_PATTERN));
	}
	
	/**
	 * Nao deve aceitar nome sem letras.
	 */
	@Test
	public void nao_deve_aceitar_nome_sem_letras() {
		depto.setNome("           ");
		assertThat(getErros(depto), hasItem(MENSAGEM_NOME_DEPARTAMENTO_PATTERN));
	}
	
	/**
	 * Nao deve aceitar nome com caracteres especiais.
	 */
	@Test
	public void nao_deve_aceitar_nome_com_caracteres_especiais() {
		depto.setNome("Departamento #");
		assertThat(getErros(depto), hasItem(MENSAGEM_NOME_DEPARTAMENTO_PATTERN));
	}
	
	/**
	 * Nao deve aceitar nome de tamanho menor que 2 caracteres.
	 */
	@Test
	public void nao_deve_aceitar_nome_de_tamanho_menor_que_2_caracteres() {
		depto.setNome("X");
		assertThat(getErros(depto), hasItem(MENSAGEM_NOME_DEPARTAMENTO_TAMANHO));
	}
	
	/**
	 * Nao deve aceitar nome de tamanho maior que 55 caracteres.
	 */
	@Test
	public void nao_deve_aceitar_nome_de_tamanho_maior_que_55_caracteres() {
		StringBuilder sb = new StringBuilder("Z");
		for(int i=0; i<55; i++)
			sb.append("A");
		depto.setNome(sb.toString());
		assertThat(getErros(depto), hasItem(MENSAGEM_NOME_DEPARTAMENTO_TAMANHO));
	}
	
	/**
	 * Nao deve aceitar nome com menos de uma letra independentemente da quantidade de caracteres da string.
	 */
	@Test
	public void nao_deve_aceitar_nome_com_menos_de_uma_letra_independentemente_da_quantidade_de_caracteres_da_string() {
		depto.setNome(" k ");
		assertThat(getErros(depto), hasItem(MENSAGEM_NOME_DEPARTAMENTO_PATTERN));
	}
	
	/**
	 * Deve definir uma nova descricao para o departamento.
	 */
	/*
	 * DESCRICAO
	 * */
	@Test
	public void deve_definir_uma_nova_descricao_para_o_departamento() {
		String descricao = "Departamento de testes";
		depto.setDescricao(descricao);
		assertThat(depto.getDescricao(), equalTo(descricao));
		assertThat(getErros(depto), not(hasItem(MENSAGEM_DESCRICAO_DEPARTAMENTO_BLANK)));
		assertThat(getErros(depto), not(hasItem(MENSAGEM_DESCRICAO_DEPARTAMENTO_PATTERN)));
		assertThat(getErros(depto), not(hasItem(MENSAGEM_DESCRICAO_DEPARTAMENTO_TAMANHO)));
	}
	
	/**
	 * Deve aceitar descricao com letras e espacos.
	 */
	@Test
	public void deve_aceitar_descricao_com_letras_e_espacos() {
		depto.setDescricao("Departamento do setor estratégico");
		assertThat(getErros(depto), not(hasItem(MENSAGEM_DESCRICAO_DEPARTAMENTO_PATTERN)));
	}
	
	/**
	 * Deve aceitar descricao com letras espacos virgula e ponto.
	 */
	@Test
	public void deve_aceitar_descricao_com_letras_espacos_virgula_e_ponto() {
		depto.setDescricao("Departamento do setor estratégico. Diretores, gerentes etc.");
		assertThat(getErros(depto), not(hasItem(MENSAGEM_DESCRICAO_DEPARTAMENTO_PATTERN)));
	}
	
	/**
	 * Nao deve aceitar descricao nula.
	 */
	@Test
	public void nao_deve_aceitar_descricao_nula() {
		depto.setDescricao(null);
		assertThat(getErros(depto), hasItem(MENSAGEM_DESCRICAO_DEPARTAMENTO_BLANK));
	}
	
	/**
	 * Nao deve aceitar descricao em branco.
	 */
	@Test
	public void nao_deve_aceitar_descricao_em_branco() {
		depto.setDescricao("");
		assertThat(getErros(depto), hasItem(MENSAGEM_DESCRICAO_DEPARTAMENTO_BLANK));
	}
	
	/**
	 * Nao deve aceitar descricao apenas com espaco.
	 */
	@Test
	public void nao_deve_aceitar_descricao_apenas_com_espaco() {
		depto.setDescricao(" ");
		assertThat(getErros(depto), hasItem(MENSAGEM_DESCRICAO_DEPARTAMENTO_PATTERN));
	}
	
	/**
	 * Nao deve aceitar descricao apenas com ponto.
	 */
	@Test
	public void nao_deve_aceitar_descricao_apenas_com_ponto() {
		depto.setDescricao(".");
		assertThat(getErros(depto), hasItem(MENSAGEM_DESCRICAO_DEPARTAMENTO_PATTERN));
	}
	
	/**
	 * Nao deve aceitar descricao com numero.
	 */
	@Test
	public void nao_deve_aceitar_descricao_com_numero() {
		depto.setDescricao("Departamento 1");
		assertThat(getErros(depto), hasItem(MENSAGEM_DESCRICAO_DEPARTAMENTO_PATTERN));
	}
	
	/**
	 * Nao deve aceitar descricao com caracteres especiais.
	 */
	@Test
	public void nao_deve_aceitar_descricao_com_caracteres_especiais() {
		depto.setDescricao("Departamento #");
		assertThat(getErros(depto), hasItem(MENSAGEM_DESCRICAO_DEPARTAMENTO_PATTERN));
	}
	
	/**
	 * Nao deve aceitar descricao de tamanho menor que 2 caracteres.
	 */
	@Test
	public void nao_deve_aceitar_descricao_de_tamanho_menor_que_2_caracteres() {
		depto.setDescricao("X");
		assertThat(getErros(depto), hasItem(MENSAGEM_DESCRICAO_DEPARTAMENTO_TAMANHO));
	}
	
	/**
	 * Nao deve aceitar descricao de tamanho maior que 100 caracteres.
	 */
	@Test
	public void nao_deve_aceitar_descricao_de_tamanho_maior_que_100_caracteres() {
		StringBuilder sb = new StringBuilder("Z");
		for(int i=0; i<100; i++)
			sb.append("A");
		depto.setDescricao(sb.toString());
		assertThat(getErros(depto), hasItem(MENSAGEM_DESCRICAO_DEPARTAMENTO_TAMANHO));
	}
	
	/**
	 * Deve redefinir o set de funcionarios.
	 */
	/*
	 * FUNCIONARIOS
	 * */
	@Test
	public void deve_redefinir_o_set_de_funcionarios() {
		Set<Funcionario> funcionarios= new HashSet<Funcionario>();
		Funcionario fun = Fixture.from(Funcionario.class).gimme("valido");
		funcionarios.add(fun);
		depto.setFuncionarios(funcionarios);
		assertThat(depto.getFuncionarios(), equalTo(funcionarios));
	}
	
	/**
	 * Nao deve redefinir o set de funcionarios por um set nulo.
	 */
	@Test
	public void nao_deve_redefinir_o_set_de_funcionarios_por_um_set_nulo() {
		depto.setFuncionarios(null);
		assertThat(getErros(depto), hasItem(MENSAGEM_SET_FUNCIONARIOS_VAZIO));
	}
	
	/**
	 * Nao deve redefinir o set de funcionarios por um set que esteja vazio.
	 */
	@Test
	public void nao_deve_redefinir_o_set_de_funcionarios_por_um_set_que_esteja_vazio() {
		Set<Funcionario> funcionarios = new HashSet<Funcionario>();
		depto.setFuncionarios(funcionarios);
		assertThat(getErros(depto), hasItem(MENSAGEM_SET_FUNCIONARIOS_VAZIO));
	}
}
