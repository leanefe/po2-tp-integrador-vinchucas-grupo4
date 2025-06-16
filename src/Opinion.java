


import java.time.LocalDateTime;

public class Opinion {
	private LocalDateTime fechaCreacion;
	private TipoOpinion tipo;
	private NivelParticipante nivelConocimiento;
	
	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}
	
	public TipoOpinion getTipo() {
		return tipo;
	}

	public NivelParticipante getNivelConocimiento() {
		return nivelConocimiento;
	}

	public Opinion(TipoOpinion tipo, NivelParticipante nivelconocimiento) {
		this.fechaCreacion = LocalDateTime.now();
		this.tipo = tipo;
		this.nivelConocimiento = nivelconocimiento;
	}
	
	
	
	
	
	
	
	
}
