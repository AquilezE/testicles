package Vista;

import Controlador.ProvC;
import Model.Producto;
import Model.Proveedor;
import Model.Tienda;

public class ProveedorV {


    public static void addProveedor(View view, ProvC controllerP) {
        String nombre = view.getInput("Inserta del nombre del proovedor");
        String telefono = view.getInput("Inserta del telefono del proovedor");
        String email = view.getInput("Inserta del email del proovedor");

        Proveedor tempProv = new Proveedor(nombre, telefono, email);

        if (controllerP.addProovedor(tempProv)) {
            view.displayMessage("Proveedor añadido: " + nombre);
        } else {
            view.displayMessage("Proveedor no añadido: " + nombre);
        }
    }

    public static void modProveedor(View view, Tienda tienda) {
        String nombre = view.getInput("Inserta el nombre del proovedor a modificar");
        String telefono = view.getInput("Inserta el nuevo telefono del proovedor");
        String email = view.getInput("Inserta el nuevo email del proovedor");

        if (ProvC.modificarProveedor(tienda, nombre, telefono, email)) {
            view.displayMessage("Proveedor modificado: " + nombre);
        } else {
            view.displayMessage("Proveedor no modificado: " + nombre);
        }
    }

    public static void eliminarProv(View view, Tienda tienda) {
        String nombre = view.getInput("Inserta el nombre del proovedor a eliminar");
        if (ProvC.eliminarProv(tienda, nombre)) {
            view.displayMessage("Proveedor eliminado: " + nombre);
        } else {
            view.displayMessage("Proveedor no eliminado: " + nombre);
        }
    }

    public static void mostrarProveedores(View view, Tienda tienda) {
        ProvC.bubbleSort(tienda);
        for (int i = 0; i < tienda.nProovedores; i++) {
            view.displayMessage(tienda.getProveedores()[i].toString());
        }
    }

    public static void buscarProovedor(View view, Tienda tienda) {
        view.displayMessage("Busqueda por:\n\t1)Nombre\n\t2)Telefono\n\t3)Email");
        int optdisplay;
        optdisplay = Integer.parseInt(view.getInput(">"));
        switch (optdisplay) {
            case 1:
                String nom;
                nom = view.getInput("Escribe el nombre del proveedor: ");
                view.displayMessage(ProvC.buscarProovedorNombre(tienda, nom).toString());
                break;
            case 2:
                String tel;
                tel = view.getInput("Escribe el telefono del proveedor: ");
                view.displayMessage(ProvC.buscarProovedorTel(tienda, tel).toString());
                break;
            case 3:
                String emeil;
                emeil = view.getInput("Escribe el codigo: ");
                view.displayMessage(ProvC.buscarProovedorEmail(tienda, emeil).toString());
                break;
        }
    }

    public static void proveedoresyProductos(View view, Tienda tienda) {
        ProvC.bubbleSort(tienda);
        for (int i = 0; i < tienda.nProovedores; i++) {
            view.displayMessage(tienda.getProveedores()[i].toString());
            for (Producto p : tienda.getProductos()) {
                if (p != null && p.getProveedor().getNombre().equals(tienda.getProveedores()[i].getNombre())) {
                    view.displayMessage(p.toString());
                }
            }

        }
    }
}