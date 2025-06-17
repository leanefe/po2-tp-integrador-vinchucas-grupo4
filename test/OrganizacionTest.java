import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrganizacionTest {

    Organizacion orga;
    FuncionalidadExterna accionPorNuevaMuestra;
    FuncionalidadExterna accionPorValidacion;
    ZonaCobertura zonaMock;
    Muestra muestraMock;

    @BeforeEach
    void setUp() {
        accionPorNuevaMuestra = mock(FuncionalidadExterna.class);
        accionPorValidacion = mock(FuncionalidadExterna.class);
        orga = new Organizacion("Salud", 100, mock(Ubicacion.class), accionPorNuevaMuestra, accionPorValidacion);
        muestraMock = mock(Muestra.class);
        zonaMock = mock(ZonaCobertura.class);
    }

    @Test
    void testAccionPorNuevaMuestra() {
        orga.accionPorNuevaMuestra(zonaMock, muestraMock);
        verify(accionPorNuevaMuestra).nuevoEvento(orga, zonaMock, muestraMock);
    }

    @Test
    void testAccionPorValidacion() {
        orga.accionPorValidacion(zonaMock, muestraMock);
        verify(accionPorValidacion).nuevoEvento(orga, zonaMock, muestraMock);
    }

    @Test
    void testSetAccionPorNuevaMuestra() {
        orga.setAccionPorNuevaMuestra(accionPorValidacion);
        orga.accionPorNuevaMuestra(zonaMock, muestraMock);
        verify(accionPorValidacion).nuevoEvento(orga, zonaMock, muestraMock);
        verify(accionPorNuevaMuestra, never()).nuevoEvento(orga, zonaMock, muestraMock);
    }

    @Test
    void testSetAccionPorValidacion() {
        orga.setAccionPorValidacion(accionPorNuevaMuestra);
        orga.accionPorNuevaMuestra(zonaMock, muestraMock);
        verify(accionPorNuevaMuestra).nuevoEvento(orga, zonaMock, muestraMock);
        verify(accionPorValidacion, never()).nuevoEvento(orga, zonaMock, muestraMock);
    }
}