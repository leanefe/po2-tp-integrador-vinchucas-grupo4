import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CriterioFechaUltimaVotacionTest {

    @Mock
    private Muestra muestraMock;

    @Mock
    private IComparadorDeFechas comparadorMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCumpleCuandoUltimaVotacionCoincideConComparador() {
        CriterioFechaUltimaVotacion criterio = new CriterioFechaUltimaVotacion(comparadorMock);
        LocalDate fechaSoloDia = LocalDate.of(2025, 5, 20); // Una LocalDate para la comparación
        LocalDateTime fechaUltimaVotacion = fechaSoloDia.atStartOfDay(); // CONVERTIR a LocalDateTime

        when(muestraMock.getUltimaFechaVotacion()).thenReturn(fechaUltimaVotacion); // Ahora recibe LocalDateTime
        when(comparadorMock.comparar(fechaSoloDia)).thenReturn(true); // El comparador sigue usando LocalDate

        assertTrue(criterio.cumple(muestraMock));

        verify(muestraMock).getUltimaFechaVotacion();
        verify(comparadorMock).comparar(fechaSoloDia); // Verificar con LocalDate, que es lo que el comparador espera
    }

    @Test
    void testNoCumpleCuandoUltimaVotacionNoCoincideConComparador() {
        CriterioFechaUltimaVotacion criterio = new CriterioFechaUltimaVotacion(comparadorMock);
        LocalDate fechaSoloDia = LocalDate.of(2025, 5, 20); // Una LocalDate
        LocalDateTime fechaUltimaVotacion = fechaSoloDia.atStartOfDay(); // CONVERTIR a LocalDateTime

        when(muestraMock.getUltimaFechaVotacion()).thenReturn(fechaUltimaVotacion); // Ahora recibe LocalDateTime
        when(comparadorMock.comparar(fechaSoloDia)).thenReturn(false); // El comparador sigue usando LocalDate

        assertFalse(criterio.cumple(muestraMock));

        verify(muestraMock).getUltimaFechaVotacion();
        verify(comparadorMock).comparar(fechaSoloDia); // Verificar con LocalDate
    }

    @Test
    void testNoCumpleCuandoMuestraNoTieneUltimaVotacion() {
        CriterioFechaUltimaVotacion criterio = new CriterioFechaUltimaVotacion(comparadorMock);

        when(muestraMock.getUltimaFechaVotacion()).thenReturn(null); // Esto está bien, null es compatible con objetos

        assertFalse(criterio.cumple(muestraMock));

        verify(muestraMock).getUltimaFechaVotacion();
        verifyNoInteractions(comparadorMock); // El comparador no debería ser llamado si la fecha es null
    }
}