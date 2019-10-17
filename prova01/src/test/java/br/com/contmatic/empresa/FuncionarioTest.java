package br.com.contmatic.empresa;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.empresa.Departamento;
import br.com.contmatic.empresa.Dependente;
import br.com.contmatic.empresa.Funcionario;

public class FuncionarioTest {
	
	Funcionario f;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("Iniciando testes da classe Funcionario...");
	}
	
	@Before
	public void setUp() {
		String nome = "Vitória";
		String rg = "516879541";
		String cpf = "50736121080";
		f = new Funcionario(nome, rg, cpf);
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
	public void deve_retornar_false_para_indicar_que_o_metodo_toString_esta_sobrescrito() {
		assertFalse(new Funcionario().toString().contains("["));
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
		f.equals(fun);
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
		List<Dependente> dependentes = new ArrayList<Dependente>();
		dependentes.add(new Dependente());
		f.setDependentes(dependentes);
		assertEquals(dependentes, f.getDependentes());
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_redefinir_lista_de_dependentes_por_uma_lista_nula() {
		List<Dependente> dependentes = null;
		f.setDependentes(dependentes);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_redefinir_a_lista_de_dependentes_por_uma_lista_igual() {
		List<Dependente> dependentes = new ArrayList<Dependente>();
		for(int i=0; i<10; i++) {
			Dependente d = new Dependente();
			dependentes.add(d);
		}
		f.setDependentes(dependentes);
		f.setDependentes(dependentes);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_redefinir_a_lista_de_dependentes_por_uma_lista_que_esteja_vazia() {
		List<Dependente> dependentes = new ArrayList<Dependente>();
		f.setDependentes(dependentes);
	}
	
	
	/*
	 * MÉTODOS
	 * */
	@Test
	public void deve_realizar_cadastro_em_departamento_valido() {
		Departamento depto = new Departamento("Novas tecnologias", "Departamento de inovações em tecnologia");
		f.cadastrar(depto);
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
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_realizar_cadastro_em_departamento_nulo() {
		f.cadastrar(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_realizar_cadastro_duas_vezes_no_mesmo_departamento() {
		Departamento depto = new Departamento("Novas tecnologias", "Departamento de inovações em tecnologia");
		f.cadastrar(depto);
		f.cadastrar(depto);
	}
}
