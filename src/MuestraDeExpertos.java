


public class MuestraDeExpertos implements EstadoMuestra{
	
	private NivelVerificacion nivelDeVerificacion = NivelVerificacion.VOTADA;

	
	/**
	 * getter Nivel de verficacion.
	 */
	@Override
	public NivelVerificacion getNivel() {
		return nivelDeVerificacion;
	}
	
	/**
	 * Agrega la opnion a la muestra y veriffica si pasa de estado o no, solo si pasa la condicion.
	 */
	@Override
	public void addOpinion(Muestra m, Opinion o) {
		if(this.laOpinionEsDeExperto(o)) {
			m.doAddOpinion(o);
			this.verificarSiPasaDeEstado(m, o);
		} else {}
	}
	
	/**
	 * Condicion si la opinion es de un experto o no.
	 */
	@Override
	public boolean laOpinionEsDeExperto(Opinion o) {
		return o.getNivelConocimiento() == NivelParticipante.EXPERTO;
	}
	
	/**
	 * Verifica si pasa de estado, si lo hace pasa al estado MuestraVerficada y notifica a las zonas de cobertura.
	 * @param Muestra
	 * @param Opinion
	 */
	public void verificarSiPasaDeEstado(Muestra m, Opinion o) {
		if(m.resultadoActualOpiniones() == o.getTipo()) { // Como cuando cambia a muestra de expertos se elimanan las opiniones de los particpantes basicos cuandos suceda la primera coincidencia de qie los expertos voten los mismo va a cambiar de estado.
			m.setEstado(new MuestraVerificada());
			m.notificarValidacion();
		}
	}

}
