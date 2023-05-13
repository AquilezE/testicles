package Model;

import java.io.Serializable;

public class Proveedor implements Serializable {
    private String nombre;
    private String telefono;
    private String email;
    private Producto[] productos = new Producto[100];

    public Proveedor(String nombre, String telefono, String email) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Producto[] geProductos(){
        return productos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("─────────────────────────────────────\n");
        sb.append(" Nombre: ").append(nombre).append("\n");
        sb.append(" Teléfono: ").append(telefono).append("\n");
        sb.append(" Email: ").append(email).append("\n");
        sb.append("─────────────────────────────────────\n");
    
        return sb.toString();
    }
    
}
