import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UbicacionTest {

    // Latitud y longitud sacados de Google Maps
    Ubicacion unq = new Ubicacion(-34.706108, -58.276997);
    Ubicacion estacionQuilmes = new Ubicacion(-34.724112, -58.260719);
    Ubicacion constitucion = new Ubicacion(-34.627792, -58.380490);
    Ubicacion barrioChino = new Ubicacion(-34.557891, -58.450225);
    List<Ubicacion> ubicaciones;

    @BeforeEach
    void setUp() {
        ubicaciones = Arrays.asList(estacionQuilmes, constitucion, barrioChino);
    }

    @Test
    void testUbicacionesAMenosDe() {
        List<Ubicacion> referencia = Arrays.asList(estacionQuilmes, constitucion);
        assertEquals(referencia, unq.ubicacionesAMenosDe(ubicaciones, 20));
    }
}