



public class MuestraBasica implements EstadoMuestra {
	
	private NivelVerificacion nivelDeVerificacion = NivelVerificacion.VOTADA;

	
	/**
	 * getter Nivel de verficacion.
	 */
	@Override
	public NivelVerificacion getNivel() {
		return nivelDeVerificacion;
	}
	
	/**
	 * Verifica dependiendo la condicion si hay que pasar de estado y de todas maneras agrega la opinion.
	 */
	@Override
	public void addOpinion(Muestra m, Opinion o) {
		if	(this.laOpinionEsDeExperto(o)){
			this.pasarDeEstado(m,o);
		} else { m.doAddOpinion(o); }
	}

	/**
	 * Valida si la opnion es de un experto o no.
	 */
	private boolean laOpinionEsDeExperto(Opinion o) {
		return o.getNivelConocimiento() == NivelParticipante.EXPERTO;
	}

	/**
	 * La muestra pasa de estado MuestraDeExpertos, eliminando las opniones anteriores y agregando la nueva.
	 */
	private void pasarDeEstado(Muestra m, Opinion o) {
			m.setEstado(new MuestraDeExpertos());
			m.restartOpiniones(); //elimina las opinines de los participantes basicos para que después el cálculo del resultado actual sea correcto.
			m.doAddOpinion(o);
	}
}
