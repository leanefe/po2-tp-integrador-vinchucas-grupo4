import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

abstract class Participante {
    protected String id; // 
    protected List<Muestra> muestrasEnviadas;
    protected List<Opinion> opinionesEmitidas; // 

    public Participante(String id) {
        this.id = id;
        this.muestrasEnviadas = new ArrayList<>();
        this.opinionesEmitidas = new ArrayList<>();
    }


	public String getId() { return id; }
    public List<Muestra> getMuestrasEnviadas() { return new ArrayList<>(muestrasEnviadas); }
    public List<Opinion> getOpinionesEmitidas() { return new ArrayList<>(opinionesEmitidas); }

    public abstract NivelParticipante getNivel(LocalDate fechaActual);

    public Muestra enviarMuestra(EspecieVinchuca especie, String fotoUrl, Ubicacion ubicacion) {
        Muestra nuevaMuestra = new Muestra(especie, fotoUrl, ubicacion, this);
        this.muestrasEnviadas.add(nuevaMuestra);
        // El GestorMuestras se encargará de registrarla globalmente
        return nuevaMuestra;
    }

    public void opinar(Muestra muestra, TipoOpinion tipoOpinion) {
        Opinion nuevaOpinion = new Opinion(this, tipoOpinion, this.getNivel(LocalDate.now()));
        this.opinionesEmitidas.add(nuevaOpinion);
        muestra.addOpinion(nuevaOpinion); // La muestra maneja la adición y cambio de estado
    }

    protected int getCantidadEnviosUltimos30Dias(LocalDate fechaActual) {
        LocalDateTime fechaLimite = fechaActual.minusDays(30).atStartOfDay();
        return (int) muestrasEnviadas.stream()
            .filter(m -> m.getFechaCreacion().isAfter(fechaLimite))
            .count();
    }

    protected int getCantidadOpinionesUltimos30Dias(LocalDate fechaActual) {
        LocalDateTime fechaLimite = fechaActual.minusDays(30).atStartOfDay();
        return (int) opinionesEmitidas.stream()
            .filter(o -> o.getFechaCreacion().isAfter(fechaLimite))
            .count();
    }
}
