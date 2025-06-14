
class CriterioOR implements CriterioBusqueda {
    private final CriterioBusqueda criterio1;
    private final CriterioBusqueda criterio2;

    public CriterioOR(CriterioBusqueda criterio1, CriterioBusqueda criterio2) {
        this.criterio1 = criterio1;
        this.criterio2 = criterio2;
    }

    @Override
    public boolean cumple(Muestra muestra) {
        return criterio1.cumple(muestra) || criterio2.cumple(muestra);
    }
}