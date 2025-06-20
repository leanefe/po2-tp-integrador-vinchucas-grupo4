


import java.time.LocalDateTime;

public class Opinion {
	private LocalDateTime fechaCreacion;
	private TipoOpinion tipo;
	private NivelParticipante nivelConocimiento;
	
	/**
	 * getter Fecha de Creacion
	 * @return LocalDateTime
	 */
	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}
	
	/**
	 * getter Tipo
	 * @return TipoOpinion
	 */
	public TipoOpinion getTipo() {
		return tipo;
	}
	
	/**
	 * getter Nivel de Conocimiento del participante.
	 * @return NivelParticipante
	 */
	public NivelParticipante getNivelConocimiento() {
		return nivelConocimiento;
	}
	
	/**
	 * Constructor de Opinion
	 * @param tipo
	 * @param nivelconocimiento
	 */
	public Opinion(TipoOpinion tipo, NivelParticipante nivelconocimiento) {
		this.fechaCreacion = LocalDateTime.now();
		this.tipo = tipo;
		this.nivelConocimiento = nivelconocimiento;
	}
	
	
	
	
	
	
	
	
}
