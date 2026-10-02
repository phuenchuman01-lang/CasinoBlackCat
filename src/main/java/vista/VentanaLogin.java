package vista;

import controlador.SessionController;
import javax.swing.*;
import java.awt.*;

public class VentanaLogin {
    private final JFrame frame = new JFrame("Login Casino Black Cat");
    private final JTextField txtUsuario = new JTextField(15);
    private final JPasswordField txtClave = new JPasswordField(15);
    private final JButton btnIngresar = new JButton("Ingresar");
    private final JButton btnRegistrar = new JButton("Registrar Jugador");

    private final SessionController session; // Inyección de dependencia

    public VentanaLogin(SessionController session) {
        this.session = session;

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

        // El Controlador procesa, la Vista solo muestra el resultado
        if (session.iniciarSesion(u, p)) {
            JOptionPane.showMessageDialog(frame, "Bienvenido a la mesa, " + session.getNombreUsuario(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
            VentanaMenu menu = new VentanaMenu(session); // Pasa la sesión correcta
            menu.mostrarVentana();
        } else {
            JOptionPane.showMessageDialog(frame, "Credenciales inválidas. Intente nuevamente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirRegistro() {
        frame.dispose();
        VentanaRegistro registro = new VentanaRegistro(session); // Pasa la sesión
        registro.mostrarVentana();
    }
}