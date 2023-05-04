package Model;
import java.io.Serializable;
import java.util.Arrays;


public class Pedido implements Serializable {
    private Producto[] prodPedidos=new Producto[30];
    private String fechaPedido;
    private Proveedor provAsignado;

    public Pedido(Producto[] prodPedidos, String fechaPedido, Proveedor provAsignado) {
        this.prodPedidos = prodPedidos;
        this.fechaPedido = fechaPedido;
        this.provAsignado = provAsignado;
    }

    public Producto[] getProdPedidos() {
        return prodPedidos;
    }

    public void setProdPedidos(Producto[] prodPedidos) {
        this.prodPedidos = prodPedidos;
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
                "prodPedidos=" + Arrays.toString(prodPedidos) +
                ", fechaPedido='" + fechaPedido + '\'' +
                ", provAsignado=" + provAsignado +
                '}';
    }
}
