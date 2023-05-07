package Controlador;

import Model.Cliente;
import Model.Proveedor;
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

    public static boolean modificarCliente(Tienda tienda, String nombre, String rfc, String direccion, String email) {
        Cliente[] clientes = tienda.getClientes();
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] != null && clientes[i].getRfc().equals(rfc)) {
                clientes[i].setEmail(email);
                clientes[i].setNombre(nombre);
                clientes[i].setDireccion(direccion);


                // Serializa la tienda despues de modificar Cliente
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
                clientes[i]=null;

                tienda.nClientes=(tienda.nClientes-1);

                ClienteC.bubbleSort(tienda);
                // Serializa tienda despues de eliminar el cliente
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

    public static Cliente buscarClienteNombre(Tienda tienda, String nombre) {
        for (Cliente p : tienda.getClientes()) {
            if (p.getNombre()!=null && p.getNombre().equals(nombre)) {
                return p;
            }
        }
        return null;
    }
    public static Cliente buscarClienteRFC(Tienda tienda,String rfc) {
        for (Cliente c : tienda.getClientes()) {
            if (c.getRfc()!=null && c.getRfc().equals(rfc)) {
                return c;
            }
        }
        return null;
    }
    public static Cliente buscarClienteEmail(Tienda tienda,String email) {
        for (Cliente c : tienda.getClientes()) {
            if (c.getEmail()!=null && c.getEmail().equals(email)) {
                return c;
            }
        }
        return null;
    }
    public static Cliente buscarClienteDireccion(Tienda tienda, String direccion) {
        for (Cliente c : tienda.getClientes()) {
            if (c.getDireccion()!=null && c.getDireccion().equals(direccion)) {
                return c;
            }
        }
        return null;
    }

    public  static void bubbleSort(Tienda tienda) {
        boolean swapped = true;
        int n = tienda.getClientes().length;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < n - 1; i++) {
                if (tienda.getClientes()[i] == null && tienda.getClientes()[i + 1] != null) {
                    // Swap null element to the end of the array
                    Cliente temp = tienda.getClientes()[i];
                    tienda.getClientes()[i] = tienda.getClientes()[i + 1];
                    tienda.getClientes()[i + 1] = temp;
                    swapped = true;
                } else if (tienda.getClientes()[i] != null && tienda.getClientes()[i + 1] != null &&
                        tienda.getClientes()[i].getNombre().compareTo(tienda.getClientes()[i + 1].getNombre()) > 0) {
                    // Swap elements that are out of order
                    Cliente temp = tienda.getClientes()[i];
                    tienda.getClientes()[i] = tienda.getClientes()[i + 1];
                    tienda.getClientes()[i + 1] = temp;
                    swapped = true;
                }
            }
            n--;
        }
    }

}
