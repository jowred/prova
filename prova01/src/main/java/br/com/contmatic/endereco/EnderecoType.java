package br.com.contmatic.endereco;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Enum EnumTipoEndereco.
 */
public enum EnderecoType{

	/** The alameda. */
	ALAMEDA("Alameda"),
	
	/** The avenida. */
	AVENIDA("Avenida"),
	
	/** The campo. */
	CAMPO("Campo"),
	
	/** The chacara. */
	CHACARA("Chácara"),
	
	/** The colonia. */
	COLONIA("Colônia"),
	
	/** The condominio. */
	CONDOMINIO("Condomínio"),
	
	/** The conjunto. */
	CONJUNTO("Conjunto"),
	
	/** The estrada. */
	ESTRADA("Estrada"),
	
	/** The largo. */
	LARGO("Largo"),
	
	/** The loteamento. */
	LOTEAMENTO("Loteamento"),
	
	/** The residencial. */
	RESIDENCIAL("Residencial"),
	
	/** The rodovia. */
	RODOVIA("Rodovia"),
	
	/** The rua. */
	RUA("Rua"),
	
	/** The sitio. */
	SITIO("Sítio"),
	
	/** The travessa. */
	TRAVESSA("Travessa"),	
	
	/** The via. */
	VIA("Via"),
	
	/** The viaduto. */
	VIADUTO("Viaduto"),
	
	/** The viela. */
	VIELA("Viela");
	
	/** The descricao. */
	private String descricao;
	
	/**
	 * Instantiates a new enum tipo endereco.
	 *
	 * @param descricao the descricao
	 */
	private EnderecoType(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Gets the descricao.
	 *
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
