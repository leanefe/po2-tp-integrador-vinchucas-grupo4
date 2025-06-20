

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
	 * getter de Especie.
	 * @return EspecieVinchuca
	 */
	public EspecieVinchuca getEspecie() {
		return especie;
	}
	
	/**
	 * getter de Esatdo.
	 * @return EstadoMuestra
	 */
	public EstadoMuestra getEstado() {
		return estado;
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
		return opiniones; 
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
	 * getter LinkDeFoto
	 * @return String
	 */
	public String getLinkFoto() {
		return linkFoto;
	}
	
	/**
	 * getter NivelVerificacion.
	 * @return NivelVerificacion
	 */
	public NivelVerificacion getNivelVerificacion() {
		return this.getEstado().getNivel();
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
	 * Calcula cuando se lo llama al metodo el resultado actual de la muestra, dependiendo de los que votaron en esa muestra se calcula y devulve el tipo de opinion.
	 * @return TipoOpinion
	 */
	public TipoOpinion resultadoActualOpiniones() {
		Map<TipoOpinion, Long> conteo = contarOpiniones();
		
		List<Map.Entry<TipoOpinion, Long>> ordenados = conteo.entrySet().stream()
				.sorted(Map.Entry.<TipoOpinion, Long>comparingByValue().reversed())
				.toList();
		
		if (ordenados.size() < 1) {
			return TipoOpinion.NINGUNA;
		}
		
		if (ordenados.size() > 1 && ordenados.get(0).getValue().equals(ordenados.get(1).getValue())) {
			return TipoOpinion.NINGUNA; //hay empate.
		}
		
		return ordenados.get(0).getKey();
	}
	
	/**
	 * Utiliza un map para contar las opiniones donde la key es el tipo de la opinion y la clave la cantidad que lo votaron.
	 * @return Map<TipoOpinion, Long>
	 */
	private Map<TipoOpinion, Long> contarOpiniones(){
		return this.getOpiniones().stream()
				.collect(Collectors.groupingBy(Opinion::getTipo, Collectors.counting()));
	}
	
	/**
	 * Agrega una opnion a la muestra, esto se lo delega al estado (State) que cada estado sabe que hacer dependiendo la opnion.
	 * @param Opinion
	 */
	public void addOpinion(Opinion o) {
		this.getEstado().addOpinion(this, o);
	}
	
	/**
	 * Agrega a la coleccion de opiniones la opniones dada por parametro, sin precupacion ya que el estado verifica antes que la opinion es valida.
	 * @param Opinion
	 */
	public void doAddOpinion(Opinion o) {
		this.getOpiniones().add(o);
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
	 * Notifica a todas las Zonas de Cobertura que la muestra cambio de estado a valido, es decir se valido la muestra.
	 */
	public void notificarValidacion() {
		for (ZonaCobertura zona : zonasDePertenencia) {
			zona.avisarValidacionMuestra(this);
		}
	}
	
}
