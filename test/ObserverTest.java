import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

public class ObserverTest {

    GestorMuestras gestorMuestras;
    Participante recolector;
    Muestra m1;
    GestorEventosEnZona gestorEventos;
    ZonaCobertura zona;
    FuncionalidadExterna accionNuevaMuestra, accionMuestraVerificada;
    IOrganizacion organizacion;

    @BeforeEach
    void setUp() {
        gestorMuestras = new GestorMuestras();
        recolector = mock(Participante.class);
        m1 = new Muestra("url", EspecieVinchuca.SORDIDA, recolector, new Ubicacion(-34.706108, -58.276997));
        gestorEventos = new GestorEventosEnZona();
        zona = new ZonaCobertura("zona", new Ubicacion(-34.711873, -58.281293), 20, gestorEventos);
        accionNuevaMuestra = mock(FuncionalidadExterna.class);
        accionMuestraVerificada = mock(FuncionalidadExterna.class);
        organizacion = new Organizacion("salud", 100, mock(Ubicacion.class), accionNuevaMuestra, accionMuestraVerificada);
        gestorEventos.suscribir(organizacion);
    }

    @Test
    public void testAccionNuevaMuestra() {
        gestorMuestras.agregarMuestra(m1);
        verify(accionNuevaMuestra, atLeastOnce()).nuevoEvento(organizacion, zona, m1);
        verify(accionMuestraVerificada, never()).nuevoEvento(organizacion, zona, m1);
    }

    @Test
    public void testAccionMuestraVerificada() {
        //TODO ver cómo se implementa la verificación
        verify(accionMuestraVerificada, atLeastOnce()).nuevoEvento(organizacion, zona, m1);
    }

    @Test
    public void testDesuscripcionDeOrganizacion() {
        gestorEventos.desuscribir(organizacion);
        gestorMuestras.agregarMuestra(m1);
        verify(accionNuevaMuestra, never()).nuevoEvento(organizacion, zona, m1);
        verify(accionMuestraVerificada, never()).nuevoEvento(organizacion, zona, m1);
    }
}
