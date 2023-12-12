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
            v.mostrarInicioAplicacion("Cargado estado anterior con exito");
            List<Conversacion> conversaciones = m.obtenerConversaciones();
            System.out.println("NUMERO DE CONVERSACIONES CARGADAS: " + conversaciones.size());
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
    
    
    public Mensaje crearMensaje(String remitente, String contenido, String fechaHora){
        return m.crearMensaje(remitente, contenido, fechaHora);
    }
    
    public Conversacion crearConversacion(List<Mensaje> mensajes, String identificador, long fechaInicio, long fechaFin){
        return m.crearConversacion(mensajes, identificador, fechaInicio, fechaFin);
    }
    
    public String getContenidoMensaje(Mensaje mensaje){
        return m.getConenidoMensaje(mensaje);
    }
    
    public void agregarConversacionAConversaciones(Conversacion conversacion){
        m.agregarConversacionAConversaciones(conversacion);
    }
    
    public List<Conversacion> obtenerConversaciones(){
        return m.obtenerConversaciones();
    }
    
    public String formatearFecha(long fecha){
        return m.formatearFecha(fecha);
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
    
    public boolean eliminarConversacion(Conversacion conversacion){
        return m.eliminarConversacion(conversacion);
    }
    
    public boolean importarConversacion() {
        return m.importarConversacion();
    }
    
    public void exportarConversacion() {
        m.exportarConversacion();
    }

    public String obtenerIdentificador(){
        return m.obtenerIdentificador();
    }
    
    public String respuestaBot(String mensaje){
        return m.respuestaBot(mensaje);
    }
    
}
