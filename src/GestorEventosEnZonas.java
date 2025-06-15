import java.util.ArrayList;
import java.util.List;

public class GestorEventosEnZonas {
    private List<IOrganizacion> suscriptores;

    public GestorEventosEnZonas() {
        suscriptores = new ArrayList<>();
    }

    public void suscribir(IOrganizacion o) {
        suscriptores.add(o);
    }

    public void desuscribir(IOrganizacion o) {
        suscriptores.remove(o);
    }

    public void avisoPorNuevaMuestra(ZonaCobertura zona, Muestra muestra) {
        for (IOrganizacion o : suscriptores) {
            o.accionPorNuevaMuestra(zona, muestra);
        }
    }

    public void avisoPorMuestraValidada(ZonaCobertura zona, Muestra muestra) {
        for (IOrganizacion o : suscriptores) {
            o.accionPorValidacion(zona, muestra);
        }
    }
}
