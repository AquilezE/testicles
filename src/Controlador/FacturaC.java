package Controlador;

import Model.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class FacturaC {

    private Tienda tienda;

    public FacturaC(Tienda tienda){
        this.tienda=tienda;
    }

    public boolean generarFactura(String nombreCliente, String codigoProd){
        Cliente tempCliete = null;
        ProductoElectronico tempProd = null;

        for (int i = 0; i <tienda.getClientes().length ; i++) {
            if (tienda.getClientes()[i].getNombre().equals(nombreCliente)){
                tempCliete=tienda.getClientes()[i];
                break;
            }
            return false;
        }
        for (int i=0; i<tienda.getProductos().length;i++){
            if(tienda.getProductos()[i].getCodigo().equals(codigoProd)){
                tempProd=(ProductoElectronico)tienda.getProductos()[i];
                break;
            }
            return false;
        }

        Factura temp= new Factura(tempCliete,tempProd);

        tempCliete.getFacturas()[tempCliete.nFacturas]=temp;
        tempCliete.nFacturas++;
        tienda.getFacturas()[tienda.nFacturas]=temp;
        tienda.nFacturas++;



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
