package controlador;

import modelo.Estadisticas;
import modelo.Usuario;

public class EstadisticasController {
    private final SessionController session;

    public EstadisticasController(SessionController session) {
        this.session = session;
    }

    public Estadisticas generarEstadisticas() {
        Usuario u = session.getUsuarioActual();
        if (u != null) {
            // Se le inyecta la lista de resultados del usuario actual
            return new Estadisticas(u.getHistorial());
        }
        return null;
    }
}