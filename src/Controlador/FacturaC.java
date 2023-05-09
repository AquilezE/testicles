package Controlador;

import Model.*;
import Vista.View;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class FacturaC {

    private Tienda tienda;

    public FacturaC(Tienda tienda){
        this.tienda=tienda;
    }

    //Agrega factura a un cliente especifico y a la tienda
    //No quita las cosas que compro en si, no se si hacerlo aqui
    public boolean generarFactura(String nombreCliente, String codigoProd){
        Cliente tempCliete = null;
        ProductoElectronico tempProd = null;

        for (int i = 0; i <tienda.getClientes().length ; i++) {
            if (tienda.getClientes()[i].getNombre().equals(nombreCliente)){
                tempCliete=tienda.getClientes()[i];
                break;
            }
        }
        for (int i=0; i<tienda.getProductos().length;i++){
            if(tienda.getProductos()[i].getCodigo().equals(codigoProd)){
                tempProd=(ProductoElectronico)tienda.getProductos()[i];
                break;
            }
        }

        if (tempCliete==null||tempProd==null){
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
    public static void mostrarFacturasCliente(View view, Tienda tienda){
        for (Cliente c: tienda.getClientes()){
            for (Factura f: c.getFacturas()){
                view.displayMessage(f.toString());
            }
        }
    }
    public static void mostrarFacturasTienda(View view, Tienda tienda){
        view.displayMessage("facturas de "+tienda.getNombre());
        for (Factura f:tienda.getFacturas()){
            if (f!=null){
                view.displayMessage("---"+f);
            }
        }
    }
    //MOSTRAR TOTAL
}
