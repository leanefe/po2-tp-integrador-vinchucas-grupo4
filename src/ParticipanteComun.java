import java.time.LocalDate;

class ParticipanteComun extends Participante {
    public ParticipanteComun(String id) {
        super(id);
    }

    @Override
    public NivelParticipante getNivel(LocalDate fechaActual) {
        int envios = getCantidadEnviosUltimos30Dias(fechaActual);
        int opiniones = getCantidadOpinionesUltimos30Dias(fechaActual);

        
        if (envios >= 10 && opiniones >= 20) {
            return NivelParticipante.EXPERTO;
        } else {
            return NivelParticipante.BASICO;
        }
    }
}