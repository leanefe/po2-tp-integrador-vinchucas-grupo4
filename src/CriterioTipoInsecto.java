
class CriterioTipoInsecto implements CriterioBusqueda {
    private final TipoOpinion tipoInsectoDetectado;

    public CriterioTipoInsecto(TipoOpinion tipoInsectoDetectado) {
        this.tipoInsectoDetectado = tipoInsectoDetectado;
    }

    @Override
    public boolean cumple(Muestra muestra) {
        return muestra.resultadoActualOpiniones().equals(tipoInsectoDetectado);
    }
}