import java.time.LocalDate;

class MismaFecha implements IComparadorDeFechas {
    private final LocalDate fechaExacta;

    public MismaFecha(LocalDate fechaExacta) {
        this.fechaExacta = fechaExacta;
    }

    @Override
    public boolean comparar(LocalDate fechaAEvaluar) {
        return fechaAEvaluar.isEqual(fechaExacta);
    }

}
