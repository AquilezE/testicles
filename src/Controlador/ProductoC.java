package Controlador;

import Model.Producto;

import Model.Tienda;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import Vista.View;

public class ProductoC {
    public static boolean modificarProducto(Tienda tienda, String codigo) {
        Producto[] productos = tienda.getProductos();
        Producto producto = null;
        View view=new View();

        for (int i = 0; i < productos.length; i++) {
            if (productos[i] != null && productos[i].getCodigo().equals(codigo)) {
                producto = productos[i];
                break;
            }
        }

        if (producto == null) {
            view.displayMessage("\tProducto no encontrado: " + codigo);
            return false;
        }

        int opcion;

        do {
            view.displayMessage("\t¿Qué deseas modificar?\n\t" +
                    "1. Precio de venta\n\t" +
                    "2. Descuento\n\t" +
                    "3. Descripción\n\t" +
                    "4. Número de existencias\n\t" +
                    "0. Cancelar");

            opcion = view.leerEntero("\tOpción: ");

            switch (opcion) {
                case 1:
                    float nuevoPrecioVenta = view.leerFloat("\tEscribe el nuevo Precio de venta: ");
                    producto.setPrecioVenta(nuevoPrecioVenta);
                    view.displayMessage("\tPrecio de venta modificado: " + codigo);
                    break;
                case 2:
                    float nuevoDescuento = view.leerFloat("\tEscribe el nuevo Descuento: ");
                    producto.setDescuento(nuevoDescuento);
                    view.displayMessage("\tDescuento modificado: " + codigo);
                    break;
                case 3:
                    String nuevaDescripcion = view.getInput("\tEscribe la nueva Descripción: ");
                    producto.setDescripcion(nuevaDescripcion);
                    view.displayMessage("\tDescripción modificada: " + codigo);
                    break;
                case 4:
                    int nuevasExistencias = view.leerEntero("\tEscribe el nuevo número de existencias: ");
                    producto.setNumExistencias(nuevasExistencias);
                    view.displayMessage("\tNúmero de existencias modificado: " + codigo);
                    break;
                case 0:
                    view.displayMessage("Modificación cancelada: " + codigo);
                    break;
                default:
                    view.displayMessage("Opción inválida. Por favor, selecciona una opción válida.");
                    break;
            }
        } while (opcion != 0);

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("tienda.ser"));
            out.writeObject(tienda);
            out.close();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return true;

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
