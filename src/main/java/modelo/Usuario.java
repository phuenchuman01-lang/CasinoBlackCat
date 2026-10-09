package modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String username;
    private String password;
    private String nombre;
    private int saldo;
    private List<Resultado> historial; // Asociación con Resultado

    public Usuario() {
        this("invitado", "1234", "Invitado");
    }

    public Usuario(String username, String password, String nombre) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.saldo = 0;
        this.historial = new ArrayList<>();
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getNombre() { return nombre; }
    public int getSaldo() { return saldo; }
    public List<Resultado> getHistorial() { return historial; }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        }
    }

    public void depositar(int monto) {
        if (monto > 0) this.saldo += monto;
    }

    public boolean retirar(int monto) {
        if (monto > 0 && monto <= saldo) {
            this.saldo -= monto;
            return true;
        }
        return false;
    }

    public boolean validarCredenciales(String u, String p) {
        return this.username.equals(u) && this.password.equals(p);
    }

    public void agregarResultado(Resultado r) {
        this.historial.add(r);
    }
}