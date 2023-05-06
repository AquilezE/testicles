package Controlador;

import Model.Cliente;
import Model.Tienda;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ClienteC {

    private Tienda tienda;


    public ClienteC(Tienda tienda){
        this.tienda=tienda;
    }
    public boolean addCliente(String nombre, String rfc, String direccion, String email) {
        // check if there is space in the inventory
        if (tienda.nClientes >= 20) {

            return false;
        }

        Cliente cliente=new Cliente(nombre,rfc,direccion,email);

        tienda.getClientes()[tienda.nClientes] =cliente;
        tienda.setnClientes(tienda.nClientes + 1);

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

    public  boolean modificarCliente(String nombre, String rfc, String direccion, String email) {
        Cliente[] clientes = tienda.getClientes();
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] != null && clientes[i].getRfc().equals(rfc)) {
                clientes[i].setEmail(email);
                clientes[i].setNombre(nombre);
                clientes[i].setDireccion(direccion);


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

    public static boolean eliminarCliente(Tienda tienda, String rfc){

        Cliente[] clientes = tienda.getClientes();
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] != null && clientes[i].getRfc().equals(rfc)) {
                clientes[i].setNombre(null);
                clientes[i].setRfc(null);
                clientes[i].setEmail(null);
                clientes[i].setDireccion(null);

                tienda.nClientes=(tienda.nClientes-1);

                ClienteC.quickSort(tienda,0,tienda.nClientes-1);
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

    public Cliente buscarClienteNombre(Tienda tienda, String nombre) {
        for (Cliente p : tienda.getClientes()) {
            if (p.getNombre()!=null && p.getNombre().equals(nombre)) {
                return p;
            }
        }
        return null;
    }
    public Cliente buscarClienteRFC(Tienda tienda,String rfc) {
        for (Cliente c : tienda.getClientes()) {
            if (c.getRfc()!=null && c.getRfc().equals(rfc)) {
                return c;
            }
        }
        return null;
    }
    public Cliente buscarClienteEmail(Tienda tienda,String email) {
        for (Cliente c : tienda.getClientes()) {
            if (c.getEmail()!=null && c.getEmail().equals(email)) {
                return c;
            }
        }
        return null;
    }
    public Cliente buscarClienteDireccion(Tienda tienda, String direccion) {
        for (Cliente c : tienda.getClientes()) {
            if (c.getDireccion()!=null && c.getDireccion().equals(direccion)) {
                return c;
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
        Cliente pivot = tienda.getClientes()[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (tienda.getClientes()[j].getNombre() != null) {
                if (tienda.getClientes()[j].getNombre().compareTo(pivot.getNombre()) < 0) {
                    i++;
                    Cliente temp = tienda.getClientes()[i];
                    tienda.getClientes()[i] = tienda.getClientes()[j];
                    tienda.getClientes()[j] = temp;
                }
            } else {
                // move null values to the end of the array
                Cliente temp = tienda.getClientes()[j];
                tienda.getClientes()[j] = tienda.getClientes()[high];
                tienda.getClientes()[high] = temp;
            }
        }
        Cliente temp = tienda.getClientes()[i + 1];
        tienda.getClientes()[i + 1] = tienda.getClientes()[high];
        tienda.getClientes()[high] = temp;
        return i + 1;
    }
}
