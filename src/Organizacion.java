public class Organizacion implements IOrganizacion {

    private String tipo;
    private int cantTrabajadores;
    private Ubicacion ubicacion;
    private FuncionalidadExterna accionPorNuevaMuestra;
    private FuncionalidadExterna accionPorValidacion;

    public Organizacion(String tipo, int cantTrabajadores, Ubicacion ubicacion, FuncionalidadExterna accionPorNuevaMuestra, FuncionalidadExterna accionPorValidacion) {
        this.tipo = tipo;
        this.cantTrabajadores = cantTrabajadores;
        this.ubicacion = ubicacion;
        this.accionPorNuevaMuestra = accionPorNuevaMuestra;
        this.accionPorValidacion = accionPorValidacion;
    }

    @Override
    public void accionPorNuevaMuestra(ZonaCobertura zona, Muestra muestra) {
        this.accionPorNuevaMuestra.nuevoEvento(this, zona, muestra);
    }

    @Override
    public void accionPorValidacion(ZonaCobertura zona, Muestra muestra) {
        this.accionPorValidacion.nuevoEvento(this, zona, muestra);
    }

    public void setAccionPorNuevaMuestra(FuncionalidadExterna accionPorNuevaMuestra) {
        this.accionPorNuevaMuestra = accionPorNuevaMuestra;
    }

    public void setAccionPorValidacion(FuncionalidadExterna accionPorValidacion) {
        this.accionPorValidacion = accionPorValidacion;
    }
}
