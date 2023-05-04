package Model;

import java.io.Serializable;

public class Cliente implements Serializable {
    private String nombre;
    private String rfc;
    private String direccion;
    private String email;
    private Factura[] facturas = new Factura[10];

    public Cliente(String nombre, String rfc, String direccion, String email) {
        this.nombre = nombre;
        this.rfc = rfc;
        this.direccion = direccion;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Factura[] getFacturas(){
        return facturas;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", rfc='" + rfc + '\'' +
                ", direccion='" + direccion + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
