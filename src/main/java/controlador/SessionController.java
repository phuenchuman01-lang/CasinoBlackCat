package controlador;
import modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

public class SessionController {
    private Usuario usuarioActual;
    private List<Usuario> usuariosRegistrados = new ArrayList<>();

    public SessionController() {
        usuariosRegistrados.add(new Usuario("admin", "1234", "Don Donnie"));
    }

    public void registrarUsuario(String usuario, String clave, String nombre) {
        if (usuario == null || usuario.isBlank() || clave == null || clave.isBlank() || nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Datos requeridos");
        }
        usuariosRegistrados.add(new Usuario(usuario, clave, nombre));
    }

    public boolean iniciarSesion(String usuario, String clave) {
        for (Usuario u : usuariosRegistrados) {
            if (u.validarCredenciales(usuario, clave)) {
                usuarioActual = u;
                return true;
            }
        }
        return false;
    }

    public boolean hayUsuario() { return usuarioActual != null; }
    public String getNombreUsuario() { return hayUsuario() ? usuarioActual.getNombre() : ""; }
    public Usuario getUsuarioActual() { return usuarioActual; }
    public void cerrarSesion() { usuarioActual = null; }
}