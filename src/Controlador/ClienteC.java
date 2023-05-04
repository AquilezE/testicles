package Controlador;

import Model.Cliente;
import Model.Producto;
import Model.Tienda;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ClienteC {

    private Tienda tienda;


    public boolean addCliente(Cliente cliente) {
        // check if there is space in the inventory
        if (tienda.nClientes >= 20) {

            return false;
        }

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

}
