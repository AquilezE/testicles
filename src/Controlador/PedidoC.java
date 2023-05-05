package Controlador;

import Model.Pedido;
import Model.Proveedor;
import Model.Tienda;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class PedidoC {

    Tienda tienda;

    public PedidoC(Tienda tienda){
        this.tienda=tienda;
    }
    public boolean generarPedido(String code, String fechaPedido, int cantidadProd){
        for (int i = 0; i < tienda.nInventory; i++) {
            if (tienda.getProductos()[i].getCodigo().equals(code)){

                tienda.getProductos()[i].setNumExistencias(tienda.getProductos()[i].getNumExistencias()+cantidadProd);

                Pedido pedidoTEMP=new Pedido(tienda.getProductos()[i],fechaPedido,tienda.getProductos()[i].getProveedor(),cantidadProd);
                tienda.getPedidos()[tienda.nPedidos]=pedidoTEMP;
                tienda.nPedidos++;

                try {
                    FileOutputStream fos = new FileOutputStream("tienda.ser");
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(tienda);
                    oos.close();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return true;
            }
        }
        return false;
    }
}
