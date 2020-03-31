package br.com.contmatic.enums;

// TODO: Auto-generated Javadoc
/**
 * The Enum EnumTipoParentesco.
 */
public enum EnumTipoParentesco {

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
	private EnumTipoParentesco(String descricao) {
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
}
