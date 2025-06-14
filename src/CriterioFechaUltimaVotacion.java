import java.time.LocalDate;
import java.time.LocalDateTime;

class CriterioFechaUltimaVotacion implements CriterioBusqueda {
    private final IComparadorDeFechas comparador;

    public CriterioFechaUltimaVotacion(IComparadorDeFechas comparador) {
        this.comparador = comparador;
    }

    @Override
    public boolean cumple(Muestra muestra) {
        LocalDateTime ultimaFechaVotacion = muestra.getUltimaFechaVotacion();
        if (ultimaFechaVotacion == null) {
            return false;
        }
        LocalDate fechaVotacionMuestra = ultimaFechaVotacion.toLocalDate();
        return comparador.comparar(fechaVotacionMuestra);
    }
}