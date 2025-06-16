import java.util.ArrayList;
import java.util.List;

public class ZonaCobertura {
    private String nombre;
    private Ubicacion epicentro;
    private double radio;
    private List<Muestra> muestras;
    private GestorEventosEnZona gestor;

    public ZonaCobertura(String nombre, Ubicacion epicentro, double radio, GestorEventosEnZona gestor) {
        this.nombre = nombre;
        this.epicentro = epicentro;
        this.radio = radio;
        this.muestras = new ArrayList<>();
        this.gestor = gestor;
    }

    public void agregarMuestra(Muestra muestra) {
        this.muestras.add(muestra);
        gestor.avisoPorNuevaMuestra(this, muestra);
    }

    public void avisarValidacionMuestra(Muestra muestra) {
        gestor.avisoPorMuestraValidada(this, muestra);
    }

    public boolean abarcaA(Ubicacion ubicacion) {
        return this.epicentro.distanciaEnKmA(ubicacion) <= this.radio;
    }

    /**
     * Dos zonas se solapan si la distancia entre sus centros es menor que la suma de sus radios.
     */
    public boolean solapaA(ZonaCobertura zona) {
        return epicentro.distanciaEnKmA(zona.getEpicentro()) <= this.radio - zona.getRadio();
    }

    public double getRadio() {
        return radio;
    }

    public Ubicacion getEpicentro() {
        return epicentro;
    }
}
