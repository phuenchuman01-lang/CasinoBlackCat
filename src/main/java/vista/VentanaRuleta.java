package vista;
import controlador.RuletaController;
import controlador.SessionController;
import modelo.TipoApuesta;
import javax.swing.*;
import java.awt.*;

public class VentanaRuleta {
    private final JFrame frame = new JFrame("Juego de Ruleta");
    private final SessionController session;
    private final RuletaController controlador;
    private JComboBox<TipoApuesta> cmbApuesta;
    private JTextField txtMonto;
    private JLabel lblResultado;

    public VentanaRuleta(SessionController session) {
        this.session = session;
        this.controlador = new RuletaController(session);
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        panel.add(new JLabel("Tipo de apuesta:"));
        cmbApuesta = new JComboBox<>(TipoApuesta.values());
        panel.add(cmbApuesta);

        panel.add(new JLabel("Monto a apostar:"));
        txtMonto = new JTextField();
        panel.add(txtMonto);

        JButton btnGirar = new JButton("Girar");
        JButton btnVolver = new JButton("Volver");
        panel.add(btnVolver); panel.add(btnGirar);

        lblResultado = new JLabel("Saldo: $" + session.getUsuarioActual().getSaldo(), SwingConstants.CENTER);

        frame.setLayout(new BorderLayout()); frame.add(panel, BorderLayout.CENTER); frame.add(lblResultado, BorderLayout.SOUTH);
        frame.setSize(500, 250); frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnGirar.addActionListener(e -> jugar());
        btnVolver.addActionListener(e -> { frame.dispose(); new VentanaMenu(session).mostrarVentana(); });
    }

    private void jugar() {
        try {
            int monto = Integer.parseInt(txtMonto.getText());
            TipoApuesta tipo = (TipoApuesta) cmbApuesta.getSelectedItem();
            int numero = controlador.realizarApuesta(tipo, monto);

            if (numero == -1) { JOptionPane.showMessageDialog(frame, "Saldo insuficiente."); return; }

            boolean gano = controlador.esVictoria(numero, tipo);
            lblResultado.setText(String.format("Salió el %d | %s | Nuevo Saldo=$%d", numero, gano ? "GANASTE" : "PERDISTE", session.getUsuarioActual().getSaldo()));
        } catch (Exception ex) { JOptionPane.showMessageDialog(frame, "Monto inválido."); }
    }

    public void mostrarVentana() { frame.setLocationRelativeTo(null); frame.setVisible(true); }
}