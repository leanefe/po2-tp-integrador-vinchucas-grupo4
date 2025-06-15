import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class GestorMuestras {
    private List<Muestra> muestras;
    private List<Participante> participantes;
    private List<Organizacion> organizaciones;
    private List<ZonaCobertura> zonasDeCobertura;

    public GestorMuestras() {
        this.muestras = new ArrayList<>();
        this.participantes = new ArrayList<>();
        this.organizaciones = new ArrayList<>();
        this.zonasDeCobertura = new ArrayList<>();
    }

    public void agregarMuestra(Muestra muestra) {
        this.muestras.add(muestra);
        this.agregarMuestraAZonas(muestra);
    }

    private void agregarMuestraAZonas(Muestra muestra) {
        for (ZonaCobertura zona : zonasDeCobertura) {
            if (zona.abarcaA(muestra.getUbicacion())) {
                zona.agregarMuestra(muestra);
            }
        }
    }

    public List<Muestra> buscarMuestras(CriterioBusqueda criterio) {
        return muestras.stream()
                       .filter(criterio::cumple) 
                       .collect(Collectors.toList());
    }

    /**
     * @param muestra
     * @param distancia en Km
     * @return Las muestras obtenidas a menor distancia que la dada.
     */
    public List<Muestra> buscarMuestrasAMenosDe(Muestra muestra, double distancia) {
        return muestras.stream()
                .filter(m -> muestra.getUbicacion().distanciaEnKmA(m.getUbicacion()) < distancia)
                .toList();
    }

    // Getters
    public List<Muestra> getMuestras() { return new ArrayList<>(muestras); }
    public List<Participante> getParticipantes() { return new ArrayList<>(participantes); }
    public List<Organizacion> getOrganizaciones() { return new ArrayList<>(organizaciones); }
    public List<ZonaCobertura> getZonasDeCobertura() { return new ArrayList<>(zonasDeCobertura); }

}
