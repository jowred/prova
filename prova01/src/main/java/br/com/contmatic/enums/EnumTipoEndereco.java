package br.com.contmatic.enums;

public enum EnumTipoEndereco{

	ALAMEDA("Alameda"),
	AVENIDA("Avenida"),
	CAMPO("Campo"),
	CHACARA("Chácara"),
	COLONIA("Colônia"),
	CONDOMINIO("Condomínio"),
	CONJUNTO("Conjunto"),
	ESTRADA("Estrada"),
	LARGO("Largo"),
	LOTEAMENTO("Loteamento"),
	RESIDENCIAL("Residencial"),
	RODOVIA("Rodovia"),
	RUA("Rua"),
	SITIO("Sítio"),
	TRAVESSA("Travessa"),	
	VIA("Via"),
	VIADUTO("Viaduto"),
	VIELA("Viela");
	
	private String descricao;
	
	private EnumTipoEndereco(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
