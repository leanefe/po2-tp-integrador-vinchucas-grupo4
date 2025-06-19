import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GestorMuestrasTest {
    
    GestorMuestras gestor;
    ZonaCobertura zonaMock;
    Muestra m1, m2, m3, muestraMock;

    @BeforeEach
    void setUp() {
        gestor = new GestorMuestras();
        zonaMock = mock(ZonaCobertura.class);
        m1 = mock(Muestra.class); m2 = mock(Muestra.class); m3 = mock(Muestra.class);
        gestor.agregarZona(zonaMock);
        muestraMock = mock(Muestra.class);
        when(muestraMock.getUbicacion()).thenReturn(mock(Ubicacion.class));
        when(muestraMock.getRecolector()).thenReturn(mock(ParticipanteComun.class));
        when(m1.getRecolector()).thenReturn(mock(ParticipanteComun.class));
        when(m2.getRecolector()).thenReturn(mock(ParticipanteComun.class));
        when(m3.getRecolector()).thenReturn(mock(ParticipanteComun.class));
        gestor.agregarMuestra(m1); gestor.agregarMuestra(m2); gestor.agregarMuestra(m3);
    }

    @Test
    void testAgregarMuestraEnZona() {
        when(zonaMock.abarcaA(muestraMock.getUbicacion())).thenReturn(true);
        gestor.agregarMuestra(muestraMock);
        verify(zonaMock).agregarMuestra(muestraMock);
        verify(muestraMock).agregarZona(zonaMock);
    }
    
    @Test
    void testAgregarMuestraFueraDeZona() {
        when(zonaMock.abarcaA(muestraMock.getUbicacion())).thenReturn(false);
        gestor.agregarMuestra(muestraMock);
        verify(zonaMock, never()).agregarMuestra(muestraMock);
        verify(muestraMock, never()).agregarZona(zonaMock);
    }

    @Test
    void buscarMuestras() {
        CriterioBusqueda cr = mock(CriterioBusqueda.class);
        when(cr.cumple(m1)).thenReturn(true);
        when(cr.cumple(m2)).thenReturn(false);
        when(cr.cumple(m3)).thenReturn(true);
        assertEquals(Arrays.asList(m1, m3), gestor.buscarMuestras(cr));
    }

    @Test
    void buscarMuestrasAMenosDe() {
        Muestra mReferencia = mock(Muestra.class);
        when(mReferencia.getUbicacion()).thenReturn(mock(Ubicacion.class));
        when(m1.getUbicacion()).thenReturn(mock(Ubicacion.class));
        when(m2.getUbicacion()).thenReturn(mock(Ubicacion.class));
        when(m3.getUbicacion()).thenReturn(mock(Ubicacion.class));
        when(mReferencia.getUbicacion().distanciaEnKmA(m1.getUbicacion())).thenReturn(5.0);
        when(mReferencia.getUbicacion().distanciaEnKmA(m2.getUbicacion())).thenReturn(2.5);
        when(mReferencia.getUbicacion().distanciaEnKmA(m3.getUbicacion())).thenReturn(10.0);
        assertEquals(Arrays.asList(m2), gestor.buscarMuestrasAMenosDe(mReferencia, 3));
    }
}