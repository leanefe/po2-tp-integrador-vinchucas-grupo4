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

public class CriterioFechaCreacionTest {

    @Mock
    private Muestra muestraMock; // Mock de Muestra

    @Mock
    private IComparadorDeFechas comparadorMock; // Mock de IComparadorDeFechas (Strategy)

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @Test
    void testCumpleCuandoFechaCreacionCoincideConComparador() {
        CriterioFechaCreacion criterio = new CriterioFechaCreacion(comparadorMock);
        LocalDate fechaMuestra = LocalDate.of(2025, 1, 10);

        // Cuando se llame a getFechaCreacion() en el mock, que devuelva una fecha específica
        when(muestraMock.getFechaCreacion()).thenReturn(fechaMuestra.atStartOfDay());
        // Cuando el comparador mock reciba esa fecha, que devuelva true
        when(comparadorMock.comparar(fechaMuestra)).thenReturn(true);

        assertTrue(criterio.cumple(muestraMock));

        // Verificamos que se llamó a los métodos esperados
        verify(muestraMock).getFechaCreacion();
        verify(comparadorMock).comparar(fechaMuestra);
    }

    @Test
    void testNoCumpleCuandoFechaCreacionNoCoincideConComparador() {
        CriterioFechaCreacion criterio = new CriterioFechaCreacion(comparadorMock);
        LocalDate fechaMuestra = LocalDate.of(2025, 1, 10);

        when(muestraMock.getFechaCreacion()).thenReturn(fechaMuestra.atStartOfDay());
        // Cuando el comparador mock reciba esa fecha, que devuelva false
        when(comparadorMock.comparar(fechaMuestra)).thenReturn(false);

        assertFalse(criterio.cumple(muestraMock));

        verify(muestraMock).getFechaCreacion();
        verify(comparadorMock).comparar(fechaMuestra);
    }

    @Test
    void testCriterioUsaLaFechaDeCreacionCorrecta() {
        // En este test usamos comparadores reales para probar la integración del criterio con el comparador
        LocalDate hoy = LocalDate.now();
        LocalDate ayer = hoy.minusDays(1);
        LocalDate manana = hoy.plusDays(1);

        // Configuración de Muestra Mock para devolver fechas específicas
        when(muestraMock.getFechaCreacion()).thenReturn(hoy.atStartOfDay());

        CriterioFechaCreacion criterioHoy = new CriterioFechaCreacion(new MismaFecha(hoy));
        assertTrue(criterioHoy.cumple(muestraMock));

        CriterioFechaCreacion criterioAyer = new CriterioFechaCreacion(new MismaFecha(ayer));
        assertFalse(criterioAyer.cumple(muestraMock));

        CriterioFechaCreacion criterioAntesDeManana = new CriterioFechaCreacion(new AntesDeFecha(manana));
        assertTrue(criterioAntesDeManana.cumple(muestraMock));

        verify(muestraMock, times(3)).getFechaCreacion(); // Se llama 3 veces en este test
    }
}