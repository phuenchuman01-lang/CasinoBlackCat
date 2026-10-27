package modelo;
import java.io.Serializable;

public class Resultado implements Serializable {
    private int numero;
    private String etiquetaApuesta;
    private int monto;
    private boolean victoria;

    public Resultado(int numero, String etiquetaApuesta, int monto, boolean victoria) {
        this.numero = numero;
        this.etiquetaApuesta = etiquetaApuesta;
        this.monto = monto;
        this.victoria = victoria;
    }

    public int getNumero() { return numero; }
    public String getApuesta() { return etiquetaApuesta; }
    public int getMonto() { return monto; }
    public boolean isVictoria() { return victoria; }

    @Override
    public String toString() {
        return String.format("Número: %d | Apuesta: %s | Monto: $%d | %s",
                numero, etiquetaApuesta, monto, victoria ? "GANASTE" : "PERDISTE");
    }
}