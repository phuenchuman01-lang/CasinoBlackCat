package controlador;
import modelo.Resultado;
import modelo.Usuario;
import java.util.List;

public class ResultadoController {
    private final SessionController session;

    public ResultadoController(SessionController session) {
        this.session = session;
    }

    public List<Resultado> obtenerHistorial() {
        Usuario u = session.getUsuarioActual();
        if (u != null) {
            return u.getHistorial();
        }
        return null;
    }
}