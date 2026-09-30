import javax.swing.*;
import java.awt.*;

public class VentanaRuleta {
    private final JFrame frame = new JFrame("Juego de Ruleta");
    private final Ruleta motorRuleta = new Ruleta();
    private final String nombreUsuario;

    private JComboBox<String> cmbApuesta;
    private JTextField txtMonto;
    private JLabel lblResultado;
    private int saldoVirtual = 1000;

    public VentanaRuleta(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Tipo de apuesta:"));
        String[] opciones = {"Rojo (R)", "Negro (N)", "Par (P)", "Impar (I)"};
        cmbApuesta = new JComboBox<>(opciones);
        panel.add(cmbApuesta);

        panel.add(new JLabel("Monto a apostar:"));
        txtMonto = new JTextField();
        panel.add(txtMonto);

        JButton btnGirar = new JButton("Girar");
        JButton btnVolver = new JButton("Volver al Menú");
        panel.add(btnVolver);
        panel.add(btnGirar);

        lblResultado = new JLabel("Saldo actual: $" + saldoVirtual, SwingConstants.CENTER);

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.add(lblResultado, BorderLayout.SOUTH);

        frame.setSize(500, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnGirar.addActionListener(e -> jugarRonda());
        btnVolver.addActionListener(e -> volverMenu());
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void jugarRonda() {
        try {
            int monto = Integer.parseInt(txtMonto.getText());
            if (monto > saldoVirtual || monto <= 0) {
                JOptionPane.showMessageDialog(frame, "Monto inválido o saldo insuficiente.");
                return;
            }

            // Extraer el caracter (R, N, P, I) del JComboBox
            String seleccion = (String) cmbApuesta.getSelectedItem();
            char tipo = seleccion.charAt(seleccion.length() - 2);

            // Delegar procesamiento a la clase Ruleta
            int numeroObtenido = motorRuleta.girarRuleta();
            boolean acierto = motorRuleta.evaluarResultado(numeroObtenido, tipo);
            motorRuleta.registrarResultado(numeroObtenido, monto, acierto);

            // Actualizar vista
            if (acierto) {
                saldoVirtual += monto;
                lblResultado.setText(String.format("Número %d | Apuesta=%c | Monto=$%d | GANASTE | Saldo=$%d",
                        numeroObtenido, tipo, monto, saldoVirtual));
            } else {
                saldoVirtual -= monto;
                lblResultado.setText(String.format("Número %d | Apuesta=%c | Monto=$%d | PERDISTE | Saldo=$%d",
                        numeroObtenido, tipo, monto, saldoVirtual));
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Ingrese un monto numérico válido.");
        }
    }

    private void volverMenu() {
        frame.dispose();
        new VentanaMenu(nombreUsuario).mostrarVentana();
    }
}