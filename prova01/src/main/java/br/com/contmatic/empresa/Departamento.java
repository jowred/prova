package br.com.contmatic.empresa;

import static br.com.contmatic.constantes.Numericas.MAX_DESCRICAO;
import static br.com.contmatic.constantes.Numericas.MAX_NOME;
import static br.com.contmatic.constantes.Numericas.MAX_NOME_DEPTO;
import static br.com.contmatic.constantes.Numericas.MIN_DESCRICAO;
import static br.com.contmatic.constantes.Numericas.MIN_NOME;
import static br.com.contmatic.constantes.Numericas.MIN_NOME_DEPTO;
import static br.com.contmatic.constantes.Numericas.PRIMEIRO_INDICE;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;

public class Departamento {
	
	@Length(min = MIN_NOME_DEPTO, max = MAX_NOME_DEPTO, message = "Nome deve ter de {min} a {max} caracteres.")
	@NotBlank(message = "Nome do departamento não pode ser nulo ou vazio.")
	@Pattern(regexp = "")
	private String nome;
	
	@Length(min = MIN_DESCRICAO, max = MAX_DESCRICAO, message = "Descrição deve ter de {min} a {max} caracteres.")
	@NotBlank(message = "Descrição não pode ser nula ou vazia.")
	private String descricao;
	
	@NotEmpty(message = "A coleção de funcionários não pode estar vazia ou ser nula.")
	private Set<Funcionario> funcionarios;
	
	public Departamento() {
		this.funcionarios = new HashSet<>();
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
//		checkNotNull(nome, "Nome do departamento não pode ser nulo.");
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

	public Set<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(Set<Funcionario> funcionarios) {
		checkSetFuncionariosNulo(funcionarios);		
		checkSetFuncionariosVazio(funcionarios);
		checkNovoSetFuncionariosIgualAntigo(funcionarios);
		this.funcionarios = funcionarios;
	}

	public boolean cadastrar(Empresa emp) {
		checkEmpresaNula(emp);
		return emp.getDepartamentos().add(this);
	}

	private void checkEmpresaNula(Empresa emp) {
		if(emp == null) {
			throw new NullPointerException("A empresa passada como parâmetro não pode ser nula.");
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
		if(qtdeLetras < MIN_NOME_DEPTO || nome.length() > MAX_NOME_DEPTO) {
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
		if(repetidos == descricao.length() - PRIMEIRO_INDICE) {
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
				throw new IllegalArgumentException("Descrição do departamento pode ser composta apenas por letras, espaços e caracteres de pontuação (\",\", \";\", \".\".");
			}
		}
	}

	private void checkDescricaoQuantidadeLetras(String descricao) {
		if(descricao.length() < MIN_DESCRICAO || descricao.length() > MAX_DESCRICAO) {
			throw new IllegalArgumentException("Descrição do departamento deve ter no mínimo 2 e no máximo 100 caracteres.");
		}
	}

	private void checkDescricaoComecaComLetra(String descricao) {
		if(!Character.isAlphabetic(descricao.charAt(0))) {
			throw new IllegalArgumentException("Descrição deve obrigatoriamente começar com uma letra.");
		}
	}

	private void checkDescricaoVazia(String descricao) {
		if(descricao.equals("")) {
			throw new IllegalArgumentException("Descrição do departamento não pode estar em branco.");
		}
	}

	private void checkDescricaoNula(String descricao) {
		if(descricao == null) {
			throw new NullPointerException("Descrição do departamento não pode ser nula.");
		}
	}
	
	private void checkNovoSetFuncionariosIgualAntigo(Set<Funcionario> funcionarios) {
		if(this.funcionarios.equals(funcionarios)) {
			throw new IllegalArgumentException("O set de funcionários a ser inserido não pode ser idêntico ao atual.");
		}
	}
	
	private void checkSetFuncionariosVazio(Set<Funcionario> funcionarios) {
		if(funcionarios.isEmpty()) {
			throw new IllegalArgumentException("O set de funcionários a ser inserido não pode estar vazio (tamanho 0).");
		}
	}
	
	private void checkSetFuncionariosNulo(Set<Funcionario> funcionarios) {
		if(funcionarios == null) {
			throw new NullPointerException("O set de funcionários não pode ser nulo.");
		}
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(this.descricao)
				.append(this.funcionarios)
				.append(this.nome)
				.toHashCode();
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
		return new EqualsBuilder()
				.append(this.descricao, other.descricao)
				.append(this.funcionarios, other.funcionarios)
				.append(this.nome, other.nome)
				.isEquals();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
