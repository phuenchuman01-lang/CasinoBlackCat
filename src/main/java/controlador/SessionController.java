package controlador;
import modelo.Usuario;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SessionController {
    private Usuario usuarioActual;
    private List<Usuario> usuariosRegistrados;
    private final String ARCHIVO_DATOS = "usuarios.dat";

    public SessionController() {
        cargarDatos();
        if (usuariosRegistrados.isEmpty()) {
            usuariosRegistrados.add(new Usuario("admin", "1234", "Don Donnie"));
            guardarDatos();
        }
    }

    @SuppressWarnings("unchecked")
    private void cargarDatos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_DATOS))) {
            usuariosRegistrados = (List<Usuario>) ois.readObject();
        } catch (Exception e) {
            usuariosRegistrados = new ArrayList<>();
        }
    }

    public void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_DATOS))) {
            oos.writeObject(usuariosRegistrados);
        } catch (Exception e) {
            System.err.println("Error guardando datos.");
        }
    }

    public void registrarUsuario(String usuario, String clave, String nombre) {
        if (usuario == null || usuario.isBlank() || clave == null || clave.isBlank() || nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Datos requeridos");
        }
        usuariosRegistrados.add(new Usuario(usuario, clave, nombre));
        guardarDatos();
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