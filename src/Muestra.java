import java.time.LocalDateTime;
import java.util.List;

public class Muestra {
	private Ubicacion ubicacion;
	private List<ZonaCobertura> zonasDePertenencia;

	public LocalDateTime getFechaCreacion() {
		// TODO Auto-generated method stub
		return null;
	}

	public LocalDateTime getUltimaFechaVotacion() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getNivelVerificacion() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object obtenerResultadoActual() {
		// TODO Auto-generated method stub
		return null;
	}

	public void agregarOpinion(Opinion nuevaOpinion) {
		// TODO Auto-generated method stub
		
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void agregarZona(ZonaCobertura zona) {
		this.zonasDePertenencia.add(zona);
	}

	public void notificarValidacion() {
		for (ZonaCobertura zona : zonasDePertenencia) {
			zona.avisarValidacionMuestra(this);
		}
	}
		// TODO En el estado correspondiente de la muestra, sumar la llamada al método notificarValidacion cuando la muestra pasa a verificada.
}


