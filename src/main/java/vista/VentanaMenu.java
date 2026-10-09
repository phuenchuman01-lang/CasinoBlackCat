package vista;
import controlador.SessionController;
import javax.swing.*;
import java.awt.*;

public class VentanaMenu {
    private final JFrame frame = new JFrame("RULETA - Menú Principal");
    private final SessionController session;
    private JTextArea txtInfo;

    public VentanaMenu(SessionController session) {
        this.session = session;
        frame.setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel(new GridLayout(6, 1, 10, 10)); // Se aumentó una fila para el nuevo botón
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnJugar = new JButton("Jugar");
        JButton btnHistorial = new JButton("Ver Historial"); // Nuevo Botón
        JButton btnRecargar = new JButton("Recargar Saldo");
        JButton btnCambiarNombre = new JButton("Modificar Perfil");
        JButton btnSalir = new JButton("Cerrar Sesión");

        panelBotones.add(btnJugar);
        panelBotones.add(btnHistorial);
        panelBotones.add(btnRecargar);
        panelBotones.add(btnCambiarNombre);
        panelBotones.add(btnSalir);

        JPanel panelInfo = new JPanel(new BorderLayout());
        panelInfo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        txtInfo = new JTextArea();
        txtInfo.setEditable(false); txtInfo.setOpaque(false);
        actualizarPerfil();

        panelInfo.add(txtInfo, BorderLayout.CENTER);
        frame.add(panelBotones, BorderLayout.WEST); frame.add(panelInfo, BorderLayout.CENTER);
        frame.setSize(600, 300); frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnJugar.addActionListener(e -> { frame.dispose(); new VentanaRuleta(session).mostrarVentana(); });
        btnHistorial.addActionListener(e -> { frame.dispose(); new VentanaHistorial(session).mostrarVentana(); }); // Enlace a la ventana
        btnRecargar.addActionListener(e -> recargar());
        btnCambiarNombre.addActionListener(e -> cambiarNombre());
        btnSalir.addActionListener(e -> { session.cerrarSesion(); frame.dispose(); new VentanaLogin(session).mostrarVentana(); });
    }

    private void actualizarPerfil() {
        txtInfo.setText("Bienvenido, " + session.getNombreUsuario() + "\nSaldo Actual: $" + session.getUsuarioActual().getSaldo() + "\n\nSeleccione una acción.");
    }

    private void recargar() {
        try {
            int monto = Integer.parseInt(JOptionPane.showInputDialog("Ingrese monto:"));
            session.getUsuarioActual().depositar(monto);
            actualizarPerfil();
        } catch(Exception ex) { JOptionPane.showMessageDialog(frame, "Monto inválido"); }
    }

    private void cambiarNombre() {
        String nuevo = JOptionPane.showInputDialog("Nuevo nombre:");
        if(nuevo != null) { session.getUsuarioActual().setNombre(nuevo); actualizarPerfil(); }
    }

    public void mostrarVentana() { frame.setLocationRelativeTo(null); frame.setVisible(true); }
}