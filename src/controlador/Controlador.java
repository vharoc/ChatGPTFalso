package controlador;

import vista.AplicacionVista;

import modelo.Mensaje;
import modelo.Modelo;
import modelo.Conversacion;

import java.util.List;

/**
 *
 * @author victo
 * 
 */

public class Controlador {
    
    AplicacionVista v;
    Modelo m;
    
    public Controlador(Modelo modelo, AplicacionVista view) {
        v = view;
        m = modelo;
        view.setController(this);
    }
    
    public void IniciarChat(){
        
        if(m.cargarEstadoAplicación()){
            List<Conversacion> conversaciones = m.obtenerConversaciones();
            v.mostrarInicioAplicacion("Cargado estado anterior con exito\n"
                    + "NUMERO DE CONVERSACIONES CARGADAS: " + conversaciones.size());
        }else{
            v.mostrarInicioAplicacion("No se encontró fichero para carga del programa. Parece que es la primera ejecución");
        }
            
        v.mostrarMenuPrincipal();
        
        if(m.guardarEstadoAplicación()){
            v.mostrarFinPrograma("Guardado el estado de la aplicación.\nSaliendo...");
        }else{
            v.mostrarFinPrograma("No se pudo guardar el estado de la aplicación.\nSaliendo...");
        }
        
    }
    
    // METODOS DE CONTROLADOR - TIPO CREACION  
    public Mensaje crearMensaje(String remitente, String contenido, String fechaHora){
        return m.crearMensaje(remitente, contenido, fechaHora);
    }
    
    public Conversacion crearConversacion(List<Mensaje> mensajes, String identificador, long fechaInicio, long fechaFin){
        return m.crearConversacion(mensajes, identificador, fechaInicio, fechaFin);
    }
    
    
    // METODOS DE CONTROLADOR - TIPO AGREGACION 
    public void agregarConversacionAConversaciones(Conversacion conversacion){
        m.agregarConversacionAConversaciones(conversacion);
    }
    
    
    // METODOS DE CONTROLADOR - TIPO OBTENCION
    public String getContenidoMensaje(Mensaje mensaje){
        return m.getConenidoMensaje(mensaje);
    }   
    
    public List<Conversacion> obtenerConversaciones(){
        return m.obtenerConversaciones();
    }
    
    public long obtenerEpoch(Conversacion conversacion){
        return m.obtenerEpoch(conversacion);
    }
    
    public List<Mensaje> obtenerMensajes(Conversacion conversacion){
        return m.obtenerMensajes(conversacion);
    }
    
    public long obtenerDia(Conversacion conversacion){
        return m.obtenerEpoch(conversacion);
    }
    
    public String getEmisor(Mensaje mensaje){
        return m.getEmisor(mensaje);
    }
    
    public String getHora(Mensaje mensaje){
        return m.getHora(mensaje);
    }
    
    public String obtenerIdentificador(){
        return m.obtenerIdentificador();
    }
      
    public String respuestaBot(String mensaje){
        return m.respuestaBot(mensaje);
    }
    
    
    // METODOS DE CONTROLADOR - TIPO UTIL VISUAL
    public String formatearFecha(long fecha){
        return m.formatearFecha(fecha);
    }
       
    public boolean eliminarConversacion(Conversacion conversacion){
        return m.eliminarConversacion(conversacion);
    }
    
    
    // METODOS DE CONTROLADOR - TIPO EXP./IMP.
    public boolean importarConversacion() {
        return m.importarConversacion();
    }
    
    public void exportarConversacion() {
        m.exportarConversacion();
    }

}
