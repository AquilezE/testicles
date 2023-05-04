package Model;

import java.io.Serializable;

public abstract class Producto implements Serializable {
    protected String codigo;
    protected float precioVenta;
    protected float descuento;
    protected String descripcion;
    protected int numExistencias;
    private Proveedor[] proveedores = new Proveedor[1];

    public Producto(String codigo, float precioVenta, float descuento, String descripcion, int numExistencias) {
        this.codigo = codigo;
        this.precioVenta = precioVenta;
        this.descuento = descuento;
        this.descripcion = descripcion;
        this.numExistencias = numExistencias;
    }


    @Override
    public String toString() {
        return "Producto{" +
                "codigo='" + codigo + '\'' +
                ", precioVenta=" + precioVenta +
                ", descuento=" + descuento +
                ", descripcion='" + descripcion + '\'' +
                ", numExistencias=" + numExistencias +
                '}';
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
}