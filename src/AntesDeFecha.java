import java.time.LocalDate;

public class AntesDeFecha implements IComparadorDeFechas {
    private final LocalDate fechaLimite;

    public AntesDeFecha(LocalDate fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    @Override
    public boolean comparar(LocalDate fechaAEvaluar) {
        return fechaAEvaluar.isBefore(fechaLimite);
        }
}