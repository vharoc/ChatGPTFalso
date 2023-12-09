package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author victo
 */
public class Conversacion {
    
    private List<Mensaje> mensajes;
    private String identificador;
    private long fechaInicio;
    private long fechaFin;
    
    public Conversacion(){
        this.mensajes = new ArrayList<>();
    }
    
    public Conversacion(List<Mensaje> mensajes, String identificador, long fechaInicio, long fechaFin){
        this.mensajes = mensajes;
        this.identificador = identificador;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;        
    }
    
    public long getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(long fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public long getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(long fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }
    
    
            
}
