package Controlador;

import Model.Factura;
import Model.Pedido;
import Model.Proveedor;
import Model.Tienda;
import Vista.View;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ControladorPedido {

    Tienda tienda;

    public ControladorPedido(Tienda tienda){
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

    public static void mostrarPedidosProveedor(View view, Tienda tienda){

        String prov=view.getInput("Ingrese el nombre del Proveedor: ");
        for (Proveedor p: tienda.getProveedores()){
            if (p!=null && p.getNombre().equals(prov)){
                view.displayMessage(p.toString());
            }
        }
        for (Pedido c: tienda.getPedidos()){
            if (c!=null&&c.getProvAsignado().getNombre().equals(prov)) {
                view.displayMessage(c.toString());
            }
        }
        //MOSTRAR TOTAL
    }
    public static void mostrarPedidosTienda(View view, Tienda tienda){

        view.displayMessage("Pedidos de "+tienda.getNombre());
        for (Pedido p:tienda.getPedidos()){
            if (p!=null){
                view.displayMessage("---"+p);
            }
        }
        //MOSTRAR TOTAL
    }
}
