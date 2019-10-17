package br.com.contmatic.empresa;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.contmatic.empresa.Dependente;
import br.com.contmatic.empresa.Funcionario;

public class DependenteTest {
	
	Dependente dependente;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		System.out.println("Iniciando testes da classe Dependente...");
	}
	
	@Before
	public void setUp() {
		String nome = "Vitória";
		String rg = "516879541";
		String cpf = "50736121080";
		dependente = new Dependente();
		dependente.setNome(nome);
		dependente.setRg(rg);
		dependente.setCpf(cpf);
	}
	
	@After
	public void tearDown() {
		dependente = null;
	}
	
	@AfterClass
	public static void tearDownAfterClass() {
		System.out.println("Encerrando testes da classe Dependente.");
	}
	
	@Test
	public void deve_retornar_false_para_indicar_que_o_metodo_toString_esta_sobrescrito() {
		assertFalse(new Dependente().toString().contains("["));
	}
	
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_dependente() {
		String nome = dependente.getNome();
		String rg = dependente.getRg();
		String cpf = dependente.getCpf();
		Dependente d1 = new Dependente();
		d1.setNome(nome);
		d1.setRg(rg);
		d1.setCpf(cpf);
		assertEquals(dependente, d1);
	}
	
	//equals
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_ambos_tem_o_mesmo_cpf() {
		String nome = "Vitória M.";
		String rg = "457894561";
		String cpf = dependente.getCpf();
		Dependente d1 = new Dependente();
		d1.setNome(nome);
		d1.setRg(rg);
		d1.setCpf(cpf);
		assertTrue(dependente.equals(d1));
	}
	
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_sao_o_mesmo_objeto() {
		Dependente dep = dependente;
		dependente.equals(dep);
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_cpf_do_objeto2_e_diferente() {
		String nome = dependente.getNome();
		String rg = dependente.getRg();
		String cpf = "73563445052";
		Dependente d1 = new Dependente();
		d1.setNome(nome);
		d1.setRg(rg);
		d1.setCpf(cpf);
		assertFalse(dependente.equals(d1));
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_equals_sobrescrito_porque_os_objetos_sao_de_classes_diferentes() {
		assertFalse(dependente.equals(new Object()));
	}
	
	//hashcode
	@Test
	public void deve_apontar_igualdade_entre_os_objetos_funcionario_usando_hashcode_sobrescrito() {
		String nome = dependente.getNome();
		String rg = dependente.getRg();
		String cpf = dependente.getCpf();
		Dependente d1 = new Dependente();
		d1.setNome(nome);
		d1.setRg(rg);
		d1.setCpf(cpf);
		assertEquals(dependente.hashCode(), d1.hashCode());
	}
	
	@Test
	public void nao_deve_apontar_igualdade_entre_os_objetos_usando_hashcode_sobrescrito() {
		String nome = "Joseph Stewart";
		String rg = "413720433";
		String cpf = "22104854016";
		Dependente d1 = new Dependente();
		d1.setNome(nome);
		d1.setRg(rg);
		d1.setCpf(cpf);
		assertNotEquals(dependente.hashCode(), d1.hashCode());
	}
	
	/*
	 * PROVEDOR
	 * */
	@Test
	public void deve_definir_um_provedor_para_o_dependente() {
		Funcionario provedor = new Funcionario();
		dependente.setProvedor(provedor);
		assertEquals(provedor, dependente.getProvedor());
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_provedor_nulo() {
		dependente.setProvedor(null);
	}
	
	/*
	 * PARENTESCO
	 * */
	@Test
	public void deve_definir_um_parentesco_para_o_dependente() {
		String parentesco = "Filho";
		dependente.setParentesco(parentesco);
		assertEquals(parentesco, dependente.getParentesco());
	}
	
	@Test
	public void deve_aceitar_parentesco_apenas_com_letras() {
		dependente.setParentesco("Enteado");
	}
	
	@Test
	public void deve_aceitar_parentesco_com_letras_e_espacos() {
		dependente.setParentesco("Filho adotivo");
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_aceitar_parentesco_nulo() {
		dependente.setParentesco(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_parentesco_em_branco() {
		dependente.setParentesco("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_parentesco_apenas_com_espaco() {
		dependente.setParentesco(" ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_parentesco_apenas_com_ponto() {
		dependente.setParentesco(".");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_parentesco_com_numero() {
		dependente.setParentesco("Filho 1");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_parentesco_sem_letras() {
		dependente.setParentesco("           ");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_parentesco_com_caracteres_especiais() {
		dependente.setParentesco("Filh#");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_parentesco_composto_unicamente_pelo_mesmo_caractere() {
		dependente.setParentesco("AAAAAA");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_parentesco_composto_unicamente_pelo_mesmo_caractere_independentemente_de_caixa() {
		dependente.setParentesco("AAaAA");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_parentesco_de_tamanho_menor_que_3_caracteres() {
		dependente.setParentesco("XX");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_parentesco_de_tamanho_maior_que_55_caracteres() {
		StringBuilder sb = new StringBuilder("Z");
		for(int i=0; i<55; i++)
			sb.append("A");
		dependente.setParentesco(sb.toString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_parentesco_com_menos_de_uma_letra_independentemente_da_quantidade_de_caracteres_da_string() {
		dependente.setParentesco(" k    ");
	}
	
	/*
	 * IDADE
	 * */
	@Test
	public void deve_definir_uma_idade_para_o_dependente() {
		int idade = 8;
		dependente.setIdade(idade);
		assertEquals(idade, dependente.getIdade());
	}
	
	@Test
	public void deve_aceitar_idade_positiva() {
		dependente.setIdade(17);
	}
	
	@Test
	public void deve_aceitar_idade_igual_a_0() {
		dependente.setIdade(0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_idade_negativa() {
		dependente.setIdade(-2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_aceitar_idade_maior_que_120() {
		dependente.setIdade(155);
	}
	
	/*
	 * MÉTODOS
	 * */
	@Test
	public void deve_redefinir_o_objeto_funcionario_provedor_do_dependente_para_o_objeto_passado_como_parametro_para_o_cadastro() {
		Funcionario fun = new Funcionario("José da Silva Carvoeiro", "260129124", "99881490006");
		dependente.cadastrar(fun);
		assertEquals(fun, dependente.getProvedor());
	}
	
	@Test
	public void deve_realizar_cadastro_como_dependente_na_lista_de_um_funcionario() {
		Funcionario fun = new Funcionario();
		dependente.cadastrar(fun);
	}
	
	@Test(expected = NullPointerException.class)
	public void nao_deve_realizar_cadastro_com_funcionario_nulo() {
		dependente.cadastrar(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nao_deve_realizar_cadastro_com_funcionario_repetido() {
		Funcionario fun = new Funcionario();
		dependente.cadastrar(fun);
		dependente.cadastrar(fun);
	}
	
	@Test
	public void deve_realizar_cadastro_de_dependente_novo_em_funcionario() {
		Funcionario fun = new Funcionario();
		dependente.cadastrar(fun);
		new Dependente("Joseph da Silva", "118489987", "89012988055").cadastrar(fun);
	}
}
