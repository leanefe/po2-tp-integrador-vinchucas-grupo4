import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParticipanteComunTest {

    // Usamos un spy para probar getNivel controlando los métodos protegidos
    // Un spy permite llamar a los métodos reales de la clase, pero también stubbear (simular)
    // métodos específicos (como los protegidos getCantidadEnviosUltimos30Dias y getCantidadOpinionesUltimos30Dias).
    private ParticipanteComun participanteComunSpy;

    @BeforeEach
    void setUp() {
        // Instancia un ParticipanteComun real y luego lo envuelve en un spy.
        participanteComunSpy = spy(new ParticipanteComun("PC001"));
    }

    @Test
    void testParticipanteComunInicializaCorrectamente() {
        // Verifica que el constructor base funciona (heredado de Participante)
        assertEquals("PC001", participanteComunSpy.getId());
        assertTrue(participanteComunSpy.getMuestrasEnviadas().isEmpty());
        assertTrue(participanteComunSpy.getOpinionesEmitidas().isEmpty());
    }

    @Test
    void testGetNivelRetornaBasicoCuandoNoCumpleEnvios() {
        LocalDate fecha = LocalDate.of(2025, 6, 19);

        // Stubbeamos los métodos protegidos para controlar los valores que devuelven
        doReturn(5).when(participanteComunSpy).getCantidadEnviosUltimos30Dias(fecha); // Menos de 10 envíos
        doReturn(20).when(participanteComunSpy).getCantidadOpinionesUltimos30Dias(fecha); // Cumple opiniones

        // Ejecutamos el método a probar
        NivelParticipante nivel = participanteComunSpy.getNivel(fecha);

        // Verificamos el resultado esperado
        assertEquals(NivelParticipante.BASICO, nivel);

        // Verificamos que los métodos protegidos fueron llamados con la fecha correcta
        verify(participanteComunSpy).getCantidadEnviosUltimos30Dias(fecha);
        verify(participanteComunSpy).getCantidadOpinionesUltimos30Dias(fecha);
    }

    @Test
    void testGetNivelRetornaBasicoCuandoNoCumpleOpiniones() {
        LocalDate fecha = LocalDate.of(2025, 6, 19);

        doReturn(10).when(participanteComunSpy).getCantidadEnviosUltimos30Dias(fecha); // Cumple envíos
        doReturn(15).when(participanteComunSpy).getCantidadOpinionesUltimos30Dias(fecha); // Menos de 20 opiniones

        NivelParticipante nivel = participanteComunSpy.getNivel(fecha);

        assertEquals(NivelParticipante.BASICO, nivel);

        verify(participanteComunSpy).getCantidadEnviosUltimos30Dias(fecha);
        verify(participanteComunSpy).getCantidadOpinionesUltimos30Dias(fecha);
    }

    @Test
    void testGetNivelRetornaBasicoCuandoNoCumpleNingunCriterio() {
        LocalDate fecha = LocalDate.of(2025, 6, 19);

        doReturn(5).when(participanteComunSpy).getCantidadEnviosUltimos30Dias(fecha);
        doReturn(15).when(participanteComunSpy).getCantidadOpinionesUltimos30Dias(fecha);

        NivelParticipante nivel = participanteComunSpy.getNivel(fecha);

        assertEquals(NivelParticipante.BASICO, nivel);

        verify(participanteComunSpy).getCantidadEnviosUltimos30Dias(fecha);
        verify(participanteComunSpy).getCantidadOpinionesUltimos30Dias(fecha);
    }

    @Test
    void testGetNivelRetornaExpertoCuandoCumpleAmbosCriteriosExactamente() {
        LocalDate fecha = LocalDate.of(2025, 6, 19);

        doReturn(10).when(participanteComunSpy).getCantidadEnviosUltimos30Dias(fecha); // Exactamente 10
        doReturn(20).when(participanteComunSpy).getCantidadOpinionesUltimos30Dias(fecha); // Exactamente 20

        NivelParticipante nivel = participanteComunSpy.getNivel(fecha);

        assertEquals(NivelParticipante.EXPERTO, nivel);

        verify(participanteComunSpy).getCantidadEnviosUltimos30Dias(fecha);
        verify(participanteComunSpy).getCantidadOpinionesUltimos30Dias(fecha);
    }

    @Test
    void testGetNivelRetornaExpertoCuandoSuperaAmbosCriterios() {
        LocalDate fecha = LocalDate.of(2025, 6, 19);

        doReturn(15).when(participanteComunSpy).getCantidadEnviosUltimos30Dias(fecha); // Más de 10
        doReturn(25).when(participanteComunSpy).getCantidadOpinionesUltimos30Dias(fecha); // Más de 20

        NivelParticipante nivel = participanteComunSpy.getNivel(fecha);

        assertEquals(NivelParticipante.EXPERTO, nivel);

        verify(participanteComunSpy).getCantidadEnviosUltimos30Dias(fecha);
        verify(participanteComunSpy).getCantidadOpinionesUltimos30Dias(fecha);
    }
}