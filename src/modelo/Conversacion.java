package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author victo
 */
public class Conversacion {
    
    private List<Mensaje> mensajes;
    
    public Conversacion(){
        this.mensajes = new ArrayList<>();
    }
    
    public void agregarMensaje(String remitente, String contenido) {
        Mensaje mensaje = new Mensaje(remitente, contenido);
        mensajes.add(mensaje);
    }
    
    public List<Mensaje> obtenerMensajes() {
        return new ArrayList<>(mensajes);
    }
            
}
