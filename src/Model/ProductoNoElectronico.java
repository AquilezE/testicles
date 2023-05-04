package Model;

import java.io.Serializable;

public class ProductoNoElectronico extends Producto  {

    public ProductoNoElectronico(String codigo, float precioVenta, float descuento, String descripcion, int numExistencias) {
        super(codigo, precioVenta, descuento, descripcion, numExistencias);
    }


}

