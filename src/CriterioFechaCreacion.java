import java.time.LocalDate;
import java.time.LocalDateTime;

class CriterioFechaCreacion implements CriterioBusqueda {
    private final IComparadorDeFechas comparador;

    public CriterioFechaCreacion(IComparadorDeFechas comparador) {
        this.comparador = comparador;
    }

    @Override
    public boolean cumple(Muestra muestra) {
        LocalDateTime fechaCreacionMuestra = muestra.getFechaCreacion();
        LocalDate fechaMuestra = fechaCreacionMuestra.toLocalDate();
        return comparador.comparar(fechaMuestra);
    }
}
