package br.com.contmatic.empresa;

import static br.com.contmatic.constantes.Mensagens.MENSAGEM_BAIRRO_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_BAIRRO_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_BAIRRO_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_CEP_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_CEP_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_CEP_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_CIDADE_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_CIDADE_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_CIDADE_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_LOGRADOURO_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_LOGRADOURO_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_LOGRADOURO_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NUMERO_MAX;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_NUMERO_MIN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_PAIS_BLANK;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_PAIS_PATTERN;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_PAIS_TAMANHO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_TIPO_ENDERECO_NULO;
import static br.com.contmatic.constantes.Mensagens.MENSAGEM_UF_NULA;
import static br.com.contmatic.constantes.Numericas.CEP_SIZE;
import static br.com.contmatic.constantes.Numericas.MAX_BAIRRO;
import static br.com.contmatic.constantes.Numericas.MAX_CIDADE;
import static br.com.contmatic.constantes.Numericas.MAX_LOGRADOURO;
import static br.com.contmatic.constantes.Numericas.MAX_NUM_RUA;
import static br.com.contmatic.constantes.Numericas.MAX_PAIS;
import static br.com.contmatic.constantes.Numericas.MIN_BAIRRO;
import static br.com.contmatic.constantes.Numericas.MIN_CIDADE;
import static br.com.contmatic.constantes.Numericas.MIN_LOGRADOURO;
import static br.com.contmatic.constantes.Numericas.MIN_NUM_RUA;
import static br.com.contmatic.constantes.Numericas.MIN_PAIS;
import static br.com.contmatic.constantes.Regex.REGEX_BAIRRO;
import static br.com.contmatic.constantes.Regex.REGEX_CEP;
import static br.com.contmatic.constantes.Regex.REGEX_CIDADE;
import static br.com.contmatic.constantes.Regex.REGEX_LOGRADOURO;
import static br.com.contmatic.constantes.Regex.REGEX_PAIS;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;

import br.com.contmatic.enums.EnumEstadosBrasileiros;
import br.com.contmatic.enums.EnumTipoEndereco;

public class Endereco {

	@Length(min = MIN_LOGRADOURO, max = MAX_LOGRADOURO, message = MENSAGEM_LOGRADOURO_TAMANHO)
	@Pattern(regexp = REGEX_LOGRADOURO, message = MENSAGEM_LOGRADOURO_PATTERN)
	@NotBlank(message = MENSAGEM_LOGRADOURO_BLANK)
	private String logradouro;
	
	@Min(value = MIN_NUM_RUA, message = MENSAGEM_NUMERO_MIN)
	@Max(value = MAX_NUM_RUA, message = MENSAGEM_NUMERO_MAX)
	private Integer numero;
	
	@Length(min = MIN_BAIRRO, max = MAX_BAIRRO, message = MENSAGEM_BAIRRO_TAMANHO)
	@Pattern(regexp = REGEX_BAIRRO, message = MENSAGEM_BAIRRO_PATTERN)
	@NotBlank(message = MENSAGEM_BAIRRO_BLANK)
	private String bairro;
	
	@Length(min = MIN_CIDADE, max = MAX_CIDADE, message = MENSAGEM_CIDADE_TAMANHO)
	@Pattern(regexp = REGEX_CIDADE, message = MENSAGEM_CIDADE_PATTERN)
	@NotBlank(message = MENSAGEM_CIDADE_BLANK)
	private String cidade;
	
	@NotNull(message = MENSAGEM_UF_NULA)
	private EnumEstadosBrasileiros uf;
	
	@Length(min = MIN_PAIS, max = MAX_PAIS, message = MENSAGEM_PAIS_TAMANHO)
	@Pattern(regexp = REGEX_PAIS, message = MENSAGEM_PAIS_PATTERN)
	@NotBlank(message = MENSAGEM_PAIS_BLANK)
	private String pais;
	
	@Length(min = CEP_SIZE, max = CEP_SIZE, message = MENSAGEM_CEP_TAMANHO)
	@Pattern(regexp = REGEX_CEP, message = MENSAGEM_CEP_PATTERN)
	@NotBlank(message = MENSAGEM_CEP_BLANK)
	private String cep;
	
	@NotNull(message = MENSAGEM_TIPO_ENDERECO_NULO)
	private EnumTipoEndereco tipo;
	
	public Endereco() {
		this.setTipoEndereco(EnumTipoEndereco.RUA);
	}

	public Endereco(String logradouro, String bairro, String cidade, EnumEstadosBrasileiros uf, String pais, String cep) {
		this.setLogradouro(logradouro);
		this.setBairro(bairro);
		this.setCidade(cidade);
		this.setUf(uf);
		this.setPais(pais);
		this.setCep(cep);
		this.setTipoEndereco(EnumTipoEndereco.RUA);
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public EnumEstadosBrasileiros getUf() {
		return uf;
	}

	public void setUf(EnumEstadosBrasileiros uf) {
		this.uf = uf;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	public EnumTipoEndereco getTipoEndereco() {
		return tipo;
	}

	public void setTipoEndereco(EnumTipoEndereco tipoEndereco) {
		this.tipo = tipoEndereco;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(this.numero)
				.append(this.cep)
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
		Endereco other = (Endereco)obj;
		return new EqualsBuilder()
				.append(this.numero, other.numero)
				.append(this.cep, other.cep)
				.isEquals();
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}	
}
