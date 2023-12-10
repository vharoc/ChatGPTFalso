package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author victo
 */
public class IReposMemoria implements IRepositorio {
    
    ArrayList<Conversacion> conversaciones;

    public IReposMemoria(int numeroAlumnosACrear) {

        conversaciones = new ArrayList<>();
        // Fake repository
        for (int i = 0; i < numeroAlumnosACrear; i++) {
            // conversaciones.add(new Conversacion(i, i, i, i));
        }
    }

    @Override
    public ArrayList<Conversacion> importarConversacion() {
        return conversaciones;
    }

    @Override
    public void exportarConversacion(List<Conversacion> conversacion) {
        
        // Si queremos matener esto en memoria como otro objeto
        // deberemos hacer una copia
        this.conversaciones.clear();
        for (Conversacion c : conversaciones) {
            // Creando copias.
            // this.conversaciones.add(new Conversacion(c));
        }
        
    }
}
