import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class GestorMuestras {
    private List<Muestra> muestras;
    private List<Participante> participantes;
    private List<Organizacion> organizaciones;
    private List<ZonaDeCobertura> zonasDeCobertura;

    public GestorMuestras() {
        this.muestras = new ArrayList<>();
        this.participantes = new ArrayList<>();
        this.organizaciones = new ArrayList<>();
        this.zonasDeCobertura = new ArrayList<>();
    }

    // Getters 
    public List<Muestra> getMuestras() { return new ArrayList<>(muestras); }
    public List<Participante> getParticipantes() { return new ArrayList<>(participantes); }
    public List<Organizacion> getOrganizaciones() { return new ArrayList<>(organizaciones); }
    public List<ZonaDeCobertura> getZonasDeCobertura() { return new ArrayList<>(zonasDeCobertura); }
    
    public List<Muestra> buscarMuestras(CriterioBusqueda criterio) {
        return muestras.stream()
                       .filter(criterio::cumple) 
                       .collect(Collectors.toList());
    }
}
