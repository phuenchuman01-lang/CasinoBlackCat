package modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estadisticas {
    private final List<Resultado> historial;

    public Estadisticas(List<Resultado> historial) {
        this.historial = historial;
    }

    public int getTotalJugadas() {
        return historial.size();
    }

    public int getVictorias() {
        int count = 0;
        for (Resultado r : historial) {
            if (r.isVictoria()) {
                count++;
            }
        }
        return count;
    }

    public double getPorcentajeVictorias() {
        if (getTotalJugadas() == 0) return 0.0;
        return (getVictorias() * 100.0) / getTotalJugadas();
    }

    public int getRachaMaxima() {
        int maxRacha = 0;
        int rachaActual = 0;
        for (Resultado r : historial) {
            if (r.isVictoria()) {
                rachaActual++;
                if (rachaActual > maxRacha) {
                    maxRacha = rachaActual;
                }
            } else {
                rachaActual = 0;
            }
        }
        return maxRacha;
    }

    public String getTipoMasJugado() {
        if (historial.isEmpty()) return null;

        Map<String, Integer> conteo = new HashMap<>();
        for (Resultado r : historial) {
            conteo.put(r.getApuesta(), conteo.getOrDefault(r.getApuesta(), 0) + 1);
        }

        String masJugado = null;
        int max = -1;
        for (Map.Entry<String, Integer> entry : conteo.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                masJugado = entry.getKey();
            }
        }
        return masJugado;
    }
}