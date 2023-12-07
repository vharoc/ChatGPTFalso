package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author victo
 */
public class Conversacion {
    
    private List<String> mensajes;
    
    public Conversacion(){
        this.mensajes = new ArrayList<>();
    }
    
    public void agregarMensaje(String mensaje) {
        mensajes.add(mensaje);
    }
    
    public List<String> obtenerMensajes() {
        return new ArrayList<>(mensajes);
    }
            
}
