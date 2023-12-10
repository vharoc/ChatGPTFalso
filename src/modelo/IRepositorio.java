package modelo;

import java.util.List;

/**
 *
 * @author victo
 *
 */

public interface IRepositorio {
    
    public List<Conversacion> importarConversacion();
    
    public void exportarConversacion(List<Conversacion> conversacion);
    
}