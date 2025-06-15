


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
	public void setNivelconocimiento(NivelParticipante nivelconocimiento) {
		this.nivelConocimiento = nivelconocimiento;
	}

	public Opinion(LocalDateTime fechaCreacion, TipoOpinion tipo, NivelParticipante nivelconocimiento) {
		super();
		this.fechaCreacion = fechaCreacion;
		this.tipo = tipo;
		this.nivelConocimiento = nivelconocimiento;
	}
	
	
	
	
	
	
	
	
}
