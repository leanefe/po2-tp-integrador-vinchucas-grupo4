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

    public void agregarMuestraEnviada(Muestra muestra) {
        muestrasEnviadas.add(muestra);
    }

    public void opinar(Muestra muestra, TipoOpinion tipoOpinion) {
        Opinion nuevaOpinion = new Opinion(tipoOpinion, this.getNivel(LocalDate.now()));
        this.opinionesEmitidas.add(nuevaOpinion);
        muestra.addOpinion(nuevaOpinion); // La muestra maneja la adición y cambio de estado
    }

    protected int getCantidadEnviosUltimos30Dias(LocalDate fechaActual) {
        LocalDateTime fechaLimite = fechaActual.minusDays(30).atStartOfDay();
        return (int) muestrasEnviadas.stream()
            .filter(m -> !m.getFechaCreacion().isBefore(fechaLimite))
            .count();
    }

    protected int getCantidadOpinionesUltimos30Dias(LocalDate fechaActual) {
        LocalDateTime fechaLimite = fechaActual.minusDays(30).atStartOfDay();
        return (int) opinionesEmitidas.stream()
            .filter(o -> !o.getFechaCreacion().isBefore(fechaLimite))
            .count();
    }
}
