import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ZonaCoberturaTest {

    ZonaCobertura zona;
    Ubicacion ubiMock;
    GestorEventosEnZona gestorMock;
    Muestra muestraMock;
    ZonaCobertura zonaMock;

    @BeforeEach
    void setUp() {
        ubiMock = mock(Ubicacion.class);
        gestorMock = mock(GestorEventosEnZona.class);
        zona = new ZonaCobertura("Zona", ubiMock, 15, gestorMock);
        muestraMock = mock(Muestra.class);
        zonaMock = mock(ZonaCobertura.class);
        when(muestraMock.getUbicacion()).thenReturn(mock(Ubicacion.class));
        when(zonaMock.getEpicentro()).thenReturn(mock(Ubicacion.class));
        when(zonaMock.getRadio()).thenReturn(10.0);
    }

    @Test
    void testAgregarMuestra() {
        zona.agregarMuestra(muestraMock);
        verify(gestorMock).avisoPorNuevaMuestra(zona, muestraMock);
    }

    @Test
    void testAvisarValidacionMuestra() {
        zona.avisarValidacionMuestra(muestraMock);
        verify(gestorMock).avisoPorMuestraValidada(zona, muestraMock);
    }

    @Test
    void testAbarcaADaTrue() {
        when(ubiMock.distanciaEnKmA(muestraMock.getUbicacion())).thenReturn(10.0);
        assertTrue(zona.abarcaA(muestraMock.getUbicacion()));
    }

    @Test
    void testAbarcaADaFalse() {
        when(ubiMock.distanciaEnKmA(muestraMock.getUbicacion())).thenReturn(28.0);
        assertFalse(zona.abarcaA(muestraMock.getUbicacion()));
    }

    @Test
    void testSolapaADaTrue() {
        when(ubiMock.distanciaEnKmA(zonaMock.getEpicentro())).thenReturn(20.0);
        assertTrue(zona.solapaA(zonaMock));
    }

    @Test
    void testSolapaADaFalse() {
        when(ubiMock.distanciaEnKmA(zonaMock.getEpicentro())).thenReturn(30.0);
        assertFalse(zona.solapaA(zonaMock));
    }

    @Test
    void testGetters() {
        assertEquals(ubiMock, zona.getEpicentro());
        assertEquals(15, zona.getRadio());
    }
}