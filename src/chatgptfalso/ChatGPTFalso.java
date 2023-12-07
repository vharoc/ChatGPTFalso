package chatgptfalso;

import controlador.Controlador;
import modelo.Modelo;
import vista.AplicacionVista;
import vista.VistaConsolaSimple;

/**
 *
 * @author victo
 */
public class ChatGPTFalso {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        AplicacionVista view;
        Modelo m;
        
        view = new VistaConsolaSimple();
        m = new Modelo();
        
        Controlador c = new Controlador(m, view);
        
        c.IniciarChat();
        
    }
    
}
