package vista;

import controlador.ResultadoController;
import controlador.SessionController;
import modelo.Resultado;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaHistorial {
    private final JFrame frame = new JFrame("Historial de Jugadas");
    private final SessionController session;
    private final ResultadoController controlador;

    public VentanaHistorial(SessionController session) {
        this.session = session;
        this.controlador = new ResultadoController(session);

        frame.setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Historial de: " + session.getNombreUsuario(), SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(lblTitulo, BorderLayout.NORTH);

        JTextArea txtHistorial = new JTextArea();
        txtHistorial.setEditable(false);
        txtHistorial.setMargin(new Insets(10, 10, 10, 10));

        // El controlador solicita la información
        List<Resultado> historial = controlador.obtenerHistorial();
        if (historial == null || historial.isEmpty()) {
            txtHistorial.setText("No hay jugadas registradas aún.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Resultado r : historial) {
                sb.append(r.toString()).append("\n");
            }
            txtHistorial.setText(sb.toString());
        }

        JScrollPane scrollPane = new JScrollPane(txtHistorial);
        frame.add(scrollPane, BorderLayout.CENTER);

        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(e -> {
            frame.dispose();
            new VentanaMenu(session).mostrarVentana();
        });

        JPanel panelSur = new JPanel();
        panelSur.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelSur.add(btnVolver);
        frame.add(panelSur, BorderLayout.SOUTH);

        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}