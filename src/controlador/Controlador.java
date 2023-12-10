package controlador;

import vista.AplicacionVista;
import vista.VistaConsolaSimple;

import modelo.Mensaje;
import modelo.Modelo;
import modelo.Conversacion;

import java.util.List;

import java.time.Instant;

/**
 *
 * @author victo
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
        v.mostrarInicioAplicacion("HOLAH MU BUENA, ETO EH 'jLLM'");
        v.mostrarMenuPrincipal();
        v.mostrarFinPrograma("\nHASTA LA PROXIMAAAAA");
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
    
    
    

}
