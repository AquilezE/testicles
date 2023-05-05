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
        return "Pedido{" +
                "prodPedido=" +  prodPedido+
                "cantidadProd="+cantidadProd+
                ", fechaPedido='" + fechaPedido + '\'' +
                ", provAsignado=" + provAsignado +
                '}';
    }
}
