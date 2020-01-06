package br.com.contmatic.enums;

public enum EnumTipoTelefone {

	CELULAR("Celular", 9),
	TELEFONE_FIXO("Telefone fixo", 8);
	
	private String descricao;
	private int tamanho;
	
	private EnumTipoTelefone(String descricao, int tamanho) {
		this.descricao = descricao;
		this.tamanho = tamanho;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getTamanho() {
		return tamanho;
	}
}
