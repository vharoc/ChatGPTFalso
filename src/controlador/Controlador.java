package controlador;

import vista.AplicacionVista;
/**
 *
 * @author victo
 */
public class Controlador {
    
    AplicacionVista v;
    
    public Controlador(AplicacionVista view) {
        v = view;
        view.setController(this);
    }
    
    public void IniciarChat(){
        v.mostrarInicioAplicacion("HOLAH MU BUENA, ETO EH 'jLLM'");
    }
}
