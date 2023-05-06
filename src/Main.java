import Controlador.*;
import Model.*;
import Vista.View;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        //Inicializa all


        //Deserializes tienda if it exists
        Tienda tienda;
        File file = new File("tienda.ser");

        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            tienda = (Tienda) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            // If the file does not exist, create a new Tienda object
            tienda = new Tienda("Abarrotes Mari", "ABC123");

            // Only serialize the new Tienda object if the file does not exist
            if (!file.exists()) {
                try {
                    FileOutputStream fileOut = new FileOutputStream(file);
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(tienda);
                    out.close();
                    fileOut.close();
                    System.out.println("Serialized data is saved in tienda.ser");
                } catch (IOException ex) {
                    //e.printStackTrace();
                }
            }
        }
        FacturaC controllerF=new FacturaC(tienda);
        TiendaC controller = new TiendaC(tienda);
        ProvC controllerP = new ProvC(tienda);
        ClienteC controllerC= new ClienteC(tienda);
        PedidoC controllerPed= new PedidoC(tienda);
        View view = new View();

        int opcionsota = 420;
        while (opcionsota != 0) {
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

            opcionsota = Integer.parseInt(view.getInput(">"));

            switch (opcionsota) {
                case (1):
        //PRODUCTO_________________PRODUCTO_____________________PRODUCTO
                    view.displayMessage("\t1.- Agregar producto");
                    view.displayMessage("\t2.- Modificar producto");
                    view.displayMessage("\t3.- Eliminar producto");
                    view.displayMessage("\t4.- Consultar todos los productos");
                    view.displayMessage("\t5.- Consultar algún producto en específico");
                    view.displayMessage("\t6.- Regresar");
                    int opcion = Integer.parseInt(view.getInput(">"));

                    switch (opcion) {
                        case 1:
                            String codigo = view.getInput("Enter product code:");
                            float precioVenta = Float.parseFloat(view.getInput("Enter product selling price:"));
                            float descuento = Float.parseFloat(view.getInput("Enter product discount:"));
                            String descripcion = view.getInput("Enter product description:");
                            int numExistencias = Integer.parseInt(view.getInput("Enter number of product units:"));
                            String numSerie = view.getInput("Enter product serial number:");
                            String nombre = view.getInput("Enter name of the provider of product");

                            boolean addedSuccessfully = controller.addProduct(codigo, precioVenta, descuento, descripcion, numExistencias, numSerie,nombre);

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
                            String provedorName=view.getInput("Enter the name of the provider");
                            if (ProductoC.modificarProducto(tienda, codigo, precioVenta, descuento, descripcion, numExistencias,provedorName)) {
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
        //PROVEEDOR______________________________PROVEEDOR__________________________PROVEEDOR
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
                    break;
       //CLIENTE______________________________________CLIENTE________________CLIENTE
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
                            String cnombre= view.getInput("Escribe el nombre del cliente");
                            String crfc= view.getInput("Escribe el RFC del cliente");
                            String cdireccion= view.getInput("Escribe la direccion del cliente");
                            String cemail= view.getInput("Escribe el email del cliente");
                            if (controllerC.addCliente(cnombre,crfc,cdireccion,cemail)){
                                view.displayMessage("Cliente "+crfc+" creado");
                            }else{
                                view.displayMessage("Cliente "+crfc+" no creado");
                            }
                            break;
                        case 2:
                            crfc= view.getInput("Escribe el RFC del cliente que desea modificar");
                            cnombre= view.getInput("Escribe el nombre nuevo del cliente");
                            cdireccion= view.getInput("Escribe la direccion nueva del cliente");
                            cemail= view.getInput("Escribe el email nuevodel cliente");
                            if (controllerC.modificarCliente(cnombre,crfc,cdireccion,cemail)){
                                view.displayMessage("Cliente "+crfc+" modificado");
                            }else{
                                view.displayMessage("Cliente "+crfc+"no modificado");
                            }
                            break;
                        case 3:
                            crfc= view.getInput("Ingrese el RFC del cliente que desea eliminar");
                            if(ClienteC.eliminarCliente(tienda,crfc)){
                                view.displayMessage("Cliente "+crfc+" eliminado");
                            }else {
                                view.displayMessage("Cliente "+crfc+"no eliminado");
                            }
                            break;
                        case 4:
                            ClienteC.quickSort(tienda, 0, tienda.nClientes - 1);
                            for (int i = 0; i < tienda.nClientes; i++) {
                                view.displayMessage(tienda.getClientes()[i].toString());
                            }
                            break;
                        case 5:
                            view.displayMessage("Busqueda por:\n\t1)Nombre\n\t2)RFC\n\t3)Email\n\t4)Direccion");
                            int optdisplay;
                            optdisplay = Integer.parseInt(view.getInput(">"));
                            switch (optdisplay) {
                                case 1:
                                    String nom;
                                    nom = view.getInput("Escribe el nombre del cliente: ");
                                    view.displayMessage(controllerC.buscarClienteNombre(tienda, nom).toString());
                                    break;
                                case 2:
                                    String rfc;
                                    rfc = view.getInput("Escribe el RFC del cliente: ");
                                    view.displayMessage(controllerC.buscarClienteRFC(tienda, rfc).toString());
                                    break;
                                case 3:
                                    String emeil;
                                    emeil = view.getInput("Escribe el E-Mail del cliente: ");
                                    view.displayMessage(controllerC.buscarClienteEmail(tienda, emeil).toString());
                                    break;
                                case 4:
                                    String dir;
                                    dir = view.getInput("Escribe el E-Mail del cliente: ");
                                    view.displayMessage(controllerC.buscarClienteDireccion(tienda, dir).toString());
                                    break;
                            }
                            break;
                        case 6:
                            break;
                    }
                    break;

//GENERAR PEDIDO:
                case 4:
                    String code= view.getInput("Escribe el codigo del producto");
                    String date=view.getInput("Escribe la fecha: "); //Si nos ponemos mamones podemos hacerlo automatico
                    int cantidad=Integer.parseInt(view.getInput("Escriba cuanto producto quiere"));
                    if(controllerPed.generarPedido(code,date,cantidad)){
                        view.displayMessage("Pedido Exitoso");
                    }else {
                        view.displayMessage("Pedido Fallido");
                    }
                    break;
      //GENERAR FACTURA
                case 5:
                    String nomCliente=view.getInput("Escribe el nombre del cliente: ");
                    String codProd=view.getInput("Escribe el codigo del producto: ");

                    if (controllerF.generarFactura(nomCliente,codProd)){
                        view.displayMessage("Factura Exitoso");
                    }else{
                        view.displayMessage("Factura Fallida");
                    };
                    break;
            }
        }
    }
}