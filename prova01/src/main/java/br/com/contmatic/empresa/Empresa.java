package br.com.contmatic.empresa;

import static br.com.contmatic.constantes.Mensagens.MENSAGEM_AREA_ATUACAO_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_AREA_ATUACAO_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_AREA_ATUACAO_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_CNPJ_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_CNPJ_INVALIDO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_EMAIL_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_EMAIL_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_EMAIL_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_ENDERECO_NULL;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NOME_FANTASIA_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NOME_FANTASIA_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NOME_FANTASIA_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_RAZAO_SOCIAL_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_RAZAO_SOCIAL_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_RAZAO_SOCIAL_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_SET_DEPARTAMENTOS_VAZIO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_SET_TELEFONES_VAZIO;
import static br.com.contmatic.constantes.Mensagens.URL_INVALIDA;
import static br.com.contmatic.constantes.Numericas.MAX_AREA_ATUACAO;
import static br.com.contmatic.constantes.Numericas.MAX_EMAIL;
import static br.com.contmatic.constantes.Numericas.MAX_NOME_FANTASIA;
import static br.com.contmatic.constantes.Numericas.MAX_RAZ_SOCIAL;
import static br.com.contmatic.constantes.Numericas.MIN_AREA_ATUACAO;
import static br.com.contmatic.constantes.Numericas.MIN_EMAIL;
import static br.com.contmatic.constantes.Numericas.MIN_NOME_FANTASIA;
import static br.com.contmatic.constantes.Numericas.MIN_RAZ_SOCIAL;
import static br.com.contmatic.constantes.Regex.REGEX_AREA_ATUACAO;
import static br.com.contmatic.constantes.Regex.REGEX_EMAIL;
import static br.com.contmatic.constantes.Regex.REGEX_NOME_FANTASIA;
import static br.com.contmatic.constantes.Regex.REGEX_RAZAO_SOCIAL;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.br.CNPJ;

public class Empresa {
	
	@Length(min = MIN_RAZ_SOCIAL, max = MAX_RAZ_SOCIAL, message = MENSAGEM_RAZAO_SOCIAL_TAMANHO)
	@Pattern(regexp = REGEX_RAZAO_SOCIAL, message = MENSAGEM_RAZAO_SOCIAL_PATTERN)
	@NotBlank(message = MENSAGEM_RAZAO_SOCIAL_BLANK)
	private String razaoSocial;
	
	@Length(min = MIN_NOME_FANTASIA, max = MAX_NOME_FANTASIA, message = MENSAGEM_NOME_FANTASIA_TAMANHO)
	@Pattern(regexp = REGEX_NOME_FANTASIA, message = MENSAGEM_NOME_FANTASIA_PATTERN)
	@NotBlank(message = MENSAGEM_NOME_FANTASIA_BLANK)
	private String nomeFantasia;
	
	@CNPJ(message = MENSAGEM_CNPJ_INVALIDO)
	@NotBlank(message = MENSAGEM_CNPJ_BLANK)
	private String cnpj;
	
	@Length(min = MIN_AREA_ATUACAO, max = MAX_AREA_ATUACAO, message = MENSAGEM_AREA_ATUACAO_TAMANHO)
	@Pattern(regexp = REGEX_AREA_ATUACAO, message = MENSAGEM_AREA_ATUACAO_PATTERN)
	@NotBlank(message = MENSAGEM_AREA_ATUACAO_BLANK )
	private String areaAtuacao;
	
	@NotEmpty(message = MENSAGEM_SET_DEPARTAMENTOS_VAZIO)
	private Set<Departamento> departamentos;
	
	@NotEmpty(message = MENSAGEM_SET_TELEFONES_VAZIO)
	private Set<Telefone> telefones;
	
	@NotNull(message = MENSAGEM_ENDERECO_NULL)
	private Endereco endereco;
	
	@Length(min = MIN_EMAIL, max = MAX_EMAIL, message = MENSAGEM_EMAIL_TAMANHO)
	@Pattern(regexp = REGEX_EMAIL, message = MENSAGEM_EMAIL_PATTERN)
	@NotBlank(message = MENSAGEM_EMAIL_BLANK)
	private String email;
	
	@URL(message = URL_INVALIDA)
	private String site;
	
	public Empresa() {
		this.departamentos = new HashSet<>();
		this.telefones = new HashSet<>();
		this.endereco = new Endereco();
	}
	
	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public String getAreaAtuacao() {
		return areaAtuacao;
	}

	public void setAreaAtuacao(String areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}

	public Set<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(Set<Departamento> departamentos) {
		this.departamentos = departamentos;
	}
	
	public Set<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(this.cnpj)
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
		Empresa other = (Empresa)obj;		
		return new EqualsBuilder()
				.append(this.cnpj, other.cnpj)
				.isEquals();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
