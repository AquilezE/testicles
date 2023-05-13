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
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════\n"); // Línea superior
        sb.append(" Producto Electrónico\n");
        sb.append("═══════════════════════════════════\n\n");
        sb.append(" Número de Serie: ").append(numSerie).append("\n");
        sb.append(" Código: ").append(codigo).append("\n");
        sb.append(" Precio de Venta: ").append(precioVenta).append("\n");
        sb.append(" Descuento: ").append(descuento).append("\n");
        sb.append(" Descripción: ").append(descripcion).append("\n");
        sb.append(" Número de Existencias: ").append(numExistencias).append("\n");
        sb.append(" Proveedor: ").append(proveedor).append("\n");
        sb.append("═══════════════════════════════════\n"); // Línea inferior
    
        return sb.toString();
    }
    
}

