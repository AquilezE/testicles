import Controlador.*;
import Model.*;
import Vista.*;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        //Deserializa lat tienda si tienda.ser existe
        Tienda tienda;
        File file = new File("tienda.ser");

        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            tienda = (Tienda) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            // Si el archivo no existe, entonces crea una tienda
            tienda = new Tienda("Abarrotes Mari", "ABC123");

            // Solo serializa la nueva tienda si el archivo no existe
            if (!file.exists()) {
                try {
                    FileOutputStream fileOut = new FileOutputStream(file);
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(tienda);
                    out.close();
                    fileOut.close();
                    System.out.println("Datos serializados guardados en tienda.ser");
                } catch (IOException ex) {
                    //e.printStackTrace();
                }
            }
        }

        //todos los controladores
        FacturaC controllerF=new FacturaC(tienda);
        TiendaC controller = new TiendaC(tienda);
        ProvC controllerP = new ProvC(tienda);
        ClienteC controllerC= new ClienteC(tienda);
        ControladorPedido controladorPedido= new ControladorPedido(tienda);
        View view = new View();

        int opcionsota = 200;
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
            System.out.println("\t1.- Manipular Productos");
            System.out.println("\t2.- Manipular Proveedores");
            System.out.println("\t3.- Manipular Clientes");
            System.out.println("\t4.- Generar Pedido");
            System.out.println("\t5.- Generar Factura");
            System.out.println("\t6.- Mostrar Facturas y Pedidos");
            System.out.println("\t7.- Mostrar Productos ordenados");
            System.out.println("\t0.- Salir");

            opcionsota = view.leerEntero(">");

            switch (opcionsota) {
                case (1):
        //PRODUCTO_________________PRODUCTO_____________________PRODUCTO
                    view.displayMessage("\t1.- Agregar producto");
                    view.displayMessage("\t2.- Modificar producto");
                    view.displayMessage("\t3.- Eliminar producto");
                    view.displayMessage("\t4.- Consultar todos los productos");
                    view.displayMessage("\t5.- Consultar algún producto en específico");
                    view.displayMessage("\t6.- Regresar");
                    int opcion = view.leerEntero(">");

                    switch (opcion) {
                        case 1:
                            View.cleanConsole();
                            ProdV.addProd(controller,view);

                            break;
                        case 2:
                            View.cleanConsole();

                            ProdV.modProd(view,tienda);

                            break;
                        case 3:
                            View.cleanConsole();

                            ProdV.delProd(view,tienda);

                            break;
                        case 4:
                            View.cleanConsole();

                            ProdV.verProductos(view,tienda);

                            break;
                        case 5:
                            View.cleanConsole();

                            ProdV.buscarProducto(view,tienda);

                            break;

                        case 6:
                            View.cleanConsole();
                            break;
                        default:
                            View.cleanConsole();
                            view.displayMessage("Opcion Invalida");
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

                    opcion = view.leerEntero(">");
                    switch (opcion) {
                        case 1:
                            View.cleanConsole();

                            ProveedorV.addProveedor(view,controllerP);
                            break;
                        case 2:
                            View.cleanConsole();

                            ProveedorV.modProveedor(view,tienda);
                            break;

                        case 3:
                            View.cleanConsole();

                            ProveedorV.eliminarProv(view,tienda);
                            break;

                        case 4:
                            View.cleanConsole();

                            ProveedorV.mostrarProveedores(view,tienda);
                            break;
                        case 5:
                            View.cleanConsole();

                            ProveedorV.buscarProovedor(view,tienda);
                            break;
                        case 6:
                            View.cleanConsole();
                            break;
                        default:
                            View.cleanConsole();
                            view.displayMessage("Opcion Invalida");
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
                    view.displayMessage("\t6.- Lista de todos los clientes con productos comprados");
                    view.displayMessage("\t7.- Regresar");

                    opcion = view.leerEntero(">");

                    switch (opcion){
                        case 1:
                            View.cleanConsole();

                            ClienteV.addCliente(view,controllerC);

                            break;
                        case 2:
                            View.cleanConsole();

                            ClienteV.modCliente(view,tienda);

                            break;
                        case 3:
                            View.cleanConsole();

                            ClienteV.eliminarCliente(view,tienda);

                            break;
                        case 4:
                            View.cleanConsole();

                            ClienteV.mostrarClientes(view,tienda);

                            break;
                        case 5:
                            View.cleanConsole();

                            ClienteV.buscarCliente(view,tienda);

                            break;
                        case 6:
                            View.cleanConsole();

                            ClienteV.clientesyProductos(view,tienda);
                            break;
                        case 7:
                            View.cleanConsole();
                            break;
                        default:
                            view.displayMessage("Opcion invalida");
                            break;
                    }
                    break;

//GENERAR PEDIDO:
                case 4:
                    View.cleanConsole();

                    PedidoV.generarPedido(controladorPedido,view);

                    break;
      //GENERAR FACTURA
                case 5:
                    View.cleanConsole();

                    FacturaV.generarPedido(controllerF,view);

                    break;
                case 6:
                    view.displayMessage("1)Mostrar todas las facturas de la tienda");
                    view.displayMessage("2)Mostrar todos los pedidos de la tienda");
                    view.displayMessage("3)Mostrar todas las facturas de un cliente");
                    view.displayMessage("4)Mostrar todos los pedidos a un proovedor");
                    view.displayMessage("5) Regresar");
                    int option=view.leerEntero(">:");

                    switch (option) {
                        case 1:  View.cleanConsole(); FacturaC.mostrarFacturasTienda(view, tienda); break;
                        case 2: View.cleanConsole(); ControladorPedido.mostrarPedidosTienda(view, tienda); break;
                        case 3: View.cleanConsole(); FacturaC.mostrarFacturasCliente(view, tienda); break;
                        case 4: View.cleanConsole(); ControladorPedido.mostrarPedidosProveedor(view, tienda); break;
                        case 5: View.cleanConsole(); break;
                        default: View.cleanConsole(); view.displayMessage("Opcion Invalida");
                    }
                    break;
                case 7:
                    view.displayMessage("Mostrar productos ordenados por:");
                    view.displayMessage("1)Precio");
                    view.displayMessage("2)Descuento");
                    view.displayMessage("3)Existencias");
                    opcion = view.leerEntero(">");

                    switch (opcion){
                        case 1:
                            View.cleanConsole();
                            TiendaC.bubbleSortPrecio(tienda);
                            ProdV.verProductos(view,tienda);
                            break;
                        case 2:
                            View.cleanConsole();
                            TiendaC.bubbleSortDescuento(tienda);
                            ProdV.verProductos(view, tienda);
                            break;
                        case 3:
                            View.cleanConsole();
                            TiendaC.bubbleSortExistencias(tienda);
                            ProdV.verProductos(view,tienda);
                            break;
                        default:
                            View.cleanConsole();
                            view.displayMessage("Opcion Invalida");
                    }
                    break;
                default:
                    View.cleanConsole();
                    view.displayMessage("Opcion invalida");
                    break;
                    }
                }
            }
        }
