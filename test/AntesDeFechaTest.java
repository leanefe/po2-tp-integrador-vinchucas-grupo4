import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class AntesDeFechaTest {

    @Test
    void testCompararFechaAntesDeLimite() {
        LocalDate fechaLimite = LocalDate.of(2025, 6, 15);
        AntesDeFecha comparador = new AntesDeFecha(fechaLimite);
        LocalDate fechaAComparar = LocalDate.of(2025, 6, 14); // un día antes
        assertTrue(comparador.comparar(fechaAComparar));
    }

    @Test
    void testCompararFechaEnLimite() {
        LocalDate fechaLimite = LocalDate.of(2025, 6, 15);
        AntesDeFecha comparador = new AntesDeFecha(fechaLimite);
        LocalDate fechaAComparar = LocalDate.of(2025, 6, 15); // misma fecha
        assertFalse(comparador.comparar(fechaAComparar));
    }

    @Test
    void testCompararFechaDespuesDeLimite() {
        LocalDate fechaLimite = LocalDate.of(2025, 6, 15);
        AntesDeFecha comparador = new AntesDeFecha(fechaLimite);
        LocalDate fechaAComparar = LocalDate.of(2025, 6, 16); // un dia despues
        assertFalse(comparador.comparar(fechaAComparar));
    }

}