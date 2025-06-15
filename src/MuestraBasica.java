



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
	if	(! m.votoUnExperto()){
		m.doAddOpinion(o);
	} else {}
		this.verificarSiPasaDeEstado(m,o);
		
	}
	
	public void verificarSiPasaDeEstado(Muestra m, Opinion o) {
		if(this.laOpinionEsDeExperto(o)) {
			m.setEstado(new MuestraDeExpertos());
			m.restartOpiniones(); //elimina las opinines de los participantes basicos que para despues calcular su resultado actual sea correcto.
		}
	}
	
	@Override
	public boolean laOpinionEsDeExperto(Opinion o) {
		return o.getNivelConocimiento() == NivelParticipante.EXPERTO;
	}
}
