package modelo;

public class Resultado {
    private int numero;
    private TipoApuesta apuesta;
    private int monto;

    public Resultado(int numero, TipoApuesta apuesta, int monto) {
        this.numero = numero; this.apuesta = apuesta; this.monto = monto;
    }
}