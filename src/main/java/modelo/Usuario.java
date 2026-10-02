package modelo;

public class Usuario {
    private String username;
    private String password;
    private String nombre;
    private int saldo;

    public Usuario() {
        this("invitado", "1234", "Invitado");
    }

    public Usuario(String username, String password, String nombre) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.saldo = 0;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getNombre() { return nombre; }
    public int getSaldo() { return saldo; }

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
}