package br.com.contmatic.empresa;

import java.util.ArrayList;
import java.util.List;

public class Departamento {
	
	private static final int PRIMEIRO_INDICE = 1;

	private static final int MIN_NOME = 2;

	private static final int MAX_NOME = 55;
	
	private static final int MIN_DESCRICAO = 2;
	
	private static final int MAX_DESCRICAO = 100;

	private String nome;
	
	private String descricao;
	
	private List<Funcionario> funcionarios;
	
	public Departamento() {
		this.funcionarios = new ArrayList<>();
	}
	
	public Departamento(String nome, String descricao) {
		this();
		this.setNome(nome);
		this.setDescricao(descricao);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		checkNomeNulo(nome);	
		checkNomeVazio(nome);
		checkNomeQuantidadeLetras(nome);
		checkNomeCaracteresValidos(nome);
		checkNomeCompostoPorApenasUmaLetra(nome);
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		checkDescricaoNula(descricao);		
		checkDescricaoVazia(descricao);		
		checkDescricaoComecaComLetra(descricao);		
		checkDescricaoQuantidadeLetras(descricao);		
		checkDescricaoCaracteresValidos(descricao);
		checkDescricaoCompostaPorUmaUnicaLetra(descricao);
		this.descricao = descricao;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		checkListaFuncionariosNula(funcionarios);		
		checkListaFuncionariosVazia(funcionarios);
		checkNovaListaFuncionariosIgualAntiga(funcionarios);
		this.funcionarios = funcionarios;
	}

	public boolean cadastrar(Empresa emp) {
		checkEmpresaNula(emp);
		checkDepartamentoRepetido(emp);
		return emp.getDepartamentos().add(this);
	}

	private void checkEmpresaNula(Empresa emp) {
		if(emp == null) {
			throw new NullPointerException("A empresa passada como parâmetro não pode ser nula.");
		}
	}

	private void checkDepartamentoRepetido(Empresa emp) {
		List<Departamento> d = emp.getDepartamentos();
		for(int i=0; i<emp.getDepartamentos().size(); i++) {
			if(d.get(i).equals(this)) {
				throw new IllegalArgumentException("A empresa passada como parâmetro já possui este departamento.");
			}
		}
	}
	
	private void checkNomeCompostoPorApenasUmaLetra(String nome) {
		int repetidos = contarLetrasRepetidas(nome);
		if (repetidos == nome.length() - PRIMEIRO_INDICE) {
			throw new IllegalArgumentException("Nome do departamento não pode ser composto unicamente pelo mesmo caractere.");
		}
	}

	private int contarLetrasRepetidas(String nome) {
		String temp = nome.toLowerCase();
		char primeiro = temp.charAt(0);
		int repetidos = 0;
		for (int i = 0; i < nome.length() - PRIMEIRO_INDICE; i++) {
			if (primeiro == temp.charAt(i + PRIMEIRO_INDICE)) {
				repetidos++;
			} else {
				break;
			}
		}
		return repetidos;
	}

	private void checkNomeCaracteresValidos(String nome) {
		for(int i=0; i<nome.length(); i++) {
			if(!Character.isAlphabetic(nome.charAt(i)) &&
					(nome.charAt(i) != ' ')) {
				throw new IllegalArgumentException("Nome do departamento pode ser composto apenas por letras e espaços.");
			}
		}
	}

	private void checkNomeQuantidadeLetras(String nome) {
		int qtdeLetras = contarQtdeLetras(nome);
		if(qtdeLetras < MIN_NOME || nome.length() > MAX_NOME) {
			throw new IllegalArgumentException("Nome do departamento deve ter no mínimo 2 e no máximo 55 caracteres, e ter ao menos 2 letras.");
		}
	}

	private int contarQtdeLetras(String nome) {
		int qtdeLetras = 0;
		for(int i=0; i<nome.length(); i++) {
			if(Character.isAlphabetic(nome.charAt(i))) {
				qtdeLetras++;
			}
		}
		return qtdeLetras;
	}

	private void checkNomeVazio(String nome) {
		if(nome.equals("")) {
			throw new IllegalArgumentException("Nome do departamento não pode estar em branco.");
		}
	}

	private void checkNomeNulo(String nome) {
		if(nome == null) {
			throw new NullPointerException("Nome do departamento não pode ser nulo.");
		}
	}
	
	private void checkDescricaoCompostaPorUmaUnicaLetra(String descricao) {
		int repetidos = contarLetrasRepetidas(descricao);
		if(repetidos == descricao.length()-PRIMEIRO_INDICE) {
			throw new IllegalArgumentException("Descrição do departamento não pode ser composta unicamente pelo mesmo caractere.");
		}
	}

	private void checkDescricaoCaracteresValidos(String descricao) {
		for(int i=0; i<descricao.length(); i++) {
			if(!Character.isAlphabetic(descricao.charAt(i)) &&
					!Character.isDigit(descricao.charAt(i)) &&
					(descricao.charAt(i) != ' ') &&
					(descricao.charAt(i) != ',') &&
					(descricao.charAt(i) != ';') &&
					(descricao.charAt(i) != '.')) {
				throw new IllegalArgumentException("Descrição do departamento pode ser composto apenas por letras, espaços e caracteres de pontuação (\",\", \";\", \".\".");
			}
		}
	}

	private void checkDescricaoQuantidadeLetras(String descricao) {
		if(descricao.length() < MIN_DESCRICAO || descricao.length() > MAX_DESCRICAO)
			throw new IllegalArgumentException("Descrição do departamento deve ter no mínimo 2 e no máximo 100 caracteres.");
	}

	private void checkDescricaoComecaComLetra(String descricao) {
		if(!Character.isAlphabetic(descricao.charAt(0)))
			throw new IllegalArgumentException("Nome deve obrigatoriamente começar com uma letra.");
	}

	private void checkDescricaoVazia(String descricao) {
		if(descricao.equals(""))
			throw new IllegalArgumentException("Descrição do departamento não pode estar em branco.");
	}

	private void checkDescricaoNula(String descricao) {
		if(descricao == null)
			throw new NullPointerException("Descrição do departamento não pode ser nula.");
	}
	
	private void checkNovaListaFuncionariosIgualAntiga(List<Funcionario> funcionarios) {
		if(this.funcionarios.equals(funcionarios)) {
			throw new IllegalArgumentException("A lista de funcionários a ser inserida não pode ser idêntica à atual.");
		}
	}
	
	private void checkListaFuncionariosVazia(List<Funcionario> funcionarios) {
		if(funcionarios.isEmpty()) {
			throw new IllegalArgumentException("A lista de funcionários a ser inserida não pode estar vazia (tamanho 0).");
		}
	}
	
	private void checkListaFuncionariosNula(List<Funcionario> funcionarios) {
		if(funcionarios == null) {
			throw new NullPointerException("A lista de funcionários não pode ser nula.");
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + descricao.hashCode();
		result = prime * result + funcionarios.hashCode();
		result = prime * result + nome.hashCode();
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento)obj;
		if(!nome.equals(other.nome))
			return false;
		if(!funcionarios.equals(other.funcionarios))
			return false;
		if(!descricao.equals(other.descricao))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Nome do departamento: " + nome + "\n"
				+ "Descrição: " + descricao + "\n";
	}
}
