package Model;

import java.io.Serializable;

public class Tienda implements Serializable {
    private String nombre;
    private String rfc;

    private Cliente[] clientes=new Cliente[20];
    public int nClientes=0;
    private Factura[] facturas = new Factura[100];

    public int nFacturas=0;
    private Producto[] productos=new Producto[100];
    public int nInventory=0;
    private Proveedor[] proveedors=new Proveedor[10];

    private Pedido[] pedidos=new Pedido[100];
    public int nPedidos=0;
    public int nProovedores=0;


    public Tienda(String nombre, String rfc) {
        this.nombre = nombre;
        this.rfc = rfc;
    }

    public int getnInventory(){
        return nInventory;
    }

    public void setnInventory(int nInventory){
        this.nInventory=nInventory;
    }
    public Producto[] getProductos() {
        return productos;
    }


    public void setnClientes(int nClientes) {
        this.nClientes = nClientes;
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

    public Factura[] getFacturas() {
        return facturas;
    }

    public Proveedor[] getProveedores() {
        return proveedors;
    }

    public Cliente[] getClientes(){
        return clientes;
    }

    public Pedido[] getPedidos() {
        return pedidos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("═════════════════════════════════════\n"); // Línea superior
        sb.append(" Tienda\n");
        sb.append("═════════════════════════════════════\n\n");
        sb.append(" Nombre: ").append(nombre).append("\n");
        sb.append(" RFC: ").append(rfc).append("\n");
        sb.append("═════════════════════════════════════\n"); // Línea inferior
    
        return sb.toString();
    }
    
}
