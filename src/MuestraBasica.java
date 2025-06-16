



public class MuestraBasica implements EstadoMuestra {
	
	private NivelVerificacion nivelDeVerificacion = NivelVerificacion.VOTADA;

	public NivelVerificacion getNivelDeVerificacion() {
		return nivelDeVerificacion;
	}

	@Override
	public NivelVerificacion getNivel() {
		return this.getNivelDeVerificacion();
	}

	@Override
	public void addOpinion(Muestra m, Opinion o) {
	if	(this.laOpinionEsDeExperto(o)){
		this.pasarDeEstado(m,o);
	} else {}
		
	}
	
	public void pasarDeEstado(Muestra m, Opinion o) {
			m.setEstado(new MuestraDeExpertos());
			m.restartOpiniones(); //elimina las opinines de los participantes basicos que para despues calcular su resultado actual sea correcto.
			m.doAddOpinion(o);
	}
	
	@Override
	public boolean laOpinionEsDeExperto(Opinion o) {
		return o.getNivelConocimiento() == NivelParticipante.EXPERTO;
	}
}
