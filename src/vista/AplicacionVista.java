package vista;

import controlador.Controlador;

/**
 *
 * @author victo
 * 
 */

public abstract class AplicacionVista {
    
    protected Controlador c;

    public abstract void mostrarInicioAplicacion(String infoInicio);
    
    public abstract void mostrarMenuPrincipal();
    
    public abstract void mostrarFinPrograma(String infoFin);

    public void setController(Controlador c) {
        this.c = c;
    }
    
}
