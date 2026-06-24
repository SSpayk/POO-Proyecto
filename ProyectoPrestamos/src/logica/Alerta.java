package logica;

import java.io.Serializable;
import java.time.LocalDateTime;


public class Alerta implements Serializable {
	private LocalDateTime fecha;
	private Boolean alarmaRecurrente;
	private Integer repeticiones;
	private Integer intervalo;
	
	public Alerta(LocalDateTime fecha,Boolean alarmaRecurrente,Integer repeticiones,Integer intervalo) {
		this.fecha = fecha;
		this.alarmaRecurrente = alarmaRecurrente;
		this.repeticiones = repeticiones;
		this.intervalo = intervalo;
	}

	public Boolean esAlarmaRecurrente() {
		return alarmaRecurrente;
	}

	public void setAlarmaRecurrente(Boolean alarmaRecurrente) {
		this.alarmaRecurrente = alarmaRecurrente;
	}

	public Integer getRepeticiones() {
		return repeticiones;
	}

	public void setRepeticiones(Integer repeticiones) {
		this.repeticiones = repeticiones;
	}

	public Integer getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(Integer intervalo) {
		this.intervalo = intervalo;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}
}
