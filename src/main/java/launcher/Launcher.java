package launcher;

import controlador.SessionController;
import vista.VentanaLogin;
import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
import javax.swing.*;

public class Launcher {
    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(new FlatDarkPurpleIJTheme()); } catch (Exception e) {}

        SwingUtilities.invokeLater(() -> {
            SessionController session = new SessionController();
            new VentanaLogin(session).mostrarVentana();
        });
    }
}