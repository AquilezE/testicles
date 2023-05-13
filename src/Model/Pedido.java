package Model;
import java.io.Serializable;


public class Pedido implements Serializable {
    private Producto prodPedido;
    private String fechaPedido;

    private int cantidadProd;
    private Proveedor provAsignado;

    public Pedido(Producto prodPedidos, String fechaPedido, Proveedor provAsignaod, int cantidadProd) {
        this.prodPedido = prodPedidos;
        this.fechaPedido = fechaPedido;
        this.provAsignado = provAsignaod;
        this.cantidadProd=cantidadProd;
    }

    public Producto getProdPedido() {
        return prodPedido;
    }

    public void setProdPedido(Producto prodPedidos) {
        this.prodPedido = prodPedidos;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Proveedor getProvAsignado() {
        return provAsignado;
    }

    public void setProvAsignado(Proveedor provAsignado) {
        this.provAsignado = provAsignado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════\n"); // Línea superior
        sb.append(" Pedido\n");
        sb.append("═══════════════════════\n\n");
        sb.append(" Productos Pedidos: ").append(prodPedido).append("\n");
        sb.append(" Cantidad de Productos: ").append(cantidadProd).append("\n");
        sb.append(" Fecha de Pedido: ").append(fechaPedido).append("\n");
        sb.append(" Proveedor Asignado: ").append(provAsignado).append("\n");
        sb.append("═══════════════════════\n"); // Línea inferior
    
        return sb.toString();
    }
    
}
