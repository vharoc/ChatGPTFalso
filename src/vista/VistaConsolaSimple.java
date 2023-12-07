package vista;

import static com.coti.tools.Esdia.*;
import controlador.Controlador;
/**
 *
 * @author victo
 */
public class VistaConsolaSimple extends AplicacionVista {
    
    @Override
    public void mostrarInicioAplicacion(String infoInicio){
        System.out.println(infoInicio);
    }
    
    @Override
    public void mostrarMenuPrincipal(){
        System.out.println("cargué");
    }
    
    @Override
    public void mostrarFinPrograma(String infoFin){
        System.out.println(infoFin);
    }
    
}
