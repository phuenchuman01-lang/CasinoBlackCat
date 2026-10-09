package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ruleta {
    private int saldoMesa;
    private Random rng = new Random();
    private int[] numerosRojos = { 1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36 };
    private List<Resultado> historialGlobal; // Asociación con Resultado

    public Ruleta() {
        this.saldoMesa = 0;
        this.historialGlobal = new ArrayList<>();
    }

    public Ruleta(int saldoInicial) {
        this.saldoMesa = saldoInicial;
        this.historialGlobal = new ArrayList<>();
    }

    public int girarRuleta() { return rng.nextInt(37); }

    public boolean evaluarResultado(int numero, TipoApuesta tipo) {
        if (numero == 0) return false;
        switch (tipo) {
            case ROJO: return esRojo(numero);
            case NEGRO: return !esRojo(numero);
            case PAR: return (numero % 2 == 0);
            case IMPAR: return (numero % 2 != 0);
            default: return false;
        }
    }

    private boolean esRojo(int n) {
        for (int num : numerosRojos) { if (num == n) return true; }
        return false;
    }

    public void registrarResultado(Resultado r) {
        this.historialGlobal.add(r);
    }
}