package Model;
public class ProductoElectronico extends Producto {

    private String numSerie;

    public ProductoElectronico(String codigo, float precioVenta, float descuento, String descripcion, int numExistencias, String numSerie,Proveedor proveedor){
        super( codigo,  precioVenta,  descuento,  descripcion, numExistencias,proveedor);
        this.numSerie=numSerie;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    @Override
    public String toString() {
        return "ProductoElectronico{" +
                "numSerie='" + numSerie + '\'' +
                ", codigo='" + codigo + '\'' +
                ", precioVenta=" + precioVenta +
                ", descuento=" + descuento +
                ", descripcion='" + descripcion + '\'' +
                ", numExistencias=" + numExistencias +
                ", proveedor="+proveedor+
                '}';
    }
}

