package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import static com.coti.tools.Esdia.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


/**
 *
 * @author victo
 * 
 */

public class Modelo {
        
    private List<Conversacion> conversaciones = new ArrayList<>();
    
    // private ILLM illm;
    // private IRepositorio repositorio;
    
    /*
    public Modelo()
        this.illm = illm;
        this.repo = repo;
    */
    
    public Mensaje crearMensaje(String remitente, String contenido, String fechaHora){
        Mensaje nuevoMensaje = new Mensaje(remitente, contenido, fechaHora);
        return nuevoMensaje;
    }
    
    public Conversacion crearConversacion(List<Mensaje> mensajes, String identificador, long fechaInicio, long fechaFin){
        return new Conversacion(mensajes, identificador, fechaInicio, fechaFin);
    }
    
    public String getConenidoMensaje(Mensaje mensaje){
        String contenido = mensaje.getContenido();
        return contenido;
    }
    
    
    public void agregarConversacionAConversaciones(Conversacion conversacion){
        conversaciones.add(conversacion);
    }
    
    public List<Conversacion> obtenerConversaciones() {
        return new ArrayList<>(conversaciones);
    }
    
    
    public String formatearFecha(long fecha){
        // Convertir a LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(fecha), ZoneId.systemDefault());
        // Formatear la fecha en el formato deseado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss");
        String fechaFormateada = localDateTime.format(formatter);
        
        return fechaFormateada;
    }
    
    
    public long obtenerEpoch(Conversacion conversacion){
        return conversacion.getFechaInicio();
    }
    
    public List<Mensaje> obtenerMensajes(Conversacion conversacion){
        return conversacion.getMensajes();
    }
    
    public long obtenerDia(Conversacion conversacion){
        return conversacion.getFechaInicio();
    }
    
    public String getEmisor(Mensaje mensaje){
        return mensaje.getRemitente();
    }
    
    public String getHora(Mensaje mensaje){
        return mensaje.getFechaHora();
    }
    
    
    
}
