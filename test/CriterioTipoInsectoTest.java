//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//import org.mockito.Mock.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CriterioTipoInsectoTest {

    @Mock
    private Muestra muestraMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCumpleCuandoResultadoActualEsElTipoInsectoRequerido() {
        CriterioTipoInsecto criterio = new CriterioTipoInsecto(TipoOpinion.VINCHUCA);

        when(muestraMock.resultadoActualOpiniones()).thenReturn(TipoOpinion.VINCHUCA);

        assertTrue(criterio.cumple(muestraMock));

        verify(muestraMock).resultadoActualOpiniones();
    }

    @Test
    void testNoCumpleCuandoResultadoActualNoEsElTipoInsectoRequerido() {
        CriterioTipoInsecto criterio = new CriterioTipoInsecto(TipoOpinion.VINCHUCA);

        when(muestraMock.resultadoActualOpiniones()).thenReturn(TipoOpinion.CHINCHE_FOLIADA);

        assertFalse(criterio.cumple(muestraMock));

        verify(muestraMock).resultadoActualOpiniones();
    }

    @Test
    void testNoCumpleCuandoResultadoEsNingunaPeroSeEsperaOtroTipo() {
        CriterioTipoInsecto criterio = new CriterioTipoInsecto(TipoOpinion.CHINCHE_FOLIADA);

        when(muestraMock.resultadoActualOpiniones()).thenReturn(TipoOpinion.NINGUNA);

        assertFalse(criterio.cumple(muestraMock));

        verify(muestraMock).resultadoActualOpiniones();
    }
}