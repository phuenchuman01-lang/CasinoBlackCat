import javax.swing.*;
import java.awt.*;

public class VentanaMenu {
    private final JFrame frame = new JFrame("RULETA - Casino Black Cat");
    private final String nombreUsuario;

    public VentanaMenu(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;

        frame.setLayout(new BorderLayout());

        // Panel izquierdo (Botones)
        JPanel panelBotones = new JPanel(new GridLayout(4, 1, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnInicio = new JButton("Inicio");
        JButton btnJugar = new JButton("Jugar");
        JButton btnHistorial = new JButton("Historial");
        JButton btnSalir = new JButton("Salir");

        panelBotones.add(btnInicio);
        panelBotones.add(btnJugar);
        panelBotones.add(btnHistorial);
        panelBotones.add(btnSalir);

        // Panel derecho (Información)
        JPanel panelInfo = new JPanel(new BorderLayout());
        panelInfo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextArea txtInfo = new JTextArea();
        txtInfo.setEditable(false);
        txtInfo.setOpaque(false);
        txtInfo.setText("Bienvenido/a al menú principal, " + nombreUsuario + "\n\n"
                + "A la izquierda tienes:\n"
                + "- Jugar: abre la ventana de juego.\n"
                + "- Historial: abre la ventana de historial (en desarrollo).\n"
                + "- Salir: cierra sesión y vuelve al login.");

        panelInfo.add(new JLabel("RULETA - Casino Black Cat", SwingConstants.CENTER), BorderLayout.NORTH);
        panelInfo.add(txtInfo, BorderLayout.CENTER);

        frame.add(panelBotones, BorderLayout.WEST);
        frame.add(panelInfo, BorderLayout.CENTER);

        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Eventos
        btnJugar.addActionListener(e -> abrirRuleta());
        btnSalir.addActionListener(e -> cerrarSesion());
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void abrirRuleta() {
        frame.dispose();
        new VentanaRuleta(nombreUsuario).mostrarVentana();
    }

    private void cerrarSesion() {
        frame.dispose();
        new VentanaLogin().mostrarVentana();
    }
}