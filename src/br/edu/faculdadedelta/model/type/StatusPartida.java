package br.edu.faculdadedelta.model.type;

public enum StatusPartida {
	
	NAO_INICIADO("Jogo n�o iniciado"),
	PRIMEIRO_TEMPO("1� Tempo"),
	SEGUNDO_TEMPO("2� Tempo"),
	ENCERRADO("Partida Encerrada");
	
	private String descricao;

	private StatusPartida(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
