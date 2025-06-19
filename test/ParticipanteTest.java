import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParticipanteTest {

    // Una clase concreta mínima para poder instanciar Participante para pruebas de sus métodos comunes
    private class ParticipanteConcreto extends Participante {
        public ParticipanteConcreto(String id) {
            super(id);
        }

        @Override
        public NivelParticipante getNivel(LocalDate fechaActual) {
            // Implementación simple para poder instanciar
            return NivelParticipante.BASICO;
        }
    }

    private ParticipanteConcreto participante; // Ahora será un SPY
    private Muestra muestraMock; // Mock de Muestra para interacciones


    @BeforeEach
    void setUp() {
        // CORRECCIÓN: Inicializa 'participante' como un SPY
        participante = spy(new ParticipanteConcreto("P001")); // <-- CAMBIO AQUÍ
        muestraMock = mock(Muestra.class);
        // Ahora, 'participante' es un spy, por lo que 'doReturn().when(participante).getNivel()' funciona.
        doReturn(NivelParticipante.BASICO).when(participante).getNivel(any(LocalDate.class));
    }

    @Test
    void testParticipanteSeInicializaCorrectamente() {
        assertEquals("P001", participante.getId());
        assertTrue(participante.getMuestrasEnviadas().isEmpty());
        assertTrue(participante.getOpinionesEmitidas().isEmpty());
    }

    @Test
    void testGetIdRetornaElIdCorrecto() {
        assertEquals("P001", participante.getId());
    }

    @Test
    void testGetMuestrasEnviadasRetornaCopiaNoModificable() {
        participante.agregarMuestraEnviada(muestraMock);
        List<Muestra> muestras = participante.getMuestrasEnviadas();

        assertNotSame(participante.muestrasEnviadas, muestras);
        assertEquals(1, muestras.size());
        assertTrue(muestras.contains(muestraMock));

        muestras.clear();
        assertFalse(participante.getMuestrasEnviadas().isEmpty());
    }

    @Test
    void testGetOpinionesEmitidasRetornaCopiaNoModificable() {
        Opinion opinionReal = new Opinion(TipoOpinion.VINCHUCA, NivelParticipante.BASICO);
        participante.opinionesEmitidas.add(opinionReal);

        List<Opinion> opiniones = participante.getOpinionesEmitidas();

        assertNotSame(participante.opinionesEmitidas, opiniones);
        assertEquals(1, opiniones.size());
        assertTrue(opiniones.contains(opinionReal));

        opiniones.clear();
        assertFalse(participante.getOpinionesEmitidas().isEmpty());
    }

    @Test
    void testAgregarMuestraEnviadaAnadeMuestraALaLista() {
        participante.agregarMuestraEnviada(muestraMock);
        assertEquals(1, participante.getMuestrasEnviadas().size());
        assertTrue(participante.getMuestrasEnviadas().contains(muestraMock));
    }

    @Test
    void testOpinarAnadeOpinionALaListaYNotificaMuestra() {
        participante.opinar(muestraMock, TipoOpinion.VINCHUCA);

        assertEquals(1, participante.getOpinionesEmitidas().size());
        verify(muestraMock, times(1)).addOpinion(any(Opinion.class));

        Opinion opinionGenerada = participante.getOpinionesEmitidas().get(0);
        assertEquals(TipoOpinion.VINCHUCA, opinionGenerada.getTipo());
        assertEquals(NivelParticipante.BASICO, opinionGenerada.getNivelConocimiento());
        assertNotNull(opinionGenerada.getFechaCreacion());
    }

    @Test
    void testGetCantidadEnviosUltimos30DiasCuentaCorrectamente() {
        LocalDate fechaActual = LocalDate.of(2025, 6, 19);

        Muestra m1 = mock(Muestra.class);
        when(m1.getFechaCreacion()).thenReturn(LocalDateTime.of(2025, 6, 18, 10, 0));
        Muestra m2 = mock(Muestra.class);
        when(m2.getFechaCreacion()).thenReturn(LocalDateTime.of(2025, 5, 20, 0, 0));
        Muestra m3 = mock(Muestra.class);
        when(m3.getFechaCreacion()).thenReturn(LocalDateTime.of(2025, 5, 21, 23, 59));

        Muestra m4 = mock(Muestra.class);
        when(m4.getFechaCreacion()).thenReturn(LocalDateTime.of(2025, 5, 19, 23, 59));

        participante.muestrasEnviadas.add(m1);
        participante.muestrasEnviadas.add(m2);
        participante.muestrasEnviadas.add(m3);
        participante.muestrasEnviadas.add(m4);

        assertEquals(3, participante.getCantidadEnviosUltimos30Dias(fechaActual));

        verify(m1).getFechaCreacion();
        verify(m2).getFechaCreacion();
        verify(m3).getFechaCreacion();
        verify(m4).getFechaCreacion();
    }

    @Test
    void testGetCantidadOpinionesUltimos30DiasCuentaCorrectamente() {
        LocalDate fechaActual = LocalDate.of(2025, 6, 19);

        Opinion o1 = mock(Opinion.class);
        when(o1.getFechaCreacion()).thenReturn(LocalDateTime.of(2025, 6, 18, 10, 0));
        Opinion o2 = mock(Opinion.class);
        when(o2.getFechaCreacion()).thenReturn(LocalDateTime.of(2025, 5, 20, 0, 0));
        Opinion o3 = mock(Opinion.class);
        when(o3.getFechaCreacion()).thenReturn(LocalDateTime.of(2025, 5, 21, 23, 59));

        Opinion o4 = mock(Opinion.class);
        when(o4.getFechaCreacion()).thenReturn(LocalDateTime.of(2025, 5, 19, 23, 59));

        participante.opinionesEmitidas.add(o1);
        participante.opinionesEmitidas.add(o2);
        participante.opinionesEmitidas.add(o3);
        participante.opinionesEmitidas.add(o4);

        assertEquals(3, participante.getCantidadOpinionesUltimos30Dias(fechaActual));

        verify(o1).getFechaCreacion();
        verify(o2).getFechaCreacion();
        verify(o3).getFechaCreacion();
        verify(o4).getFechaCreacion();
    }
}