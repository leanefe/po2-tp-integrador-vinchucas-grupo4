import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CriterioANDTest {

    @Mock
    private CriterioBusqueda criterio1Mock;
    @Mock
    private CriterioBusqueda criterio2Mock;
    @Mock
    private Muestra muestraMock; // Necesitamos una muestra para pasar al método cumple

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCumpleCuandoAmbosCriteriosSonVerdaderos() {
        CriterioAND criterioAnd = new CriterioAND(criterio1Mock, criterio2Mock);

        when(criterio1Mock.cumple(muestraMock)).thenReturn(true);
        when(criterio2Mock.cumple(muestraMock)).thenReturn(true);

        assertTrue(criterioAnd.cumple(muestraMock));

        verify(criterio1Mock).cumple(muestraMock);
        verify(criterio2Mock).cumple(muestraMock);
    }

    @Test
    void testNoCumpleCuandoElPrimerCriterioEsFalso() {
        CriterioAND criterioAnd = new CriterioAND(criterio1Mock, criterio2Mock);

        when(criterio1Mock.cumple(muestraMock)).thenReturn(false);
       
        when(criterio2Mock.cumple(muestraMock)).thenReturn(true); // Aunque sea true, no importa

        assertFalse(criterioAnd.cumple(muestraMock));

        verify(criterio1Mock).cumple(muestraMock);
        verify(criterio2Mock, never()).cumple(muestraMock); // Verificamos que no se llamó al segundo criterio
    }

    @Test
    void testNoCumpleCuandoElSegundoCriterioEsFalso() {
        CriterioAND criterioAnd = new CriterioAND(criterio1Mock, criterio2Mock);

        when(criterio1Mock.cumple(muestraMock)).thenReturn(true);
        when(criterio2Mock.cumple(muestraMock)).thenReturn(false);

        assertFalse(criterioAnd.cumple(muestraMock));

        verify(criterio1Mock).cumple(muestraMock);
        verify(criterio2Mock).cumple(muestraMock);
    }

    @Test
    void testNoCumpleCuandoAmbosCriteriosSonFalsos() {
        CriterioAND criterioAnd = new CriterioAND(criterio1Mock, criterio2Mock);

        when(criterio1Mock.cumple(muestraMock)).thenReturn(false);
        
        when(criterio2Mock.cumple(muestraMock)).thenReturn(false); // No importa

        assertFalse(criterioAnd.cumple(muestraMock));

        verify(criterio1Mock).cumple(muestraMock);
        verify(criterio2Mock, never()).cumple(muestraMock);
    }
}