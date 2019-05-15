package br.edu.faculdadedelta.model;

import br.edu.faculdadedelta.model.type.StatusPartida;

public class Partida {
	
	private Long id;
	
	private Time timeCasa;
	private Time timeVisitante;
	private StatusPartida statusPartida;
	
	private String horarioPartida;
	private String localPartida;
	
	private int saldoGolCasa;
	private int saldoGolVisitante;
	
	public Partida() {
		super();
	}

	public Partida(Long id, Time timeCasa, Time timeVisitante, StatusPartida statusPartida, String horarioPartida,
			String localPartida, int saldoGolCasa, int saldoGolVisitante) {
		super();
		this.id = id;
		this.timeCasa = timeCasa;
		this.timeVisitante = timeVisitante;
		this.statusPartida = statusPartida;
		this.horarioPartida = horarioPartida;
		this.localPartida = localPartida;
		this.saldoGolCasa = saldoGolCasa;
		this.saldoGolVisitante = saldoGolVisitante;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Time getTimeCasa() {
		return timeCasa;
	}

	public void setTimeCasa(Time timeCasa) {
		this.timeCasa = timeCasa;
	}

	public Time getTimeVisitante() {
		return timeVisitante;
	}

	public void setTimeVisitante(Time timeVisitante) {
		this.timeVisitante = timeVisitante;
	}

	public StatusPartida getStatusPartida() {
		return statusPartida;
	}

	public void setStatusPartida(StatusPartida statusPartida) {
		this.statusPartida = statusPartida;
	}

	public String getHorarioPartida() {
		return horarioPartida;
	}

	public void setHorarioPartida(String horarioPartida) {
		this.horarioPartida = horarioPartida;
	}

	public String getLocalPartida() {
		return localPartida;
	}

	public void setLocalPartida(String localPartida) {
		this.localPartida = localPartida;
	}

	public int getSaldoGolCasa() {
		return saldoGolCasa;
	}

	public void setSaldoGolCasa(int saldoGolCasa) {
		this.saldoGolCasa = saldoGolCasa;
	}

	public int getSaldoGolVisitante() {
		return saldoGolVisitante;
	}

	public void setSaldoGolVisitante(int saldoGolVisitante) {
		this.saldoGolVisitante = saldoGolVisitante;
	}
	
	public String getPlacar() {
		return this.saldoGolCasa + " X " + this.saldoGolVisitante;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partida other = (Partida) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public StatusPartida[] getStatus(){
		   return StatusPartida.values();
		 }
	
}
