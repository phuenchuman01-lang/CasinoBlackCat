import javax.swing.*;
import java.awt.*;

public class VentanaRegistro {
    private final JFrame frame = new JFrame("Registro - Casino Black Cat");
    private final JTextField txtNombre = new JTextField(15);
    private final JTextField txtUsuario = new JTextField(15);
    private final JPasswordField txtClave = new JPasswordField(15);
    private final JButton btnRegistrar = new JButton("Crear Cuenta");
    private final JButton btnVolver = new JButton("Volver");

    public VentanaRegistro() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Nombre completo:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Nuevo Usuario:"));
        panel.add(txtUsuario);
        panel.add(new JLabel("Nueva Clave:"));
        panel.add(txtClave);
        panel.add(btnVolver);
        panel.add(btnRegistrar);

        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnRegistrar.addActionListener(e -> registrarUsuario());
        btnVolver.addActionListener(e -> volverLogin());
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void registrarUsuario() {
        String nombre = txtNombre.getText().trim();
        String usr = txtUsuario.getText().trim();
        String pass = new String(txtClave.getPassword()).trim();

        if (nombre.isEmpty() || usr.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Todos los campos son obligatorios", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        VentanaLogin.USUARIOS.add(new Usuario(usr, pass, nombre));
        JOptionPane.showMessageDialog(frame, "Usuario registrado exitosamente. Puede iniciar sesión.", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
        volverLogin();
    }

    private void volverLogin() {
        frame.dispose();
        VentanaLogin login = new VentanaLogin();
        login.mostrarVentana();
    }
}