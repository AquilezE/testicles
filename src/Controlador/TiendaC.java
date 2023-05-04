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
    public boolean addProduct(Producto producto) {
        // check if there is space in the inventory
        if (tienda.getnInventory() >= 100) {

            return false;
        }

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
            if (p.getDescripcion()!=null && p.getCodigo().equals(codigo)) {
                return p;
            }
        }
        return null;
    }

    public Producto buscarProductoDesc(Tienda tienda, String descripcion) {
        for (Producto p : tienda.getProductos()) {
            if (p.getDescripcion()!=null && p.getDescripcion().equals(descripcion)) {
                return p;
            }
        }
        return null;
    }

    public Producto buscarProductoNSerie(Tienda tienda, String numSerie) {
        for (int i=0; i<tienda.getnInventory();i++) {
            if (tienda.getProductos()[i].getCodigo() != null && tienda.getProductos()[i] instanceof ProductoElectronico && ((ProductoElectronico) tienda.getProductos()[i]).getNumSerie().equals(numSerie)) {
                return tienda.getProductos()[i];
            }
        }
        return null;
    }

    public static void quickSort(Tienda tienda, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(tienda, low, high);
            quickSort(tienda, low, pivotIndex - 1);
            quickSort(tienda, pivotIndex + 1, high);
        }
    }

    private static int partition(Tienda tienda, int low, int high) {
        Producto pivot = tienda.getProductos()[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (tienda.getProductos()[j].getPrecioVenta() != 0) {
                if (tienda.getProductos()[j].getPrecioVenta() < pivot.getPrecioVenta()) {
                    i++;
                    Producto temp = tienda.getProductos()[i];
                    tienda.getProductos()[i] = tienda.getProductos()[j];
                    tienda.getProductos()[j] = temp;
                }
            }
        }
        Producto temp = tienda.getProductos()[i + 1];
        tienda.getProductos()[i + 1] = tienda.getProductos()[high];
        tienda.getProductos()[high] = temp;
        return i + 1;
    }

}