package br.edu.faculdadedelta.model.type;

public enum StatusPartida {
	
	NAO_INICIADO("Jogo não iniciado"),
	PRIMEIRO_TEMPO("1º Tempo"),
	SEGUNDO_TEMPO("2º Tempo"),
	ENCERRADO("Partida Encerrada");
	
	private String descricao;

	private StatusPartida(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
