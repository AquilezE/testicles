package Controlador;

import Model.*;

import java.io.*;

public class TiendaC {
    private Tienda tienda;


    // constructor to initialize tienda object
    public TiendaC(Tienda tienda) {
        this.tienda = tienda;
    }

    // method to add a new product to the store's inventory
    public boolean addProduct(String codigo, float precioVenta, float descuento, String descripcion, int numExistencias, String numSerie, String nombre) {
        // check if there is space in the inventory
        if (tienda.getnInventory() >= 100) {

            return false;
        }
        Proveedor temp = null;
        for (Proveedor p : tienda.getProveedores()) {
            if (p.getNombre() != null && p.getNombre().equals(nombre)) {
                temp = p;
                break;
            }
        }
        Producto producto = new ProductoElectronico(codigo, precioVenta, descuento, descripcion, numExistencias, numSerie, temp);

        tienda.getProductos()[tienda.getnInventory()] = producto;
        tienda.setnInventory(tienda.getnInventory() + 1);


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

    public Producto buscarProductoCode(Tienda tienda, String codigo) {
        for (Producto p : tienda.getProductos()) {
            if (p.getDescripcion() != null && p.getCodigo().equals(codigo)) {
                return p;
            }
        }
        return null;
    }

    public Producto buscarProductoDesc(Tienda tienda, String descripcion) {
        for (Producto p : tienda.getProductos()) {
            if (p.getDescripcion() != null && p.getDescripcion().equals(descripcion)) {
                return p;
            }
        }
        return null;
    }

    public Producto buscarProductoNSerie(Tienda tienda, String numSerie) {
        for (int i = 0; i < tienda.getnInventory(); i++) {
            if (tienda.getProductos()[i].getCodigo() != null && tienda.getProductos()[i] instanceof ProductoElectronico && ((ProductoElectronico) tienda.getProductos()[i]).getNumSerie().equals(numSerie)) {
                return tienda.getProductos()[i];
            }
        }
        return null;
    }

    public static void bubbleSort(Tienda tienda) {
        boolean swapped = true;
        int n = tienda.getProductos().length;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                if (tienda.getProductos()[i] == null && tienda.getProductos()[i + 1] != null) {
                    // Swap null element to the end of the array
                    Producto temp = tienda.getProductos()[i];
                    tienda.getProductos()[i] = tienda.getProductos()[i + 1];
                    tienda.getProductos()[i + 1] = temp;
                    swapped = true;
                } else if (tienda.getProductos()[i] != null && tienda.getProductos()[i + 1] != null &&
                        tienda.getProductos()[i].getPrecioVenta() > tienda.getProductos()[i + 1].getPrecioVenta()) {
                    // Swap elements that are out of order
                    Producto temp = tienda.getProductos()[i];
                    tienda.getProductos()[i] = tienda.getProductos()[i + 1];
                    tienda.getProductos()[i + 1] = temp;
                    swapped = true;
                }
            }
            n--;
        }
    }
}