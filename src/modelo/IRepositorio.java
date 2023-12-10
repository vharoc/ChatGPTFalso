package modelo;

import java.util.ArrayList;
        
/**
 *
 * @author victo
 */
public interface IRepositorio {
    
    public ArrayList<Conversacion> importarConversacion();
    
    public void exportarConversacion(ArrayList<Conversacion> conversacion);
    
}
