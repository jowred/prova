package br.com.contmatic.enums;

// TODO: Auto-generated Javadoc
/**
 * The Enum EnumTipoTelefone.
 */
public enum EnumTipoTelefone {

	/** The celular. */
	CELULAR("Celular", 9),
	
	/** The telefone fixo. */
	TELEFONE_FIXO("Telefone fixo", 8);
	
	/** The descricao. */
	private String descricao;
	
	/** The tamanho. */
	private int tamanho;
	
	/**
	 * Instantiates a new enum tipo telefone.
	 *
	 * @param descricao the descricao
	 * @param tamanho the tamanho
	 */
	private EnumTipoTelefone(String descricao, int tamanho) {
		this.descricao = descricao;
		this.tamanho = tamanho;
	}

	/**
	 * Gets the descricao.
	 *
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Gets the tamanho.
	 *
	 * @return the tamanho
	 */
	public int getTamanho() {
		return tamanho;
	}
}
