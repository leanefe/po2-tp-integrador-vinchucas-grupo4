import java.time.LocalDate;

class ParticipanteSiempreExperto extends Participante {
    public ParticipanteSiempreExperto(String id) {
        super(id);
    }

    @Override
    public NivelParticipante getNivel(LocalDate fechaActual) {
        return NivelParticipante.EXPERTO;
    }
}