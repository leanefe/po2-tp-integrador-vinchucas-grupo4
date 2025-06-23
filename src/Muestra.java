

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
	
	/**
	 * getter de Fecha De Creacion.
	 * @return LocalDateTime
	 */
	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}
	
	/**
	 * setter de Estado.
	 * @param estado
	 */
	public void setEstado(EstadoMuestra estado) {
		this.estado = estado;
	}
	
	/**
	 * getter Opiniones
	 * @return List<Opinion>
	 */
	public List<Opinion> getOpiniones() {
		return new ArrayList<>(opiniones);
	}
	
	/**
	 * getter Recolector
	 * @return Participante
	 */
	public Participante getRecolector() {
		return recolector;
	}
	
	/**
	 * getter Ubicacion
	 * @return Ubicacion
	 */
	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	/**
	 * getter NivelVerificacion.
	 * @return NivelVerificacion
	 */
	public NivelVerificacion getNivelVerificacion() {
		return this.estado.getNivel();
	}
	
	/**
	 * getter Ultima Fecha Votacion.
	 * @return LocalDateTime
	 */
	public LocalDateTime getUltimaFechaVotacion() {
		return this.getOpiniones().getLast().getFechaCreacion();
	}
	
	/**
	 * Constructor de la clase Muestra
	 * @param linkFoto
	 * @param especie
	 * @param recolector
	 * @param ubicacion
	 */
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
	
	/**
	 * @return La opinión más votada en la muestra hasta el momento. En caso de empate, devuelve NINGUNA.
	 */
	public TipoOpinion resultadoActualOpiniones() {

		if (this.opiniones.isEmpty()) { return TipoOpinion.NINGUNA; }

		// Se ordena por fuera del método para que sea un poco más legible
		List<Map.Entry<TipoOpinion, Long>> conteoOrdenado = this.getConteoOpiniones().entrySet().stream()
				.sorted(Map.Entry.<TipoOpinion, Long>comparingByValue().reversed())
				.toList();

		if (conteoOrdenado.size() > 1 && conteoOrdenado.get(0).getValue() == conteoOrdenado.get(1).getValue()) {
			return TipoOpinion.NINGUNA; // Caso de empate
		} else {
			return conteoOrdenado.getFirst().getKey();
		}
	}
	
	/**
	 * Utiliza un map para contar las opiniones donde la key es el tipo de la opinion y la clave la cantidad que lo votaron.
	 * @return Map<TipoOpinion, Long>
	 */
	private Map<TipoOpinion, Long> getConteoOpiniones(){
		return this.getOpiniones().stream()
				.collect(Collectors.groupingBy(Opinion::getTipo, Collectors.counting()));
	}
	
	/**
	 * Agrega una opnion a la muestra, esto se lo delega al estado (State) que cada estado sabe que hacer dependiendo la opnion.
	 * @param Opinion
	 */
	public void addOpinion(Opinion o) {
		this.estado.addOpinion(this, o);
	}
	
	/**
	 * Agrega a la coleccion de opiniones la opniones dada por parametro, sin precupacion ya que el estado verifica antes que la opinion es valida.
	 * @param Opinion
	 */
	public void doAddOpinion(Opinion o) {
		this.opiniones.add(o);
	}
	
	/**
	 * Elimina todas las opiniones que estan actualmente en la muestra.
	 */
	public void restartOpiniones() {
		this.opiniones.clear();
	}

	/**
	 * Agrega una nueva zona de cobertura a la coleecion zonasDePertenencia.
	 * @param ZonaCobetura.
	 */
	public void agregarZona(ZonaCobertura zona) {
		this.zonasDePertenencia.add(zona);
	}
	
	/**
	 * En caso de haberse validado, lo notifica a sus Zonas de Cobertura.
	 */
	public void notificarValidacion() {
		for (ZonaCobertura zona : zonasDePertenencia) {
			zona.avisarValidacionMuestra(this);
		}
	}
	
}
