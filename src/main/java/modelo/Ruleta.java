package modelo;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ruleta {
    private int saldoMesa;
    private Random rng = new Random();
    private int[] numerosRojos = { 1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36 };
    private List<Resultado> historialGlobal;

    public Ruleta() { this.saldoMesa = 0; this.historialGlobal = new ArrayList<>(); }

    public int girarRuleta() { return rng.nextInt(37); }

    public String getColor(int numero) {
        if (numero == 0) return "Verde";
        for (int num : numerosRojos) {
            if (num == numero) return "Rojo";
        }
        return "Negro";
    }

    public void registrarResultado(Resultado r) { this.historialGlobal.add(r); }
}