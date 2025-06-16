

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
	
	public EspecieVinchuca getEspecie() {
		return especie;
	}
	
	public EstadoMuestra getEstado() {
		return estado;
	}
	public void setEstado(EstadoMuestra estado) {
		this.estado = estado;
	}
	public List<Opinion> getOpiniones() {
		return new ArrayList<>(opiniones);
	}
	
	public Participante getRecolector() {
		return recolector;
	}
	
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	
	public String getLinkFoto() {
		return linkFoto;
	}
	
	public Muestra(String linkFoto, EspecieVinchuca especie, Participante recolector, Ubicacion ubicacion) {
		super();
		this.linkFoto = linkFoto;
		this.fechaCreacion = LocalDateTime.now();
		this.especie = especie;
		this.estado = new MuestraBasica();
		this.opiniones = new ArrayList<>();
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
	
	public void restartOpiniones() {
		this.opiniones.clear();
	}

	public void agregarZona(ZonaCobertura zona) {
		this.zonasDePertenencia.add(zona);
	}

	public void notificarValidacion() {
		for (ZonaCobertura zona : zonasDePertenencia) {
			zona.avisarValidacionMuestra(this);
		}
	}
	
	public NivelVerificacion getNivelVerificacion() {
		return this.getEstado().getNivel();
	}
	
	public LocalDateTime getUltimaFechaVotacion() {
		return this.getOpiniones().getLast().getFechaCreacion();
	}
}
