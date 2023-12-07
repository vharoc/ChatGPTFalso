package controlador;

import vista.AplicacionVista;
import vista.VistaConsolaSimple;
import modelo.Modelo;
import modelo.Conversacion;

import java.util.List;

/**
 *
 * @author victo
 */
public class Controlador {
    
    AplicacionVista v;
    VistaConsolaSimple consolaSimple;
    Modelo m;
    
    public Controlador(Modelo modelo, AplicacionVista view) {
        v = view;
        m = modelo;
        view.setController(this);
    }
    
    public void IniciarChat(){
        v.mostrarInicioAplicacion("HOLAH MU BUENA, ETO EH 'jLLM'");
        
        v.mostrarMenuPrincipal();
        
        v.mostrarFinPrograma("\nHASTA LA PROXIMAAAAA");
    }
    
    public void nuevaConversacion(){
        m.nuevaConversacion();
    }
    
    public List<Conversacion> mostrarEliminarConversaciones() {
        
        List<Conversacion> conversaciones = m.obtenerConversaciones();
        return conversaciones;
    }

}
