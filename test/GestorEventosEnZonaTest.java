import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GestorEventosEnZonaTest {

    GestorEventosEnZona gestor;
    IOrganizacion orgaMock;
    ZonaCobertura zonaMock;
    Muestra muestraMock;

    @BeforeEach
    void setUp() {
        gestor = new GestorEventosEnZona();
        orgaMock = mock(IOrganizacion.class);
        zonaMock = mock(ZonaCobertura.class);
        muestraMock = mock(Muestra.class);
        gestor.suscribir(orgaMock);
    }

    @Test
    void testAvisoPorNuevaMuestra() {
        gestor.avisoPorNuevaMuestra(zonaMock, muestraMock);
        verify(orgaMock).accionPorNuevaMuestra(zonaMock, muestraMock);
    }

    @Test
    void testAvisoPorMuestraValidada() {
        gestor.avisoPorNuevaMuestra(zonaMock, muestraMock);
        gestor.avisoPorMuestraValidada(zonaMock, muestraMock);
        verify(orgaMock).accionPorValidacion(zonaMock, muestraMock);
    }

    @Test
    void testNoAvisaPorDesuscripcion() {
        gestor.desuscribir(orgaMock);
        gestor.avisoPorNuevaMuestra(zonaMock, muestraMock);
        gestor.avisoPorMuestraValidada(zonaMock, muestraMock);
        verify(orgaMock, never()).accionPorNuevaMuestra(zonaMock, muestraMock);
        verify(orgaMock, never()).accionPorValidacion(zonaMock, muestraMock);
    }
}