package controlador;
import modelo.Resultado;
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
        if (!u.retirar(monto)) return -1; //Sin plata

        int numero = ruleta.girarRuleta();
        boolean victoria = ruleta.evaluarResultado(numero, tipo);

        if (victoria) {
            u.depositar(monto * 2);
        }

        // Se crea el objeto y se asocia a las clases requeridas
        Resultado res = new Resultado(numero, tipo, monto, victoria);
        u.agregarResultado(res);
        ruleta.registrarResultado(res);

        return numero;
    }

    public boolean esVictoria(int numero, TipoApuesta tipo) {
        return ruleta.evaluarResultado(numero, tipo);
    }
}