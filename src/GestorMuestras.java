import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class GestorMuestras {
    private List<Muestra> muestras;
    private List<Participante> participantes;
    private List<ZonaCobertura> zonasDeCobertura;

    public GestorMuestras() {
        this.muestras = new ArrayList<>();
        this.participantes = new ArrayList<>();
        this.zonasDeCobertura = new ArrayList<>();
    }

    public void agregarMuestra(Muestra muestra) {
        this.muestras.add(muestra);
        this.relacionarMuestraYZonas(muestra);
        muestra.getRecolector().agregarMuestraEnviada(muestra);
    }

    private void relacionarMuestraYZonas(Muestra muestra) {
        for (ZonaCobertura zona : zonasDeCobertura) {
            if (zona.abarcaA(muestra.getUbicacion())) {
                zona.agregarMuestra(muestra);
                muestra.agregarZona(zona);
            }
        }
    }

    public void agregarZona(ZonaCobertura zona) {
        this.zonasDeCobertura.add(zona);
    }

    public List<Muestra> buscarMuestras(CriterioBusqueda criterio) {
        return muestras.stream()
                       .filter(criterio::cumple) 
                       .collect(Collectors.toList());
    }

    public List<Muestra> buscarMuestrasAMenosDe(Muestra muestra, double distancia) {
        return muestras.stream()
                .filter(m -> muestra.getUbicacion().distanciaEnKmA(m.getUbicacion()) < distancia)
                .toList();
    }
}
