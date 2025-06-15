import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class DespuesDeFechaTest {

    @Test
    void testCompararFechaAntesDeLimite() {
        LocalDate fechaLimite = LocalDate.of(2025, 6, 15);
        DespuesDeFecha comparador = new DespuesDeFecha(fechaLimite);
        LocalDate fechaAComparar = LocalDate.of(2025, 6, 14);
        assertFalse(comparador.comparar(fechaAComparar));
    }

    @Test
    void testCompararFechaEnLimite() {
        LocalDate fechaLimite = LocalDate.of(2025, 6, 15);
        DespuesDeFecha comparador = new DespuesDeFecha(fechaLimite);
        LocalDate fechaAComparar = LocalDate.of(2025, 6, 15);
        assertFalse(comparador.comparar(fechaAComparar));
    }

    @Test
    void testCompararFechaDespuesDeLimite() {
        LocalDate fechaLimite = LocalDate.of(2025, 6, 15);
        DespuesDeFecha comparador = new DespuesDeFecha(fechaLimite);
        LocalDate fechaAComparar = LocalDate.of(2025, 6, 16);
        assertTrue(comparador.comparar(fechaAComparar));
    }
    
}