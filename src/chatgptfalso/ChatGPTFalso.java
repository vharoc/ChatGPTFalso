package chatgptfalso;

import controlador.Controlador;
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
        
        view = new VistaConsolaSimple();
        
        Controlador c = new Controlador(view);
        
        c.IniciarChat();
        
    }
    
}
