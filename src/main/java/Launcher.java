import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
import javax.swing.*;

public class Launcher {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkPurpleIJTheme());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            VentanaLogin login = new VentanaLogin();
            login.mostrarVentana();
        });
    }
}