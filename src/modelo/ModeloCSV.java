package modelo;

/**
 *
 * @author victo
 * 
 */

public class ModeloCSV {
    
    private String mensaje;
    private String tipo;
    private int tamaño;
    
    public ModeloCSV(String mensaje, String tipo, int tamaño){
        this.mensaje = mensaje;
        this.tipo = tipo;
        this.tamaño = tamaño;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }
    
    
    
    
}
