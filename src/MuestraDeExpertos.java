


public class MuestraDeExpertos implements EstadoMuestra{
	
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
		if(this.laOpinionEsDeExperto(o)) {
			m.doAddOpinion(o);
		} else {}
		
		this.verificarSiPasaDeEstado(m, o);
	}
	
	@Override
	public boolean laOpinionEsDeExperto(Opinion o) {
		return o.getNivelConocimiento() == NivelParticipante.EXPERTO;
	}
	
	public void verificarSiPasaDeEstado(Muestra m, Opinion o) {
		if(m.resultadoActualOpiniones() == o.getTipo()) { // Como cuando cambia a muestra de expertos se elimanan las opiniones de los particpantes basicos cuandos suceda la primera coincidencia de qie los expertos voten los mismo va a cambiar de estado.
			m.setEstado(new MuestraVerificada());
			m.notificarValidacion();
		}
	}

}
