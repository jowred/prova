package br.com.contmatic.enums;

public enum EnumTipoParentesco {

	NAO_DECLARADO("Não declarado"),
	FILHO("Filho"),
	AVO("Avô/avó"),
	MAE("Mãe"),
	PAI("Pai"),
	TIO("Tio"),
	SOBRINHO("Sobrinho"),
	PRIMO("Primo"),
	ENTEADO("Enteado");
	
	private String descricao;
	
	private EnumTipoParentesco(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
