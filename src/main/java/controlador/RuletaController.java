package controlador;
import modelo.Ruleta;
import modelo.TipoApuesta;
import modelo.Usuario;

public class RuletaController {
    private Ruleta ruleta;
    private SessionController session;

    public RuletaController(SessionController session) {
        this.session = session;
        this.ruleta = new Ruleta();
    }

    public int realizarApuesta(TipoApuesta tipo, int monto) {
        Usuario u = session.getUsuarioActual();
        if (!u.retirar(monto)) return -1; // Fondos insuficientes

        int numero = ruleta.girarRuleta();
        if (ruleta.evaluarResultado(numero, tipo)) {
            u.depositar(monto * 2);
        }
        return numero;
    }

    public boolean esVictoria(int numero, TipoApuesta tipo) {
        return ruleta.evaluarResultado(numero, tipo);
    }
}