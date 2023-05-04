package Controlador;

import Model.Producto;
import Model.ProductoElectronico;
import Model.Tienda;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ProductoC {
    public static boolean modificarProducto(Tienda tienda, String codigo, float nuevoPrecioVenta, float nuevoDescuento, String nuevaDescripcion, int nuevasExistencias) {
        Producto[] productos = tienda.getProductos();
        for (int i = 0; i < productos.length; i++) {
            if (productos[i] != null && productos[i].getCodigo().equals(codigo)) {
                productos[i].setPrecioVenta(nuevoPrecioVenta);
                productos[i].setDescuento(nuevoDescuento);
                productos[i].setDescripcion(nuevaDescripcion);
                productos[i].setNumExistencias(nuevasExistencias);
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
            if (productos[i] != null && productos[i].getCodigo().equals(codigo)) {
                productos[i].setPrecioVenta(0);
                productos[i].setDescuento(0);
                productos[i].setDescripcion(null);
                productos[i].setNumExistencias(0);
                productos[i].setCodigo(null);
                ProductoElectronico temp=(ProductoElectronico) productos[i];
                temp.setNumSerie(null);

                tienda.setnInventory(tienda.getnInventory()-1);

                TiendaC.quickSort(tienda,0,tienda.getnInventory()-1);
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
