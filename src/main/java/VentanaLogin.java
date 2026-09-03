import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaLogin {

    public static final List<Usuario> USUARIOS = new ArrayList<>();

    private final JFrame frame = new JFrame("Login Casino Black Cat");
    private final JTextField txtUsuario = new JTextField(15);
    private final JPasswordField txtClave = new JPasswordField(15);
    private final JButton btnIngresar = new JButton("Ingresar");
    private final JButton btnRegistrar = new JButton("Registrar Jugador");

    public VentanaLogin() {
        if (USUARIOS.isEmpty()) {
            USUARIOS.add(new Usuario("admin", "1234", "Don Donnie"));
        }

        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel panelUser = new JPanel();
        panelUser.add(new JLabel("Usuario: "));
        panelUser.add(txtUsuario);

        JPanel panelPass = new JPanel();
        panelPass.add(new JLabel("Clave: "));
        panelPass.add(txtClave);

        panel.add(panelUser);
        panel.add(panelPass);
        panel.add(btnIngresar);
        panel.add(btnRegistrar);

        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnIngresar.addActionListener(e -> login());
        btnRegistrar.addActionListener(e -> abrirRegistro());
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void login() {
        String u = txtUsuario.getText();
        String p = new String(txtClave.getPassword());
        String nombre = validarCredenciales(u, p);

        if (!nombre.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Bienvenido a la mesa, " + nombre, "Éxito", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();

            Ruleta.menu();
        } else {
            JOptionPane.showMessageDialog(frame, "Credenciales inválidas. Intente nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String validarCredenciales(String u, String p) {
        for (Usuario user : USUARIOS) {
            if (user.validarCredenciales(u, p)) {
                return user.getNombre();
            }
        }
        return "";
    }

    private void abrirRegistro() {
        frame.dispose();
        VentanaRegistro registro = new VentanaRegistro();
        registro.mostrarVentana();
    }
}