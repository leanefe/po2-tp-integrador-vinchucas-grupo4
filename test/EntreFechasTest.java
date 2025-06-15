import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EntreFechasTest {

    private final LocalDate FECHA_DESDE = LocalDate.of(2025, 6, 10);
    private final LocalDate FECHA_HASTA = LocalDate.of(2025, 6, 20);
    private EntreFechas comparador = new EntreFechas(FECHA_DESDE, FECHA_HASTA);

    @Test
    void testCompararFechaDentroDelRango() {
        LocalDate fechaAComparar = LocalDate.of(2025, 6, 15);
        assertTrue(comparador.comparar(fechaAComparar));
    }

    @Test
    void testCompararFechaEnLimiteInferior() {
        LocalDate fechaAComparar = FECHA_DESDE;
        assertTrue(comparador.comparar(fechaAComparar));
    }

    @Test
    void testCompararFechaEnLimiteSuperior() {
        LocalDate fechaAComparar = FECHA_HASTA;
        assertTrue(comparador.comparar(fechaAComparar));
    }

    @Test
    void testCompararFechaAntesDelRango() {
        LocalDate fechaAComparar = LocalDate.of(2025, 6, 9);
        assertFalse(comparador.comparar(fechaAComparar));
    }

    @Test
    void testCompararFechaDespuesDelRango() {
        LocalDate fechaAComparar = LocalDate.of(2025, 6, 21);
        assertFalse(comparador.comparar(fechaAComparar));
    }

    
}