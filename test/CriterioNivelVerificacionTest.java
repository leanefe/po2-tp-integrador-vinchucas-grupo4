import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CriterioNivelVerificacionTest {

    @Mock
    private Muestra muestraMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCumpleCuandoNivelVerificacionEsElRequerido() {
        CriterioNivelVerificacion criterio = new CriterioNivelVerificacion(NivelVerificacion.VERIFICADA);

        when(muestraMock.getNivelVerificacion()).thenReturn(NivelVerificacion.VERIFICADA);

        assertTrue(criterio.cumple(muestraMock));

        verify(muestraMock).getNivelVerificacion();
    }

    @Test
    void testNoCumpleCuandoNivelVerificacionNoEsElRequerido() {
        CriterioNivelVerificacion criterio = new CriterioNivelVerificacion(NivelVerificacion.VERIFICADA);

        when(muestraMock.getNivelVerificacion()).thenReturn(NivelVerificacion.VOTADA); // Es VOTADA, no VERIFICADA

        assertFalse(criterio.cumple(muestraMock));

        verify(muestraMock).getNivelVerificacion();
    }
}