package Vista;

import Controlador.ClienteC;
import Model.Factura;
import Model.Tienda;

public class ClienteV {

    public static void addCliente(View view, ClienteC controllerC){
        String cnombre= view.getInput("Escribe el nombre del cliente");
        String crfc= view.getInput("Escribe el RFC del cliente");
        String cdireccion= view.getInput("Escribe la direccion del cliente");
        String cemail= view.getInput("Escribe el email del cliente");
        if (controllerC.addCliente(cnombre,crfc,cdireccion,cemail)){
            view.displayMessage("Cliente "+crfc+" creado");
        }else{
            view.displayMessage("Cliente "+crfc+" no creado");
        }
    }

    public static void modCliente(View view, Tienda tienda){

        String crfc= view.getInput("Escribe el RFC del cliente que desea modificar");
        String cnombre= view.getInput("Escribe el nombre nuevo del cliente");
        String cdireccion= view.getInput("Escribe la direccion nueva del cliente");
        String cemail= view.getInput("Escribe el email nuevodel cliente");
        if (ClienteC.modificarCliente(tienda,cnombre,crfc,cdireccion,cemail)){
            view.displayMessage("Cliente "+crfc+" modificado");
        }else{
            view.displayMessage("Cliente "+crfc+"no modificado");
        }
    }

    public static void eliminarCliente(View view,Tienda tienda){
        String crfc= view.getInput("Ingrese el RFC del cliente que desea eliminar");
        if(ClienteC.eliminarCliente(tienda,crfc)){
            view.displayMessage("Cliente "+crfc+" eliminado");
        }else {
            view.displayMessage("Cliente "+crfc+"no eliminado");
        }
    }

    public static void mostrarClientes(View view, Tienda tienda){
        ClienteC.bubbleSort(tienda);
        for (int i = 0; i < tienda.nClientes; i++) {
            view.displayMessage(tienda.getClientes()[i].toString());
        }
    }

    public static void buscarCliente(View view, Tienda tienda){
        view.displayMessage("Busqueda por:\n\t1)Nombre\n\t2)RFC\n\t3)Email\n\t4)Direccion");
        int optdisplay;
        optdisplay = view.leerEntero(">");
        switch (optdisplay) {
            case 1:
                String nom;
                nom = view.getInput("Escribe el nombre del cliente: ");
                view.displayMessage(ClienteC.buscarClienteNombre(tienda, nom).toString());
                break;
            case 2:
                String rfc;
                rfc = view.getInput("Escribe el RFC del cliente: ");
                view.displayMessage(ClienteC.buscarClienteRFC(tienda, rfc).toString());
                break;
            case 3:
                String emeil;
                emeil = view.getInput("Escribe el E-Mail del cliente: ");
                view.displayMessage(ClienteC.buscarClienteEmail(tienda, emeil).toString());
                break;
            case 4:
                String dir;
                dir = view.getInput("Escribe el E-Mail del cliente: ");
                view.displayMessage(ClienteC.buscarClienteDireccion(tienda, dir).toString());
                break;
        }
    }
    public static void clientesyProductos(View view, Tienda tienda){
        ClienteC.bubbleSort(tienda);
        for (int i = 0; i < tienda.nClientes; i++) {
            view.displayMessage(tienda.getClientes()[i].toString());
            for (Factura f: tienda.getClientes()[i].getFacturas()) {
                if (f!=null) {
                    view.displayMessage(f.getProdVendido().getCodigo());
                }
            }

        }

    }
}
