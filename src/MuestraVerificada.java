



public class MuestraVerificada implements EstadoMuestra{
	
	private NivelVerificacion nivelDeVerificacion = NivelVerificacion.VERIFICADA;
	
	
	/**
	 * getter Nivel de verficacion.
	 */
	@Override
	public NivelVerificacion getNivel() {
		return nivelDeVerificacion;
	}
	
	/**
	 * No agrega la opinion porque la muestra ya esta Verificada.
	 */
	@Override
	public void addOpinion(Muestra m, Opinion o) {
		if (this.laOpinionEsDeExperto(o)){
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
