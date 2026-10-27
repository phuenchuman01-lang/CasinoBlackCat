package vista;

import controlador.EstadisticasController;
import controlador.SessionController;
import modelo.Estadisticas;

import javax.swing.*;
import java.awt.*;

public class VentanaEstadisticas {
    private final JFrame frame = new JFrame("Estadísticas del Jugador");
    private final SessionController session;
    private final EstadisticasController controlador;

    public VentanaEstadisticas(SessionController session) {
        this.session = session;
        this.controlador = new EstadisticasController(session);

        frame.setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Estadísticas de: " + session.getNombreUsuario(), SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(lblTitulo, BorderLayout.NORTH);

        JTextArea txtEstadisticas = new JTextArea();
        txtEstadisticas.setEditable(false);
        txtEstadisticas.setMargin(new Insets(15, 15, 15, 15));
        txtEstadisticas.setFont(new Font("Monospaced", Font.PLAIN, 14));

        Estadisticas stats = controlador.generarEstadisticas();

        if (stats == null || stats.getTotalJugadas() == 0) {
            txtEstadisticas.setText("Aún no tienes jugadas registradas para calcular métricas.");
        } else {
            String tipoMasJugado = stats.getTipoMasJugado();
            String tipoStr = (tipoMasJugado != null) ? tipoMasJugado : "N/A";

            String reporte = String.format(
                    "Total de Jugadas    : %d\n" +
                            "Total de Victorias  : %d\n" +
                            "Porcentaje de Éxito : %.2f%%\n" +
                            "Racha Máxima        : %d victorias seguidas\n" +
                            "Apuesta Preferida   : %s",
                    stats.getTotalJugadas(),
                    stats.getVictorias(),
                    stats.getPorcentajeVictorias(),
                    stats.getRachaMaxima(),
                    tipoStr
            );
            txtEstadisticas.setText(reporte);
        }

        frame.add(new JScrollPane(txtEstadisticas), BorderLayout.CENTER);

        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.addActionListener(e -> {
            frame.dispose();
            new VentanaMenu(session).mostrarVentana();
        });

        JPanel panelSur = new JPanel();
        panelSur.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelSur.add(btnVolver);
        frame.add(panelSur, BorderLayout.SOUTH);

        frame.setSize(420, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}