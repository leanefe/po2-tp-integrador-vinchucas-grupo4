import java.util.List;

public class Ubicacion {
    private double latitud;
    private double longitud;

    /**
     * @param latitud El signo indica si se encuentra por encima (+) o debajo (-) del ecuador.
     * @param longitud El signo indica si se encuentra al este (+) u oeste (-) del meridiano de Greenwich.
     */
    public Ubicacion(double latitud, double longitud){
        this.latitud = latitud;
        this.longitud = longitud;
    }

    /**
     *  El método usa la fórmula de Haversine para calcular la distancia entre dos puntos, tomando sus latitudes y longitudes.
     * @param ubicacion
     * @return La distancia en kilómetros entre esta ubicación y la ubicación dada por parámetro.
     */
    public double distanciaEnKmA(Ubicacion ubicacion){
        final double radioTierra = 6371;

        double latReferencia = Math.toRadians(this.latitud);
        double lonReferencia = Math.toRadians(this.longitud);
        double latComparacion = Math.toRadians(ubicacion.latitud);
        double lonComparacion = Math.toRadians(ubicacion.longitud);

        double deltaLat = latComparacion - latReferencia;
        double deltaLon = lonComparacion - lonReferencia;

        double a = Math.pow(Math.sin(deltaLat / 2), 2)
                + Math.cos(latReferencia) * Math.cos(latComparacion)
                * Math.pow(Math.sin(deltaLon / 2), 2);

        return radioTierra * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    }

    /**
     * @param ubicaciones
     * @param distancia en Km
     * @return Aquellas ubicaciones de la lista que se encuentran a menor distancia (en Km) que la pasada por parámetro.
     */
    public List<Ubicacion> ubicacionesAMenosDe(List<Ubicacion> ubicaciones, double distancia){
        return ubicaciones.stream()
                .filter(u -> this.distanciaEnKmA(u) < distancia)
                .toList();
    }
}
