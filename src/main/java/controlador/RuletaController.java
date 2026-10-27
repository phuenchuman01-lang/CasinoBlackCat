package controlador;
import modelo.ApuestaBase;
import modelo.Resultado;
import modelo.Ruleta;
import modelo.Usuario;

public class RuletaController {
    private Ruleta ruleta;
    private SessionController session;

    public RuletaController(SessionController session) {
        this.session = session;
        this.ruleta = new Ruleta();
    }

    // El controlador ahora recibe el objeto polimórfico
    public Resultado realizarApuesta(ApuestaBase apuesta) {
        Usuario u = session.getUsuarioActual();
        if (!u.retirar(apuesta.getMonto())) return null;

        int numero = ruleta.girarRuleta();
        String color = ruleta.getColor(numero);

        boolean victoria = apuesta.acierta(numero, color);

        if (victoria) {
            u.depositar(apuesta.getMonto() * 2);
        }

        Resultado res = new Resultado(numero, apuesta.getEtiqueta(), apuesta.getMonto(), victoria);
        u.agregarResultado(res);
        ruleta.registrarResultado(res);
        session.guardarDatos(); // Guardamos el estado para persistencia

        return res;
    }
}