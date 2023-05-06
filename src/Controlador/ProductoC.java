package Controlador;

import Model.Producto;
import Model.ProductoElectronico;
import Model.Proveedor;
import Model.Tienda;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ProductoC {
    public static boolean modificarProducto(Tienda tienda, String codigo, float nuevoPrecioVenta, float nuevoDescuento, String nuevaDescripcion, int nuevasExistencias, String nameProv) {
        Producto[] productos = tienda.getProductos();
        for (int i = 0; i < productos.length; i++) {
            if (productos[i] != null && productos[i].getCodigo().equals(codigo)) {
                productos[i].setPrecioVenta(nuevoPrecioVenta);
                productos[i].setDescuento(nuevoDescuento);
                productos[i].setDescripcion(nuevaDescripcion);
                productos[i].setNumExistencias(nuevasExistencias);
                for (Proveedor p: tienda.getProveedores()) {
                        if(p!=null&&p.getNombre().equals(nameProv)){
                            productos[i].setProveedor(p);
                        }
                }

                // Serialize the updated Tienda object after modifying the Producto
                try {
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("tienda.ser"));
                    out.writeObject(tienda);
                    out.close();
                } catch (IOException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
                return true;
            }
        }
        return false;
    }

    public static boolean eliminarProducto(Tienda tienda, String codigo){

        Producto[] productos = tienda.getProductos();
        for (int i = 0; i < productos.length; i++) {
            if (productos[i]==null){
                continue;
            }
            if (productos[i].getCodigo().equals(codigo)) {
                productos[i]=null;

                TiendaC.bubbleSort(tienda);
                tienda.nInventory=tienda.nInventory-1;
                // Serialize the updated Tienda object after modifying the Producto
                try {
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("tienda.ser"));
                    out.writeObject(tienda);
                    out.close();
                } catch (IOException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
                return true;
            }
        }
        return false;
    }
}
