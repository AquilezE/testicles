package Vista;

import Controlador.ControladorPedido;
import Model.*;

public class PedidoV {


    public static void generarPedido(ControladorPedido controladorPedido, View view){
        String code= view.getInput("Escribe el codigo del producto");
        String date=view.getInput("Escribe la fecha: "); //Si nos ponemos mamones podemos hacerlo automatico
        int cantidad=Integer.parseInt(view.getInput("Escriba cuanto producto quiere"));
        if(controladorPedido.generarPedido(code,date,cantidad)){
            view.displayMessage("Pedido Exitoso");
        }else {
            view.displayMessage("Pedido Fallido");
        }
    }
}
