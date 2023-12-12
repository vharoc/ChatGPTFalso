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
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author victo
 * 
 */

public class Modelo {
        
    private List<Conversacion> conversaciones = new ArrayList<>();
    
    private final ILLM illm;
    private final IRepositorio repositorio;
    
    File ficheroEstadoSerializado;
    
    public Modelo(IRepositorio repositorio, ILLM illm){
        this.repositorio = repositorio;
        this.illm = illm;
        ficheroEstadoSerializado = Paths.get(System.getProperty("user.home"), "OneDrive","Escritorio", "jLLM", "jLLM.bin").toFile();
    }
    

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
    
    public boolean eliminarConversacion(Conversacion conversacion){
        return conversaciones.remove(conversacion);
    }
    
    public boolean importarConversacion() {
        List<Conversacion> conver = repositorio.importarConversacion();
        
        if (conver != null) {
            for (Conversacion conversacion : conver) {
                
                if (!conversaciones.contains(conversacion)) {
                    conversaciones.add(conversacion);
                }
            }
            
            Map<Long, Conversacion> conversacionesMap = new HashMap<>();
            for (Conversacion conversacion : conversaciones) {
                conversacionesMap.put(conversacion.getFechaInicio(), conversacion);
            }
            Map<Long, Conversacion> conversacionesUnicasMap = new HashMap<>(conversacionesMap);
            
            conversaciones.clear();
            conversaciones.addAll(conversacionesUnicasMap.values());
            
            return true;
        } else {
            return false;
        }
    }
    
    public void exportarConversacion() {
        repositorio.exportarConversacion(conversaciones);
    }
    
    public String obtenerIdentificador(){
        return illm.obtenerIdentificador();
    }
    
    
    public String respuestaBot(String mensaje){
        return illm.speak(mensaje);
    }
    
    
    public boolean guardarEstadoAplicación() {

        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(ficheroEstadoSerializado));
            oos.writeObject(conversaciones);
            return true;
        } catch (IOException ex) {
            // Dejamos el error para la depuración, por el canal err.
            System.err.println("Error durante la serialización: " + ex.getMessage());
            return false;
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException ex) {
                    // Dejamos el error para la depuración, por el canal err.
                    System.err.println("Error al cerrar el flujo: " + ex.getMessage());
                    return false;
                }
            }
        }

    }
    
    public boolean cargarEstadoAplicación() {

        if (ficheroEstadoSerializado.exists() && ficheroEstadoSerializado.isFile()) {
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(new FileInputStream(ficheroEstadoSerializado));
                this.conversaciones = (List<Conversacion>) ois.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                // Dejamos el error para la depuración, por el canal err.
                System.err.println("Error durante la deserialización: " + ex.getMessage());
                return false;
            } finally {
                if (ois != null) {
                    try {
                        ois.close();
                    } catch (IOException ex) {
                        // Dejamos el error para la depuración, por el canal err.
                        System.err.println("Error durante la deserialización: " + ex.getMessage());
                        return false;
                    }
                }
            }
            return true;
        } else {
            return false;
        }

    }
    
    
}
