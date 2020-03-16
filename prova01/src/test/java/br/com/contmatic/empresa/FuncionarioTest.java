package br.com.contmatic.empresa;

import static br.com.contmatic.constantes.Mensagens.MENSAGEM_DATA_NASCIMENTO_FUTURE;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
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

import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class FuncionarioTest {
	
	Funcionario f;
	
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

	private Validator validator = factory.getValidator();
	
	public Set<String> getErros(Funcionario funcionario) {
		Set<String> erros = new HashSet<>();
		for (ConstraintViolation<Funcionario> constraintViolation : validator.validate(funcionario)) {
			erros.add(constraintViolation.getMessageTemplate());
			System.out.println(constraintViolation.getMessageTemplate());
		}
		return erros;
	}
	
	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("Iniciando testes da classe Funcionario...");
		FixtureFactoryLoader.loadTemplates("br.com.contmatic.templates");
	}
	
	@Before
	public void setUp() {
		f = Fixture.from(Funcionario.class).gimme("valido");
	}
	
	@After
	public void tearDown() {
		f = null;
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		System.out.println("Encerrando testes da classe Funcionario.");
	}
	
	@Test
	public void deve_validar_objeto_criado_com_o_fixture() {
		assertThat(getErros(f).size(), is(0));
	}
	
	@Test
	public void deve_indicar_que_o_metodo_toString_esta_sobrescrito_por_nao_conter_o_caractere_arroba() {
		assertThat(new Funcionario().toString(), not(containsString("@")));
	}
	
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_funcionario() {
		String nome = f.getNome();
		String rg = f.getRg();
		String cpf = f.getCpf();
		Funcionario fun = new Funcionario(nome, rg, cpf);
		assertEquals(f, fun);
	}
	
	//equals
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_ambos_tem_o_mesmo_cpf() {
		String nome = "Vitória M.";
		String rg = f.getRg();
		String cpf = f.getCpf();
		Funcionario fun = new Funcionario(nome, rg, cpf);
		assertTrue(f.equals(fun));
	}
	
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_sao_o_mesmo_objeto() {
		Funcionario fun = f;
		assertTrue(f.equals(fun));
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_cpf_do_objeto2_e_diferente() {
		String nome = f.getNome();
		String rg = f.getRg();
		String cpf = "73563445052";
		Funcionario fun = new Funcionario(nome, rg, cpf);
		assertFalse(f.equals(fun));
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_os_objetos_sao_de_classes_diferentes() {
		assertFalse(f.equals(new Object()));
	}
	
	//hashcode
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_funcionario_usando_hashcode_sobrescrito() {
		String nome = f.getNome();
		String rg = f.getRg();
		String cpf = f.getCpf();
		Funcionario fun = new Funcionario(nome, rg, cpf);
		assertEquals(f.hashCode(), fun.hashCode());
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_hashcode_sobrescrito() {
		String nome = "Joseph Stewart";
		String rg = "413720433";
		String cpf = "22104854016";
		Funcionario fun = new Funcionario(nome, rg, cpf);
		assertNotEquals(f.hashCode(), fun.hashCode());
	}
	
	@Test
	public void deve_redefinir_lista_de_dependentes() {
		Set<Dependente> dependentes = new HashSet<Dependente>();
		dependentes.add(new Dependente());
		f.setDependentes(dependentes);
		assertEquals(dependentes, f.getDependentes());
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_redefinir_lista_de_dependentes_por_uma_lista_nula() {
		Set<Dependente> dependentes = null;
		f.setDependentes(dependentes);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_redefinir_a_lista_de_dependentes_por_uma_lista_igual() {
		Set<Dependente> dependentes = new HashSet<Dependente>();
		for(int i=0; i<10; i++) {
			Dependente d = new Dependente();
			dependentes.add(d);
		}
		f.setDependentes(dependentes);
		f.setDependentes(dependentes);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_redefinir_a_lista_de_dependentes_por_uma_lista_que_esteja_vazia() {
		Set<Dependente> dependentes = new HashSet<Dependente>();
		f.setDependentes(dependentes);
	}
	
	
	/*
	 * MÉTODOS
	 * */
	@Test
	public void deve_realizar_cadastro_em_departamento_valido() {
		Departamento depto = new Departamento("Novas tecnologias", "Departamento de inovações em tecnologia");
		f.cadastrar(depto);
		assertThat(depto.getFuncionarios(), hasItem(f));
	}
	
	@Test
	public void deve_realizar_cadastro_de_novo_funcionario_no_departamento() {
		Departamento depto = new Departamento("Novas tecnologias", "Departamento de inovações em tecnologia");
		f.cadastrar(depto);
		String nome = "José Franco Gomes";
		String rg = "228670755";
		String cpf = "16292336093";
		Funcionario fun = new Funcionario(nome, rg, cpf);
		fun.cadastrar(depto);
		assertThat(depto.getFuncionarios(), hasItem(fun));
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_realizar_cadastro_em_departamento_nulo() {
		f.cadastrar(null);
	}
	
	@Test
	public void nao_deve_realizar_cadastro_duas_vezes_no_mesmo_departamento() {
		Departamento depto = new Departamento("Novas tecnologias", "Departamento de inovações em tecnologia");
		f.cadastrar(depto);
		assertFalse(f.cadastrar(depto));
	}
	
	/*
	 * DATA ADMISSAO
	 */
	@Test
	public void deve_aceitar_data_de_nascimento_especificada() {
		f.setDataAdmissao(new LocalDate("2015-01-25"));
		assertThat(getErros(f).isEmpty(), is(true));
	}

	@Test
	public void nao_deve_aceitar_data_de_nascimento_futura() {
		LocalDate localDate = new LocalDate(2040, 4, 1);
		f.setDataAdmissao(localDate);
		assertThat(getErros(f), hasItem(MENSAGEM_DATA_NASCIMENTO_FUTURE));
	}
}
