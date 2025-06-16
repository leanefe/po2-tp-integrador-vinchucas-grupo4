



public class MuestraVerificada implements EstadoMuestra{
	
	private NivelVerificacion nivelDeVerificacion = NivelVerificacion.VERIFICADA;
	
	public NivelVerificacion getNivelDeVerificacion() {
		return nivelDeVerificacion;
	}

	@Override
	public NivelVerificacion getNivel() {
		return this.getNivelDeVerificacion();
	}

	@Override
	public void addOpinion(Muestra m, Opinion o) {
		if (this.laOpinionEsDeExperto(o)){
			m.doAddOpinion(o);
		}
	}
	
	@Override
	public boolean laOpinionEsDeExperto(Opinion o) {
		return o.getNivelConocimiento() == NivelParticipante.EXPERTO;
	}


}
