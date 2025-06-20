



public class MuestraVerificada implements EstadoMuestra{
	
	private NivelVerificacion nivelDeVerificacion = NivelVerificacion.VERIFICADA;
	
	/**
	 * getter Nivel de Verficacion
	 * @return NivelVerificacion
	 */
	public NivelVerificacion getNivelDeVerificacion() {
		return nivelDeVerificacion;
	}
	
	/**
	 * getter Nivel de verficacion.
	 */
	@Override
	public NivelVerificacion getNivel() {
		return this.getNivelDeVerificacion();
	}
	
	/**
	 * Agrega la opnion dependiendo si cumple la condicion o no.
	 */
	@Override
	public void addOpinion(Muestra m, Opinion o) {
		if (this.laOpinionEsDeExperto(o)){
			m.doAddOpinion(o);
		}
	}
	
	/**
	 * Verfica si la opnion es de un experto o no.
	 */
	@Override
	public boolean laOpinionEsDeExperto(Opinion o) {
		return o.getNivelConocimiento() == NivelParticipante.EXPERTO;
	}


}
