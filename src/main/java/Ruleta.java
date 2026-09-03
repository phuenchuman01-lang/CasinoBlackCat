import java.util.Random;
import java.util.Scanner;

public class Ruleta {

    public static final int MAX_HISTORIAL = 100;
    public static int[] historialNumeros = new int[MAX_HISTORIAL];
    public static int[] historialApuestas = new int[MAX_HISTORIAL];
    public static boolean[] historialAciertos = new boolean[MAX_HISTORIAL];
    public static int historialSize = 0;
    public static Random rng = new Random();

    public static int[] numerosRojos = {
            1, 3, 5, 7, 9, 12, 14, 16, 18,
            19, 21, 23, 25, 27, 30, 32, 34, 36
    };

    public static void menu() {
        Scanner in = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = leerOpcion(in);
            ejecutarOpcion(opcion, in);
        } while (opcion != 3);

        in.close();
    }

    public static void mostrarMenu() {
        System.out.println("\n=== CASINO BLACK CAT: RULETA ===");
        System.out.println("1. Iniciar ronda");
        System.out.println("2. Ver estadísticas");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public static int leerOpcion(Scanner in) {
        int opcion = in.nextInt();
        return opcion;
    }

    public static void ejecutarOpcion(int opcion, Scanner in) {
        switch (opcion) {
            case 1:
                System.out.println("\n[INFO] Entrando a la mesa de ruleta...");
                iniciarRonda(in);
                break;
            case 2:
                System.out.println("\n[INFO] Calculando estadísticas...");
                mostrarEstadisticas();
                break;
            case 3:
                System.out.println("\nGracias por visitar el Casino Black Cat. ¡Hasta pronto!");
                break;
            default:
                System.out.println("\n[ERROR] Opción inválida. Intente nuevamente.");
        }
    }

    public static void iniciarRonda(Scanner in) {
        if (historialSize >= MAX_HISTORIAL) {
            System.out.println("[ADVERTENCIA] El historial está lleno. No se pueden jugar más rondas.");
            return;
        }

        char tipoApuesta = leerTipoApuesta(in);

        System.out.print("Ingrese el monto que desea apostar: $");
        int apuesta = in.nextInt();

        int numero = girarRuleta();
        boolean acierto = evaluarResultado(numero, tipoApuesta);

        registrarResultado(numero, apuesta, acierto);
        mostrarResultado(numero, tipoApuesta, apuesta, acierto);
    }

    public static char leerTipoApuesta(Scanner in) {
        char tipo;
        boolean valido = false;
        do {
            System.out.print("Seleccione tipo de apuesta (R: Rojo, N: Negro, P: Par, I: Impar): ");
            tipo = in.next().toUpperCase().charAt(0);

            if (tipo == 'R' || tipo == 'N' || tipo == 'P' || tipo == 'I') {
                valido = true;
            } else {
                System.out.println("[ERROR] Tipo de apuesta inválido. Intente nuevamente.");
            }
        } while (!valido);

        return tipo;
    }

    public static int girarRuleta() {
        return rng.nextInt(37);
    }

    public static boolean evaluarResultado(int numero, char tipo) {
        if (numero == 0) {
            return false;
        }

        switch (tipo) {
            case 'R': return esRojo(numero);
            case 'N': return !esRojo(numero);
            case 'P': return (numero % 2 == 0);
            case 'I': return (numero % 2 != 0);
            default: return false;
        }
    }

    public static boolean esRojo(int n) {
        for (int i = 0; i < numerosRojos.length; i++) {
            if (numerosRojos[i] == n) {
                return true;
            }
        }
        return false;
    }

    public static void registrarResultado(int numero, int apuesta, boolean acierto) {
        if (historialSize < MAX_HISTORIAL) {
            historialNumeros[historialSize] = numero;
            historialApuestas[historialSize] = apuesta;
            historialAciertos[historialSize] = acierto;
            historialSize++;
        }
    }

    public static void mostrarResultado(int numero, char tipo, int monto, boolean acierto) {
        System.out.println("\n*** LA RULETA ESTÁ GIRANDO... ***");
        System.out.println("¡Ha salido el número " + numero + "!");

        if (acierto) {
            System.out.println(">>> ¡Felicidades! Ha acertado su apuesta. Ganancia: $" + (monto * 2) + " <<<");
        } else {
            System.out.println(">>> Lo sentimos, ha perdido $" + monto + ". Mejor suerte para la próxima. <<<");
        }
    }

    public static void mostrarEstadisticas() {
        if (historialSize == 0) {
            System.out.println("No hay estadísticas disponibles. Juegue una ronda primero.");
            return;
        }

        int totalApostado = 0;
        int totalAciertos = 0;
        int gananciaNeta = 0;

        for (int i = 0; i < historialSize; i++) {
            totalApostado += historialApuestas[i];
            if (historialAciertos[i]) {
                totalAciertos++;
                gananciaNeta += historialApuestas[i];
            } else {
                gananciaNeta -= historialApuestas[i];
            }
        }

        double porcentajeAciertos = ((double) totalAciertos / historialSize) * 100.0;

        System.out.println("\n=== ESTADÍSTICAS DEL JUGADOR ===");
        System.out.println("Rondas jugadas: " + historialSize);
        System.out.println("Monto total apostado: $" + totalApostado);
        System.out.println("Total de aciertos: " + totalAciertos);
        System.out.printf("Porcentaje de aciertos: %.2f%%\n", porcentajeAciertos);

        if (gananciaNeta > 0) {
            System.out.println("Ganancia neta: +$" + gananciaNeta);
        } else if (gananciaNeta < 0) {
            System.out.println("Pérdida neta: -$" + Math.abs(gananciaNeta));
        } else {
            System.out.println("Balance neto: $0");
        }
    }
}