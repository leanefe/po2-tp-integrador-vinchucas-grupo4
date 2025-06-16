import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import ar.edu.unq.vinchuca.criterio.CriterioBusqueda;
import ar.edu.unq.vinchuca.criterio.CriterioOR;
import ar.edu.unq.vinchuca.model.Muestra;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CriterioORTest {

    @Mock
    private CriterioBusqueda criterio1Mock;
    @Mock
    private CriterioBusqueda criterio2Mock;
    @Mock
    private Muestra muestraMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCumpleCuandoElPrimerCriterioEsVerdadero() {
        CriterioOR criterioOr = new CriterioOR(criterio1Mock, criterio2Mock);

        when(criterio1Mock.cumple(muestraMock)).thenReturn(true);
       
        when(criterio2Mock.cumple(muestraMock)).thenReturn(false); // Aunque sea false, no importa

        assertTrue(criterioOr.cumple(muestraMock));

        verify(criterio1Mock).cumple(muestraMock);
        verify(criterio2Mock, never()).cumple(muestraMock); // Verificamos que no se llamó al segundo criterio
    }

    @Test
    void testCumpleCuandoElSegundoCriterioEsVerdadero() {
        CriterioOR criterioOr = new CriterioOR(criterio1Mock, criterio2Mock);

        when(criterio1Mock.cumple(muestraMock)).thenReturn(false);
        when(criterio2Mock.cumple(muestraMock)).thenReturn(true);

        assertTrue(criterioOr.cumple(muestraMock));

        verify(criterio1Mock).cumple(muestraMock);
        verify(criterio2Mock).cumple(muestraMock);
    }

    @Test
    void testNoCumpleCuandoAmbosCriteriosSonFalsos() {
        CriterioOR criterioOr = new CriterioOR(criterio1Mock, criterio2Mock);

        when(criterio1Mock.cumple(muestraMock)).thenReturn(false);
        when(criterio2Mock.cumple(muestraMock)).thenReturn(false);

        assertFalse(criterioOr.cumple(muestraMock));

        verify(criterio1Mock).cumple(muestraMock);
        verify(criterio2Mock).cumple(muestraMock);
    }

    @Test
    void testCumpleCuandoAmbosCriteriosSonVerdaderos() {
        CriterioOR criterioOr = new CriterioOR(criterio1Mock, criterio2Mock);

        when(criterio1Mock.cumple(muestraMock)).thenReturn(true);
        
        when(criterio2Mock.cumple(muestraMock)).thenReturn(true); // No importa

        assertTrue(criterioOr.cumple(muestraMock));

        verify(criterio1Mock).cumple(muestraMock);
        verify(criterio2Mock, never()).cumple(muestraMock);
    }
}