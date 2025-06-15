

import java.util.Date;

public class Opinion {
	private Date fechaCreacion;
	private TipoOpinion tipo;
	private NivelParticipante nivelConocimiento;
	
	public Date getFechaCreacion() {
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

	public Opinion(Date fechaCreacion, TipoOpinion tipo, NivelParticipante nivelconocimiento) {
		super();
		this.fechaCreacion = fechaCreacion;
		this.tipo = tipo;
		this.nivelConocimiento = nivelconocimiento;
	}
	
	
	
	
	
	
	
	
}
