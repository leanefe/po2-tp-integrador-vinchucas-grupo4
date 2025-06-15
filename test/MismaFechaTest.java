import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class MismaFechaTest {

    private final LocalDate FECHA_EXACTA = LocalDate.of(2025, 6, 15);
    private MismaFecha comparador = new MismaFecha(FECHA_EXACTA);

    @Test
    void testCompararFechaEsLaMisma() {
        LocalDate fechaAComparar = LocalDate.of(2025, 6, 15);
        assertTrue(comparador.comparar(fechaAComparar));
    }

    @Test
    void testCompararFechaEsDiferente() {
        LocalDate fechaAComparar = LocalDate.of(2025, 6, 16);
        assertFalse(comparador.comparar(fechaAComparar));
    }

}