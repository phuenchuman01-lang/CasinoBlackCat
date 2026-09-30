import java.util.Random;

public class Ruleta {
    public static final int MAX_HISTORIAL = 100;
    public int[] historialNumeros = new int[MAX_HISTORIAL];
    public int[] historialApuestas = new int[MAX_HISTORIAL];
    public boolean[] historialAciertos = new boolean[MAX_HISTORIAL];
    public int historialSize = 0;

    private Random rng = new Random();
    private int[] numerosRojos = { 1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36 };

    public int girarRuleta() {
        return rng.nextInt(37);
    }

    public boolean evaluarResultado(int numero, char tipo) {
        if (numero == 0) return false;

        switch (tipo) {
            case 'R': return esRojo(numero);
            case 'N': return !esRojo(numero);
            case 'P': return (numero % 2 == 0);
            case 'I': return (numero % 2 != 0);
            default: return false;
        }
    }

    public boolean esRojo(int n) {
        for (int i = 0; i < numerosRojos.length; i++) {
            if (numerosRojos[i] == n) return true;
        }
        return false;
    }

    public void registrarResultado(int numero, int apuesta, boolean acierto) {
        if (historialSize < MAX_HISTORIAL) {
            historialNumeros[historialSize] = numero;
            historialApuestas[historialSize] = apuesta;
            historialAciertos[historialSize] = acierto;
            historialSize++;
        }
    }
}