package modelo;

public class Resultado {
    private int numero;
    private TipoApuesta apuesta;
    private int monto;
    private boolean victoria;

    public Resultado(int numero, TipoApuesta apuesta, int monto, boolean victoria) {
        this.numero = numero;
        this.apuesta = apuesta;
        this.monto = monto;
        this.victoria = victoria;
    }

    public int getNumero() { return numero; }
    public TipoApuesta getApuesta() { return apuesta; }
    public int getMonto() { return monto; }
    public boolean isVictoria() { return victoria; }

    @Override
    public String toString() {
        return String.format("Número: %d | Apuesta: %s | Monto: $%d | %s",
                numero, apuesta.name(), monto, victoria ? "GANASTE" : "PERDISTE");
    }
}