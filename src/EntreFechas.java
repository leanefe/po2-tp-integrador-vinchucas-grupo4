import java.time.LocalDate;

class EntreFechas implements IComparadorDeFechas {
    private final LocalDate fechaDesde;
    private final LocalDate fechaHasta;

    public EntreFechas(LocalDate fechaDesde, LocalDate fechaHasta) {
        if (fechaDesde.isAfter(fechaHasta)) {
            throw new IllegalArgumentException("La fecha 'desde' no puede ser posterior a la fecha 'hasta'.");
        }
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
    }

    @Override
    public boolean comparar(LocalDate fechaAEvaluar) {
        return (fechaAEvaluar.isAfter(fechaDesde) || fechaAEvaluar.isEqual(fechaDesde)) &&
               (fechaAEvaluar.isBefore(fechaHasta) || fechaAEvaluar.isEqual(fechaHasta));
    }
}
