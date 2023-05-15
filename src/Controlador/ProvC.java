package Controlador;


import Model.Producto;
import Model.Proveedor;
import Model.Tienda;
import Vista.View;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ProvC {
    private Tienda tienda;

    public ProvC(Tienda tienda) {
        this.tienda = tienda;
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
        Proveedor[] proveedores = tienda.getProveedores();
        Proveedor proveedor = null;
        View view=new View();

        for (int i = 0; i < proveedores.length; i++) {
            if (proveedores[i] != null && proveedores[i].getNombre().equals(nombre)) {
                proveedor = proveedores[i];
                break;
            }
        }

        if (proveedor == null) {
            view.displayMessage("Proveedor no encontrado: " + nombre);
            return false;
        }

        int opcion;

        do {
            view.displayMessage("¿Qué deseas modificar?\n" +
                    "1. Teléfono\n" +
                    "2. Email\n" +
                    "0. Cancelar");

            opcion = view.leerEntero("Opción: ");

            switch (opcion) {
                case 1:
                    String nuevoTelefono = view.getInput("Escribe el nuevo teléfono: ");
                    proveedor.setTelefono(nuevoTelefono);
                    view.displayMessage("Teléfono modificado: " + nombre);
                    break;
                case 2:
                    String nuevoEmail = view.getInput("Escribe el nuevo email: ");
                    proveedor.setEmail(nuevoEmail);
                    view.displayMessage("Email modificado: " + nombre);
                    break;
                case 0:
                    view.displayMessage("Modificación cancelada: " + nombre);
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

    public static boolean eliminarProv(Tienda tienda, String nombre) {

        Proveedor[] proveedors = tienda.getProveedores();
        for (int i = 0; i < proveedors.length; i++) {
            if (proveedors[i] != null && proveedors[i].getNombre().equals(nombre)) {

                proveedors[i] = null;

                tienda.nProovedores = (tienda.nProovedores - 1);

                ProvC.bubbleSort(tienda);
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

    public static Proveedor buscarProovedorNombre(Tienda tienda, String nombre) {
        for (Proveedor p : tienda.getProveedores()) {
            if (p.getNombre() != null && p.getNombre().equals(nombre)) {
                return p;
            }
        }
        return null;
    }

    public static Proveedor buscarProovedorEmail(Tienda tienda, String email) {
        for (Proveedor p : tienda.getProveedores()) {
            if (p.getEmail() != null && p.getEmail().equals(email)) {
                return p;
            }
        }
        return null;
    }

    public static Proveedor buscarProovedorTel(Tienda tienda, String tel) {
        for (Proveedor p : tienda.getProveedores()) {
            if (p.getTelefono() != null && p.getTelefono().equals(tel)) {
                return p;
            }
        }
        return null;
    }
    public  static void bubbleSort(Tienda tienda) {
        boolean swapped = true;
        int n = tienda.getProveedores().length;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                if (tienda.getProveedores()[i] == null && tienda.getProveedores()[i + 1] != null) {
                    // Swap null element to the end of the array
                    Proveedor temp = tienda.getProveedores()[i];
                    tienda.getProveedores()[i] = tienda.getProveedores()[i + 1];
                    tienda.getProveedores()[i + 1] = temp;
                    swapped = true;
                } else if (tienda.getProveedores()[i] != null && tienda.getProveedores()[i + 1] != null &&
                        tienda.getProveedores()[i].getNombre().compareTo(tienda.getProveedores()[i + 1].getNombre()) > 0) {
                    // Swap elements that are out of order
                    Proveedor temp = tienda.getProveedores()[i];
                    tienda.getProveedores()[i] = tienda.getProveedores()[i + 1];
                    tienda.getProveedores()[i + 1] = temp;
                    swapped = true;
                }
            }
            n--;
        }
    }
}