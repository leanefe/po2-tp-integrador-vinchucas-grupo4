import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParticipanteSiempreExpertoTest {

    private ParticipanteSiempreExperto participanteSiempreExperto;

    @BeforeEach
    void setUp() {
        // Instancia de la clase real a probar
        participanteSiempreExperto = new ParticipanteSiempreExperto("PSE001");
    }

    @Test
    void testParticipanteSiempreExpertoInicializaCorrectamente() {
        // Verifica que el constructor base funciona (heredado de Participante)
        assertEquals("PSE001", participanteSiempreExperto.getId());
        assertTrue(participanteSiempreExperto.getMuestrasEnviadas().isEmpty());
        assertTrue(participanteSiempreExperto.getOpinionesEmitidas().isEmpty());
    }

    @Test
    void testGetNivelSiempreRetornaExpertoIndependientementeDeLaFecha() {
        // Prueba con una fecha arbitraria
        LocalDate fechaCualquiera = LocalDate.of(2024, 1, 1);
        assertEquals(NivelParticipante.EXPERTO, participanteSiempreExperto.getNivel(fechaCualquiera));

        // Prueba con otra fecha diferente para asegurar que siempre es EXPERTO
        LocalDate otraFecha = LocalDate.of(2025, 12, 31);
        assertEquals(NivelParticipante.EXPERTO, participanteSiempreExperto.getNivel(otraFecha));

        // Prueba con la fecha actual del sistema
        assertEquals(NivelParticipante.EXPERTO, participanteSiempreExperto.getNivel(LocalDate.now()));
    }
}