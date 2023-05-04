package Model;
import java.io.Serializable;


public class Factura implements Serializable {
    private Cliente cliente;
    private Producto prodVendido;
    private Tienda[] tiendas = new Tienda[1];
    private Cliente[] clientes = new Cliente[1];

    public Factura(Cliente cliente, Producto prodVendido) {
        this.cliente = cliente;
        this.prodVendido = prodVendido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Producto getProdVendido() {
        return prodVendido;
    }

    public void setProdVendido(Producto prodVendido) {
        this.prodVendido = prodVendido;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "cliente=" + cliente +
                ", prodVendido=" + prodVendido +
                '}';
    }
}
