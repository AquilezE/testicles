package Vista;

import Controlador.FacturaC;
import Model.Cliente;
import Model.Factura;
import Model.Tienda;

public class FacturaV {
    public static void generarPedido(FacturaC controllerF, View view){
        String nomCliente=view.getInput("Escribe el nombre del cliente: ");
        String codProd=view.getInput("Escribe el codigo del producto: ");

        if (controllerF.generarFactura(nomCliente,codProd)){
            view.displayMessage("Factura generada exitosamente");
        }else{
            view.displayMessage("Factura no generada");
        }
    }
}
