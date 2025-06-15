import java.time.LocalDate;

public class DespuesDeFecha implements IComparadorDeFechas {
    private final LocalDate fechaLimite;

    public DespuesDeFecha(LocalDate fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    @Override
    public boolean comparar(LocalDate fechaAEvaluar) {
        return fechaAEvaluar.isAfter(fechaLimite);
    }
}
