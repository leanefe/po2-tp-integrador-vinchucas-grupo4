import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

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
        LocalDate fechaUltimaVotacion = LocalDate.of(2025, 5, 20);

        when(muestraMock.getUltimaFechaVotacion()).thenReturn(fechaUltimaVotacion);
        when(comparadorMock.comparar(fechaUltimaVotacion)).thenReturn(true);

        assertTrue(criterio.cumple(muestraMock));

        verify(muestraMock).getUltimaFechaVotacion();
        verify(comparadorMock).comparar(fechaUltimaVotacion);
    }

    @Test
    void testNoCumpleCuandoUltimaVotacionNoCoincideConComparador() {
        CriterioFechaUltimaVotacion criterio = new CriterioFechaUltimaVotacion(comparadorMock);
        LocalDate fechaUltimaVotacion = LocalDate.of(2025, 5, 20);

        when(muestraMock.getUltimaFechaVotacion()).thenReturn(fechaUltimaVotacion);
        when(comparadorMock.comparar(fechaUltimaVotacion)).thenReturn(false);

        assertFalse(criterio.cumple(muestraMock));

        verify(muestraMock).getUltimaFechaVotacion();
        verify(comparadorMock).comparar(fechaUltimaVotacion);
    }

    @Test
    void testNoCumpleCuandoMuestraNoTieneUltimaVotacion() {
        CriterioFechaUltimaVotacion criterio = new CriterioFechaUltimaVotacion(comparadorMock);

        when(muestraMock.getUltimaFechaVotacion()).thenReturn(null); // Muestra sin votaciones

        assertFalse(criterio.cumple(muestraMock));

        verify(muestraMock).getUltimaFechaVotacion();
        verifyNoInteractions(comparadorMock); // El comparador no debería ser llamado si la fecha es null
    }
}