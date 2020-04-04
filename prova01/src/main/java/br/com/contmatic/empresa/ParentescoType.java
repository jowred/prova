package br.com.contmatic.empresa;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Enum EnumTipoParentesco.
 */
public enum ParentescoType {

	/** The nao declarado. */
	NAO_DECLARADO("Não declarado"),
	
	/** The filho. */
	FILHO("Filho"),
	
	/** The avo. */
	AVO("Avô/avó"),
	
	/** The mae. */
	MAE("Mãe"),
	
	/** The pai. */
	PAI("Pai"),
	
	/** The tio. */
	TIO("Tio"),
	
	/** The sobrinho. */
	SOBRINHO("Sobrinho"),
	
	/** The primo. */
	PRIMO("Primo"),
	
	/** The enteado. */
	ENTEADO("Enteado");
	
	/** The descricao. */
	private String descricao;
	
	/**
	 * Instantiates a new enum tipo parentesco.
	 *
	 * @param descricao the descricao
	 */
	private ParentescoType(String descricao) {
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
