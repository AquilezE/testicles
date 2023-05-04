package Controlador;


import Model.Proveedor;
import Model.Tienda;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ProvC {
    private Tienda tienda;

    public ProvC(Tienda tienda) {
        this.tienda=tienda;
    }

    public boolean addProovedor(Proveedor proveedor) {
        if (tienda.nProovedores >= 10) {
            return false;
        }

        tienda.getProveedores()[tienda.nProovedores] = proveedor;
        tienda.nProovedores++;

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

    public static boolean modificarProveedor(Tienda tienda, String nombre, String newTelefono, String newEmail) {
        Proveedor[] proveedor = tienda.getProveedores();
        for (int i = 0; i < proveedor.length; i++) {
            if (proveedor[i] != null && proveedor[i].getNombre().equals(nombre)) {
                proveedor[i].setEmail(newEmail);
                proveedor[i].setTelefono(newTelefono);

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

    public static boolean eliminarProv(Tienda tienda, String nombre){

        Proveedor[] proveedors = tienda.getProveedores();
        for (int i = 0; i < proveedors.length; i++) {
            if (proveedors[i] != null && proveedors[i].getNombre().equals(nombre)) {
                proveedors[i].setNombre(null);
                proveedors[i].setTelefono(null);
                proveedors[i].setEmail(null);

                tienda.nProovedores=(tienda.nProovedores-1);

                ProvC.quickSortProveedores(tienda,0,tienda.nProovedores-1);
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
    public Proveedor buscarProovedorNombre(Tienda tienda,String nombre) {
        for (Proveedor p : tienda.getProveedores()) {
            if (p.getNombre()!=null && p.getNombre().equals(nombre)) {
                return p;
            }
        }
        return null;
    }
    public Proveedor buscarProovedorEmail(Tienda tienda,String email) {
        for (Proveedor p : tienda.getProveedores()) {
            if (p.getEmail()!=null && p.getEmail().equals(email)) {
                return p;
            }
        }
        return null;
    }
    public Proveedor buscarProovedorTel(Tienda tienda,String tel) {
        for (Proveedor p : tienda.getProveedores()) {
            if (p.getTelefono()!=null && p.getTelefono().equals(tel)) {
                return p;
            }
        }
        return null;
    }

    public static void quickSortProveedores(Tienda tienda, int low, int high) {
        if (low < high) {
            int pi = partitionProveedores(tienda, low, high);
            quickSortProveedores(tienda, low, pi - 1);
            quickSortProveedores(tienda, pi + 1, high);
        }
    }

    private static int partitionProveedores(Tienda tienda, int low, int high) {
        Proveedor pivot = tienda.getProveedores()[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            // Compare emails, if they exist
            if (tienda.getProveedores()[j].getEmail() != null && pivot.getEmail() != null) {
                if (tienda.getProveedores()[j].getEmail().compareTo(pivot.getEmail()) < 0) {
                    i++;
                    Proveedor temp = tienda.getProveedores()[i];
                    tienda.getProveedores()[i] = tienda.getProveedores()[j];
                    tienda.getProveedores()[j] = temp;
                }
            }
            // If both are null, don't swap
            else if (tienda.getProveedores()[j].getEmail() == null && pivot.getEmail() == null) {
                continue;
            }
            // If j's email is null, swap with the end
            else if (tienda.getProveedores()[j].getEmail() == null) {
                continue;
            }
            // If pivot's email is null, swap with j
            else {
                i++;
                Proveedor temp = tienda.getProveedores()[i];
                tienda.getProveedores()[i] = tienda.getProveedores()[j];
                tienda.getProveedores()[j] = temp;
            }
        }

        Proveedor temp = tienda.getProveedores()[i + 1];
        tienda.getProveedores()[i + 1] = tienda.getProveedores()[high];
        tienda.getProveedores()[high] = temp;

        return i + 1;
    }
}
