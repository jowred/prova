package br.com.contmatic.empresa;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.empresa.Departamento;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Funcionario;

public class DepartamentoTest {
	
	Empresa emp;
	
	Departamento depto;

	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("Iniciando os testes da classe Departamento...");
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		System.out.println("Encerrando os testes da classe Departamento.");
	}
	
	@Before
	public void setUp() {
		emp = new Empresa();
		depto = new Departamento("RH", "Departamento de recursos humanos");
	}
	
	@After
	public void tearDown() {
		emp = null;
		depto = null;
	}
	
	@Test
	public void deve_indicar_que_o_metodo_toString_esta_sobrescrito_por_nao_conter_o_caractere_arroba() {
		assertThat(new Departamento().toString(), not(containsString("@")));
	}
	
	@Test
	public void deve_indicar_que_a_lista_de_funcionarios_e_inicializada_na_construcao_do_objeto() {
		Departamento d1 = new Departamento();
		assertNotNull(d1.getFuncionarios());
	}
	
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_departamento() {
		Departamento depto2 = new Departamento(depto.getNome(), depto.getDescricao());
		assertEquals(depto, depto2);
	}
	
	//equals
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_departamento_usando_equals_sobrescrito_porque_os_valores_sao_iguais() {
		Departamento depto2 = new Departamento(depto.getNome(), depto.getDescricao());
		assertTrue(depto.equals(depto2));
	}
	
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_departamento_usando_equals_sobrescrito_porque_sao_o_mesmo_objeto() {
		Departamento depto2 = depto;
		assertTrue(depto.equals(depto2));
	}
		
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_departamento_usando_equals_sobrescrito_porque_objeto2_e_nulo() {
		Departamento depto2 = null;
		assertFalse(depto.equals(depto2));
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_departamento_usando_equals_sobrescrito_porque_os_valores_sao_diferentes() {
		Departamento depto2 = new Departamento("Diretoria", "Departamento reservado para o setor estratégico");
		assertFalse(depto.equals(depto2));
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_sao_de_classes_diferentes() {
		assertFalse(depto.equals(new Object()));
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_departamento_usando_equals_sobrescrito_porque_nome_do_objeto2_e_diferente() {
		String nome = "Rec Humanos";
		String descricao = depto.getDescricao();
		Departamento depto2 = new Departamento(nome, descricao); 
		assertFalse(depto.equals(depto2));
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_departamento_usando_equals_sobrescrito_porque_descricao_do_objeto2_e_diferente() {
		String nome = depto.getNome();
		String descricao = "Departamento recursos humanos";
		Departamento depto2 = new Departamento(nome, descricao); 
		assertFalse(depto.equals(depto2));
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_departamento_usando_equals_sobrescrito_porque_lista_funcionarios_do_objeto2_e_diferente() {
		depto = new Departamento("RH", "Recursos humanos");
		String nome = depto.getNome();
		String descricao = depto.getDescricao();
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		funcionarios.add(new Funcionario());
		Departamento depto2 = new Departamento(nome, descricao);
		depto2.setFuncionarios(funcionarios);
		assertFalse(depto.equals(depto2));
	}
	
	//hashcode
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_departamento_usando_hashcode_sobrescrito() {
		Departamento depto2 = new Departamento(depto.getNome(), depto.getDescricao());
		assertTrue(depto.hashCode() == depto2.hashCode());
	}

	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_departamento_usando_hashcode_sobrescrito() {
		Departamento depto2 = new Departamento("Direção", "Departamento reservado para o setor estratégico");
		assertFalse(depto.hashCode() == depto2.hashCode());
	}
	
	
	/*
	 * NOME
	 * */
	@Test
	public void deve_definir_um_novo_nome_para_o_departamento() {
		String nome = "Tecnologia";
		depto.setNome(nome);
		assertEquals(nome, depto.getNome());
	}
	
	@Test
	public void deve_aceitar_nome_apenas_com_letras() {
		depto.setNome("Diretoria");
	}
	
	@Test
	public void deve_aceitar_nome_com_letras_e_espacos() {
		depto.setNome("Novas tecnologias");
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_nome_nulo() {
		depto.setNome(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_em_branco() {
		depto.setNome("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_apenas_com_espaco() {
		depto.setNome(" ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_apenas_com_ponto() {
		depto.setNome(".");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_com_numero() {
		depto.setNome("Departamento 1");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_sem_letras() {
		depto.setNome("           ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_com_caracteres_especiais() {
		depto.setNome("Departamento #");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_composto_unicamente_pelo_mesmo_caractere() {
		depto.setNome("AAAAAA");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_composto_unicamente_pelo_mesmo_caractere_independentemente_de_caixa() {
		depto.setNome("AAaAA");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_de_tamanho_menor_que_2_caracteres() {
		depto.setNome("X");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_de_tamanho_maior_que_55_caracteres() {
		StringBuilder sb = new StringBuilder("Z");
		for(int i=0; i<55; i++)
			sb.append("A");
		depto.setNome(sb.toString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_nome_com_menos_de_uma_letra_independentemente_da_quantidade_de_caracteres_da_string() {
		depto.setNome(" k ");
	}
	
	/*
	 * DESCRICAO
	 * */
	@Test
	public void deve_definir_uma_nova_descricao_para_o_departamento() {
		String descricao = "Departamento de testes";
		depto.setDescricao(descricao);
		assertEquals(descricao, depto.getDescricao());
	}
	
	@Test
	public void deve_aceitar_descricao_com_letras_e_espacos() {
		depto.setDescricao("Departamento do setor estratégico");
	}
	
	@Test
	public void deve_aceitar_descricao_com_letras_espacos_virgula_ponto_e_virgula_e_ponto() {
		depto.setDescricao("Departamento do setor estratégico; diretores, gerentes etc.");
	}
	
	@Test
	public void deve_aceitar_descricao_com_numero() {
		depto.setDescricao("Departamento 1");
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_descricao_nula() {
		depto.setDescricao(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_descricao_em_branco() {
		depto.setDescricao("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_descricao_apenas_com_espaco() {
		depto.setDescricao(" ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_descricao_apenas_com_ponto() {
		depto.setDescricao(".");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_descricao_com_caracteres_especiais() {
		depto.setDescricao("Departamento #");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_descricao_composta_unicamente_pelo_mesmo_caractere() {
		depto.setDescricao("AAAAAA");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_descricao_composta_unicamente_pelo_mesmo_caractere_independentemente_de_caixa() {
		depto.setDescricao("aAaAa");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_descricao_de_tamanho_menor_que_2_caracteres() {
		depto.setDescricao("X");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_descricao_de_tamanho_maior_que_100_caracteres() {
		StringBuilder sb = new StringBuilder("Z");
		for(int i=0; i<100; i++)
			sb.append("A");
		depto.setDescricao(sb.toString());
	}
	
	/*
	 * FUNCIONARIOS
	 * */
	@Test
	public void deve_redefinir_a_lista_de_funcionarios() {
		List<Funcionario> funcionarios= new ArrayList<Funcionario>();
		for(int i=0; i<10; i++) {
			Funcionario f = new Funcionario();
			funcionarios.add(f);
		}
		depto.setFuncionarios(funcionarios);
		assertEquals(funcionarios, depto.getFuncionarios());
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_redefinir_a_lista_de_funcionarios_por_uma_lista_nula() {
		List<Funcionario> funcionarios = null;
		depto.setFuncionarios(funcionarios);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_redefinir_a_lista_de_funcionarios_por_uma_lista_igual() {
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		for(int i=0; i<10; i++) {
			Funcionario f = new Funcionario();
			funcionarios.add(f);
		}
		depto.setFuncionarios(funcionarios);
		depto.setFuncionarios(funcionarios);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_redefinir_a_lista_de_funcionarios_por_uma_lista_que_esteja_vazia() {
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		depto.setFuncionarios(funcionarios);
	}
	
	/*
	 * MÉTODOS
	 * */
	@Test(expected = NullPointerException.class)
	public void nao_deve_realizar_cadastro_em_empresa_nula() {
		emp = null;
		depto.cadastrar(emp);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_realizar_cadastro_de_departamento_repetido() {
		depto.cadastrar(emp);
		depto.cadastrar(emp);
	}
	
	@Test
	public void deve_realizar_cadastro_de_departamento_ainda_nao_existente() {
		depto.cadastrar(emp);
		new Departamento("Finanças", "Assuntos financeiros").cadastrar(emp);
	}
}
