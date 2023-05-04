import Controlador.ProductoC;
import Controlador.ProvC;
import Controlador.TiendaC;
import Model.*;
import Vista.View;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Main {
    public static void main(String[] args) {
        //Inicializa all


        //Deserializes tienda if it exists
        Tienda tienda;
        try {
            FileInputStream fis = new FileInputStream("tienda.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            tienda = (Tienda) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            //If not found, creates it
            tienda = new Tienda("Abarrotes Mari", "ABC123");
        }

        TiendaC controller = new TiendaC(tienda);
        ProvC controllerP = new ProvC(tienda);
        View view = new View();

        int opcion = 420;
        while (opcion != 0) {
            try {
                FileInputStream fis = new FileInputStream("tienda.ser");
                ObjectInputStream ois = new ObjectInputStream(fis);
                tienda = (Tienda) ois.readObject();
                ois.close();
                fis.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~MENU~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("\t1.- Manipular productos");
            System.out.println("\t2.- Manipular proveedores");
            System.out.println("\t3.- Manipular clientes");
            System.out.println("\t4.- Generar Pedido");
            System.out.println("\t5.- Generar factura");
            System.out.println("\t6.- CONSULTA LIBRE");
            System.out.println("\t7.- CONSULTA LIBRE");
            System.out.println("\t0.- Salir");

            opcion = Integer.parseInt(view.getInput(">"));

            switch (opcion) {
                case (1):

                    view.displayMessage("\t1.- Agregar producto");
                    view.displayMessage("\t2.- Modificar producto");
                    view.displayMessage("\t3.- Eliminar producto");
                    view.displayMessage("\t4.- Consultar todos los productos");
                    view.displayMessage("\t5.- Consultar algún producto en específico");
                    view.displayMessage("\t6.- Regresar");
                    opcion = Integer.parseInt(view.getInput(">"));

                    switch (opcion) {
                        case 1:
                            String codigo = view.getInput("Enter product code:");
                            float precioVenta = Float.parseFloat(view.getInput("Enter product selling price:"));
                            float descuento = Float.parseFloat(view.getInput("Enter product discount:"));
                            String descripcion = view.getInput("Enter product description:");
                            int numExistencias = Integer.parseInt(view.getInput("Enter number of product units:"));
                            String numSerie = view.getInput("Enter product serial number:");


                            Producto producto = new ProductoElectronico(codigo, precioVenta, descuento, descripcion, numExistencias, numSerie);
                            boolean addedSuccessfully = controller.addProduct(producto);

                            if (addedSuccessfully) {
                                view.displayMessage("Product added successfully");
                            } else {
                                view.displayMessage("Failed to add product");
                            }
                            break;
                        case 2:
                            codigo = view.getInput("Enter new product code:");
                            precioVenta = Float.parseFloat(view.getInput("Enter new product selling price:"));
                            descuento = Float.parseFloat(view.getInput("Enter new product discount:"));
                            descripcion = view.getInput("Enter new product description:");
                            numExistencias = Integer.parseInt(view.getInput("Enter new number of product units:"));
                            if (ProductoC.modificarProducto(tienda, codigo, precioVenta, descuento, descripcion, numExistencias)) {
                                view.displayMessage("Producto modificado: " + codigo);
                            } else {
                                view.displayMessage("Producto no modificado: " + codigo);
                            }
                            break;
                        case 3:
                            codigo = view.getInput("Enter product code:");
                            if (ProductoC.eliminarProducto(tienda, codigo)) {
                                view.displayMessage("Producto eliminado: " + codigo);
                            } else {
                                view.displayMessage("Producto no eliminado: " + codigo);
                            }
                            break;
                        case 4:
                            TiendaC.quickSort(tienda, 0, tienda.getnInventory() - 1);
                            tienda.setnInventory(tienda.getnInventory() - 1);
                            for (int i = 0; i < tienda.nInventory; i++) {
                                view.displayMessage(tienda.getProductos()[i].toString());
                            }
                            break;
                        case 5:
                            view.displayMessage("Busqueda por:\n\t1)Numero de Serie\n\t2)Descripcion\n\t3)Codigo");
                            int optdisplay;
                            optdisplay = Integer.parseInt(view.getInput(">"));
                            switch (optdisplay) {
                                case 1:
                                    String nSerie;
                                    nSerie = view.getInput("Escribe el numero de serie: ");
                                    view.displayMessage(controller.buscarProductoCode(tienda, nSerie).toString());
                                    break;
                                case 2:
                                    String Desc;
                                    Desc = view.getInput("Escribe la descripcion: ");
                                    view.displayMessage(controller.buscarProductoDesc(tienda, Desc).toString());
                                    break;
                                case 3:
                                    String code;
                                    code = view.getInput("Escribe el codigo: ");
                                    view.displayMessage(controller.buscarProductoCode(tienda, code).toString());
                                    break;

                            }
                        case 6:
                            break;
                    }
                    break;
                case 2:
                    view.displayMessage("\t1.- Agregar proveedor");
                    view.displayMessage("\t2.- Modificar proveedor");
                    view.displayMessage("\t3.- Eliminar proveedor");
                    view.displayMessage("\t4.- Consultar todos los proveedores");
                    view.displayMessage("\t5.- Consultar algún proveedor en específico");
                    view.displayMessage("\t6.- Regresar");

                    opcion = Integer.parseInt(view.getInput(">"));
                    switch (opcion) {
                        case 1:
                            String nombre = view.getInput("Inserta del nombre del proovedor");
                            String telefono = view.getInput("Inserta del telefono del proovedor");
                            String email = view.getInput("Inserta del email del proovedor");

                            Proveedor tempProv = new Proveedor(nombre, telefono, email);

                            if (controllerP.addProovedor(tempProv)) {
                                view.displayMessage("Proveedor añadido: " + nombre);
                            } else {
                                view.displayMessage("Proveedor no añadido: " + nombre);
                            }
                            break;
                        case 2:
                            nombre = view.getInput("Inserta el nombre del proovedor a modificar");
                            telefono = view.getInput("Inserta el nuevo telefono del proovedor");
                            email = view.getInput("Inserta el nuevo email del proovedor");

                            if (ProvC.modificarProveedor(tienda, nombre, telefono, email)) {
                                view.displayMessage("Proveedor modificado: " + nombre);
                            } else {
                                view.displayMessage("Proveedor no modificado: " + nombre);
                            }
                            break;
                        case 3:
                            nombre = view.getInput("Inserta el nombre del proovedor a eliminar");
                            if (ProvC.eliminarProv(tienda, nombre)) {
                                view.displayMessage("Proveedor eliminado: " + nombre);
                            } else {
                                view.displayMessage("Proveedor no eliminado: " + nombre);
                            }
                            break;
                        case 4:
                            ProvC.quickSortProveedores(tienda, 0, tienda.nProovedores - 1);
                            for (int i = 0; i < tienda.nProovedores; i++) {
                                view.displayMessage(tienda.getProveedores()[i].toString());
                            }
                            break;
                        case 5:
                            view.displayMessage("Busqueda por:\n\t1)Nombre\n\t2)Telefono\n\t3)Email");
                            int optdisplay;
                            optdisplay = Integer.parseInt(view.getInput(">"));
                            switch (optdisplay) {
                                case 1:
                                    String nom;
                                    nom = view.getInput("Escribe el nombre del proveedor: ");
                                    view.displayMessage(controllerP.buscarProovedorNombre(tienda, nom).toString());
                                    break;
                                case 2:
                                    String tel;
                                    tel = view.getInput("Escribe el telefono del proveedor: ");
                                    view.displayMessage(controllerP.buscarProovedorTel(tienda, tel).toString());
                                    break;
                                case 3:
                                    String emeil;
                                    emeil = view.getInput("Escribe el codigo: ");
                                    view.displayMessage(controllerP.buscarProovedorEmail(tienda, emeil).toString());
                                    break;
                            }
                        case 6:
                            break;
                    }
                case 3:
                    view.displayMessage("\t1.- Agregar cliente");
                    view.displayMessage("\t2.- Modificar cliente");
                    view.displayMessage("\t3.- Eliminar cliente");
                    view.displayMessage("\t4.- Consultar todos los clientes");
                    view.displayMessage("\t5.- Consultar algún cliente en específico");
                    view.displayMessage("\t6.- Regresar");

                    opcion = Integer.parseInt(view.getInput(">"));

                    switch (opcion){
                        case 1:

                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            break;

                    }
                    break;
                case 4:

                    break;
                case 5:
                    break;
            }
        }
    }
}