
class CriterioNivelVerificacion implements CriterioBusqueda {
    private final NivelVerificacion nivelRequerido;

    public CriterioNivelVerificacion(NivelVerificacion nivelRequerido) {
        this.nivelRequerido = nivelRequerido;
    }

    @Override
    public boolean cumple(Muestra muestra) {
        return muestra.getNivelVerificacion().equals(nivelRequerido);
    }
}