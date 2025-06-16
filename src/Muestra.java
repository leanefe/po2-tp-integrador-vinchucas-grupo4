

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Muestra {
	private String linkFoto;
	private LocalDateTime fechaCreacion;
	private EspecieVinchuca especie;
	private EstadoMuestra estado;
	private List<Opinion> opiniones = new ArrayList<Opinion>();
	private Participante recolector;
	private Ubicacion ubicacion;
	private List<ZonaCobertura> zonasDePertenencia;
	
	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public EspecieVinchuca getEspecie() {
		return especie;
	}
	public void setEspecie(EspecieVinchuca especie) {
		this.especie = especie;
	}
	public EstadoMuestra getEstado() {
		return estado;
	}
	public void setEstado(EstadoMuestra estado) {
		this.estado = estado;
	}
	public List<Opinion> getOpiniones() {
		return opiniones;
	}
	public void setOpiniones(List<Opinion> opiniones) {
		this.opiniones = opiniones;
	}
	public Participante getRecolector() {
		return recolector;
	}
	public void setRecolector(Participante recolector) {
		this.recolector = recolector;
	}
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getLinkFoto() {
		return linkFoto;
	}
	public void setLinkFoto(String linkFoto) {
		this.linkFoto = linkFoto;
	}
	
	
	public Muestra(String linkFoto, LocalDateTime fechaCreacion, EspecieVinchuca especie, EstadoMuestra estado,
			List<Opinion> opiniones, Participante recolector, Ubicacion ubicacion) {
		super();
		this.linkFoto = linkFoto;
		this.fechaCreacion = fechaCreacion;
		this.especie = especie;
		this.estado = new MuestraBasica();
		this.opiniones = opiniones;
		this.recolector = recolector;
		this.ubicacion = ubicacion;
		this.zonasDePertenencia = new ArrayList<>();
	}
	public TipoOpinion resultadoActualOpiniones() { //Ver metodo
		return getOpiniones().stream()
				.collect(Collectors.groupingBy(Opinion::getTipo, Collectors.counting()))
				.entrySet().stream()
				.max(Map.Entry.comparingByValue())
				.map(Map.Entry::getKey)
				.orElse(TipoOpinion.NINGUNA);
	}
	
	public void addOpinion(Opinion o) {
		this.getEstado().addOpinion(this, o);
	}
	
	public void doAddOpinion(Opinion o) {
		this.getOpiniones().add(o);
	}
	
	public boolean votoUnExperto() {
		return this.getOpiniones().stream()
				.anyMatch(o -> o.getNivelConocimiento() == NivelParticipante.EXPERTO);
	}
	
	public void restartOpiniones() {
		this.getOpiniones().clear();
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
	
	public NivelVerificacion getNivelVerificacion() {
		return this.getEstado().getNivel();
	}
	
	public LocalDateTime getUltimaFechaVotacion() {
		return this.getOpiniones().getLast().getFechaCreacion();
	}
}
