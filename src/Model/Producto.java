package Model;

import java.io.Serializable;

public abstract class Producto implements Serializable {
    protected String codigo;
    protected float precioVenta;
    protected float descuento;
    protected String descripcion;
    protected int numExistencias;

    protected Proveedor proveedor;

    public Producto(String codigo, float precioVenta, float descuento, String descripcion, int numExistencias,Proveedor proveedor) {
        this.codigo = codigo;
        this.precioVenta = precioVenta;
        this.descuento = descuento;
        this.descripcion = descripcion;
        this.numExistencias = numExistencias;
        this.proveedor=proveedor;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════\n"); // Línea superior
        sb.append(" Producto No Electronico\n");
        sb.append("═══════════════════════\n\n");
        sb.append(" Código: ").append(codigo).append("\n");
        sb.append(" Precio de Venta: ").append(precioVenta).append("\n");
        sb.append(" Descuento: ").append(descuento).append("\n");
        sb.append(" Descripción: ").append(descripcion).append("\n");
        sb.append(" Número de Existencias: ").append(numExistencias).append("\n");
        sb.append(" Proveedor: ").append(proveedor).append("\n");
        sb.append("═══════════════════════\n"); // Línea inferior
    
        return sb.toString();
    }
    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNumExistencias() {
        return numExistencias;
    }

    public void setNumExistencias(int numExistencias) {
        this.numExistencias = numExistencias;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}